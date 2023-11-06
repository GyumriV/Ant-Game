//holds collection of game objects and other state variables
//this is the "Data Model"
package com.mycompany.a3;

import java.util.Observable;
import java.util.Random;
import java.util.Vector;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

//make a method to get the GameObject collection?

public class GameWorld extends Observable{
	private GameObjectCollection gameObjects;
	private int clock = 0;
	private int lives = 3;
	private Random random = new Random();
	private boolean sound = false;
	
	 Vector<GameObject> obj1 = new Vector<>();
	 Vector<GameObject> obj2 = new Vector<>(); 
	
	private int mapHeight;
	private int mapWidth;
	
	private boolean isPaused = false;
	
	private BGSound bgSound;
	
	
	private Sound spiderSound;
	private Sound flagSound;
	private Sound foodSound;
	
	//sets the initial state of the game
	public void init() {
		gameObjects = new GameObjectCollection();
		//initializing game objects
		gameObjects.add(Ant.getAnt());
		gameObjects.add(new Flag(100,new Point(40,40),ColorUtil.BLUE,1,this));
		gameObjects.add(new Flag(100,new Point(253,478),ColorUtil.BLUE,2,this));
		gameObjects.add(new Flag(100,new Point(393,554),ColorUtil.BLUE,3,this));
		gameObjects.add(new Flag(100,new Point(792,677),ColorUtil.BLUE,4,this));
		gameObjects.add(new FoodStation(70 + random.nextInt(100),new Point(50+random.nextInt(950),10 + 50+random.nextInt(950)), ColorUtil.GREEN,this));
		gameObjects.add(new Spider(random.nextInt(100) + 50,new Point(50+random.nextInt(950),10 + 50+random.nextInt(950)), ColorUtil.BLACK,this));
		gameObjects.add(new Spider(random.nextInt(100) + 50,new Point(50+random.nextInt(950),10 + 50+random.nextInt(950)), ColorUtil.BLACK,this));	
	
		
	}
	
	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}
	
	public boolean getPaused() {
		return this.isPaused;
	}
	
	
	public void setMapWidth(int width) {
		
		this.mapWidth = width;
	}
	
	public int getMapWidth() {
		
		return this.mapWidth;
	}
	
	public void setMapHeight(int height) {
		
		this.mapHeight = height;
	}
	
	public int getMapHeight() {
		return this.mapHeight;
	}
	
	public void createSounds() {
		
		spiderSound = new Sound("spider.wav");
		flagSound = new Sound("bellplate-corner2.mp3");
		foodSound = new Sound("snack-crunch3.wav");
		bgSound = new BGSound("hqBackground2.mp3");
		
	}
	
	public void bgSoundOff() {
		if(this.getSound()==false) {
			bgSound.pause();
		}
	}
	
	public GameObjectCollection getGameObjects() {
		return gameObjects;
	}
	
	//Help - shows list of commands user can input to perform actions
	public void help() {	
		String listCommands = "Here is a list of available commands\n"
				+ "Accelerate: 'a'\n  Brake: 'b'\n Turn Left: l\n Turn Right: 'r'\n Food Station Collision: 'f'\n "
				+ "Spider Collission 'g'\n Tick: 't'\n";
		
		Dialog.show("Help", listCommands, "Ok", null);	
	}
	//Accelerate
	public void accelerate() {
	    System.out.println("Accelerate!");
	    IIterator iterator = gameObjects.getIterator();
	    while(iterator.hasNext()) {
	        GameObject gameObject = iterator.getNext();
	        if(gameObject instanceof Ant) {
	            // Add 5 to the ant's current speed
	            Ant ant = (Ant)gameObject;
	            ant.setSpeed(ant.getSpeed() + 5);
	            // Ant's speed cannot exceed its maximumSpeed
	            if (ant.getSpeed() > ant.getMaximumSpeed()) {
	                ant.setSpeed(ant.getMaximumSpeed());
	            } 
	        }
	    }
	    //Notify Observers
	    setChanged();
		notifyObservers(this);
	}

	//About
	public void aboutInfo() {
		Dialog.show("About", "Name: Vardan Vardanyan\n Class: CSC 133", "Ok", null);
	}
	
	//Get clock value
	public int getClock() {
		return this.clock;
	}
	//Return lives to ScoreView
	public int getLives() {
		return this.lives;
	}
	//Return last flag reached to ScoreView
	public int getLastFlagScore() {
		IIterator iterator = gameObjects.getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = iterator.getNext();
			if(gameObject instanceof Ant) {
				return ((Ant) gameObject).getLastFlagReached();
			}
		}
		return -1;
	}
	//Return health level to ScoreView
	public int getHealthScore() {
		IIterator iterator = gameObjects.getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = iterator.getNext();
			if(gameObject instanceof Ant) {
				return ((Ant) gameObject).getHealthLevel();
			}
		}
		return -1;
	}
	//Return food level to ScoreView
	public int getFoodScore() {
		IIterator iterator = gameObjects.getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = iterator.getNext();
			if(gameObject instanceof Ant) {
				return ((Ant) gameObject).getFoodLevel();
			}
		}
		return -1;
	}
	//Set sound	
	public void setSound(boolean soundCheck) {
		this.sound = soundCheck;
		setChanged();
		notifyObservers(this);
	}
	
	//Return sound
	public boolean getSound() {
		return this.sound;
	}
	
	//Reduces the speed of the Ant
	public void brake() {
		System.out.println("brake is applied");
		IIterator iterator = gameObjects.getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = iterator.getNext();
			if(gameObject instanceof Ant) {
				if(((Ant) gameObject).getSpeed()>0) {
					((Ant) gameObject).setSpeed(((Ant) gameObject).getSpeed()-5);
				}else {
					((Ant) gameObject).setSpeed(0);
				}
			}
		}
		//Notify Observers
		setChanged();
		notifyObservers(this);
	}
	
	//Change Ant's heading by 5 degrees to the left (in the negative direction on the compass)
	public void left() {
		System.out.println("left");
		IIterator iterator = gameObjects.getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = iterator.getNext();
			if(gameObject instanceof Ant) {
				((Ant) gameObject).steer(-20);
			}
		}
		//Notify Observers
		setChanged();
		notifyObservers(this);
	} 
	
	//Change Ant's heading by 5 degrees to the right (in the positive direction on the compass)
	void right() {
		System.out.println("right");
		IIterator iterator = gameObjects.getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = iterator.getNext();
			if(gameObject instanceof Ant) {
				((Ant) gameObject).steer(20);
			}
		}
		//Notify Observers
		setChanged();		
		notifyObservers(this);
	}
	
	//Check to see whether the number x is exactly one greater than the flag indicated by lastFlagReached field of ant
	//If it is, updated lastFlagReached field of the ant by increasing it by one
	public void flagCheck(int x) {
		System.out.println("Flag Check");
		IIterator iterator = gameObjects.getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = iterator.getNext();
			if(gameObject instanceof Ant) {
				if(((Ant) gameObject).getLastFlagReached()+1 == x) {
					System.out.println("Reached next flag.");
					((Ant) gameObject).setLastFlagReached(x);
				}
			}
		}
		//Notify Observers
		setChanged();						
		notifyObservers(this);
	}
	
	//Notifies player when they have collided with a Food Station
	//When this occurs, the Ant's food level is increased by the capacity of the FoodStation
	//Afterwards, the FoodStation color is faded and another is created with a random location and size
	public void food() {	//why isnt food working?
		System.out.println("You have reached a Food Station!");
		//Amount to fade foodStation
		int fadeAmount = 180;
		IIterator iterator = gameObjects.getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = iterator.getNext();
			if(gameObject instanceof Ant) {
				GameObject foodObject = iterator.getNext();
				if(foodObject instanceof FoodStation) {
					((Ant) gameObject).setHealthLevel(((Ant) gameObject).getHealthLevel() + ((FoodStation)foodObject).getCapacity());
					((FoodStation)foodObject).setCapacity(0);
					((FoodStation) foodObject).setColor(ColorUtil.argb(fadeAmount, 0, 0, 255));
					gameObjects.add(new FoodStation(10 + random.nextInt(50),new Point(50+random.nextInt(950),10 + 50+random.nextInt(950)), ColorUtil.GREEN,this));
				}
			}
		}
		
		setChanged();
		notifyObservers(this);	
	}
		
	//Notifies the player that they have collided with a Spider
	//when this happens, the Ant's health is reduced by 1 and speed is updated when setting health level
	public void spiderCollision() {
		//Value for fading ant
		int fadeAmount = 180;
	    System.out.println("You have collided with a Spider!");
	    
	    IIterator iterator = gameObjects.getIterator();
	    while(iterator.hasNext()) {
	    	GameObject gameObject = iterator.getNext();
	    	if(gameObject instanceof Ant) {
	    		((Ant) gameObject).setHealthLevel(((Ant) gameObject).getHealthLevel() - 2);
	    		fadeAmount += 10;
                ((Ant) gameObject).setColor(ColorUtil.argb(fadeAmount, 255, 255, 0));
	    	}
	    }

	    setChanged();
		notifyObservers(this);
	}
	
	//tell the game world that the "game clock" has ticked
	//when the game has ticked it needs to do these things
	//1. Spiders update their heading
	//2. all movable objects are told to update their positions according to their current heading and speed
	//3. the ant's food level is reduced by the amount indicated by its foodConsumptinRate
	//4. the elapsed time "game clock" is incremented by one
	public void tick() {
	   // System.out.println("Tick");
	    IIterator iterator = gameObjects.getIterator();
	    // Increase clock by 1 every tick
	    this.clock++;
	    // When player loses 3 lives new game initiated
	    if (this.lives <= 0) {
	        System.out.println("Game over, you failed!");
	        GameObject gameObject = iterator.getNext();
	        while(iterator.hasNext()) {
	        	if(gameObject instanceof Ant) {
	        		((Ant) gameObject).removeAnt();
	        		break;
	        	}
	        	break;
	        }
	        new Game();
	    } 
	    
	    while (iterator.hasNext()) {
	        GameObject gameObject = iterator.getNext();
	      
	        if (gameObject instanceof Ant) {
	            if (((Ant) gameObject).getLastFlagReached() == 4) {
	                System.out.println("Game over, you win!");
	                System.out.println("Total time: " + this.clock);
	                ((Ant) gameObject).removeAnt();
	                this.init();
	                break;
	                
	            }
	            
	            if (((Ant) gameObject).getHealthLevel() <= 0) {
	                System.out.println("You have lost a life");
	                this.lives -= 1;
	                ((Ant) gameObject).removeAnt();
	                this.init();
	                break;
	                
	            }
	            
	            // Tells Ant to move
	            ((Ant) gameObject).move(20, getMapHeight(), getMapWidth());	//check
	            // Ant's food level is reduced by the amount indicated by its foodConsumptionRate   
	            ((Ant) gameObject).setFoodLevel(((Ant) gameObject).getFoodLevel() - ((Ant) gameObject).getFoodConsumptionRate());
	            //If Ant's food level hits 0, then set food level to 0 and set speed to 0 and reduce a life
	            if(((Ant) gameObject).getFoodLevel() < 0) {
	            	((Ant) gameObject).setFoodLevel(0);
	            	((Ant) gameObject).setSpeed(0);
	            	this.lives--;
	            	((Ant) gameObject).removeAnt();
	            	this.init();
	            }
	        } else if (gameObject instanceof Spider) {
	            // Spider adds or subtracts small random values (e.g. 5) degrees to heading as they move
	            ((Spider) gameObject).setHeading( ((Spider) gameObject).getHeading() + new Random().nextInt(10) + -5);
	            // Tells spider to move
	            ((Spider) gameObject).move(20, getMapHeight(), getMapWidth());	//pass clock
	        }
	    }
	   
	    
	    //checks for collisions and handles them
	    IIterator iter = gameObjects.getIterator();
	    while(iter.hasNext()) {
	    	GameObject curObj = iter.getNext();
	    	IIterator iter2 = gameObjects.getIterator();
	    	while(iter2.hasNext()) {
	    		GameObject otherObj = iter2.getNext();
	    		if(curObj != otherObj) {
	    			if(curObj.collidesWith(otherObj)) {
	    				if(curObj instanceof Ant && otherObj instanceof Spider) {
	    					if(obj1.contains(otherObj) && obj2.contains(curObj)) {	
	    						continue;
	    					}else {
	    						if(this.getSound() == true) {
	    							spiderSound.play();
	    						}
	    						otherObj.handleCollision(curObj);
		    					obj1.add(otherObj);
		    					obj2.add(curObj);
	    					}
	    				}else if(curObj instanceof Ant && otherObj instanceof Flag) {
	    					if(obj1.contains(otherObj) && obj2.contains(curObj)) {
	    						continue;
	    					}else {
	    						if(this.getSound() == true) {
	    							flagSound.play();
	    						}
	    						otherObj.handleCollision(curObj);
		    					obj1.add(otherObj);
		    					obj2.add(curObj);
	    					}
	    				}else if(curObj instanceof Ant && otherObj instanceof FoodStation) {
	    					if(obj1.contains(otherObj) && obj2.contains(curObj)) {
	    						continue;
	    					}else {
	    						if(this.getSound() == true) {
	    							foodSound.play();
	    						}
	    						otherObj.handleCollision(curObj);
		    					obj1.add(otherObj);
		    					obj2.add(curObj);
	    					}
	    				}
	    			}
	    		} 
	    	}
	    }
	    
	    if(this.getSound()==true) {
	    	bgSound.play();
	    }else {
	    	bgSound.pause();
	    }

	    //removing collisions from vectors
	    for(int i = obj1.size() - 1; i >= 0; i--) {
	    	GameObject curObj = obj1.get(i);
	    	boolean checkCollision = false;
	    	for(int j = 0; j < obj2.size();j++) {
	    		GameObject otherObj = obj2.get(j);
	    		if(curObj.collidesWith(otherObj)) {
	    			checkCollision = true;
	    			break;
	    		}
	    	}
	    	if(!checkCollision) {
	    		obj1.remove(i);
	    	}
	    }
	    
	    for(int i = obj2.size() - 1; i >= 0; i--) {
	    	GameObject otherObj = obj2.get(i);
	    	boolean checkCollision = false;
	    	for(int j = 0; j < obj1.size();j++) {
	    		GameObject curObj = obj1.get(j);
	    		if(curObj.collidesWith(otherObj)) {
	    			checkCollision = true;
	    			break;
	    		}
	    	}
	    	
	    	if(!checkCollision) {
	    		obj2.remove(i);
	    	}
	    }

	    setChanged();
	    notifyObservers(this);
	}
			
	//Output lines of text on the console describing the current game/ant state values.
	public void display() {
		System.out.println("Display");
		//all display labels are updated in ScoreView. I commented this out from a1.
		/*
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
		*/
	}  
	
	//tell the game world to output a "map" showing the current world
	public void map() {
		System.out.println("Map");
		
		IIterator iterator = gameObjects.getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = iterator.getNext();
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
			
	//Exits the game
	public void exit() {
		Boolean bOk = Dialog.show("Confirm quit", "Are you sure you want to quit?", "Ok", "Cancel");
		if (bOk) {
			Display.getInstance().exitApplication();
		}
	}	

	
}