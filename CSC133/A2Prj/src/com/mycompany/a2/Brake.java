package com.mycompany.a2;

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
		gw.brake();
	}
	
}
