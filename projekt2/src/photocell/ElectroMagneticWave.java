package photocell;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class ElectroMagneticWave
{
	final static private long speedOfLight = 299792458; //m/s
	final static private double planckConstant = 6.626 * Math.pow(10, -34); //J*s
	final static private double planckConstant1 = 4.1356 * Math.pow(10, -15); //eV*s
	final static private double electronicCharge = 1.602 * Math.pow(10, -19); //C	
	static private final double gamma = 0.80;
	static private final double intensityMax = 255;
	float length; //nm
	static float frequency; //THz
	double photonEnergy; //eV
	static float intensity; //%
	Color color; //RGB
	Color color1, color2;
	static int x[] = {145, 145, 230, 205};
	static int y[] = {120, 195, 65, 50};
	static int n = 4;
	int alpha;
	static Polygon p = new Polygon(x, y, n);
	
	public static long getSpeedoflight() 
	{
		return speedOfLight;
	}
	
	public static double getPlanckconstant() 
	{
		return planckConstant;
	}
	
	public static double getElectroniccharge() 
	{
		return electronicCharge;
	}	
		
	public float getFrequency() 
	{
		return frequency;
	}

	public void setFrequency(float frequency) 
	{
		ElectroMagneticWave.frequency = frequency;
	}

	public static float getIntensity() 
	{
		return intensity;
	}

	public static void setIntensity(float intensity) 
	{
		ElectroMagneticWave.intensity = intensity;
	}

	public ElectroMagneticWave(float lambda) 
	{
		length = lambda;
		frequency = waveLengthToFrequency();
		photonEnergy =  photonEnergy();
		color = waveLengthToColor();
		intensity = 10;
	}
	
	public double photonEnergy()
	{
		double E;
		E= planckConstant1 * frequency/ Math.pow(10, -12);
		return Math.round(E * 100.0) / 100.0;
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
	public Color getColor() 
	{
		return color;
	}

	public void setColor(Color color) 
	{
		this.color = color;
	}
		
	public int getAlpha()
	{
		return alpha;
	}

	public void setAlpha(int alpha) 
	{
		this.alpha = alpha;
	}

	public  Color newColorWithAlpha(Color original, int alpha) 
	{
		return new Color(original.getRed(), original.getGreen(), original.getBlue(), alpha);	
	}

	public void paint(Graphics g)
	{
		if(PhotoCellSettings.sliderLightIntensity.getValue()>0)
		{
			g.setColor(Main.wave.color1);
		    g.fillPolygon(p);
		}
	}
}