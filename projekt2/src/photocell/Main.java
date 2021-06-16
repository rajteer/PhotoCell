package photocell;

import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

public class Main 
{
	static ElectroMagneticWave wave = new ElectroMagneticWave(PhotoCellSettings.sliderWaveLengthMin);
		    
	public static void main(String[] args) throws FontFormatException, IOException
	{
		SwingUtilities.invokeLater(new Runnable() 
		{

			public void run() 
			{
				Image img = null;
				InputStream input = getClass().getResourceAsStream("einstein.png");
				try {                
					 img = ImageIO.read(input);
		 		} 
				catch (IOException e) 
				{
		 			System.err.println("Blad odczytu obrazka");
		 			e.printStackTrace();
				}
				Interface window;
				try 
				{
					window = new Interface();
					window.setSize(1000,800);
					window.setVisible(true);
					window.setTitle("Fotokomórka");
					window.setIconImage(img);
				} 
				catch (FontFormatException | IOException e) 
				{
					e.printStackTrace();
				}
		
			}
		});
	}
}