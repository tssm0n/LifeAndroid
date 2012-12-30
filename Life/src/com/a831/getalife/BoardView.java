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

	private static final int CELL_PIXEL_SIZE = 20;
	private static final long ITERATION_TIME = 1500;
	
	private Paint paint;
	private RectF rect;
	private LifeEngine engine;

    private IterationHandler iterationHandler = new IterationHandler();

    private boolean boardInitialized = false;
    private boolean enabled = true;
    
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
			engine.initializeRandom(canvas.getWidth()/CELL_PIXEL_SIZE, canvas.getHeight()/CELL_PIXEL_SIZE);
		}
		drawBoard(canvas);
	}

	private void drawBoard(Canvas canvas) {
		for(int x = 0; x < (canvas.getWidth()/CELL_PIXEL_SIZE); x++){
			for(int y = 0; y < (canvas.getHeight()/CELL_PIXEL_SIZE); y++){
				if(engine.isCellAlive(x, y)){
					drawCell(canvas, x, y);
				}
			}
		}
	}
	
	private void drawCell(Canvas canvas, int x, int y) {
		rect.set(x*CELL_PIXEL_SIZE, y*CELL_PIXEL_SIZE, 
				(x+1)*CELL_PIXEL_SIZE, (y+1)*CELL_PIXEL_SIZE);
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
