package com.mycompany.a3;
import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public abstract class GameObject implements IDrawable, ICollider{

	private int size;
	private Point location;
	private int color;
	
	public GameObject(int size, Point location, int color) {
		this.size = size;
		this.location = location;
		this.color = color;
	}
	
	public boolean collidesWith(GameObject otherObject) {	//check in on this
		boolean result = false;
		//centers
		int thisCenterX = (int)this.getLocation().getX() + (this.getSize()/2);	
		int thisCenterY = (int)this.getLocation().getY() + (this.getSize()/2);
		//other object centers
		int otherCenterX = (int)otherObject.getLocation().getX() + (otherObject.getSize()/2);
		int otherCenterY = (int)otherObject.getLocation().getY() + (otherObject.getSize()/2);
		
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distanceBetween = (dx*dx + dy+dy);
		
		int thisRadius = this.getSize()/2;
		int otherRadius = otherObject.getSize()/2;
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius);
		
		if (distanceBetween <= radiiSqr) {
			result = true;
		}
		
		
		return result;
	}
	public void handleCollision(GameObject otherObject) {
		System.out.println("from gameObject");
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
	}
	
	public int getSize() {
		return size;
	}
	
	public void setLocation(Point location) {
		this.location = location;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public void setColor(int newColor) {	
		this.color = newColor;
	}	
	
	public int getColor() {
		return color;
	}
	
}