package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Accelerate extends Command{
	private GameWorld gw;
	
	Accelerate(GameWorld gw){
		super("Accelerate");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(gw.getPaused()) {
			this.setEnabled(false);
			System.out.println("cant accelerate when game is paused");
			
		}else {
			this.setEnabled(true);
			gw.accelerate();
		}
	}
}
