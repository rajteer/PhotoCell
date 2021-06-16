package photocell;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Photoelectron 
{
	int xPos = 130;
	int yPos = 200;
	int xVelo=1;
	int yVelo=1;
	int dt=5;
    int width = 5;
    int height = 5;
    Color color = Color.BLACK;
    int pauza = 200;
    int N;
    int p;
    public int getP() 
    {
		return p;
	}
	public void setP(int p)
	{
		this.p = p;
	}
	public int getN() 
    {
		return N;
	}
	public void setN(int n) 
	{
		N = n;
	}
	public int getxPos() 
    {
		return xPos;
	}
	public void setxPos(int xPos) 
	{
		this.xPos = xPos;
	}
	public int getyPos() 
	{
		return yPos;
	}
	public void setyPos(int yPos) 
	{
		this.yPos = yPos;
	}
	public int getxVelo() 
	{
		return xVelo;
	}
	public void setxVelo(int xVelo) 
	{
		this.xVelo = xVelo;
	}
	public int getyVelo() 
	{
		return yVelo;
	}
	public void setyVelo(int yVelo) 
	{
		this.yVelo = yVelo;
	}
	public int getWidth() 
	{
		return width;
	}
	public void setWidth(int width)
	{
		this.width = width;
	}
	public int getHeight() 
	{
		return height;
	}
	public void setHeight(int height) 
	{
		this.height = height;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) 
	{
		this.color = color;
	}
	public void paint(Graphics g)
	{
	   // g2.setColor(getColor());
		g.setColor(new Color(0,0,139));
        g.fillOval(xPos,yPos,getWidth(),getHeight());
     
    }

}
