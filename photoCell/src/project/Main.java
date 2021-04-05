package project;

public class Main
{	
	static ElectroMagneticWave wave = new ElectroMagneticWave(PhotoCellSettings.sliderWaveLengthMin);
	
	public static void main(String[] args)
	{	
		//PhotoEffect window = new PhotoEffect();
		//window.setVisible(true);
		Interface mainFrame = new Interface();
		mainFrame.setVisible(true);
	}
}
