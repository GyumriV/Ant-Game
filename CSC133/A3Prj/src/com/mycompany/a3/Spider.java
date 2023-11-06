package com.mycompany.a3;

import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public class Spider extends Movable{

	private GameWorld gw;
	
	
	public Spider(int size,Point location,int color, GameWorld gw) {
		super(size,location,color);
		
		//initial random heading for Spider
		Random random = new Random();
		int randHead = random.nextInt(539) + 0;
		this.setHeading(randHead);
		this.gw = gw;
	}
	
	@Override
	public void setColor(int newColor) {
		super.setColor(newColor);
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		int x = (int) this.getLocation().getX() + (int) pCmpRelPrnt.getX();
		int y = (int) this.getLocation().getY() + (int) pCmpRelPrnt.getY();
		
		int[] xCoor = {x - this.getSize()/2, x, x+this.getSize()/2};
		int[] yCorr = {y - this.getSize()/2, y+this.getSize()/2,y-this.getSize()/2};
		
		g.drawPolygon(xCoor, yCorr, 3);
	}

	@Override
	public void handleCollision(GameObject otherObject) {
		if(otherObject instanceof Ant) {
			gw.spiderCollision();
		}
	}
	
	@Override
	public void move(int elapsedTime, int mapHeight, int mapWidth) {
		double distance = this.getSpeed() * (elapsedTime/1000.0);
		double deltaX = Math.cos(Math.toRadians(90-this.getHeading()) * distance);
		double deltaY = Math.sin(Math.toRadians(90-this.getHeading()) * distance);

		float currentX = this.getLocation().getX() + (float)deltaX;
		float currentY = this.getLocation().getY() + (float)deltaY;
		
		if((currentX + this.getSize()/2 >= mapWidth) || (currentX <0)) {
			System.out.println("we passed the x mark");			
			this.setHeading(90-this.getHeading()*-1);
		}
		if((currentY + this.getSize()/2 >= mapHeight) || (currentY < 0)) {
			System.out.println("we passed the y mark");
			this.setHeading(90 - this.getHeading() * -1);
		}
		
		Point point = new Point(currentX,currentY);
		this.setLocation(point);
		
	}
	
	
	
}