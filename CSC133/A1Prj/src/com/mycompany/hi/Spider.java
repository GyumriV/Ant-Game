package com.mycompany.hi;
import java.util.Random;
import com.codename1.charts.models.Point;

public class Spider extends Movable{

	public Spider(int size,Point location,int color) {
		super(size,location,color);
		
		//initial random heading for Spider
		Random random = new Random();
		int randHead = random.nextInt(539) + 0;
		this.setHeading(randHead);
	}
	
}
