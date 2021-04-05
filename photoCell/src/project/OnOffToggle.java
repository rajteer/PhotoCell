package project;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class OnOffToggle extends JFrame
{
	
	JToggleButton flashLightToggle;
	JToggleButton animationLightToggle;
	JButton resetButton;
	JPanel panel;
	
	OnOffToggle()
	{
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(250,250);
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		this.add(panel);
		
		flashLightToggle = new JToggleButton("Włącz źródło światła");
		panel.add(flashLightToggle);
		ItemListener itemListener = new ItemListener() 
		{
            public void itemStateChanged(ItemEvent itemEvent)
            {
                int state = itemEvent.getStateChange();
  
                if (state == ItemEvent.SELECTED) 
                {
                    flashLightToggle.setText("Wyłącz źródło światła");
                }
                else 
                {
                	flashLightToggle.setText("Włącz źródło światła");
                }
            }
		};
    		
            animationLightToggle = new JToggleButton("Start");
            animationLightToggle.setBackground(Color.GREEN);
    		panel.add(flashLightToggle);
    		ItemListener listenerAnimationLightToggle = new ItemListener()
    		{
                public void itemStateChanged(ItemEvent itemEvent)
                {
                    int state = itemEvent.getStateChange();
      
                    if (state == ItemEvent.SELECTED) 
                    {
                    	animationLightToggle.setText("Pauza");
                    	animationLightToggle.setForeground(Color.RED);
                    }
                    else 
                    {
                    	animationLightToggle.setText("Wznów");
                    	animationLightToggle.setBackground(Color.GREEN);
                    	animationLightToggle.setForeground(Color.BLACK);
                    }
                }
    		};
    		panel.add(animationLightToggle);
    		
    		resetButton = new JButton("Reset");
    		
    		panel.add(resetButton);
    		
    		animationLightToggle.addItemListener(listenerAnimationLightToggle);
    		flashLightToggle.addItemListener(itemListener);
	}
	public static void main(String[] args)
	{
		OnOffToggle window = new OnOffToggle();
		window.setVisible(true);
	}
}
