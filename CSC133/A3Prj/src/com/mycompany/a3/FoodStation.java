package com.mycompany.a3;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;


public class FoodStation extends Fixed{
	//initial capacity is proportional to the size of the food station
	private int capacity;
	private GameWorld gw;
	
	
	FoodStation(int size,Point location,int color, GameWorld gw){
		super(size,location,color);
		this.capacity = size;
		this.gw = gw;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public void setCapacity(int newCapacity) {
		this.capacity = newCapacity;
	} 
	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int iShapeX = (int)this.getLocation().getX();
		int iShapeY = (int)this.getLocation().getY();
		int px = (int)pPtrRelPrnt.getX();
		int py = (int)pPtrRelPrnt.getY();
		int xLoc = (int)pCmpRelPrnt.getX() + iShapeX;
		int yLoc = (int)pCmpRelPrnt.getY() + iShapeY;
		
		if((px >= xLoc) && (px <= xLoc+this.getSize())
			&& (py >= yLoc) && (py <= yLoc + this.getSize())) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		int iShapeX = (int)this.getLocation().getX();
		int iShapeY = (int)this.getLocation().getY();
		int xLoc = (int)pCmpRelPrnt.getX() + iShapeX;
		int yLoc = (int)pCmpRelPrnt.getY() + iShapeY;
		
		int xCenter = xLoc + this.getSize()/2;
		int yCenter = yLoc + this.getSize()/2;
		
		if(this.isSelected() && gw.getPaused() == true) {
			System.out.println("we selected the food station");
			g.drawRect(xLoc, yLoc, this.getSize(), this.getSize());
			g.setColor(ColorUtil.BLACK);
			g.drawString(""+getCapacity(), xCenter, yCenter);
		}else {
			g.fillRect(xLoc, yLoc, this.getSize(), this.getSize());
			g.setColor(ColorUtil.BLACK);
			g.drawString(""+getCapacity(), xCenter, yCenter);
		}
	}
	
	@Override
	public void handleCollision(GameObject otherObject) {
		if(otherObject instanceof Ant) {
			System.out.println("Ant has collided with food station in the game object");
			gw.food();
		}
	}
	
}