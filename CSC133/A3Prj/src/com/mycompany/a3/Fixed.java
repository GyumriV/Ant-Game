package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

//all fixed game objects are not allowed to change location once they are created
public abstract class Fixed extends GameObject implements ISelectable {
	 
	private boolean isSelected;
	
	public Fixed(int size,Point location, int color) {
		super(size,location,color);
	}
	 
	@Override
	public void setLocation(Point location) {
		super.setLocation(location);
	}
	
	public void setSelected(boolean b) {
		 this.isSelected = b;
	}
	
	public boolean isSelected() {
			
		 return this.isSelected;
	}
	
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		return false;
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
	}
	 
}
