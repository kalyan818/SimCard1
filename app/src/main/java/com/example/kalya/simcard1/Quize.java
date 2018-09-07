package com.example.kalya.simcard1;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Quize extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private RelativeLayout relativeLayout, relativeLayout1, relativeLayout2, relativeLayout3, relativeLayout4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quize);
        textView = (TextView) findViewById(R.id.back);
        relativeLayout = (RelativeLayout) findViewById(R.id.first);
        relativeLayout1 = (RelativeLayout) findViewById(R.id.second);
        relativeLayout2 = (RelativeLayout) findViewById(R.id.third);
        relativeLayout.setOnClickListener(this);
        relativeLayout1.setOnClickListener(this);
        relativeLayout2.setOnClickListener(this);
        textView.setOnClickListener(this);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        if (textView == view) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        if (relativeLayout == view) {
            Intent i = new Intent(this,MainFrame.class);
            startActivity(i);
            finish();
        }
        if (relativeLayout1 == view) {
            Intent i = new Intent(this,MainFrame1.class);
            startActivity(i);
            finish();
        }
        if (relativeLayout2 == view) {
            Intent i = new Intent(this,MainFrame2.class);
            startActivity(i);
            finish();
        }
    }

}