package com.mycompany.a3;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Ant extends Movable implements ISteerable{
	
	private int maximumSpeed = 100;
	private int foodLevel = 10000;
	private int foodConsumptionRate = 2;
	private int healthLevel = 10;	 
	private int lastFlagReached = 1;
	private static Ant ant;

	
	private Ant(int size,Point location,int color) {
		super(size,location,color);
		this.setHeading(0);	
		this.setSpeed(10);
		this.foodLevel = 10000;
	}
	
	
	public static Ant getAnt() {
		if(ant == null) {
			ant = new Ant(100,new Point(40,40),ColorUtil.MAGENTA);
		}
		return ant;
	}
	
	public boolean collidesWith(GameObject obj) {
		boolean result = false;
		int thisCenterX = (int)this.getLocation().getX() + (this.getSize()/2);
		int thisCenterY = (int)this.getLocation().getY() + (this.getSize()/2);
		int otherCenterX = (int)obj.getLocation().getX() + (obj.getSize()/2);
		int otherCenterY = (int)obj.getLocation().getY() + (obj.getSize()/2);
		
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distanceBetween = (dx*dx + dy*dy);
		
		int thisRadius = this.getSize()/2;
		int otherRadius = obj.getSize()/2;
		int radiiSqr = (thisRadius * thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius);
		
		if(distanceBetween <= radiiSqr) {
			result = true;
		}
		return result;
	}
	
	@Override
	public void handleCollision(GameObject otherObject) {
		System.out.println("we hit something");
	}
	//Ant should be filled circle
	//Size of ant indicated by the diameter of the circle
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		int xCenter = (int)this.getLocation().getX() + this.getSize()/2;
		int yCenter = (int)this.getLocation().getY() + this.getSize()/2;
		int x = xCenter - this.getSize()/2;
		int y = yCenter - this.getSize()/2;
		g.fillArc(x + (int)pCmpRelPrnt.getX(), y + (int)pCmpRelPrnt.getY(), this.getSize(), this.getSize(), 0, 360);
	}
	
	@Override
	public void move(int elapsedTime, int mapHeight, int mapWidth) {
		double distance = this.getSpeed() * (elapsedTime/1000.0);
		
		float currentX = this.getLocation().getX();
		float currentY = this.getLocation().getY();
		
		double deltaX = Math.cos(Math.toRadians(90 - this.getHeading()))* this.getSpeed() * distance;
		double deltaY = Math.sin(Math.toRadians(90 - this.getHeading())) * this.getSpeed()  * distance;
		
		currentX += deltaX;
		currentY += deltaY;

		Point point = new Point(currentX,currentY);
		this.setLocation(point);
		
	}
	
	//allows user to change ant's heading
	public void steer(int steerAmount) {
		this.setHeading(this.getHeading() + steerAmount);
	}
	//setters and getters
	public void setMaximumSpeed() {
		this.maximumSpeed = this.healthLevel - (10 - healthLevel * 5);
	}
	
	public int getMaximumSpeed() {
		return this.maximumSpeed;
	}
	
	public void setLastFlagReached(int lastFlagReached) {
		this.lastFlagReached = lastFlagReached;
	}
	
	public int getLastFlagReached() {
		return this.lastFlagReached;
	}
	
	public int getFoodConsumptionRate() {
		return this.foodConsumptionRate;
	}
	
	public void setHealthLevel(int newHealthLevel) {
		this.healthLevel = newHealthLevel;
		this.setMaximumSpeed();
	}
	
	public int getHealthLevel() {
		return this.healthLevel;
	}
	
	public void setFoodLevel(int newFoodLevel) {
		this.foodLevel = newFoodLevel;
	}
	
	public int getFoodLevel() {
		return this.foodLevel;
	}
	
	public void removeAnt() {
		Ant.ant = null;
	}
}
