package com.mycompany.hi;
import com.codename1.charts.models.Point;

public abstract class GameObject {

	private int size;
	private Point location;
	private int color;
	
	public GameObject(int size, Point location, int color) {
		this.size = size;
		this.location = location;
		this.color = color;
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
