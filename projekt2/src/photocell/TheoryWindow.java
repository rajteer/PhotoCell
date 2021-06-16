package photocell;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class TheoryWindow extends JFrame 
{

	JScrollPane jScrollPane;
	JEditorPane jEditorPane;
	URL url;
	public TheoryWindow(String urlString) 
	{
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(500,500);
		
		jEditorPane = new JEditorPane();
		jEditorPane.setEditable(false);   
	    url= TheoryWindow.class.getResource(urlString);
	    
	    try 
	    {   
	         jEditorPane.setPage(url);
	    } 
	    catch (IOException e) 
	    { 
	         jEditorPane.setContentType("text/html");
	         jEditorPane.setText("<html>Page not found.</html>");
	    }
	    jScrollPane = new JScrollPane(jEditorPane);
	    this.add(jScrollPane);
	}
}
