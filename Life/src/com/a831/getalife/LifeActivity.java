package com.a831.getalife;

import android.app.Activity;
import android.os.Bundle;

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

	private void enableGame(boolean enabled) {
		BoardView board = (BoardView)findViewById(R.id.boardView1);
		board.setEnabled(enabled);
	}
    
    
}