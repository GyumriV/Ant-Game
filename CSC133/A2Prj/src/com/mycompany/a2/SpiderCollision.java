package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SpiderCollision extends Command{
private GameWorld gw;
	
	SpiderCollision(GameWorld gw){
		super("Collide with Spider");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.spiderCollision();
	}

}
