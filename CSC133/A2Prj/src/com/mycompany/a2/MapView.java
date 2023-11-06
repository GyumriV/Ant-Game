package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer{

	private GameWorld gw;
	
	MapView(){
		this.getAllStyles().setBorder(Border.createLineBorder(BASELINE, ColorUtil.rgb(255, 0,0)));
	}
	//displays map information
	public void update(Observable o, Object arg) {
		gw = (GameWorld) arg;
		gw.map();
	}
	
}
