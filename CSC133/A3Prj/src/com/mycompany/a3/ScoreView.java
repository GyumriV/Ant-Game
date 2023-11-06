package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;


public class ScoreView extends Container implements Observer{
	private GameWorld gw;
	private Label clockValue;
	private Label livesLeftValue;
	private Label lastFlagValue;
	private Label foodValue;
	private Label healthValue;
	private Label soundValue;
	//ADD LABELS TO THE SCOREVIEW IN THE CONSTRUCTOR
	ScoreView(GameWorld gw){
		this.setLayout(new FlowLayout(Component.CENTER));
		
		Label clockLabel = new Label("Time: ");
		clockValue = new Label(" " + gw.getClock());
		clockLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		clockValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(clockLabel);
		this.add(clockValue);
		//Lives
		Label livesLeftLabel = new Label("Lives Left: ");
		livesLeftValue = new Label(" " + gw.getLives());
		livesLeftLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		livesLeftValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(livesLeftLabel);
		this.add(livesLeftValue);
		//Last Flag Reached
		Label lastFlagLabel = new Label("Last Flag Reached: ");
		lastFlagValue = new Label(" " + gw.getLastFlagScore());
		lastFlagLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		lastFlagValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(lastFlagLabel);
		this.add(lastFlagValue);
		//Food Level
		Label foodLabel = new Label("Food Level: ");
		foodValue = new Label(" " + gw.getFoodScore());
		foodLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		foodValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(foodLabel);
		this.add(foodValue);
		//Health Level
		Label healthLabel = new Label("Health Level: ");
		healthValue = new Label(" " + gw.getHealthScore());
		healthLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		healthValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(healthLabel);
		this.add(healthValue);
		//Sound
		Label soundLabel = new Label("Sound: ");
		soundValue = new Label("OFF");
		soundLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		soundValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(soundLabel);
		this.add(soundValue);
		
	}
	
	public void update(Observable o, Object arg) {
		//code here to update labels from the game/ant state data
		gw = (GameWorld) arg;
		this.clockValue.setText(" " + gw.getClock());
		this.livesLeftValue.setText(" " + gw.getLives());
		this.lastFlagValue.setText(" " + gw.getLastFlagScore());
		this.foodValue.setText(" " + gw.getFoodScore());
		this.healthValue.setText(" " + gw.getHealthScore());
		if(gw.getSound() == true) {
			this.soundValue.setText("ON");
			
		}else {
			this.soundValue.setText("OFF");
		}
		this.revalidate();
	}
}
