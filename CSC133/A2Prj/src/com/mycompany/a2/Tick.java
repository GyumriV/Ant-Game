package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Tick extends Command{

	private GameWorld gw;
	
	Tick(GameWorld gw){
		super("Tick");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.tick();
	}
	
}
