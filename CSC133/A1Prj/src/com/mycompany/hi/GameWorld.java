//holds collection of game objects and other state variables
package com.mycompany.hi;

import java.util.ArrayList;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class GameWorld {
	private ArrayList<GameObject> gameObject = new ArrayList<GameObject>();
	private int clock = 0;
	private int lives = 3;
	private Random random = new Random();
	//sets the initial state of the game
	public void init() {
		//initializing game objects
		gameObject.add(new Ant(10,new Point(40,40),ColorUtil.YELLOW));
		gameObject.add(new Flag(10,new Point(40,40),ColorUtil.BLUE,1));
		gameObject.add(new Flag(10,new Point(253,478),ColorUtil.BLUE,2));
		gameObject.add(new Flag(10,new Point(393,554),ColorUtil.BLUE,3));
		gameObject.add(new Flag(10,new Point(792,677),ColorUtil.BLUE,4));
		gameObject.add(new FoodStation(10 + random.nextInt(50),new Point(50+random.nextInt(950),10 + 50+random.nextInt(950)), ColorUtil.GREEN));
		gameObject.add(new Spider(random.nextInt(50) + 10,new Point(50+random.nextInt(950),10 + 50+random.nextInt(950)), ColorUtil.BLACK));
		gameObject.add(new Spider(random.nextInt(50) + 10,new Point(50+random.nextInt(950),10 + 50+random.nextInt(950)), ColorUtil.BLACK));
		
		
	}
	//increases the speed of the Ant
	public void accelerate() {
		System.out.println("Accelerate");
		//set the maximum speed according to it's health level
		Ant ant = (Ant)gameObject.get(0);
		//adds 5 to ant's current speed
		ant.setSpeed(ant.getSpeed() + 5);
		//if ant speed is set to something greater than it's maximum speed, then speed will be set to the maximum allowed
		if(ant.getSpeed() >= ant.getMaximumSpeed()) {
			ant.setSpeed(ant.getMaximumSpeed());
		}
		//if food level is 0, then the speed is equal to 0 as well
		if(ant.getFoodLevel() <= 0) {
			ant.setSpeed(0);
		}
	}
	
	//reduces the speed of the Ant
	public void brake() {
		System.out.println("brake is applied");
		Ant ant = (Ant)gameObject.get(0);
		if(ant.getSpeed() > 0) {
			ant.setSpeed(ant.getSpeed()-5);
		}else {
			ant.setSpeed(0);
		} 
	}
	
	//change ant's heading by 5 degrees to the left (in the negative direction on the compass)
	public void left() {
		System.out.println("left");
		Ant ant = (Ant)gameObject.get(0);
		ant.steer(-5);
	} 
	
	
	//change ant's heading by 5 degrees to the right (in the positive direction on the compass)
	void right() {
		System.out.println("right");
		Ant ant = (Ant)gameObject.get(0);
		ant.steer(5);
	}
	
	
		
	//check to see whether the number x is exactly one greater than the flag indicated by lastFlagReached field of ant
	//if it is, updated lastFlagReached field of the ant by increasing it by one
	public void flagCheck(int x) {
		//convert ASCII int value to actual int value entered
		x = x-48;
		System.out.println("You have collided with a Flag " + x);
		Ant ant = (Ant)gameObject.get(0);
		for(GameObject gameObject: gameObject) {
			if(gameObject instanceof Flag) {
				if(((Flag) gameObject).getSequenceNumber() == ant.getLastFlagReached()+1) {
					ant.setLastFlagReached(x);
				}
			}
		}
	}
	
	
	//Notifies player when they have collided with a Food Station
	//When this occurs, the Ant's food level is increased by the capacity of the FoodStation
	//afterwards, the FoodStation color is faded and another is created with a random location and size
	public void food() {
		System.out.println("You have reached a Food Station!");
		Ant ant = (Ant)gameObject.get(0);
		for(GameObject gameObject: gameObject) {
			if(gameObject instanceof FoodStation) {
				if(ant.getLocation() == gameObject.getLocation()) {
					System.out.println("Ant has reached a FoodStation!");
					ant.setFoodLevel(ant.getFoodLevel()+((FoodStation) gameObject).getCapacity());
					((FoodStation) gameObject).setCapacity(0);
					gameObject.setColor(ColorUtil.rgb(100, 250, 0));
					Random random = new Random();
					this.gameObject.add(new FoodStation(10 + random.nextInt(50),new Point(50+random.nextInt(950),10 + 50+random.nextInt(950)), ColorUtil.GREEN));
				}
			}
		}
			
	}
		
	
	//Notifies the player that they have collided with a Spider
	//when this happens, the Ant's health is reduced by 1 and speed is updated when setting health level
	public void spiderCollision() {
		System.out.println("You have collided with a Spider!");
		Ant ant = (Ant)gameObject.get(0);
		for(GameObject gameObject: gameObject) {
			if(gameObject instanceof Spider) {
				if(ant.getLocation() == gameObject.getLocation()) {
					System.out.println("Spider has hit the player");
					ant.setHealthLevel(ant.getHealthLevel()-1);
					ant.setColor(ColorUtil.rgb(255, 255, ColorUtil.blue(ant.getColor()+50)));
					
				}
			}
		}
	}
	
	
	//tell the game world that the "game clock" has ticked
	//when the game has ticked it needs to do these things
	//1. Spiders update their heading as indicated above
	//2. all movable objects are told to update their positions according to their current heading and speed
	//3. the ant's food level is reduced by the amount indicated by its foodConsumptinRate
	//4. the elapsed time "game clock" is incremented by one (the game clock in this assignment is simply a variable which increments by one with each tick)
	public void tick() {
		this.clock += 1;
		Ant ant = (Ant)gameObject.get(0);
		
		//when player loses 3 lives new GameWorld is initialized
		if (this.lives <= 0) {
			System.out.println("Game over, you failed!");
			new Game();
		}
		//if player reaches last flag, they have won the game
		if(ant.getLastFlagReached() == 4) {
			System.out.println("Game over, you win!");
			System.out.println("Total time: " + this.clock);
		}
			
		//Spider adds or subtracts small random values (e.g. 5) degrees to heading as they move
		//also handles if Spider hits side of world
		for(GameObject gameObject: gameObject) {
			if(gameObject instanceof Spider) {
				((Spider) gameObject).setHeading(new Random().nextInt(10) + -10);
				if(gameObject.getLocation().getX() == 0) {
					((Spider) gameObject).setHeading(0);
				}else if(gameObject.getLocation().getX() == 1000){
					((Spider) gameObject).setHeading(new Random().nextInt(270) + 90);
				}else if(gameObject.getLocation().getY() == 0){
					((Spider) gameObject).setHeading(new Random().nextInt(179) + 1);
				}else if(gameObject.getLocation().getY() == 1000){
					((Spider) gameObject).setHeading(new Random().nextInt(181) + 359);
				}
			}
		}
		
		//movable objects are told to update their positions
		for(GameObject gameObject: gameObject) {
			if(gameObject instanceof Ant) {
				((Ant) gameObject).move();
			}
			if(gameObject instanceof Spider) {
				((Spider) gameObject).move();
			}
		}
		
		//ant's food level is reduced by the amount indicated by its foodConsumptionRate
		ant.setFoodLevel(ant.getFoodLevel()-ant.getFoodConsumptionRate());
		
	}
		
		
	//output lines of text on the console describing the current game/ant state values. The display should include
	//1. number of lives left
	//2. the current clock value
	//3. the highest flag number the ant has reached sequentially so far
	//4. the ant's current food level
	//5. ant's health level
	public void display() {
		Ant ant = (Ant)gameObject.get(0);
		//number of lives left
		System.out.println("Lives left: " + this.lives);
		//current clock value
		System.out.println("Game clock: " +this.clock);
		//highest flag reached by ant
		System.out.println("Highest Flag Reached: " + ant.getLastFlagReached());
		//ant's current food level
		System.out.println("Ant's Food Level: " + ant.getFoodLevel());
		//ant's health level
		System.out.println("Ant's Health Level: " + ant.getHealthLevel());
	}  

		
	//tell the game world to output a "map" showing the current world
	public void map() {
		for(GameObject gameObject: gameObject) {
			if(gameObject instanceof Ant) {
				System.out.println("Ant: loc = " +gameObject.getLocation().getX() + ", " + gameObject.getLocation().getY() +
						" color = [" + ColorUtil.red(gameObject.getColor()) + "," + ColorUtil.green(gameObject.getColor()) +","+ ColorUtil.blue(gameObject.getColor())
						+"] heading=" + ((Ant)gameObject).getHeading() + " speed=" + ((Ant)gameObject).getSpeed() + " size="+ ((Ant)gameObject).getSize()+
						" maxSpeed=" + ((Ant)gameObject).getMaximumSpeed() + " foodConsumptionRate=" + ((Ant)gameObject).getFoodConsumptionRate());
			}
			if(gameObject instanceof Flag) {
				System.out.println("Flag: loc = " +gameObject.getLocation().getX() + ", " + gameObject.getLocation().getY() +
						" color = [" + ColorUtil.red(gameObject.getColor()) + "," + ColorUtil.green(gameObject.getColor()) +","+ ColorUtil.blue(gameObject.getColor())
						+"]" + " size="+ gameObject.getSize() + " seqNum=" + ((Flag)gameObject).getSequenceNumber());
			}
			if(gameObject instanceof Spider) {
				System.out.println("Spider: loc = " +gameObject.getLocation().getX() + ", " + gameObject.getLocation().getY() +
						" color = [" + ColorUtil.red(gameObject.getColor()) + "," + ColorUtil.green(gameObject.getColor()) +","+ ColorUtil.blue(gameObject.getColor())
						+"] heading=" + ((Spider)gameObject).getHeading() + " speed=" + ((Spider)gameObject).getSpeed() + " size="+ ((Spider)gameObject).getSize());
			}
			if(gameObject instanceof FoodStation) {
				System.out.println("FoodStation: loc = " +gameObject.getLocation().getX() + ", " + gameObject.getLocation().getY() +
						" color = [" + ColorUtil.red(gameObject.getColor()) + "," + ColorUtil.green(gameObject.getColor()) +","+ ColorUtil.blue(gameObject.getColor())
						+"]" + " size="+ gameObject.getSize() + " capacity=" + ((FoodStation)gameObject).getCapacity());
			}
		}
	}
		
		
	//exits the game
	public void exit() {
		System.exit(0);
	}	
	
}

		
		
		
		
		
		
		
		
		
		
		
		
		
		

