package project;

import java.awt.FontFormatException;
import java.io.IOException;

public class Main 
{
	static ElectroMagneticWave wave = new ElectroMagneticWave(PhotoCellSettings.sliderWaveLengthMin);
		    
	 public static void main(String[] args) throws FontFormatException, IOException
	{   
		Interface mainFrame = new Interface();
		mainFrame.setVisible(true);
	 }
}
