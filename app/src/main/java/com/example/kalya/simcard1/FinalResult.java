package com.example.kalya.simcard1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FinalResult extends AppCompatActivity implements View.OnClickListener{
    RelativeLayout relativeLayout;
    TextView textView,txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);
        relativeLayout = (RelativeLayout)findViewById(R.id.LaunchNewQuiz);
        textView = (TextView)findViewById(R.id.Cancel);
        txt = (TextView)findViewById(R.id.AnsweredRightQuestions);
        textView.setOnClickListener(this);
        relativeLayout.setOnClickListener(this);
        String a = getIntent().getStringExtra("Message");
        txt.setText(a);
    }
    //below method invokes when user manually click back button in the system(mobile phones back button )(physical back button in mobile)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            Intent i = new Intent(this,Quize.class);
            startActivity(i);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        if (view == textView){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }
        if (view == relativeLayout){
            Intent i = new Intent(this,Quize.class);
            startActivity(i);
            finish();
        }
    }
}
