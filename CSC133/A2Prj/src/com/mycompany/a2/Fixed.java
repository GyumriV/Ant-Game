package com.mycompany.a2;

import com.codename1.charts.models.Point;

//all fixed game objects are not allowed to change location once they are created
public abstract class Fixed extends GameObject {
	 public Fixed(int size,Point location, int color) {
		 super(size,location,color);
	 }
	 
	 @Override
	 public void setLocation(Point location) {
		 super.setLocation(location);
	 }
	 
}
