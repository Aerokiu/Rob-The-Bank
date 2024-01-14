
import javax.swing.*;
import java.awt.*;

public class Robber {
	
	public int xPos, yPos;
	public boolean robbermove;
	public ImageIcon robberstand;
	public boolean capequip,speedshoes;
	

	public Robber(int x,int y) 
	{
		
		xPos =  x;
		yPos =  y;
		
		robberstand = new ImageIcon("robber.png");
		
		//xPos = 100;
		//yPos = 90;
		
	}
	
	public int getX(int x){
		return xPos;
	}
	
	public int getY(int y) {
		return yPos;
	}
	
	
	public void move() {
		
		xPos += 5;
	}
	
	
	public void fly() {
				
		yPos -= 5;
	
	}
	}
	

