package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class FlagCheck extends Command{
	private GameWorld gw;
	
	FlagCheck(GameWorld gw){
		super("Collide with Flag");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.flagCheck();
	}
}
