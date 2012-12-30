package com.a831.getalife;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.TouchUtils;

public class LifeActivityTest extends ActivityUnitTestCase<LifeActivity> {

	public LifeActivityTest() {
		super(LifeActivity.class);
	}
	
	public void testEnabledOnCreate(){
		Intent intent = new Intent(getInstrumentation().getTargetContext(), LifeActivity.class);
		startActivity(intent, null, null);
		
		LifeActivity activity = getActivity();
		BoardView board = (BoardView) activity.findViewById(R.id.boardView1);
		
		assertTrue(board.isEnabled());
		
	}
	
	public void testDisabledOnPause(){
		Intent intent = new Intent(getInstrumentation().getTargetContext(), LifeActivity.class);
		startActivity(intent, null, null);
		
		LifeActivity activity = getActivity();
		BoardView board = (BoardView) activity.findViewById(R.id.boardView1);
		
		getInstrumentation().callActivityOnPause(activity);
		assertFalse(board.isEnabled());
	}
	
	public void testPauseAndResume(){
		Intent intent = new Intent(getInstrumentation().getTargetContext(), LifeActivity.class);
		startActivity(intent, null, null);
		
		LifeActivity activity = getActivity();
		BoardView board = (BoardView) activity.findViewById(R.id.boardView1);
		assertTrue(board.isEnabled());
		
		getInstrumentation().callActivityOnPause(activity);
		assertFalse(board.isEnabled());
		
		getInstrumentation().callActivityOnResume(activity);
		assertTrue(board.isEnabled());
	}

}
