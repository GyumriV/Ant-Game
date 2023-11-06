package com.mycompany.a3;

import com.codename1.charts.models.Point;

public abstract class Movable extends GameObject{
	private int heading = 0;
	private int speed = 10;	
	
	public Movable(int size,Point location,int color) {
		super(size,location,color);
	}
	
	//updates its location based on its current heading and speed
	//movable game objects move simultaneously according to their individual speed and heading
	
	public void move(int elapsedTime, int mapHeight, int mapWidth) {
		
		double distance = this.getSpeed() * (elapsedTime/1000.0);

		
		float currentX = this.getLocation().getX();
		float currentY = this.getLocation().getY();
		
		
		double deltaX = Math.cos(Math.toRadians(90 - this.heading) * distance);
		double deltaY = Math.sin(Math.toRadians(90 - this.heading) * distance) ;
		
		currentX += deltaX;
		currentY += deltaY;

		Point point = new Point(currentX,currentY); 
		this.setLocation(point);
		
	}
	
	public void setHeading(int newHeading) {
		this.heading = newHeading;
	}
	
	public int getHeading() {
		return heading;
	}
	
	public void setSpeed(int newSpeed) {
		this.speed = newSpeed;
	}
	
	public int getSpeed() {	
		return speed;	
	}
	
	
}