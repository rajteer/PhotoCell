package project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class PlotPanel extends JPanel
{
	JLabel plotLabel;
	//selection of plots
	JCheckBox frequencyPlot, voltagePlot;
	
	PlotPanel()
	{
		Font f = new Font("Monospaced", Font.BOLD, 12);
		Dimension size = new Dimension(100,100);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(new BorderLayout());
		 
		plotLabel=new JLabel("Wykresy");
		plotLabel.setFont(f);
		plotLabel.setLocation(50, 50);
		add(plotLabel, BorderLayout.PAGE_START);
		
		//Frequency Plot
		frequencyPlot=new JCheckBox("Zależność napięcia hamowania od częstotliwości");	
		frequencyPlot.setFont(f);
		frequencyPlot.setLocation(50, 100);
		add(frequencyPlot, BorderLayout.LINE_START);
		
		XYSeries series = new XYSeries("");
		series.add(1, 0);
		series.add(2, 1);
		series.add(3, 2);
		series.add(4, 3);
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		JFreeChart chart = ChartFactory.createXYLineChart("", "f[THz]", "Uh[V]", dataset, PlotOrientation.VERTICAL, true, true, false);
		ChartFrame frame1=new ChartFrame("",chart);
		frame1.setSize(500,400);
		
		frequencyPlot.addItemListener(new ItemListener() 
		{
			@Override
			public void itemStateChanged(ItemEvent e) 
			{
				if(frequencyPlot.isSelected())
				{
					frame1.setVisible(true);
				}
				else
				{
					frame1.setVisible(false);
				}
	        }
	    });
		
		//BreakVoltage Plot
		voltagePlot=new JCheckBox("Zależność prądu od przyłożonego napięcia      ");	
		voltagePlot.setFont(f);
		voltagePlot.setLocation(50,150);
		add(voltagePlot, BorderLayout.PAGE_END);
		
		XYSeries series1 = new XYSeries("");
		series1.add(-1, 0);
		series1.add(0.5, 2.5);
		series1.add(1, 3);
		series1.add(2, 3);
		series1.add(4, 3);
		XYSeriesCollection dataset1 = new XYSeriesCollection();
		dataset1.addSeries(series1);
		JFreeChart chart1 = ChartFactory.createXYLineChart("", "U", "I", dataset1, PlotOrientation.VERTICAL, true, true, false);
		ChartFrame frame2=new ChartFrame("",chart1);
		frame2.setSize(500,400);
		
		voltagePlot.addItemListener(new ItemListener() 
		{
			@Override
			public void itemStateChanged(ItemEvent e) 
			{
				if(voltagePlot.isSelected())
				{
					frame2.setVisible(true);
				}
				else
				{
					frame2.setVisible(false);
				}
	        
	        }
	    });
	}

}
