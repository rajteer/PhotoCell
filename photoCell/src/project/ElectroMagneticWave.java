//Julia Sieruta | Pawel Rajter
//proba
package project;

import java.awt.Color;

public class ElectroMagneticWave {
	
	final static long speedOfLight = 299792458; //m/s
	final static double planckConstant = 4.13566 * Math.pow(10, -15); //eV * s
	
	static private final double gamma = 0.80;
	static private final double intensityMax = 255;
	
	float length; //nm
	float frequency; //THz
	double photonEnergy; //eV
	int intensity; //%
	Color color; //RGB
	
	public ElectroMagneticWave(float lambda) 
	{
		length = lambda;
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

	    if((length >= 380) && (length < 440)) {
	        Red = -(length - 440) / (440 - 380);
	        Green = 0.0;
	        Blue = 1.0;
	    } else if((length >= 440) && (length < 490)) {
	        Red = 0.0;
	        Green = (length - 440) / (490 - 440);
	        Blue = 1.0;
	    } else if((length >= 490) && (length < 510)) {
	        Red = 0.0;
	        Green = 1.0;
	        Blue = -(length - 510) / (510 - 490);
	    } else if((length >= 510) && (length < 580)) {
	        Red = (length - 510) / (580 - 510);
	        Green = 1.0;
	        Blue = 0.0;
	    } else if((length >= 580) && (length < 645)) {
	        Red = 1.0;
	        Green = -(length - 645) / (645 - 580);
	        Blue = 0.0;
	    } else if((length >= 645) && (length < 781)) {
	        Red = 1.0;
	        Green = 0.0;
	        Blue = 0.0;
	    } else {
	        Red = 0.0;
	        Green = 0.0;
	        Blue = 0.0;
	    }

	    if((length >= 380) && (length < 420)) {
	        factor = 0.3 + 0.7 * (length - 380) / (420 - 380);
	    } else if((length >= 420) && (length < 701)) {
	        factor = 1.0;
	    } else if((length >= 701) && (length < 781)) {
	        factor = 0.3 + 0.7 * (780 - length) / (780 - 700);
	    } else {
	        factor = 0.0;
	    }

	    int[] rgb = new int[3];

	    rgb[0] = Red == 0.0 ? 0 : (int)Math.round(intensityMax * Math.pow(Red * factor, gamma));
	    rgb[1] = Green == 0.0 ? 0 : (int)Math.round(intensityMax * Math.pow(Green * factor, gamma));
	    rgb[2] = Blue == 0.0 ? 0 : (int)Math.round(intensityMax * Math.pow(Blue * factor, gamma));
	    
	    return new Color(rgb[0], rgb[1], rgb[2]);
	}
	
	public float waveLengthToFrequency()
	{
		return frequency = speedOfLight / (length * 1000);
	}
	
	public float frequencyToWaveLength()
	{
		return length = speedOfLight / (frequency * 1000);
	}	
}
