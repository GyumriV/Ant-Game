package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Right extends Command{
	private GameWorld gw;
	
	Right(GameWorld gw){
		super("Right");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(gw.getPaused()) {
			this.setEnabled(false);
			System.out.println("cannot steer right when paused");
		}else {
			this.setEnabled(true);
			gw.right();
		}
		
	}
}
