package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Flag extends Fixed{
	
	private int sequenceNumber;
	private GameWorld gw;
	
	public Flag(int size,Point location,int color, int sequenceNumber, GameWorld gw) {
		super(size,location,color);
		this.sequenceNumber = sequenceNumber;
		this.gw = gw;
	}
	//getter
	public int getSequenceNumber() {
		return this.sequenceNumber;
	}
	@Override
	public void setColor(int newColor) {
		super.setColor(newColor);
	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int iShapeX = (int)this.getLocation().getX();
		int iShapeY = (int)this.getLocation().getY();
		int px = (int)pPtrRelPrnt.getX();
		int py = (int)pPtrRelPrnt.getY();
		int xLoc = (int)pCmpRelPrnt.getX() + iShapeX;
		int yLoc = (int)pCmpRelPrnt.getY() + iShapeY;
		
		int minX = xLoc - this.getSize()/2;
		int maxX = xLoc + this.getSize()/2;
		
		int minY = yLoc - this.getSize()/2;
		int maxY = yLoc + this.getSize()/2;
		
		if((px >= minX) && (px <= maxX+this.getSize()/2)
			&& (py >= minY) && (py <= maxY + this.getSize()/2)) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		int x = (int) this.getLocation().getX() + (int) pCmpRelPrnt.getX();
		int y = (int) this.getLocation().getY() + (int) pCmpRelPrnt.getY();
		
		int[] xCoor = {x - this.getSize()/2, x, x+this.getSize()/2};
		int[] yCorr = {y - this.getSize()/2, y+this.getSize()/2,y-this.getSize()/2};
		
		if(this.isSelected() && gw.getPaused() == true) {
			System.out.println("we have selected a flag");
			g.drawPolygon(xCoor, yCorr, 3);
			g.setColor(ColorUtil.BLACK);
			
			g.drawString("" + getSequenceNumber(), x, y);
			
		}else {
			g.fillPolygon(xCoor, yCorr, 3);
			g.setColor(ColorUtil.WHITE);
			
			g.drawString("" + getSequenceNumber(), x, y);
		}
	}

	@Override
	public void handleCollision(GameObject otherObject) {
		System.out.println("you have collided with flag in the flag gameobject");
		if(otherObject instanceof Ant) {
			if(this.sequenceNumber == 1) {
				return;
			}else {
				gw.flagCheck(this.sequenceNumber);
			}
		}
		
	}
	
}