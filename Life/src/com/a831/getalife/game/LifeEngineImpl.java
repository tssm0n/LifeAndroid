package com.a831.getalife.game;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LifeEngineImpl implements LifeEngine {
	
	private Set<Coordinate> board;

	public LifeEngineImpl() {
		board = new HashSet<Coordinate>();
	}

	@Override
	public boolean isCellAlive(int x, int y) {
		return board.contains(new Coordinate(x,y));
	}

	@Override
	public void toggleCell(int x, int y) {
		Coordinate coord = new Coordinate(x,y);
		if(board.contains(coord)){
			board.remove(coord);
		} else {
			board.add(coord);
		}
		
	}

	@Override
	public void iterate() {
		Set<Coordinate> nextBoard = new HashSet<Coordinate>(board.size());
		
		for(Coordinate coord : board){
			iterateCellAndNeighbors(coord, nextBoard);
		}
		
		board = nextBoard;
		
	}
	
	@Override
	public void initializeRandom(int maxX, int maxY){
		board = new HashSet<Coordinate>(maxX*maxY*3);
		Random rand = new Random(new Date().getTime());
		for(int x = 0; x <= maxX; x++){
			for(int y = 0; y <= maxY; y++){
				if(rand.nextBoolean() && rand.nextBoolean()){
					toggleCell(x, y);
				}
			}
		}
	}

	int countNeighbors(int x, int y) {
		int count = 0;
		for(int xIndex = x-1; xIndex <= x+1; xIndex++){
			for(int yIndex = y-1; yIndex <= y+1; yIndex++){
				if(!(xIndex == x && yIndex == y) && isCellAlive(xIndex, yIndex)){
					count++;
				}
			}
		}
		return count;
	}

	boolean nextCellState(int x, int y) {
		int neighborCount = countNeighbors(x, y);
		if(!isCellAlive(x, y)){
			return neighborCount == 3;
		}
		
		return neighborCount == 2 || neighborCount == 3;
	}

	private void iterateCellAndNeighbors(Coordinate coord,
			Set<Coordinate> nextBoard) {
		int x = coord.getX();
		int y = coord.getY();
		
		for(int xIndex = x-1; xIndex <= x+1; xIndex++){
			for(int yIndex = y-1; yIndex <= y+1; yIndex++){
				Coordinate newCoordinate = new Coordinate(xIndex,yIndex);
				if(!nextBoard.contains(newCoordinate) && nextCellState(xIndex, yIndex)){
					nextBoard.add(newCoordinate);
				}
			}
		}
	}
	
}
