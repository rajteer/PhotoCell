package project;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

//Window with theoretical information
public class TheoryWindow extends JFrame 
{
	JPanel panelTheoryWindow;
	JTextPane textPaneTheoryWindow;
	JScrollPane scrollPaneTheoryWindow;
	 
	public TheoryWindow() throws IOException 
	{
		this.setSize(800,600);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Wstęp teretyczny");
		
		panelTheoryWindow = new JPanel();
	    textPaneTheoryWindow = new JTextPane();
	    textPaneTheoryWindow.setEditable(false);
	    
	    panelTheoryWindow.setLayout(new BorderLayout());
	    
	    try
	    {
	           File file = new File(getClass().getResource("theoryInfo.txt").getFile());
	           FileReader fr = new FileReader(file);
	           while(fr.read() != -1){
	             textPaneTheoryWindow.read(fr,null);
	           }
	           fr.close();
	    } 
	    
	    catch(Exception ex)
	    {
	         ex.printStackTrace();
	    }
	    
	    Font font = new Font("",Font.ITALIC,18);
	    textPaneTheoryWindow.setFont(font);
	    
	    scrollPaneTheoryWindow = new JScrollPane(textPaneTheoryWindow);
	    panelTheoryWindow.add(scrollPaneTheoryWindow,BorderLayout.CENTER);
	    setContentPane(panelTheoryWindow);
	}	
	
	
	public static void main(String[] args) throws IOException
	{
		TheoryWindow window = new TheoryWindow();
		window.setVisible(true);
		window.setResizable(false);
	}
}
