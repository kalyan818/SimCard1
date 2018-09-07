package com.example.kalya.simcard1;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

//
//
//
//
//
//
//
//this is also not used in this project
//when applicaion opens then it shows this page for particular time its just like when u  open a skype app its shows skype for fewseconds and transfer to next page same concept
public class HomeActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    //GestureDetector detector;
    Boolean mButtonAClicked = true;
    Boolean mButtonBClicked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //detector = new GestureDetector(this, this);
        new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
                Intent homeIntent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }


   /*@Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        render();
        return true;
    }

    private void render() {
        long startTime = System.currentTimeMillis();
        long difference = System.currentTimeMillis() - startTime;
        final int i=0;
        if (difference <= 250){
        }
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        Toast.makeText(this, "Long Pressed gesture", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Toast.makeText(this, "Fling gesture", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
         return detector.onTouchEvent(motionEvent);
    }*/
}
