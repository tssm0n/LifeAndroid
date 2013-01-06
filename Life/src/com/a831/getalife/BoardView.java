package com.a831.getalife;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.a831.getalife.game.LifeEngine;
import com.a831.getalife.game.LifeEngineImpl;

public class BoardView extends View {

	private static final int DEFAULT_CELL_PIXEL_SIZE = 20;
	private static final int ZOOM_SIZE = 3;
	private static final long ITERATION_TIME = 1500;
	private static final float BOARD_INITIAL_SIZE_MODIFIER = 1.3F;
	
	private Paint paint;
	private RectF rect;
	private LifeEngine engine;

    private IterationHandler iterationHandler = new IterationHandler();

    private boolean boardInitialized = false;
    private boolean enabled = true;
    
    private int cellPixelSize = DEFAULT_CELL_PIXEL_SIZE;
    
	public BoardView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initialize();
	}

	public BoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
	}

	public BoardView(Context context) {
		super(context);
		initialize();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if(!boardInitialized){
			boardInitialized = true;
			engine.initializeRandom((int)(canvas.getWidth()/(cellPixelSize/BOARD_INITIAL_SIZE_MODIFIER)), 
					(int)(canvas.getHeight()/(cellPixelSize/BOARD_INITIAL_SIZE_MODIFIER)));
		}
		drawBoard(canvas);
	}
	
	

	private void drawBoard(Canvas canvas) {
		for(int x = 0; x < (canvas.getWidth()/cellPixelSize); x++){
			for(int y = 0; y < (canvas.getHeight()/cellPixelSize); y++){
				if(engine.isCellAlive(x, y)){
					drawCell(canvas, x, y);
				}
			}
		}
	}
	
	private void drawCell(Canvas canvas, int x, int y) {
		rect.set(x*cellPixelSize, y*cellPixelSize, 
				(x+1)*cellPixelSize, (y+1)*cellPixelSize);
		canvas.drawRect(rect, paint);
	}

	private void initialize(){
		engine = new LifeEngineImpl();
		
		paint = new Paint();
		paint.setColor(0xff74AC23);
		rect = new RectF();
		
		iterationHandler.sleep(ITERATION_TIME);

	}

	public void iterate() {
		engine.iterate();
	}
	
	
    public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public boolean isEnabled() {
		return enabled;
	}
	
	public void zoom(boolean in){
		if(in){
			cellPixelSize += ZOOM_SIZE;
		} else {
			cellPixelSize -= ZOOM_SIZE;
			if(cellPixelSize < 1){
				cellPixelSize = 1;
			}
		}
		
		invalidate();
	}


	class IterationHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
        	if(BoardView.this.enabled){
	            BoardView.this.invalidate();
	            BoardView.this.iterate();
        	}
    		sleep(ITERATION_TIME);
        }

        public void sleep(long delayMillis) {
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    }
}
