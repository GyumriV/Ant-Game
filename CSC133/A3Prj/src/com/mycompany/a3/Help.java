package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Help extends Command{
private GameWorld gw;
	
	Help(GameWorld gw){
		super("Help");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.help();
	}
}
