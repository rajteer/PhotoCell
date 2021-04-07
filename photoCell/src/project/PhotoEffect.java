package project;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PhotoEffect extends JPanel
{
	//picture with measurement system
	private Image img;
	
	//voltmeter
	JTextField voltmeterTextField;
	
	//buttons begin and end animation
	JButton startButton, endButton;
	
	//slider of voltage
	JSlider voltageSlider;
	
	public PhotoEffect() throws FontFormatException, IOException
	{
		
		InputStream input = getClass().getResourceAsStream("pht3.png");
		try {                
			 img = ImageIO.read(input);
 		} 
		catch (IOException e) 
		{
 			System.err.println("Blad odczytu obrazka");
 			e.printStackTrace();
		}
		
	    Dimension size = new Dimension(500,300);
	    Font f = new Font("Monospaced", Font.BOLD, 12);
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	    
	    //new font
	    InputStream input1 = getClass().getResourceAsStream("CursedTimerUlil-Aznm.ttf");
 		Font font = Font.createFont(Font.TRUETYPE_FONT, input1);
 	    font = font.deriveFont(Font.BOLD,24);
 	    GraphicsEnvironment ge =
 	    GraphicsEnvironment.getLocalGraphicsEnvironment();
 	    ge.registerFont(font);
	    
		startButton= new JButton("Rozpocznij");
		startButton.setSize(new Dimension(110, 25));
		startButton.setLocation(130, 460);
		startButton.setFont(f);
		add(startButton);
		
		endButton= new JButton("Zatrzymaj");
		endButton.setSize(new Dimension(100, 25));
		endButton.setLocation(250, 460);
		endButton.setFont(f);
		add(endButton);
		
	  	voltageSlider= new JSlider(SwingConstants.HORIZONTAL,-8, 8, 0);
	  	voltageSlider.setMinorTickSpacing(2);
	  	voltageSlider.setMajorTickSpacing(4);
	  	voltageSlider.setSize(new Dimension(180, 30));//70
	  	voltageSlider.setLocation(150, 420);
	  	voltageSlider.addChangeListener(new ChangeListener()
	    {           
	        @Override
	        public void stateChanged(ChangeEvent evt)
	        {
	            voltmeterTextField.setText(voltageSlider.getValue()+"V");
	        }
	    });
	 
	  	add(voltageSlider);
	  	
	    voltmeterTextField=new JTextField(String.format("%d", voltageSlider.getValue()));
	    voltmeterTextField.setSize(new Dimension(30, 40));
	    voltmeterTextField.setFont(font);
	    voltmeterTextField.setHorizontalAlignment(SwingConstants.CENTER); //centering text in JTextField
	    voltmeterTextField.setEditable(false);
		add(voltmeterTextField);
		voltmeterTextField.setSize(70,30);
		voltmeterTextField.setLocation(210, 260);

	}
		
	@Override
	public void paintComponent(Graphics g) 
	{
		g.drawImage(img, 0, 0, null);
	}
	
}
