//manages the flow of control in the game ("a controller")
//a controller enforces rules such as what actions a player may take and what happens as a result
package com.mycompany.hi;
import com.codename1.ui.Form;		
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;



public class Game extends Form{				//abstract?	
	
	private GameWorld gw;
	private boolean flagX = false;
	public Game(){
		gw = new GameWorld();
		gw.init();
		play();
	}
	
	private void play() {
		
		Label myLabel = new Label("Enter a Coommand:");
		this.addComponent(myLabel);
		final TextField myTextField=new TextField();
		this.addComponent(myTextField);
		this.show();
		
		myTextField.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent evt) {
			//boolean flagX = false;	
			String sCommand=myTextField.getText().toString();
			myTextField.clear();
			if(sCommand.length() != 0)
				switch (sCommand.charAt(0)) {
				case 'a':
					if(flagX == false) {
						gw.accelerate();
					}else {
						System.out.println("you must enter either 'y' for Yes or 'n' for No");
						break;
					}
					break;
				case 'b':
					if(flagX == false) {
						gw.brake();
					}else {
						System.out.println("you must enter either 'y' for Yes or 'n' for No");
						break;
					}
					break;
				case 'l':
					if(flagX == false) {
						gw.left();
					}else {
						System.out.println("you must enter either 'y' for Yes or 'n' for No");
						break;
					}
					break;
				case 'r':
					if(flagX == false) {
						gw.right();
					}else {
						System.out.println("you must enter either 'y' for Yes or 'n' for No");
						break;
					}
					break;
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					if(flagX == false) {
						gw.flagCheck((int)sCommand.charAt(0));
					}else {
						System.out.println("you must enter either 'y' for Yes or 'n' for No");
						break;
					}
					break;
				case 'f':
					if(flagX == false) {
						gw.food();
					}else {
						System.out.println("you must enter either 'y' for Yes or 'n' for No");
						break;
					}
					break;
				case 'g':
					if(flagX == false) {
						gw.spiderCollision();
					}else {
						System.out.println("you must enter either 'y' for Yes or 'n' for No");
						break;
					}
					break;
				case 't':
					if(flagX == false) {
						gw.tick();
					}else {
						System.out.println("you must enter either 'y' for Yes or 'n' for No");
						break;
					}
					break;
				case 'd':
					if(flagX == false) {
						gw.display();
					}else {
						System.out.println("you must enter either 'y' for Yes or 'n' for No");
						break;
					}
					break;
				case 'm':
					if(flagX == false) {
						gw.map();
					}else {
						System.out.println("you must enter either 'y' for Yes or 'n' for No");
						break;
					}
					break;
				case 'x':
					System.out.println("Do you want to exit game? Enter 'y' for Yes or 'n' for No."); 
					flagX = true;	
					break;
				case 'y':
					if(flagX == true) {
						System.out.println("exiting game");
						flagX = false;
						gw.exit();
					}
					break;
				case 'n':
					if(flagX == true) {
						System.out.println("you have selected 'no'.");
						flagX = false;
					}
					break;
							
				default:
					if (flagX == true) {
						System.out.println("you must enter either 'y' for Yes or 'n' for No");
						break;
					}
					System.out.println("you did not enter a valid command");
					break;
				} //switch
			} //actionPerformed
		} //new ActionListener()
		); //addActionListener
		
		
	}
}
	
	
					
	

