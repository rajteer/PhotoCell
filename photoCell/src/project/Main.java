package project;

import javax.swing.JFrame;

public class Main
{	
	public static void main(String[] args)
	{	
		ElectroMagneticWave wave = new ElectroMagneticWave(380);
		
		PhotoEffect window = new PhotoEffect();
		window.setVisible(true);
	}
}
