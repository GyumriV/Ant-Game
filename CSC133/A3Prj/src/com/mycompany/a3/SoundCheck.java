package com.mycompany.a3;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;


public class SoundCheck extends Command{

	private GameWorld gw;
	private CheckBox checkBox;
	private Toolbar myToolbar;
	
	
	
	
	SoundCheck(GameWorld gw, CheckBox checkBox, Toolbar myToolbar){
		super("Sound");
		this.gw = gw;
		this.checkBox = checkBox;
		this.myToolbar = myToolbar;
	}
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(checkBox.isSelected()) {
			gw.setSound(true);
		}else {
			gw.setSound(false);
		}
		myToolbar.closeSideMenu();
	}
	
}
