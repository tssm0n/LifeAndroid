package com.a831.getalife.game;

import android.test.AndroidTestCase;

public class LifeEngineImplTest extends AndroidTestCase {

	private LifeEngineImpl engine;
	
	protected void setUp() throws Exception {
		super.setUp();
		engine = new LifeEngineImpl();
	}
	
	public void testToggleAndget(){
		engine.toggleCell(1, 1);
		assertTrue(engine.isCellAlive(1, 1));
		
		engine.toggleCell(1,1);
		assertFalse(engine.isCellAlive(1, 1));
	}

	public void testCountNeighbors(){
		engine.toggleCell(2,2);
		engine.toggleCell(1,1);
		engine.toggleCell(2,3);
		
		int count = engine.countNeighbors(2, 2);
		assertEquals(2, count);
		
		engine.toggleCell(2,3);
		count = engine.countNeighbors(2, 2);
		assertEquals(1, count);
		
		engine.toggleCell(2,3);
		engine.toggleCell(2,4);
		count = engine.countNeighbors(2, 2);
		assertEquals(2, count);
		
		engine.toggleCell(2,1);
		engine.toggleCell(3,3);
		count = engine.countNeighbors(2, 2);
		assertEquals(4, count);
	}
	
	public void testDeath(){
		assertFalse(engine.nextCellState(2,2));
		
		engine.toggleCell(2,2);
		
		assertFalse(engine.nextCellState(2,2));
		
		engine.toggleCell(2,3);
		
		assertFalse(engine.nextCellState(2,2));
		
		engine.toggleCell(2,1);
		
		assertTrue(engine.nextCellState(2,2));
		
		engine.toggleCell(1,1);
		
		assertTrue(engine.nextCellState(2,2));
		
		engine.toggleCell(1, 2);
		
		assertFalse(engine.nextCellState(2,2));
		
		engine.toggleCell(1, 3);
		
		assertFalse(engine.nextCellState(2,2));
	}
	
	public void testLife(){
		assertFalse(engine.nextCellState(2,2));
		
		engine.toggleCell(2,1);
		
		assertFalse(engine.nextCellState(2,2));
		
		engine.toggleCell(2,3);
		
		assertFalse(engine.nextCellState(2,2));
		
		engine.toggleCell(1,1);
		
		assertTrue(engine.nextCellState(2,2));
		
		engine.toggleCell(1,2);
		
		assertFalse(engine.nextCellState(2,2));
		
		engine.toggleCell(1,3);
		
		assertFalse(engine.nextCellState(2,2));
		
	}
	
	public void testIterate(){
		engine.toggleCell(2,2);
		engine.toggleCell(2,3);
		engine.toggleCell(2,1);
		
		assertFalse(engine.nextCellState(2,3));
		
		engine.iterate();
		
		assertTrue(engine.isCellAlive(2,2));
		assertFalse(engine.isCellAlive(2,3));
		assertTrue(engine.isCellAlive(3,2));
		
	}
}
