package com.mycompany.a2;

import com.codename1.charts.models.Point;

public abstract class Movable extends GameObject{
	private int heading = 0;
	private int speed = 10;	
	
	public Movable(int size,Point location,int color) {
		super(size,location,color);
	}
	
	//updates its location based on its current heading and speed
	//movable game objects move simultaneously according to their individual speed and heading
	public void move() {
		double deltaX = Math.cos(Math.toRadians(90-this.heading) * speed);
		double deltaY = Math.sin(Math.toRadians(90-this.heading) * speed);
		float x =(float) deltaX;
		float y = (float) deltaY;
		
		Point point = new Point(this.getLocation().getX()+x,this.getLocation().getY()+y);
		super.setLocation(point);
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