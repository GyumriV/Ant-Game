package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ExitGame extends Command{
	
	private GameWorld gw;
	
	ExitGame(GameWorld gw){
		super("Exit Game");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.exit();
	}

}
