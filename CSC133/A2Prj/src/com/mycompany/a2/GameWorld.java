//holds collection of game objects and other state variables
//this is the "Data Model"
package com.mycompany.a2;

import java.util.Observable;
import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.TextField;

public class GameWorld extends Observable{
	private GameObjectCollection gameObjects;
	private int clock = 0;
	private int lives = 3;
	private Random random = new Random();
	private boolean sound = false;
	
	//sets the initial state of the game
	public void init() {
		gameObjects = new GameObjectCollection();
		//initializing game objects
		gameObjects.add(Ant.getAnt());
		gameObjects.add(new Flag(10,new Point(40,40),ColorUtil.BLUE,1));
		gameObjects.add(new Flag(10,new Point(253,478),ColorUtil.BLUE,2));
		gameObjects.add(new Flag(10,new Point(393,554),ColorUtil.BLUE,3));
		gameObjects.add(new Flag(10,new Point(792,677),ColorUtil.BLUE,4));
		gameObjects.add(new FoodStation(10 + random.nextInt(50),new Point(50+random.nextInt(950),10 + 50+random.nextInt(950)), ColorUtil.GREEN));
		gameObjects.add(new Spider(random.nextInt(50) + 10,new Point(50+random.nextInt(950),10 + 50+random.nextInt(950)), ColorUtil.BLACK));
		gameObjects.add(new Spider(random.nextInt(50) + 10,new Point(50+random.nextInt(950),10 + 50+random.nextInt(950)), ColorUtil.BLACK));	
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
		//Notify Observers
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
				((Ant) gameObject).steer(-5);
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
				((Ant) gameObject).steer(5);
			}
		}
		//Notify Observers
		setChanged();		
		notifyObservers(this);
	}
	
	
	//Check to see whether the number x is exactly one greater than the flag indicated by lastFlagReached field of ant
	//If it is, updated lastFlagReached field of the ant by increasing it by one
	public void flagCheck() {
		System.out.println("Flag Check");
		
		TextField myTF = new TextField();
		Command enter = new Command("Enter");
		Command cancel = new Command("Cancel");
		Command[] cmds = new Command[] {enter,cancel};
		Command c = Dialog.show("Enter a number to see if you are at right flag: ", myTF, cmds);
		//Makes sure the user has entered a numeric value 1-9
		if(c == enter) {
			boolean numCheck = false;
			int x = 0;
			while(!numCheck) {
				try {
					x = Integer.parseInt(myTF.getText());
					if(x < 1 || x > 9) {
						throw new NumberFormatException();
					}
					numCheck = true;
				}catch(NumberFormatException e) {
					myTF.remove();
					Dialog.show("The number must be between 1 and 9 ", myTF, cmds);
				}
			}
			//If Flag is 1 more than the last flag reached by Ant, update last flag reached
			System.out.println("You have collided with a Flag " + myTF.getText());
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
			
		}else {
			System.out.println("Cancel");
		}
		//Notify Observers
		setChanged();						
		notifyObservers(this);
	}
	
	//Notifies player when they have collided with a Food Station
	//When this occurs, the Ant's food level is increased by the capacity of the FoodStation
	//Afterwards, the FoodStation color is faded and another is created with a random location and size
	public void food() {
		System.out.println("You have reached a Food Station!");
		//Amount to fade foodStation
		int fadeAmount = 180;
		IIterator iterator = gameObjects.getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = iterator.getNext();
			if(gameObject instanceof Ant) {
				while(iterator.hasNext()) {
					GameObject foodGameObject = iterator.getNext();
					if(foodGameObject instanceof FoodStation) {
						if(((Ant) gameObject).getLocation() == ((FoodStation) foodGameObject).getLocation()) {
							System.out.println("Ant has reached a FoodStation!");
							((Ant) gameObject).setFoodLevel(((Ant) gameObject).getFoodLevel()+((FoodStation) foodGameObject).getCapacity());
							((FoodStation) foodGameObject).setCapacity(0); 		//set food capacity to 0
							//Fades color of foodStation
							((FoodStation) foodGameObject).setColor(ColorUtil.argb(fadeAmount, 0, 0, 255));
							//Add a new random foodStation
							gameObjects.add(new FoodStation(10 + random.nextInt(50),new Point(50+random.nextInt(950),10 + 50+random.nextInt(950)), ColorUtil.GREEN));
						}	
					}
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
	    IIterator outerIterator = gameObjects.getIterator();
	    while (outerIterator.hasNext()) {
	        GameObject gameObject = outerIterator.getNext();
	        if (gameObject instanceof Ant) {
	            IIterator innerIterator = gameObjects.getIterator();
	            while (innerIterator.hasNext()) {
	                GameObject spiderGameObject = innerIterator.getNext();
	                if (spiderGameObject instanceof Spider) {
	                    if (((Ant) gameObject).getLocation().equals(((Spider) spiderGameObject).getLocation())) {
	                        System.out.println("Spider has hit the player");
	                        //Decreases Ant's health level
	                        ((Ant) gameObject).setHealthLevel(((Ant) gameObject).getHealthLevel() - 1);
	                        //if health is 0 then lose a life and restart game
	                        if(((Ant) gameObject).getHealthLevel() <= 0) {
	                        	this.lives--;
	                        	((Ant) gameObject).removeAnt();
	        	            	this.init();
	                        }
	                        // Fades color of ant, increases fade amount each time it collides with spider
	                        fadeAmount += 10;
	                        ((Ant) gameObject).setColor(ColorUtil.argb(fadeAmount, 255, 255, 0));
	                    }
	                }
	            }
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
	    System.out.println("Tick");
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
	            }
	            // Tells Ant to move
	            ((Ant) gameObject).move();
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
	            // Also handles if Spider hits side of world
	            ((Spider) gameObject).setHeading(new Random().nextInt(10) + -10);
	            if (gameObject.getLocation().getX() == 0) {
	                ((Spider) gameObject).setHeading(0);
	            } else if (gameObject.getLocation().getX() == 1000) {
	                ((Spider) gameObject).setHeading(new Random().nextInt(270) + 90);
	            } else if (gameObject.getLocation().getY() == 0) {
	                ((Spider) gameObject).setHeading(new Random().nextInt(179) + 1);
	            } else if (gameObject.getLocation().getY() == 1000) {
	                ((Spider) gameObject).setHeading(new Random().nextInt(181) + 359);
	            }
	            // Tells spider to move
	            ((Spider) gameObject).move();
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