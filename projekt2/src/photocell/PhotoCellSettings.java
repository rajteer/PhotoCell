package photocell;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PhotoCellSettings extends JPanel 
{	
	//sliders used to make changes in ElectroMagneticWave class
	static JSlider sliderWaveLength;
	static int  sliderWaveLengthMin= 380; 
	int sliderWaveLengthMax = 780;
	
	static JSlider sliderWaveFrequency;
	int sliderWaveFrequencyMin =  384; 
	int sliderWaveFrequencyMax = 789;
	
	static JSlider sliderLightIntensity;
	int sliderLightIntensityMin =  0; 
	int sliderLightIntensityMax = 100;
	
	//text fields used to implement changes and display the value of an object of ElectroMagneticWave class 
	JTextField textFieldWaveLength;
	JTextField textFieldWaveFrequency;
	JTextField textFieldLightIntensity;
	
	//slider captions
	JLabel labelWaveLength;
	JLabel labelWaveLengthUnit;
	JLabel labelWaveFrequency;
	JLabel labelWaveFrequencyUnit;
	JLabel labelLightIntensity;
	JLabel labelLightIntensityUnit;
	JLabel labelElectroMagneticWaveInfo;
	JLabel labelElectroMagneticWaveValue;
	
	//panels
	static JPanel panelElectroMagneticWaveSpectre;
	JPanel panelWaveLength;
	JPanel panelWaveFrequency;
	JPanel panelLightIntensity;
	JPanel panelElectroMagneticWaveInfo;
	JPanel electroMagneticWaveSpectre;
	JPanel selectedColor;
	
	//frame
	JFrame frame = new JFrame();
	
	//communication
	static String kom1, error, numbers, kom2, kom3;
	
	//value of intensity slider
	int N;
	
	PhotoCellSettings()
	{
		this.setLayout(new GridLayout(5,1));
		this.setSize(600,300);
		panelElectroMagneticWaveSpectre = new JPanel();
		panelElectroMagneticWaveSpectre.setLayout(new FlowLayout());
		panelElectroMagneticWaveSpectre.setBackground(Main.wave.color);
		panelElectroMagneticWaveSpectre.setOpaque(true);
		
		
		this.add(panelElectroMagneticWaveSpectre);
		
		panelWaveLength = new JPanel();
		panelWaveLength.setLayout(new FlowLayout());
		this.add(panelWaveLength);
		
		panelWaveFrequency = new JPanel();
		panelWaveFrequency.setLayout(new FlowLayout());
		this.add(panelWaveFrequency);
		
		panelLightIntensity = new JPanel();
		panelLightIntensity.setLayout(new FlowLayout());
		this.add(panelLightIntensity);
		
		panelElectroMagneticWaveInfo = new JPanel();
		panelElectroMagneticWaveInfo.setLayout(new FlowLayout());
		this.add(panelElectroMagneticWaveInfo);
		
		sliderWaveLength = new JSlider(JSlider.HORIZONTAL, sliderWaveLengthMin, sliderWaveLengthMax, sliderWaveLengthMin);
		sliderWaveLength.setPreferredSize(new Dimension(200,50));
		
		sliderWaveLength.addChangeListener(new sliderWaveLengthChangeListener());
		
		labelWaveLength = new JLabel();
		labelWaveLengthUnit = new JLabel("nm");
		textFieldWaveLength = new JTextField(String.valueOf(sliderWaveLengthMin));
		textFieldWaveLength.setPreferredSize( new Dimension( 50, 30 ) );
		textFieldWaveLength.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int number;
				String text = textFieldWaveLength.getText();
				try
				{
					number = Integer.parseInt(text);
					if(number >= 380 && number <= 780 )
					{
						Main.wave.length = number;
						Main.wave.waveLengthToColor();
						sliderWaveLength.setValue((int) Main.wave.length);
						sliderWaveFrequency.setValue((int) Main.wave.frequency);	
						Main.wave.photonEnergy =  Main.wave.photonEnergy();
						labelElectroMagneticWaveValue.setText(String.valueOf( Main.wave.photonEnergy) + " eV");
					}
					else
					{
						JOptionPane.showMessageDialog(frame,
							    kom1,error,													
							    JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(NumberFormatException exception)
				{
					JOptionPane.showMessageDialog(frame,
							numbers,error,
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		panelWaveLength.add(labelWaveLength);
		panelWaveLength.add(sliderWaveLength);
		panelWaveLength.add(textFieldWaveLength);
		panelWaveLength.add(labelWaveLengthUnit);
		
		sliderWaveFrequency = new JSlider(JSlider.HORIZONTAL, sliderWaveFrequencyMin, sliderWaveFrequencyMax, sliderWaveFrequencyMax);
		sliderWaveFrequency.setPreferredSize(new Dimension(200,50));
		
		sliderWaveFrequency.addChangeListener(new sliderWaveFrequencyhChangeListener());
		sliderWaveFrequency.setInverted(true);
		
		labelWaveFrequency = new JLabel();
		labelWaveFrequencyUnit = new JLabel("THz");
		textFieldWaveFrequency = new JTextField(String.valueOf(sliderWaveFrequencyMax));
		textFieldWaveFrequency.setPreferredSize( new Dimension( 50, 30 ) );
		textFieldWaveFrequency.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int number;
				String text = textFieldWaveFrequency.getText();
				try
				{
					number = Integer.parseInt(text);
					
					if(number >= 380 && number <= 780 )
					{
						Main.wave.frequency = number;
						Main.wave.frequencyToWaveLength();
						sliderWaveLength.setValue((int)Main.wave.length);
						sliderWaveFrequency.setValue((int)Main.wave.frequency);	
						Main.wave.photonEnergy = Main.wave.photonEnergy();
						labelElectroMagneticWaveValue.setText(String.valueOf(Main.wave.photonEnergy) + " eV");
					}
					else
					{
						JOptionPane.showMessageDialog(frame,		
							    kom2,error,
							    JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(NumberFormatException exception)
				{
					JOptionPane.showMessageDialog(frame,
						    numbers,error,
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		panelWaveFrequency.add(labelWaveFrequency);
		panelWaveFrequency.add(sliderWaveFrequency);
		panelWaveFrequency.add(textFieldWaveFrequency);
		panelWaveFrequency.add(labelWaveFrequencyUnit);
		
		sliderLightIntensity = new JSlider(JSlider.HORIZONTAL, sliderLightIntensityMin, sliderLightIntensityMax, sliderLightIntensityMin);
		
		sliderLightIntensity.addChangeListener(new sliderLightIntensityChangeListener());
		
		labelLightIntensity = new JLabel("Nat????nie fali");
		labelLightIntensityUnit = new JLabel("%");
		textFieldLightIntensity = new JTextField(String.valueOf(sliderLightIntensityMin));
		textFieldLightIntensity.setPreferredSize( new Dimension( 50, 30 ) );
		textFieldLightIntensity.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int number;
				String text = textFieldLightIntensity.getText();
				try
				{
					number = Integer.parseInt(text);
					if(number >= 0 && number <= 100)
					{
						Main.wave.intensity = number;
						sliderLightIntensity.setValue((int)Main.wave.intensity);		
					}
					else
					{
						JOptionPane.showMessageDialog(frame, kom3,error, JOptionPane.ERROR_MESSAGE);		
					}
				}
				catch(NumberFormatException exception)
				{
					JOptionPane.showMessageDialog(frame,
							numbers,error,
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		panelLightIntensity.add(labelLightIntensity);
		panelLightIntensity.add(sliderLightIntensity);
		panelLightIntensity.add(textFieldLightIntensity);
		panelLightIntensity.add(labelLightIntensityUnit);
		
		labelElectroMagneticWaveInfo = new JLabel();
		panelElectroMagneticWaveInfo.add(labelElectroMagneticWaveInfo);
		labelElectroMagneticWaveValue = new JLabel();
		labelElectroMagneticWaveValue.setText(String.valueOf(Main.wave.photonEnergy) + " eV");
		panelElectroMagneticWaveInfo.add(labelElectroMagneticWaveValue);
	}
	
	public class sliderWaveLengthChangeListener implements ChangeListener 
	{
		 @Override
	       public void stateChanged(ChangeEvent e) 
	       {
	           String value = String.format("%d", sliderWaveLength.getValue());
	           
	           Main.wave.length= Integer.parseInt(value);
	           Main.wave.frequency =  Main.wave.waveLengthToFrequency();
	           
	           textFieldWaveLength.setText(value);
	           
	           textFieldWaveFrequency.setText(String.valueOf((int)( Main.wave.frequency)));
	           sliderWaveFrequency.setValue((int)( Main.wave.frequency));
	           
	           Main.wave.color =  Main.wave.waveLengthToColor();
	           panelElectroMagneticWaveSpectre.setBackground( Main.wave.color);
	           
	           Main.wave.photonEnergy =  Main.wave.photonEnergy();
	           labelElectroMagneticWaveValue.setText(String.valueOf( Main.wave.photonEnergy) + " eV");
	           PlotPanel.updateCurrentVoltageChart();
	       }
	}
	
	public class sliderWaveFrequencyhChangeListener implements ChangeListener 
	{
		 @Override
	       public void stateChanged(ChangeEvent e) 
	       {
			 String value = String.format("%d", sliderWaveFrequency.getValue());
			 
			 Main.wave.frequency = Integer.parseInt(value);
			 Main.wave.length =  Main.wave.frequencyToWaveLength();
	           
			 textFieldWaveFrequency.setText(value);
			 
			 textFieldWaveLength.setText(String.valueOf((int)( Main.wave.length)));
			 sliderWaveLength.setValue((int)( Main.wave.length));
	           
			 Main.wave.color =  Main.wave.waveLengthToColor();
			 N= sliderLightIntensity.getValue();
			 panelElectroMagneticWaveSpectre.setBackground( Main.wave.color);
	         Main.wave.color1=Main.wave.newColorWithAlpha(Main.wave.color,N);
			 Main.wave.setColor(Main.wave.color1);
			 Main.wave.photonEnergy =  Main.wave.photonEnergy();
			 labelElectroMagneticWaveValue.setText(String.valueOf( Main.wave.photonEnergy) + " eV");
	       }
	 }	
	public class sliderLightIntensityChangeListener implements ChangeListener 
	{
		 @Override
	       public void stateChanged(ChangeEvent e) 
	       {
			 String value = String.format("%d", sliderLightIntensity.getValue());
			 N= sliderLightIntensity.getValue();
			 Main.wave.intensity=Integer.parseInt(value);
			 Main.wave.color =  Main.wave.waveLengthToColor();
			 panelElectroMagneticWaveSpectre.setBackground( Main.wave.color);
	         Main.wave.color1=Main.wave.newColorWithAlpha(Main.wave.color,N);
			 Main.wave.setColor(Main.wave.color1);
	         textFieldLightIntensity.setText(value);
	         PlotPanel.updateCurrentVoltageChart();
	       }
	 }
}