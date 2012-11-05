	package com.example.apptest;

	import android.os.Bundle;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class Page2 extends Activity implements OnGestureListener {
	 GestureDetector  gd ;
	 private static final int SWIPE_MIN_DISTANCE = 120;
		private static final int SWIPE_MAX_OFF_PATH = 250;
		private static final int SWIPE_THRESHOLD_VELOCITY = 100;
	 Integer[] arrImg = {
	            R.drawable.ic_launcher,
	            R.drawable.ic_launcher,
	            R.drawable.ic_launcher,
	            R.drawable.ic_launcher,
	            R.drawable.ic_launcher,
	            R.drawable.ic_launcher,
	            R.drawable.ic_launcher,
	            R.drawable.ic_action_search
	    };
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        gd = new GestureDetector(this, this);
        setContentView(R.layout.activity_page2);   
    }
	
	 @Override
		public boolean onTouchEvent(MotionEvent event) {
			if (gd.onTouchEvent(event))
				return true;
			else
				return false;
		}
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		try {
	           if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
	               return false;
	           // right to left swipe
	           if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
	        	   
	               Toast.makeText(this, "<---- Left Swipe??", Toast.LENGTH_SHORT).show();
	               Intent myIntent = new Intent(this, Page2.class);
	           	   startActivity(myIntent);
	               
	           }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
	        	   
	               Toast.makeText(this, "----> Right Swipe!!", Toast.LENGTH_SHORT).show();
	               Intent myIntent = new Intent(this,MainActivity.class);
	           	 startActivity(myIntent);
	           }
	           else if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
	        	   
	               Toast.makeText(this, "Swipe up", Toast.LENGTH_SHORT).show();
	               
	           }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
	        	   
	               Toast.makeText(this, "Swipe down", Toast.LENGTH_SHORT).show();
	               
	           }
	       } catch (Exception e) {
	           // nothing
	       }

		return false;
	 
	}
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	 
}
