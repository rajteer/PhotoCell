package photocell;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.orsonpdf.PDFDocument;
import com.orsonpdf.PDFGraphics2D;
import com.orsonpdf.Page;

public class SaveData extends JFrame
{
	private static PrintWriter printWriter;
	JPanel panel;
	JTextPane textPane;
	JScrollPane scrollPane;
	
	SaveData()
	{
		this.setSize(800,600);
		panel = new JPanel();
	    textPane = new JTextPane();
	    textPane.setEditable(false);
	    panel.setLayout(new BorderLayout());
	    
	    Font font = new Font("",Font.ITALIC,8);
	    textPane.setFont(font);
	    textPane.setText("HRJ");
	    scrollPane = new JScrollPane(textPane);
	    panel.add(textPane,BorderLayout.CENTER);
	    setContentPane(panel);
	}
	public static void saveAsPDF()
	{
        PDFDocument pdfDoc = new PDFDocument();
        pdfDoc.setTitle("PDF");
        
        Page page1 = pdfDoc.createPage(new Rectangle(612, 468));
        PDFGraphics2D g2page1 = page1.getGraphics2D();
        PlotPanel.stoppingVoltageChart.draw(g2page1, new Rectangle(0, 0, 612, 468));
        
        Page page3 = pdfDoc.createPage(new Rectangle(612, 468));
        PDFGraphics2D g2page3 = page3.getGraphics2D();
        PlotPanel.currentVoltageChart.draw(g2page3, new Rectangle(0, 0, 612, 468));
        
        File file = new File("PDFa.pdf");
     	pdfDoc.writeToFile(file);
        
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Choose path");
        int result = chooser.showDialog(null, "Save");
        if (result == JFileChooser.APPROVE_OPTION) 
        {
        	File outputFile = new File(chooser.getSelectedFile() + ".pdf"); 
            pdfDoc.writeToFile(outputFile);
        }
	}
	
	public static void saveAstxt()
	{
		JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Choose path");
        int result = chooser.showDialog(null, "Save");
        if (result == JFileChooser.APPROVE_OPTION) 
        {
        	File outputFile = new File(chooser.getSelectedFile() + ".csv"); 
        	try {
				printWriter = new PrintWriter(outputFile);
				for (int i = 0; i < PlotPanel.dataList.size(); i++) 
				{
					printWriter.println(PlotPanel.dataList.get(i));
					System.out.println(PlotPanel.dataList.get(i));
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
        }
	}
}
