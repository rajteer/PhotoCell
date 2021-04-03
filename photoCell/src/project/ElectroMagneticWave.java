package project;

import java.awt.Color;

public class ElectroMagneticWave {
	
	final static long speedOfLight = 299792458; //m/s
	final static double planckConstant = 4.13566 * Math.pow(10, -15); //eV * s
	static private final double gamma = 0.80;
	static private final double intensityMax = 255;
	
	float waveLength; //nm
	float frequency; //THz
	double photonEnergy; //eV
	int intensity; //%
	Color color; //RGB
	
	public ElectroMagneticWave(float lambda) 
	{
		waveLength = lambda;
		frequency = waveLengthToFrequency();
		photonEnergy =  photonEnergy();
		color = waveLengthToColor();
		intensity = 100;
	}
	
	public float photonEnergy()
	{
		return (float) (planckConstant * frequency);
	}
	
	public Color waveLengthToColor()
	{	
		double factor;
	    double Red, Green, Blue;

	    if((waveLength >= 380) && (waveLength < 440)) {
	        Red = -(waveLength - 440) / (440 - 380);
	        Green = 0.0;
	        Blue = 1.0;
	    } else if((waveLength >= 440) && (waveLength < 490)) {
	        Red = 0.0;
	        Green = (waveLength - 440) / (490 - 440);
	        Blue = 1.0;
	    } else if((waveLength >= 490) && (waveLength < 510)) {
	        Red = 0.0;
	        Green = 1.0;
	        Blue = -(waveLength - 510) / (510 - 490);
	    } else if((waveLength >= 510) && (waveLength < 580)) {
	        Red = (waveLength - 510) / (580 - 510);
	        Green = 1.0;
	        Blue = 0.0;
	    } else if((waveLength >= 580) && (waveLength < 645)) {
	        Red = 1.0;
	        Green = -(waveLength - 645) / (645 - 580);
	        Blue = 0.0;
	    } else if((waveLength >= 645) && (waveLength < 781)) {
	        Red = 1.0;
	        Green = 0.0;
	        Blue = 0.0;
	    } else {
	        Red = 0.0;
	        Green = 0.0;
	        Blue = 0.0;
	    }

	    if((waveLength >= 380) && (waveLength < 420)) {
	        factor = 0.3 + 0.7 * (waveLength - 380) / (420 - 380);
	    } else if((waveLength >= 420) && (waveLength < 701)) {
	        factor = 1.0;
	    } else if((waveLength >= 701) && (waveLength < 781)) {
	        factor = 0.3 + 0.7 * (780 - waveLength) / (780 - 700);
	    } else {
	        factor = 0.0;
	    }

	    int[] rgb = new int[3];

	    rgb[0] = Red == 0.0 ? 0 : (int)Math.round(intensityMax * Math.pow(Red * factor, gamma));
	    rgb[1] = Green == 0.0 ? 0 : (int)Math.round(intensityMax * Math.pow(Green * factor, gamma));
	    rgb[2] = Blue == 0.0 ? 0 : (int)Math.round(intensityMax * Math.pow(Blue * factor, gamma));
	    
	    return new Color(rgb[0], rgb[1], rgb[2], intensity * 255/100);
	}
	
	public float waveLengthToFrequency()
	{
		return frequency = speedOfLight / (waveLength * 1000);
	}
	
	public float frequencyToWaveLength()
	{
		return waveLength = speedOfLight / (frequency * 1000);
	}	
}