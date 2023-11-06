package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class FoodStationCollision extends Command{
private GameWorld gw;
	
	FoodStationCollision(GameWorld gw){
		super("Collide with Food Station");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.food();
	}
}
