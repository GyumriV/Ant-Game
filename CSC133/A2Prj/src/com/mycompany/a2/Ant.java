package com.mycompany.a2;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Ant extends Movable implements ISteerable{	
	
	private int maximumSpeed = 50;
	private int foodLevel = 20;
	private int foodConsumptionRate = 2;
	private int healthLevel = 10;	 
	private int lastFlagReached = 1;
	private static Ant ant;
	private Ant(int size,Point location,int color) {
		super(size,location,color);
		this.setHeading(0);	
		this.setSpeed(10);
		this.foodLevel = 20;
				
	}
	
	//new in a2
	public static Ant getAnt() {
		if(ant == null) {
			ant = new Ant(10,new Point(40,40),ColorUtil.YELLOW);
		}
		return ant;
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
