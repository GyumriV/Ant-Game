package com.mycompany.hi;
import com.codename1.charts.models.Point;


public class Flag extends Fixed{
	
	private int sequenceNumber;
	
	public Flag(int size,Point location,int color, int sequenceNumber) {
		super(size,location,color);
		this.sequenceNumber = sequenceNumber;
	}
	//getter
	public int getSequenceNumber() {
		return this.sequenceNumber;
	}
}
