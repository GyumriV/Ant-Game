package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Left extends Command{
	GameWorld gw;
	
	Left(GameWorld gw){
		super("Left");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(gw.getPaused()) {
			this.setEnabled(false);
			System.out.println("Cannot steer left when paused");
		}else {
			this.setEnabled(true);
			gw.left();
		}
	}
}
