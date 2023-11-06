//manages the flow of control in the game ("a controller")
//a controller enforces rules such as what actions a player may take and what happens as a result
package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;		
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Toolbar;


public class Game extends Form{	
	
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	
	public Game(){
	    this.setLayout(new BorderLayout());	
		gw = new GameWorld();
		gw.init();
		sv = new ScoreView(gw);
		mv = new MapView();
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
		CheckBox checkBox = new CheckBox("Sound");
		Sound sound = new Sound(this.gw,checkBox,myToolbar);
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
		Accelerate accelerateCommand = new Accelerate(this.gw);
		Button accelButton = new Button(accelerateCommand);
		accelButton.getAllStyles().setBgTransparency(255);
		accelButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		accelButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		accelButton.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		accelButton.addActionListener(accelerateCommand);
		addKeyListener('a',accelerateCommand);
		leftContainer.add(accelButton);
		//Left
		Left leftCommand = new Left(this.gw);
		Button leftButton = new Button(leftCommand);
		leftButton.getAllStyles().setBgTransparency(255);
		leftButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		leftButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		leftButton.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		leftButton.addActionListener(leftCommand);
		addKeyListener('l',leftCommand);
		leftContainer.add(leftButton);
		this.add(BorderLayout.WEST, leftContainer);
		
		//Right Container
		Container rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		rightContainer.getAllStyles().setPadding(Component.TOP, 500);
		rightContainer.getAllStyles().setBorder(Border.createLineBorder(BASELINE, ColorUtil.BLACK));
		//Brake
		Brake brakeCommand = new Brake(this.gw);
		Button brakeButton = new Button(brakeCommand);
		brakeButton.getAllStyles().setBgTransparency(255);
		brakeButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		brakeButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		brakeButton.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		brakeButton.addActionListener(brakeCommand);
		addKeyListener('b',brakeCommand);
		rightContainer.add(brakeButton);
		//Right
		Right rightCommand = new Right(this.gw);
		Button rightButton = new Button(rightCommand);
		rightButton.getAllStyles().setBgTransparency(255);
		rightButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		rightButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		rightButton.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		rightButton.addActionListener(rightCommand);
		addKeyListener('r',rightCommand);
		rightContainer.add(rightButton);
		this.add(BorderLayout.EAST, rightContainer);
		
		//Bottom Container
		Container bottomContainer = new Container(new FlowLayout(Component.CENTER));
		bottomContainer.getAllStyles().setBorder(Border.createLineBorder(BASELINE, ColorUtil.BLACK));
		//Flag Check
		FlagCheck flagCommand = new FlagCheck(this.gw);
		Button flagButton = new Button(flagCommand);
		flagButton.getAllStyles().setBgTransparency(255);
		flagButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		flagButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		flagButton.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		flagButton.addActionListener(flagCommand);
		bottomContainer.add(flagButton);
		//Spider Collision
		SpiderCollision spiderColCommand = new SpiderCollision(this.gw);
		Button spiderColButton = new Button(spiderColCommand);
		spiderColButton.getAllStyles().setBgTransparency(255);
		spiderColButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		spiderColButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		spiderColButton.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		spiderColButton.addActionListener(spiderColCommand);
		addKeyListener('g',spiderColCommand);
		bottomContainer.add(spiderColButton);
		//Food Station Collision
		FoodStationCollision foodCommand = new FoodStationCollision(this.gw);
		Button foodStationButton = new Button(foodCommand);
		foodStationButton.getAllStyles().setBgTransparency(255);
		foodStationButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		foodStationButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		foodStationButton.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		foodStationButton.addActionListener(foodCommand);
		addKeyListener('f', foodCommand);
		bottomContainer.add(foodStationButton);
		//Tick
		Tick tickCommand = new Tick(this.gw);
		Button tickButton = new Button(tickCommand);
		tickButton.getAllStyles().setBgTransparency(255);
		tickButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		tickButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		tickButton.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		tickButton.addActionListener(tickCommand);
		addKeyListener('t',tickCommand);
		bottomContainer.add(tickButton);
		this.add(BorderLayout.SOUTH, bottomContainer);
		
		
		this.show();
	}
}