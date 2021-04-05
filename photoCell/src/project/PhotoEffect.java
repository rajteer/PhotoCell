package project;



import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PhotoEffect extends JPanel
{

	private Image img;
	JTextField voltmeter;
	JButton startButton;
	JButton endButton;
	JSlider voltageSlider;
	
	  public PhotoEffect(String img)
	  {
	    this(new ImageIcon(img).getImage());
	  }
	  
	public PhotoEffect(Image img)
	{
	    this.img = img;
	    Dimension size = new Dimension(500,300);
	    Font f = new Font("Monospaced", Font.BOLD, 12);
	    Font f1 = new Font("Monospaced", Font.BOLD, 24);
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	    
	    
		//przyciski rozpoczêcia i zakoñczenia animacji
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
		
		//slider napiêcia na bateri wyœwietlaj¹cy siê na woltomierzu
	  	voltageSlider= new JSlider(JSlider.HORIZONTAL,-8, 8, 0);
	  	voltageSlider.setMinorTickSpacing(2);
	  	voltageSlider.setMajorTickSpacing(4);
	  	//voltageSlider.setPaintTicks(true);
	  	//voltageSlider.setPaintLabels(true);
	  	voltageSlider.setSize(new Dimension(150, 30));//70
	  	voltageSlider.setLocation(150, 420);
	 
	  	add(voltageSlider);
	  	
	  //woltomierz
	    voltmeter=new JTextField(String.format("%d", voltageSlider.getValue()));
	    voltmeter.setSize(new Dimension(20, 40));
	    voltmeter.setFont(f1);
	   // voltmeter.setEditable(false);
		add(voltmeter);
		voltmeter.setSize(70,30);
		voltmeter.setLocation(210, 260);

		//zdarzenia
	 	voltageSlider.addChangeListener(new ChangeListener()
	    {           
	        @Override
	        public void stateChanged(ChangeEvent evt)
	        {
	            voltmeter.setText(voltageSlider.getValue()+"V");
	        }
	    });
	}
		//t³o panelu to uk³ad pomiarowy 
	  public void paintComponent(Graphics g) 
	  {
	    g.drawImage(img, 0, 0, null);
	  }
	
}
