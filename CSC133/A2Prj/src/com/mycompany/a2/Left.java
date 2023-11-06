package com.mycompany.a2;

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
		gw.left();;
	}
}
