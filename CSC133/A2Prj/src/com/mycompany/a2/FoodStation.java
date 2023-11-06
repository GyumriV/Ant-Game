package com.mycompany.a2;
import com.codename1.charts.models.Point;


public class FoodStation extends Fixed{
	//initial capacity is proportional to the size of the food station
	private int capacity;
	
	FoodStation(int size,Point location,int color){
		super(size,location,color);
		this.capacity = size;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public void setCapacity(int newCapacity) {
		this.capacity = newCapacity;
	} 
}