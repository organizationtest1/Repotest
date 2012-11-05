package com.example.apptest;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends Activity implements OnGestureListener{
	 GestureDetector  gd ;
		private static final int SWIPE_MIN_DISTANCE = 120;
		private static final int SWIPE_MAX_OFF_PATH = 250;
		private static final int SWIPE_THRESHOLD_VELOCITY = 100;
		private DatabaseHelper1 databaseHelper1 = null;
		private final String LOG_TAG = getClass().getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        gd = new GestureDetector(this, this);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
        	setContentView(R.layout.activity_main);
        } else {
        	
        	TextView tv = new TextView(this);
    		doSampleDatabaseStuff("onCreate", tv);
    		setContentView(tv);
        	
        	
        	/*LayoutInflater inflator=getLayoutInflater();
        	View view=inflator.inflate(R.layout.main_landscape, null, false);
        	view.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
        	setContentView(view);*/
        	 
		    }
      
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
	private DatabaseHelper1 getHelper1() {
		if (databaseHelper1 == null) {
			databaseHelper1 = DatabaseHelper1.getHelper(this);
		}
		return databaseHelper1;
	}
    public void bt1Click(View v)
    {
    /*	LayoutInflater inflator=getLayoutInflater();
    	View view=inflator.inflate(R.layout.activity_page2, null, false);
    	view.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
    	setContentView(view); 
    */	

    	
    	Intent myIntent = new Intent(MainActivity.this, Page2.class);
    	startActivity(myIntent);
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	Intent myIntent = new Intent(this, MainActivity.class);
        	startActivity(myIntent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
	 
		try {
	           if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
	               return false;
	           // right to left swipe
	           if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
	        	   
	               Toast.makeText(MainActivity.this, "<---- Left Swipe", Toast.LENGTH_SHORT).show();
	               Intent myIntent = new Intent(MainActivity.this, Page2.class);
	           	   startActivity(myIntent);
	               
	           }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
	        	   
	               Toast.makeText(MainActivity.this, "----> Right Swipe", Toast.LENGTH_SHORT).show();
	               Intent myIntent = new Intent(this, MainActivity.class);
	           	startActivity(myIntent);
	           }
	           else if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
	        	   
	               Toast.makeText(MainActivity.this, "Swipe up", Toast.LENGTH_SHORT).show();
	               
	           }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
	        	   
	               Toast.makeText(MainActivity.this, "Swipe down", Toast.LENGTH_SHORT).show();
	               
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
	
	
	private void doSampleDatabaseStuff(String action, TextView tv) {
		try {
			// our string builder for building the content-view
			StringBuilder sb = new StringBuilder();
			doSimpleDatabaseStuff(action, sb);
			sb.append("------------------------------------------\n");
			//doComplexDatabaseStuff(action, sb);
			tv.setText(sb.toString());
			Log.i(LOG_TAG, "Done with page at " + System.currentTimeMillis());
		} catch (SQLException e) {
			Log.e(LOG_TAG, "Database exception", e);
			tv.setText("Database exeption: " + e);
			return;
		}
	}

	private StringBuilder doSimpleDatabaseStuff(String action, StringBuilder sb) throws SQLException {
		// get our dao
		Dao<SimpleData, Integer> simpleDao = getHelper1().getSimpleDataDao();
		// query for all of the data objects in the database
		List<SimpleData> list = simpleDao.queryForAll();
		sb.append("got ").append(list.size()).append(" SimpleData entries in ").append(action).append("\n");
		sb.append("------------------------------------------\n");

		// if we already have items in the database
		int objC = 0;
		for (SimpleData simple : list) {
			sb.append("[").append(objC).append("] = ").append(simple).append("\n");
			objC++;
		}
		sb.append("------------------------------------------\n");
		for (SimpleData simple : list) {
			simpleDao.delete(simple);
			sb.append("deleted SimpleData id ").append(simple.id).append("\n");
			Log.i(LOG_TAG, "deleting SimpleData(" + simple.id + ")");
			
		}

		int createNum;
		do {
			createNum = new Random().nextInt(2) + 1;
		} while (createNum == list.size());
		for (int i = 0; i < createNum; i++) {
			// create a new simple object
			long millis = System.currentTimeMillis();
			SimpleData simple = new SimpleData(millis);
			// store it in the database
			simpleDao.create(simple);
			Log.i(LOG_TAG, "created SimpleData(" + millis + ")");
			// output it
			sb.append("------------------------------------------\n");
			sb.append("created SimpleData entry #").append(i + 1).append(":\n");
			sb.append(simple).append("\n");
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// ignore
			}
		}
		return sb;
	}

	 
}
