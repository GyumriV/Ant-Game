package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class About extends Command{

private GameWorld gw;
	
	About(GameWorld gw){
		super("About");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.aboutInfo();
	}
	
}
