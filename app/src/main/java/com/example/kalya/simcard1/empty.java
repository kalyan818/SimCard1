package com.example.kalya.simcard1;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
//
//
//
//
//
//
//
// this is not used in my project this was a just trail one for timing its not necessary

public class empty extends AppCompatActivity implements View.OnClickListener{


    private Button Btn,Btn1;
    int i = 0;
    int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        Btn = (Button)findViewById(R.id.button4);
        Btn1 = (Button)findViewById(R.id.button5);
        Btn.setOnClickListener(this);
        Btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(final View view) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (view == Btn) {
                    i = 10;
                    if (view == Btn1) {
                        j = 10;
                    }
                }
                if (i == j){
                    Toast.makeText(getApplicationContext(),"1click",Toast.LENGTH_LONG).show();
                }
                i=22;
                j=25;
            }
        },500);
    }
}
