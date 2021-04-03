package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class PhotoCellSettings extends JFrame 
{
	ElectroMagneticWave wave = new ElectroMagneticWave(380);
	
	int frequency;
	int length;
	int intensity;
	
	//sliders used to make changes in ElectroMagneticWave class
	JSlider sliderWaveLength;
	int  sliderWaveLengthMin= 380; 
	int sliderWaveLengthMax = 780;
	
	JSlider sliderWaveFrequency;
	int sliderWaveFrequencyMin =  384; 
	int sliderWaveFrequencyMax = 789;
	
	JSlider sliderLightIntensity;
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
	
	//panels
	JPanel panelElectroMagneticWaveSpectre;
	JPanel panelWaveLength;
	JPanel panelWaveFrequency;
	JPanel panelLightIntensity;
	
	JPanel electroMagneticWaveSpectre;
	JPanel selectedColor;
	
	JFrame frame = new JFrame();
	
	PhotoCellSettings()
	{
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(4,1));
		
		this.setSize(600,300);
		this.setResizable(true);
		
		panelElectroMagneticWaveSpectre = new JPanel();
		panelElectroMagneticWaveSpectre.setLayout(new GridLayout(1,1));
		wave.waveLengthToColor();

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
		
		sliderWaveLength = new JSlider(JSlider.HORIZONTAL, sliderWaveLengthMin, sliderWaveLengthMax, sliderWaveLengthMin);
		sliderWaveLength.setPreferredSize(new Dimension(200,50));
		
		sliderWaveLength.addChangeListener(new sliderWaveLengthChangeListener());
		
		labelWaveLength = new JLabel("Długość fali");
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
						wave.frequency = number;
						wave.frequencyToWaveLength();
						sliderWaveLength.setValue((int)wave.waveLength);
						sliderWaveFrequency.setValue((int)wave.frequency);		
					}
					else
					{
						JOptionPane.showMessageDialog(frame,
							    "Wartości powinny mieścić się w zakresie od 380nm do 780nm.","Coś się zepsuło",
							    JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(NumberFormatException exception)
				{
					JOptionPane.showMessageDialog(frame,
						    "Proszę wprowadzić wartość, która jest liczbą.","Coś się zepsuło",
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
		
		labelWaveFrequency = new JLabel("Częstotliwość fali");
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
						wave.frequency = number;
						wave.frequencyToWaveLength();
						sliderWaveLength.setValue((int)wave.waveLength);
						sliderWaveFrequency.setValue((int)wave.frequency);		
					}
					else
					{
						JOptionPane.showMessageDialog(frame,
							    "Wartości powinny mieścić się w zakresie od 384THz do 789THz.","Coś się zepsuło",
							    JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(NumberFormatException exception)
				{
					JOptionPane.showMessageDialog(frame,
						    "Proszę wprowadzić wartość, która jest liczbą.","Coś się zepsuło",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		panelWaveFrequency.add(labelWaveFrequency);
		panelWaveFrequency.add(sliderWaveFrequency);
		panelWaveFrequency.add(textFieldWaveFrequency);
		panelWaveFrequency.add(labelWaveFrequencyUnit);
		
		sliderLightIntensity = new JSlider(JSlider.HORIZONTAL, sliderLightIntensityMin, sliderLightIntensityMax, sliderLightIntensityMax);
		sliderLightIntensity.setPreferredSize(new Dimension(200,50));
		
		sliderLightIntensity.addChangeListener(new sliderLightIntensityChangeListener());
		
		labelLightIntensity = new JLabel("Natężenie fali");
		labelLightIntensityUnit = new JLabel("%");
		textFieldLightIntensity = new JTextField(String.valueOf(sliderLightIntensityMax));
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
						wave.intensity = number;
						sliderLightIntensity.setValue((int)wave.intensity);		
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Wartości powinny mieścić się w zakresie od 0% do 100%.","Coś się zepsuło", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(NumberFormatException exception)
				{
					JOptionPane.showMessageDialog(frame,
						    "Proszę wprowadzić wartość, która jest liczbą.","Coś się zepsuło",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		panelLightIntensity.add(labelLightIntensity);
		panelLightIntensity.add(sliderLightIntensity);
		panelLightIntensity.add(textFieldLightIntensity);
		panelLightIntensity.add(labelLightIntensityUnit);
	}
	
	public class sliderWaveLengthChangeListener implements ChangeListener 
	{
		 @Override
	       public void stateChanged(ChangeEvent e) 
	       {
	           String value = String.format("%d", sliderWaveLength.getValue());
	           
	           length = Integer.parseInt(value);
	           textFieldWaveLength.setText(value);
	           sliderWaveFrequency.setValue((int)(ElectroMagneticWave.speedOfLight / (length * 1000)));
	           wave.waveLength = length;
	           panelElectroMagneticWaveSpectre.setBackground(wave.color);
	       }
	}
	
	public class sliderWaveFrequencyhChangeListener implements ChangeListener 
	{
		 @Override
	       public void stateChanged(ChangeEvent e) 
	       {
			 String value = String.format("%d", sliderWaveFrequency.getValue());
			 frequency = Integer.parseInt(value);
			 wave.waveLengthToColor();
			 textFieldWaveFrequency.setText(value);
			 sliderWaveLength.setValue((int)(ElectroMagneticWave.speedOfLight / (frequency * 1000)));
	       }
	 }	
	
	public class sliderLightIntensityChangeListener implements ChangeListener 
	{
		 @Override
	       public void stateChanged(ChangeEvent e) 
	       {
	           String value = String.format("%d", sliderLightIntensity.getValue());
	           wave.intensity = Integer.parseInt(value) * 255/100;
	           textFieldLightIntensity.setText(value);
	           wave.waveLengthToFrequency();
	       }
	 }
	
	public static void main(String[] args)
	{
		PhotoCellSettings window = new PhotoCellSettings();
		window.setVisible(true);
		window.setTitle("Sliders");
	}
}