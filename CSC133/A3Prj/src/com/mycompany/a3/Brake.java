package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Brake extends Command {
	private GameWorld gw;
	
	Brake(GameWorld gw){
		super("Brake");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(gw.getPaused()) {
			this.setEnabled(false);
			System.out.println("cannot brake when game is paused");
		}else {
			this.setEnabled(true);
			gw.brake();
		}
	}
	
}
