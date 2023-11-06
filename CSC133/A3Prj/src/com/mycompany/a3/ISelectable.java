package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

//all fixed objects are selectable
//selection only allowed in pause mode
//switching to play mode automatically "unselects" the object
public interface ISelectable {
	
	public void setSelected(boolean b);
	public boolean isSelected();
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);
	public void draw(Graphics g, Point pCmpRelPrnt);
	
}
