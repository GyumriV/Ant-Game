package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Position extends Command{
	
	Position() {
		super("Position");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("you clicked on position");
	}
	
}
