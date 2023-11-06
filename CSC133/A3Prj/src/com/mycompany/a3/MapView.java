package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.plaf.Border;


public class MapView extends Container implements Observer{

	private GameWorld gw;
	
	MapView(GameWorld gw){
		this.getAllStyles().setBorder(Border.createLineBorder(BASELINE, ColorUtil.rgb(255, 0,0)));
		this.gw = gw;
	}

	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Point pCmpRelPrnt = new Point(this.getX(),this.getY());
		IIterator iterator = gw.getGameObjects().getIterator();
		
		while(iterator.hasNext()) {
			GameObject gameObject = iterator.getNext();
			((IDrawable) gameObject).draw(g, pCmpRelPrnt);;
		}	
	}
	
	public void pointerPressed(int x, int y) {
		
		IIterator iterator = gw.getGameObjects().getIterator();
		
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		Point pPtrRelPrnt = new Point(x,y);
		Point pCmpRelPrnt = new Point(getX(), getY());
		while(iterator.hasNext()) {
			GameObject gameObject = iterator.getNext();
			if(gameObject instanceof Ant) {
				continue;
			}else if (gameObject instanceof Spider){
				continue;
			}else {
				if(((Fixed) gameObject).contains(pPtrRelPrnt, pCmpRelPrnt)) {
					((Fixed) gameObject).setSelected(true);
				}else {
					((Fixed) gameObject).setSelected(false);
				}
			}
			
		}
		repaint();
	}
	
	//displays map information 
	public void update(Observable o, Object arg) {
		gw = (GameWorld) arg;
		//gw.map();
		repaint();
	}
	
}
