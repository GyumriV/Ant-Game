package com.mycompany.hi;
import com.codename1.charts.models.Point;

public class Ant extends Movable implements ISteerable{	
	
	private int maximumSpeed = 50;
	private int foodLevel = 20;
	private int foodConsumptionRate = 2;
	private int healthLevel = 10;	 
	private int lastFlagReached = 1;
	
	public Ant(int size,Point location,int color) {
		super(size,location,color);
		this.setHeading(0);	
		this.setSpeed(10);
				
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
	
	
	
	
	
	
	
	
	
}
