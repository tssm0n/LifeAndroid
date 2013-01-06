package com.a831.getalife;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class LifeActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        enableGame(true);
    }

	@Override
	protected void onPause() {
		super.onPause();
		enableGame(false);
	}

	@Override
	protected void onResume() {
		super.onResume();
		enableGame(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.mainmenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		BoardView board = (BoardView)findViewById(R.id.boardView1);
		switch(item.getItemId()){
			case R.id.zoomInItem:
				board.zoom(true);
				return true;
			case R.id.zoomOutItem:
				board.zoom(false);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void enableGame(boolean enabled) {
		BoardView board = (BoardView)findViewById(R.id.boardView1);
		board.setEnabled(enabled);
	}
    
    
}