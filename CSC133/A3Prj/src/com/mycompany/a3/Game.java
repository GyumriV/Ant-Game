//manages the flow of control in the game ("a controller")
//a controller enforces rules such as what actions a player may take and what happens as a result
package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.Toolbar;


public class Game extends Form implements Runnable{
	
	
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;

	private Button accelButton;
	private Button leftButton;
	private Button brakeButton;
	private Button rightButton;
	private Button positionButton;
	private Button pauseButton;
	private CheckBox checkBox;
	
	private Accelerate accelerateCommand;
	private Left leftCommand;
	private Right rightCommand;
	private Brake brakeCommand;
	private UITimer timer;
	
	
	public Game(){
		//this.show();
		
		this.setLayout(new BorderLayout());	
		gw = new GameWorld();
		gw.init();
		sv = new ScoreView(gw);
		mv = new MapView(gw);	
		gw.addObserver(sv);
		gw.addObserver(mv);
		
		this.add(BorderLayout.NORTH,sv);
		this.add(BorderLayout.CENTER,mv);
		
		//Title
		Toolbar myToolbar = new Toolbar();
		setToolbar(myToolbar);
		myToolbar.setTitle("StartToFinish Game");
		//Accelerate SideMenu
		Accelerate accSideMenu = new Accelerate(this.gw);
		myToolbar.addCommandToLeftSideMenu(accSideMenu);
		//Exit - Allows player to exit game											
		ExitGame quit = new ExitGame(this.gw);
		myToolbar.addCommandToLeftSideMenu(quit);
		//Sound - Allows player to check Sound On or Off
		checkBox = new CheckBox("Sound");
		SoundCheck sound = new SoundCheck(this.gw,checkBox,myToolbar);
		checkBox.setCommand(sound);
		checkBox.getAllStyles().setBgTransparency(240);
		checkBox.getAllStyles().setBgColor(ColorUtil.rgb(141, 159, 180));
		checkBox.getAllStyles().setFgColor(ColorUtil.WHITE);
		myToolbar.addComponentToLeftSideMenu(checkBox);
		//About - Student Name and Course
		About about = new About(this.gw);
		myToolbar.addCommandToLeftSideMenu(about);
		//Help Command - Displays available commands
		Help help = new Help(this.gw);
		myToolbar.addCommandToRightBar(help);
			
		//Left Container
		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		leftContainer.getAllStyles().setPadding(Component.TOP, 500);
		leftContainer.getAllStyles().setBorder(Border.createLineBorder(BASELINE, ColorUtil.BLACK));
		//Accelerate
		accelerateCommand = new Accelerate(this.gw);
		accelButton = new Button(accelerateCommand);
		accelButton.getAllStyles().setBgTransparency(255);
		accelButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		accelButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		accelButton.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		accelButton.addActionListener(accelerateCommand);
		addKeyListener('a',accelerateCommand);
		//style when disabled
		accelButton.getDisabledStyle().setBgColor(ColorUtil.WHITE);
		accelButton.getDisabledStyle().setFgColor(ColorUtil.BLUE);
		accelButton.getDisabledStyle().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		
		leftContainer.add(accelButton);
		//Left
		leftCommand = new Left(this.gw);
		leftButton = new Button(leftCommand);
		leftButton.getAllStyles().setBgTransparency(255);
		leftButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		leftButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		leftButton.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		leftButton.addActionListener(leftCommand);
		addKeyListener('l',leftCommand);
		//style when disabled
		leftButton.getDisabledStyle().setBgColor(ColorUtil.WHITE);
		leftButton.getDisabledStyle().setFgColor(ColorUtil.BLUE);
		leftButton.getDisabledStyle().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		
		leftContainer.add(leftButton);
		this.add(BorderLayout.WEST, leftContainer);
		
		//Right Container
		Container rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		rightContainer.getAllStyles().setPadding(Component.TOP, 500);
		rightContainer.getAllStyles().setBorder(Border.createLineBorder(BASELINE, ColorUtil.BLACK));
		//Brake
		Brake brakeCommand = new Brake(this.gw);
		brakeButton = new Button(brakeCommand);
		brakeButton.getAllStyles().setBgTransparency(255);
		brakeButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		brakeButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		brakeButton.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		brakeButton.addActionListener(brakeCommand);
		//style when disabled
		brakeButton.getDisabledStyle().setBgColor(ColorUtil.WHITE);
		brakeButton.getDisabledStyle().setFgColor(ColorUtil.BLUE);
		brakeButton.getDisabledStyle().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		
		addKeyListener('b',brakeCommand);
		rightContainer.add(brakeButton);
		//Right
		rightCommand = new Right(this.gw);
		rightButton = new Button(rightCommand);
		rightButton.getAllStyles().setBgTransparency(255);
		rightButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		rightButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		rightButton.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		rightButton.addActionListener(rightCommand);
		addKeyListener('r',rightCommand);
		//style when disabled
		rightButton.getDisabledStyle().setBgColor(ColorUtil.WHITE);
		rightButton.getDisabledStyle().setFgColor(ColorUtil.BLUE);
		rightButton.getDisabledStyle().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		rightContainer.add(rightButton);
		this.add(BorderLayout.EAST, rightContainer);
		
		//Bottom Container
		Container bottomContainer = new Container(new FlowLayout(Component.CENTER));
		bottomContainer.getAllStyles().setBorder(Border.createLineBorder(BASELINE, ColorUtil.BLACK));
		
		Position positionCommand = new Position();
		positionButton = new Button(positionCommand);
		positionButton.getAllStyles().setBgTransparency(255);
		positionButton.getAllStyles().setBgColor(ColorUtil.WHITE);
		positionButton.getAllStyles().setFgColor(ColorUtil.BLUE);
		positionButton.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		//style when disabled
		positionButton.getDisabledStyle().setBgColor(ColorUtil.BLUE);
		positionButton.getDisabledStyle().setFgColor(ColorUtil.WHITE);
		positionButton.getDisabledStyle().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		bottomContainer.add(positionButton);
		
		//Pause button
		Label pauseLabel = new Label("Pause");
		Pause pauseCommand = new Pause(pauseLabel,this);
		pauseButton = new Button(pauseCommand);
		pauseButton.getAllStyles().setBgTransparency(255);
		pauseButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		pauseButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		pauseButton.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		bottomContainer.add(pauseButton);
		this.add(BorderLayout.SOUTH, bottomContainer);

		this.show();
		
		gw.init();
		gw.createSounds();
		
		timer = new UITimer(this);
		timer.schedule(20, true, this);
		
		gw.setMapHeight(mv.getHeight() - (sv.getHeight()*2));
		gw.setMapWidth(mv.getWidth() - (sv.getHeight()*4));
	}
	
	
	public void pauseGame() {
		timer.cancel();
		gw.setPaused(true);
		gw.setSound(false);
		gw.bgSoundOff();
		this.accelButton.setEnabled(false);
		this.removeKeyListener('a', accelerateCommand);
		this.leftButton.setEnabled(false);
		this.removeKeyListener('l', leftCommand);
		this.rightButton.setEnabled(false);
		this.removeKeyListener('r', rightCommand);
		this.brakeButton.setEnabled(false);
		this.removeKeyListener('b', brakeCommand);
		this.positionButton.setEnabled(true);
		pauseButton.setText("Play");
	}
	
	public void resumeGame() {
		timer.schedule(20, true, this);
		gw.setPaused(false);
		gw.setSound(true);
		this.accelButton.setEnabled(true);
		this.addKeyListener('a', accelerateCommand);
		this.leftButton.setEnabled(true);
		this.addKeyListener('l', leftCommand);
		this.rightButton.setEnabled(true);
		this.addKeyListener('r', rightCommand);
		this.brakeButton.setEnabled(true);
		this.addKeyListener('b', brakeCommand);
		this.positionButton.setEnabled(false);
		pauseButton.setText("Pause");
	}
	
	public void run() {
		gw.tick();
		
	}
	
}