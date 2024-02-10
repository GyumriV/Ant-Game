# **Ant Game**

## **Description**

This is a mobile game that I have developed for my CSC 133 class (Object Oriented Programming - Graphics) at Sac State.
In this game you are controlling an Ant with the objective of reaching all flag stations in order while avoiding spiders and starvation.
The ant will have a health bar, number of lives, food level and speed. If you collide with a spider, your health will be reduced.
For each game tick, your food level will go down so you need to get to all the flags to avoid starvation.
You have three lives. If your health or food level reach 0 then you will lose a life and have to start over. Losing 3 lives will be game over.

If you would like to play the final product, run A3Prj. A1Prj and A2Prj are previous versions in which I will describe below. 

## **Requirements**
- Java SE JDK Version 17
- Eclipse IDE for Java Developers
- Codename One(CN1) plugin
- JavaFX SDK 11.0.2 (only for A3Prj)

## **How to run Ant Game** <br><br>
After you have successfully installed the requirements, open the A3Prj folder in the Eclipse IDE.
At the left panel you will see the list of files for the project. 
Near the bottom of the list, you will see a file named "Simulator_A1Prj.launch".
Right click this files, hover over "run as" and select "Simulator_A1Prj"
 - You can use this same method to run A1Prj and A2Prj as well.

## **Troubleshooting** <br><br>
If the sound is not playing:
	- make sure that you have downloaded the correct version of JavaFX (11.0.2)
	- set PATH_TO_JAVAFX_LIB system enviornment variable to the JavaFX/lib folder in you Java directory
	- created a JavaFX user library by selecting Window > Preferences > Java > Build Path > User Libraries > New
		- and name this user library as "JavaFX11"
	Adding  the JavaFX11 library to the project.
		- right click on A3Prj > Build Path > Configure Build Path
		- go to the Library tab > Add Library > User Library > Next > check JavaFX11 > Finish > Apply and Close
		
If the game does not automatically play when you run the program: <br> <br>
	- click on the "pause" button at the bottom of the display twice to fix this issue.

### **A1Prj** <br> <br>
In the first assignment I have created the structure of my game such as:
	GameWorld: initializes the game, creates the Game Objects and houses the methods used by Game Objects.
	Ant and Spider which are Movable Game Objects. Flag and FoodStations which are fixed Game Objects.
<br>
When you run A1Prj, a screen resembling a mobile device will pop up displaying a text "Enter a Command". 
Next to this will be an empty textbox where a user can enter one of the commands listed below, executing the command and displaying the information in the Eclipse console IDE.
<br> <br>
Commands:<br>
- **a :** 'accelerate' increases the speed of the Ant
- **b :** 'brake' decreases the speed of the Ant
- **l :** 'left' steers the Ant to the left
- **r :**'right' steers the Ant to the right
- **a number 1 - 9:** 'flagCheck' will check if Ant has reached next Flag
- **f :** 'foodStation' handles collission with a Food Station
- **g :** 'spiderCollission' handles collission with a Spider
- **t :** 'tick' handles operations for every game second
- **d :** 'display' shows gameObject information such as heatlh and location in the Eclipse Console
	
### **A2Prj** <br> <br>
In this assignment I have implemented a GUI so that the user is able to see Ant information clearly while being able to execute commands by simplying pressing the corresponding keys or by clicking on the buttons.
Since there is a GUI now, there is no need for the flagCheck or Display Command as they can be done on screen.
<br>
To get a list of commands in the application, click on the "Help" button on the top right corner of the display.

### **A3Prj** <br> <br>
This is the final version of the project. 
I have added visual representation for my game Objects as well as enabled sounds.
The user can now pause the game by clicking on the button located at the bottom of the screen.
User is now able to hear background music, as well as sound effects when Ant collides with other Game Objects.
The sound setting is accessable by clicking on the tool bar located at the top left of the display.
