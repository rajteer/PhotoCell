package project;

import java.awt.Color;

public class ElectroMagneticWave {
	
	final static long speedOfLight = 299792458; //m/s
	final static double planckConstant = 4.13566 * Math.pow(10, -15); //eV * s
	
	float waveLength; //nm
	float frequency; //THz
	double photonEnergy; //eV
	int intensity; //%
	Color color; //RGB
	
	public ElectroMagneticWave(float lambda) {
		waveLength = lambda;
		frequency = speedOfLight / (waveLength * 1000);
		photonEnergy = planckConstant * frequency;
	}
	
	public void waveLengthToColor()
	{	
		int r = 0;
		int b = 0;
		int g = 0;
		color = new Color(r,g,b);
	}
	
	public void waveLengthToFrequency()
	{
		frequency = speedOfLight / (waveLength * 1000);
	}
	
	public void frequencyToWaveLength()
	{
		waveLength = speedOfLight / (frequency * 1000);
	}
}