package com.mycompany.hi;

public interface ISteerable {
	
	public void steer(int steerAmount);		//don't think this is public
	//allows other objects to change their heading (direction of movement) after they are created
	//difference between steerable and Movable is that other objects can request a change in the heading of steerable objects
	//   whereas other objects can only request that a movable object update its own location according to its current speed and heading
	
	
}
