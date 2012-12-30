package com.a831.getalife.game;

public class Coordinate {
	private int x;
	private int y;
	
	public Coordinate(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object object2) {
		if(object2 == null || !(object2 instanceof Coordinate)){
			return false;
		}
		
		Coordinate coord2 = (Coordinate) object2;
		return coord2.getX() == x && coord2.getY() == y;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}


	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
}
