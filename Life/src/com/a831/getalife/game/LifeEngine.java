package com.a831.getalife.game;

public interface LifeEngine {
	
	public void initializeRandom(int maxX, int maxY);

	public boolean isCellAlive(int x, int y);
	
	public void toggleCell(int x, int y);
	
	public void iterate();
}
