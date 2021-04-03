package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class PhotoCellSettings extends JFrame 
{
	ElectroMagneticWave wave = new ElectroMagneticWave(380);
	
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
	
	//button for approving changes
	JButton buttonPhotoCellSettings;
	
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
	JPanel panelPhotoCellSettings;
	
	JPanel electroMagneticWaveSpectre;
	JPanel selectedColor;
	
	
	PhotoCellSettings()
	{
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(5,1));
		
		this.setSize(600,300);
		this.setResizable(false);
		
		panelElectroMagneticWaveSpectre = new JPanel();
		
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
		
		panelPhotoCellSettings = new JPanel();
		panelPhotoCellSettings.setLayout(new FlowLayout());
		this.add(panelPhotoCellSettings);
		
		sliderWaveLength = new JSlider(JSlider.HORIZONTAL, sliderWaveLengthMin, sliderWaveLengthMax, sliderWaveLengthMin);
		sliderWaveLength.setPreferredSize(new Dimension(200,50));
		
		sliderWaveLength.addChangeListener(new sliderWaveLengthChangeListener());
		
		labelWaveLength = new JLabel("Długość fali");
		labelWaveLengthUnit = new JLabel("nm");
		textFieldWaveLength = new JTextField("380");
		textFieldWaveLength.setPreferredSize( new Dimension( 50, 30 ) );
		
		panelWaveLength.add(labelWaveLength);
		panelWaveLength.add(sliderWaveLength);
		panelWaveLength.add(textFieldWaveLength);
		panelWaveLength.add(labelWaveLengthUnit);
		
		sliderWaveFrequency = new JSlider(JSlider.HORIZONTAL, sliderWaveFrequencyMin, sliderWaveFrequencyMax, sliderWaveFrequencyMin);
		sliderWaveFrequency.setPreferredSize(new Dimension(200,50));
		
		sliderWaveFrequency.addChangeListener(new sliderWaveFrequencyhChangeListener());
		sliderWaveFrequency.setInverted(true);
		
		labelWaveFrequency = new JLabel("Częstotliwość fali");
		labelWaveFrequencyUnit = new JLabel("THz");
		textFieldWaveFrequency = new JTextField("380");
		textFieldWaveFrequency.setPreferredSize( new Dimension( 50, 30 ) );
		
		panelWaveFrequency.add(labelWaveFrequency);
		panelWaveFrequency.add(sliderWaveFrequency);
		panelWaveFrequency.add(textFieldWaveFrequency);
		panelWaveFrequency.add(labelWaveFrequencyUnit);
		
		sliderLightIntensity = new JSlider(JSlider.HORIZONTAL, sliderLightIntensityMin, sliderLightIntensityMax, sliderLightIntensityMin);
		sliderLightIntensity.setPreferredSize(new Dimension(200,50));
		sliderLightIntensity.setValue(50);
		
		sliderLightIntensity.addChangeListener(new sliderLightIntensityChangeListener());
		
		labelLightIntensity = new JLabel("Natężenie fali");
		labelLightIntensityUnit = new JLabel("%");
		textFieldLightIntensity = new JTextField();
		textFieldLightIntensity.setPreferredSize( new Dimension( 50, 30 ) );
		
		panelLightIntensity.add(labelLightIntensity);
		panelLightIntensity.add(sliderLightIntensity);
		panelLightIntensity.add(textFieldLightIntensity);
		panelLightIntensity.add(labelLightIntensityUnit);
		
		buttonPhotoCellSettings = new JButton("Zastosuj zmiany");
		panelPhotoCellSettings.add(buttonPhotoCellSettings);
	}
	
	public class sliderWaveLengthChangeListener implements ChangeListener 
	{
		 @Override
	       public void stateChanged(ChangeEvent e) 
	       {
	           String value = String.format("%d", sliderWaveLength.getValue());
	           wave.waveLength = Integer.parseInt(value);
	           wave.waveLengthToFrequency();
	           textFieldWaveLength.setText(value);
	           sliderWaveFrequency.setValue((int)wave.frequency);
	           panelElectroMagneticWaveSpectre.setBackground(wave.color);
	       }
	}
	
	public class sliderWaveFrequencyhChangeListener implements ChangeListener 
	{
		 @Override
	       public void stateChanged(ChangeEvent e) 
	       {
			 String value = String.format("%d", sliderWaveFrequency.getValue());
			 wave.frequency = Integer.parseInt(value);
			 wave.frequencyToWaveLength();
			 wave.waveLengthToColor();
			 textFieldWaveLength.setText(value);
			 textFieldWaveFrequency.setText(value);
			 sliderWaveLength.setValue((int)wave.waveLength);
			 
	       }
	 }	
	
	public class sliderLightIntensityChangeListener implements ChangeListener 
	{
		 @Override
	       public void stateChanged(ChangeEvent e) 
	       {
	           String value = String.format("%d", sliderLightIntensity.getValue());
	           wave.intensity = Integer.parseInt(value);
	           textFieldLightIntensity.setText(value);
	       }
	 }
	
	public static void main(String[] args)
	{
		PhotoCellSettings window = new PhotoCellSettings();
		window.setVisible(true);
	}
}