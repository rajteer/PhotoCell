package photocell;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.Statement;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class PlotPanel extends JPanel
{
static JTabbedPane tabbedPane = new JTabbedPane();
	
	JButton saveButton, savrAsPDFButton;
	
	JPanel bottomPanel, topPanel, upperPanel, lowerPanel;
	static HashMap<String, Double> elementMap = new HashMap<String, Double>();
	
	static ChartPanel chartpanel;
	static ChartPanel chartpanel2;
	static int selectedIndex = 0;
	static JFreeChart stoppingVoltageChart;
	static JFreeChart currentVoltageChart;
	static JLabel elementName;
	static String FreVol, Plot1, Plot2;
	static ArrayList<String> dataList = new ArrayList<String>();
	
	private static final Object[][] rowData = {};
	private static final Object[] columnNames = {"lp.", "f[Hz]", "Uh[V]"};
	static DefaultTableModel listTableModel;
	
	JTable jTable;
	static DefaultTableModel lowerPanelListTableModel;
	private static final Object[] lowerPanelColumnNames = {" ", "W [eV]", "Max λ [nm]", "Min ν [THz]"};
	private static final Object[][] lowerPanelRowData = {};
	public static DefaultTableModel getListTableModel() 
	{
		return listTableModel;
	}

	PlotPanel()
	{
		this.setLayout(new BorderLayout());
		
		upperPanel = new JPanel();
		upperPanel.setLayout(new BorderLayout());
		this.add(upperPanel, BorderLayout.PAGE_START);
		
		lowerPanel = new JPanel();
		lowerPanel.setLayout(new BorderLayout());
		this.add(lowerPanel, BorderLayout.CENTER);
		
		elementMap.put("Na", 3.781  * Math.pow(10, -19)); 
		elementMap.put("Rb", 3.618 * Math.pow(10, -19));
		elementMap.put("Cs", 3.424 * Math.pow(10, -19));
		elementMap.put("Ca", 4.592 * Math.pow(10, -19)); 
		elementMap.put("K", 3.664 * Math.pow(10, -19));
		
		listTableModel = new DefaultTableModel(rowData, columnNames);
		
		XYDataset dataset = generateStoppingVoltageDataSet();
		stoppingVoltageChart = createStoppingVoltageChart(dataset);
        chartpanel = new ChartPanel(stoppingVoltageChart);
        
		XYDataset dataset2 = generateCurrentVoltageDataSet();
		currentVoltageChart = createCurrentVoltageChart(dataset2);
        chartpanel2 = new ChartPanel(currentVoltageChart);
        
        tabbedPane.addTab("Uh(f)", chartpanel);
        tabbedPane.addTab("I(U)", chartpanel2);
        upperPanel.add(tabbedPane, BorderLayout.CENTER);
        
        topPanel = new JPanel();
        upperPanel.add(topPanel, BorderLayout.PAGE_START);
        elementName = new JLabel();
        localizeLabel(Locale.getDefault());
        
        topPanel.add(elementName);
        
        bottomPanel = new JPanel();
        saveButton = new JButton();
        savrAsPDFButton = new JButton();
    
        bottomPanel.add(saveButton);
        bottomPanel.add(savrAsPDFButton);
        upperPanel.add(bottomPanel, BorderLayout.PAGE_END);
       
        tabbedPane.addChangeListener(new ChangeListener()
        {
        	@Override
        	public void stateChanged(ChangeEvent e) {
        		{
        			JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
        			selectedIndex = tabbedPane.getSelectedIndex();
        			localizeLabel(Locale.getDefault());
        		}	
        	}
        });
        
        saveButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
		        JFileChooser chooser = new JFileChooser();
		        chooser.setDialogTitle("Choose path");
		        int result = chooser.showDialog(null, "Save");
		        if (result == JFileChooser.APPROVE_OPTION) 
		        {
		            File outputFile = new File(chooser.getSelectedFile() + ".png");
		            Rectangle rec1 = chartpanel.getBounds();
		            Rectangle rec2 = chartpanel2.getBounds();
		            
		            BufferedImage img = new BufferedImage(rec1.width, rec1.height, BufferedImage.TYPE_INT_ARGB);
		            Graphics g = img.getGraphics();
		            chartpanel.paint(g);
		            
		            BufferedImage img2 = new BufferedImage(rec2.width, rec2.height, BufferedImage.TYPE_INT_ARGB);
		            Graphics g2 = img2.getGraphics();
		            chartpanel2.paint(g2);
		            
		            try 
		            {
		            	if(selectedIndex == 0)
		            	{
		            		ImageIO.write(img, "png", outputFile);
		            	}
		            	else if(selectedIndex == 1)
		            	{
		            		ImageIO.write(img2, "png", outputFile);
		            	}
		            } 
		            catch (IOException ex) 
		            {
		                ex.printStackTrace();
		            }
		        }
			}
		});
        
        savrAsPDFButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
		        SaveData.saveAsPDF();
			}
		});
        
        //lower Panel
       lowerPanelListTableModel = new DefaultTableModel(lowerPanelRowData, lowerPanelColumnNames);
       jTable = new JTable(lowerPanelListTableModel);
       updateLowerPanelTable();
       JTableHeader header = jTable.getTableHeader();
   
       lowerPanel.add(header, BorderLayout.NORTH);
       lowerPanel.add(jTable, BorderLayout.CENTER);
       
       lowerPanel.add(jTable);
       jTable.setRowHeight(50);
	}
	
	public static void updateChart()
	{
		tabbedPane.remove(chartpanel);
		XYDataset dataset = generateStoppingVoltageDataSet();
		currentVoltageChart = createStoppingVoltageChart(dataset);
		chartpanel = new ChartPanel(currentVoltageChart);

		tabbedPane.addTab("Uh(f)", chartpanel);
		tabbedPane.invalidate();
		tabbedPane.revalidate();
		tabbedPane.repaint();
	}
	
	public static XYDataset generateStoppingVoltageDataSet()
	{    
		dataList.clear();
		dataList.add( java.time.LocalDate.now() + "," + java.time.LocalTime.now());
		dataList.add("częstotliwość,napięcie hamowania");
		
		double w = elementMap.get(Interface.getChoosedElement());
		XYSeries series1 = new XYSeries("negative");
		XYSeries series2 = new XYSeries("positive");
		
		double stoppingVoltage;
		int i = 1;
		listTableModel.setRowCount(0);
		
		for (double v = 0; v < 10; v = v + 0.01) 
		{
			stoppingVoltage = -1*(w)/ElectroMagneticWave.getElectroniccharge() + (ElectroMagneticWave.getPlanckconstant()/ElectroMagneticWave.getElectroniccharge()) * v*Math.pow(10, 14);
			
			dataList.add(round(v,3)+ "," +  round(stoppingVoltage,3));
			String rowString = i + ".";
			listTableModel.addRow(new Object[]{rowString, round(v,3), round(stoppingVoltage,3)});
			i++;
			
			if(stoppingVoltage < 0)
			{
				series1.add(v*Math.pow(10, 14), stoppingVoltage);
			}
			
			if(stoppingVoltage >= 0)
			{
				series2.add(v*Math.pow(10, 14), stoppingVoltage);
			}
		}
		
		XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
	}
	
	public static JFreeChart createStoppingVoltageChart(XYDataset dataset)
	{
		JFreeChart stoppingVoltageChart = ChartFactory.createXYLineChart(
				"",
				"f [Hz]", 
				"Uh [V]", 
				generateStoppingVoltageDataSet(), 
				PlotOrientation.VERTICAL,
				false,
				false,
				false
				);
		
		XYPlot plot = (XYPlot) stoppingVoltageChart.getPlot();
		XYItemRenderer renderer = plot.getRenderer();
		renderer.setSeriesPaint(0, new Color(255, 140, 102));
		renderer.setSeriesPaint(1, new Color(204, 51, 0));
		return stoppingVoltageChart;
	}
	public static XYDataset generateCurrentVoltageDataSet()
	{
		double w = elementMap.get(Interface.getChoosedElement());
		XYSeries series1 = new XYSeries("negative");
		
		double stoppingVoltage = (-1*w)/ElectroMagneticWave.getElectroniccharge() + (ElectroMagneticWave.getPlanckconstant()/ElectroMagneticWave.getElectroniccharge()) * Main.wave.getFrequency()*Math.pow(10, 12);
		
		double v0 = w/ElectroMagneticWave.getPlanckconstant();
		
		if(Main.wave.getFrequency()*Math.pow(10, 12) > v0 && Main.wave.getIntensity() != 0)
		{
			series1.add(-stoppingVoltage, 0);
			series1.add(0, Main.wave.getIntensity());
			series1.add(10, Main.wave.getIntensity());
		}
		
		XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);

        return dataset;
	}
	
	public static JFreeChart createCurrentVoltageChart(XYDataset dataset)
	{
		JFreeChart CurrentVoltageChart = ChartFactory.createXYLineChart(
				"",
				"U [V]", 
				"I [nA]", 
				generateCurrentVoltageDataSet(), 
				PlotOrientation.VERTICAL,
				false,
				false,
				false
				);		
		return CurrentVoltageChart;
	}
	public static void updateCurrentVoltageChart()
	{
		tabbedPane.remove(chartpanel2);
		XYDataset dataset = generateCurrentVoltageDataSet();
		currentVoltageChart = createCurrentVoltageChart(dataset);
		chartpanel2 = new ChartPanel(currentVoltageChart);

		tabbedPane.addTab("I(U)", chartpanel2);
		tabbedPane.invalidate();
		tabbedPane.revalidate();
		tabbedPane.repaint();
	}
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public static void updateLowerPanelTable()
	{
		double lambda0 = (ElectroMagneticWave.getPlanckconstant() * ElectroMagneticWave.getSpeedoflight()) / elementMap.get(Interface.getChoosedElement());
		double v0 = elementMap.get(Interface.getChoosedElement()) / ElectroMagneticWave.getPlanckconstant();
		double w = round(elementMap.get(Interface.getChoosedElement()) * 6.241509 *Math.pow(10, 18),2);
		lowerPanelListTableModel.setRowCount(0);
		lowerPanelListTableModel.addRow(new Object[]{Interface.getChoosedElement(),w, (int)(lambda0* Math.pow(10, 9)), (int)(v0* Math.pow(10, -12))});
	}
	void localizeLabel(Locale loc) 
	{
		elementName.setFont(new Font("Serif", Font.PLAIN, 20));
		String name;
		name = Interface.getChoosedElement() ;
		elementName.setText(Plot1 + name);
		Locale.setDefault(loc);
		ResourceBundle info =ResourceBundle.getBundle("photocell.Info");  
		String[] txt = {info.getString("Plot1"),info.getString("Plot2")};
		if(selectedIndex == 0)
		{
			Statement stmt = new Statement(elementName, "setText",new Object[] { txt[0] +name} );
			try 
			{
				stmt.execute();

			} 
			catch(Exception exc) 
			{ 
				exc.printStackTrace(); 
			}
		}
		else if(selectedIndex == 1)
		{
			Statement stmt = new Statement(elementName, "setText",new Object[] { txt[1]} );
			try 
			{
				stmt.execute();

			} 
			catch(Exception exc) 
			{ 
				exc.printStackTrace(); 
			}
		}
		
		FreVol=info.getString("FreVol");
	}
	
}