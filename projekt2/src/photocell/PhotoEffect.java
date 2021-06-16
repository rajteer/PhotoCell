package photocell;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
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
	int Intensity=0;
	//slider of voltage
	static JSlider voltageSlider;
	//light source
	static ElectroMagneticWave w = new ElectroMagneticWave(500);
	
	//photoelectrons
	static ConcurrentLinkedQueue<Photoelectron> photons = new  ConcurrentLinkedQueue<Photoelectron>();
	volatile boolean stopped = false;
	volatile boolean suspended = false;
	

	public PhotoEffect() throws FontFormatException, IOException
	{
		setOpaque(true);
		InputStream input = getClass().getResourceAsStream("obrazek0.png");
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
		startButton.setOpaque(true);
		startButton.setLocation(130, 460);
		startButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				resumeThread();
			}
		});
		add(startButton);
		
		endButton= new JButton("Zatrzymaj");
		endButton.setSize(new Dimension(100, 25));
		endButton.setOpaque(true);
		endButton.setLocation(250, 460);
		endButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				suspendThread();
			}
		});
		add(endButton);
		
	  	voltageSlider= new JSlider(JSlider.HORIZONTAL,-4, 4, 0);
	  	voltageSlider.setMinorTickSpacing(2);
	  	voltageSlider.setMajorTickSpacing(4);
	  	voltageSlider.setSize(new Dimension(180, 30));//70
	  	voltageSlider.setOpaque(true);
	  	voltageSlider.setLocation(150, 420);
	  	voltageSlider.addChangeListener(new voltageSliderChangeListener());
	  	add(voltageSlider);
	  
	    voltmeterTextField=new JTextField(String.valueOf(voltageSlider.getValue())+"V");
	    voltmeterTextField.setFont(font);
	    voltmeterTextField.setHorizontalAlignment(JTextField.CENTER); 
	    voltmeterTextField.setEditable(false);
		add(voltmeterTextField);
		voltmeterTextField.setSize(70,40);
		voltmeterTextField.setLocation(210, 275);
		
		
		animator.start();
		
		
	}
	public class voltageSliderChangeListener implements ChangeListener 
	{
		@Override
		public void stateChanged(ChangeEvent e) 
		{
			voltmeterTextField.setText(voltageSlider.getValue()+"V");
		}
	}

	public Thread animator = new Thread()
	{
		public void run()
		{
			 while(!stopped) 
			 {
			      try 
			      {
			        synchronized(this) 
			        {
			          while (suspended) wait();
			        }
			      } catch (InterruptedException exc)
			      {
			          exc.printStackTrace();
			      }
				Random r = new Random();
				Photoelectron p = new Photoelectron();
				p.setxPos(r.nextInt(11)+140);
				p.setyPos(r.nextInt(61)+120);
				p.setWidth(8);
				p.setHeight(8);
				p.setxVelo(r.nextInt(1)+1);
				p.setP(r.nextInt(71)+30);
				
				if(Interface.getChoosedElement()== "Cs")
				{
		  			if((Main.wave.length<579 && p.p>97 )  && Main.wave.intensity>15)
			    	{
		  				photons.add(p);	
			    	}
		  			if((Main.wave.length<550 && p.p>95 ) && Main.wave.intensity>15)
			    	{
		  				photons.add(p);	
			    	}
		  			if((Main.wave.length<530 && p.p>93 ) && Main.wave.intensity>15)
			    	{
		  				photons.add(p);	
			    	}
		  			if((Main.wave.length<500 && p.p>90 ) && Main.wave.intensity>15)
			    	{
		  				photons.add(p);	
			    	}
		  			if((Main.wave.length<450 && p.p>88 ) && Main.wave.intensity>15)
			    	{
		  				photons.add(p);	
			    	}
				}
				if(Interface.getChoosedElement()== "Na")
				{
		  			if((Main.wave.length<525 && p.p>98 ) && Main.wave.intensity>15)
			    	{
		  				photons.add(p);	
			    	}
		  			if((Main.wave.length<510 && p.p>95 ) && Main.wave.intensity>15)
			    	{
		  				photons.add(p);	
			    	}
		  			if((Main.wave.length<450 && p.p>92 ) && Main.wave.intensity>15)
			    	{
		  				photons.add(p);	
			    	}
		  			if((Main.wave.length<400 && p.p>87 ) && Main.wave.intensity>15)
			    	{
		  				photons.add(p);	
			    	}
				}
				if(Interface.getChoosedElement()== "Rb")
				{
		  			if((Main.wave.length<547 && p.p>97 ) && Main.wave.intensity>15)
			    	{
		  				photons.add(p);	
			    	}
		  			if((Main.wave.length<510 && p.p>95) && Main.wave.intensity>15)
			    	{
		  				photons.add(p);	
			    	}
		  			if((Main.wave.length<480 && p.p>93 ) && Main.wave.intensity>15)
			    	{
		  				photons.add(p);	
			    	}
		  			if((Main.wave.length<440 && p.p>90 ) && Main.wave.intensity>15)
			    	{
		  				photons.add(p);	
			    	}
		  			if((Main.wave.length<400 && p.p>87 ) && Main.wave.intensity>15)
			    	{
		  				photons.add(p);	
			    	}
				}
				if(Interface.getChoosedElement()== "Ca")
				{
		  			if((Main.wave.length<431 && p.p>97 ) && Main.wave.intensity>15)
			    	{
		  				photons.add(p);	
			    	}
		  			if((Main.wave.length<400 && p.p>94 ) && Main.wave.intensity>15)
			    	{
		  				photons.add(p);	
			    	}
				}
				if(Interface.getChoosedElement()== "K")
				{
		  			if((Main.wave.length<540 && p.p>97 ) && Main.wave.intensity>15)
			    	{
		  				photons.add(p);	
			    	}
		  			if((Main.wave.length<510 && p.p>95 ) && Main.wave.intensity>15)
			    	{
		  				photons.add(p);	
			    	}
		  			if((Main.wave.length<470 && p.p>93 ) && Main.wave.intensity>15)
			    	{
		  				photons.add(p);	
			    	}
		  			if((Main.wave.length<440 && p.p>90 ) && Main.wave.intensity>15)
			    	{
		  				photons.add(p);	
			    	}
		  			if((Main.wave.length<400 && p.p>87 ) && Main.wave.intensity>15)
			    	{
		  				photons.add(p);	
			    	}
				}
	  			for (Photoelectron pr : photons) 
				{
					pr.getxPos();
					if(voltageSlider.getValue()==1)
					{
						pr.xVelo=2;
					}
					if(voltageSlider.getValue()==2)
					{
						pr.xVelo=3;
					}
					if(voltageSlider.getValue()==3)
					{
						pr.xVelo=4;
					}
					if(voltageSlider.getValue()==4)
					{
						pr.xVelo=5;
					}
					if(voltageSlider.getValue()==-1)
					{
						if(pr.xPos > 300 - pr.getWidth())
							pr.xVelo=-2;
					}
					if(voltageSlider.getValue()==-2)
					{
						if(pr.xPos > 250 - pr.getWidth())
							pr.xVelo=-3;
					}
					if(voltageSlider.getValue()==-3)
					{
						if(pr.xPos > 200 - pr.getWidth())
							pr.xVelo=-4;
					}
					if(voltageSlider.getValue()==-4)
					{
						if(pr.xPos > 180 - pr.getWidth())
						pr.xVelo=-5;
					}
					pr.xPos += pr.xVelo;
				}
				Iterator<Photoelectron> iter = photons.iterator();
		        while(iter.hasNext())
		        {
		        	Photoelectron f = iter.next();
		        	if(f.xPos > 330 - f.getWidth() || f.xPos< 130+f.getWidth())
		        	{
		        		iter.remove();
		        	}
		      	}
		        repaint();
		        try 
		        {
		        	sleep(20);
		        } 
		        catch(Exception e)
		        {
		        	e.printStackTrace();
		        };
	         }	
		 }
	  }; 
	  public void stopThread() 
	  {
		    stopped = true;
		  
	  }
	  public void suspendThread() 
	  {
		    suspended = true;
	  }
	  public void resumeThread() 
	  {
		  suspended = false;
		  synchronized(animator) 
		  {
			  animator.notify();
		  }
	  }
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
		w.paint(g);
		for (Photoelectron pr : photons  ) 
		{
			pr.paint(g);
		}
		
	}

}

