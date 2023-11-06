package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;

public class Pause extends Command{
	
	private boolean check = false;
	private Game game;
	
	
	Pause(Label pauseLabel, Game game){
		super(pauseLabel.getText());
		
		this.game = game;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		check = !check;
		if(check) {
			System.out.println("clicked pause");
			game.pauseGame();
			
			
		}else {
			System.out.println("click play");
			game.resumeGame();
			
		}	
	}
}
