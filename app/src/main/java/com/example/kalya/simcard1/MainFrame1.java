package com.example.kalya.simcard1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainFrame1 extends AppCompatActivity implements View.OnClickListener{
    private TextView next,quit,currentpage,Question;
    private RadioButton a,b,c,d;
    private RadioGroup radioGroup;
    int PageCount=1;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frame1);
        next = (TextView)findViewById(R.id.Next);
        Question = (TextView)findViewById(R.id.Question1);
        quit = (TextView)findViewById(R.id.quit);
        currentpage = (TextView) findViewById(R.id.currentPage);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        a = (RadioButton) findViewById(R.id.a1);
        b = (RadioButton) findViewById(R.id.b1);
        c = (RadioButton) findViewById(R.id.c1);
        d = (RadioButton) findViewById(R.id.d1);
        Hello();
        next.setOnClickListener(this);
        quit.setOnClickListener(this);
    }
    private void Hello() {
        Question.setText("select your option once and click next");
        a.setText("hello");
        b.setText("user");
        c.setText("how can");
        d.setText("i help u");
        PageCount = 0;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            Quit();
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onClick(View view) {
        if (view == quit) {
            Quit();
        }
        if (view == next) {
            String[] str = new String[15];
            str[0] = "Exception and Error are immediate subclasses of a class called?";
            str[1] = "The order of the three top level elements of the java source file are?";
            str[2] = "The minimum value of char type variable is ?";
            str[3] = "Java uses ___ to represent characters ?";
            str[4] = "Which one is not supported by OOP";
            str[5] = "Java programs are?";
            str[6] = "The new operator?";
            str[7] = "Which of the following statement is correct?";
            str[8] = "Java language has support for which of the following types of comment?";
            str[9] = "Command to execute a compiled java program is : ";
            str[10] = " ______ is a mechanism for naming and visibility control of a class and its content.";
            str[11] = "The java compiler";
            str[12] = " Two threads cannot simultaneously enter into the methods of the same object if the methods are?";
            str[13] = "While using threads which of the following is incorrect?";
            str[14] = "What is the name of the method used to schedule a thread for execution?";
            String[] Optionsa = new String[15];
            Optionsa[0] = "Object";
            Optionsa[1] = "Import, Package, Class";
            Optionsa[2] = "‘\\u0020’ ";
            Optionsa[3] = "ASCII code";
            Optionsa[4] = "Abstraction";
            Optionsa[5] = "Platform-dependent";
            Optionsa[6] = "returns a pointer to a variable";
            Optionsa[7] = "For positive numbers, result of operators >> and >>> are same ";
            Optionsa[8] = "block, line and javadoc ";
            Optionsa[9] = "javac";
            Optionsa[10] = "Object";
            Optionsa[11] = "creates executable";
            Optionsa[12] = "static";
            Optionsa[13] = "You invoke the Run method";
            Optionsa[14] = "init()";
            String[] Optionsb = new String[15];
            Optionsb[0] = "Throwable";
            Optionsb[1] = "Class, Import, Package ";
            Optionsb[2] = "‘\\u00ff’";
            Optionsb[3] = "Unicode";
            Optionsb[4] = "Polymorphism";
            Optionsb[5] = "Interpreter-dependent";
            Optionsb[6] = "creates a variable called new";
            Optionsb[7] = "Java provides two operators to do left shift <<< and << ";
            Optionsb[8] = "javadoc, literal and string";
            Optionsb[9] = "java";
            Optionsb[10] = "Packages";
            Optionsb[11] = "translates java source code to byte code";
            Optionsb[12] = "synchronized";
            Optionsb[13] = "You implement Runnable interface";
            Optionsb[14] = "start()";
            String[] Optionsc = new String[15];
            Optionsc[0] = "AWT";
            Optionsc[1] = "Package, Import, Class";
            Optionsc[2] = "‘\\u0010’";
            Optionsc[3] = "Byte code";
            Optionsc[4] = "Encapsulation";
            Optionsc[5] = "Platform-independent";
            Optionsc[6] = "obtains memory for a new variable";
            Optionsc[7] = ">> is the zero fill right shift operator";
            Optionsc[8] = "javadoc, char and string";
            Optionsc[9] = "run";
            Optionsc[10] = "Interfaces";
            Optionsc[11] = "creates classes";
            Optionsc[12] = "private";
            Optionsc[13] = "You extend from Thread class";
            Optionsc[14] = "run()";
            String[] Optionsd = new String[15];
            Optionsd[0] = "Panel";
            Optionsd[1] = "Random order";
            Optionsd[2] = "‘\\u0000’";
            Optionsd[3] = "None of the above";
            Optionsd[4] = "Global variables";
            Optionsd[5] = "Interpreter-independent";
            Optionsd[6] = "tells how much memory is available";
            Optionsd[7] = ">>> is the signed right shift operator";
            Optionsd[8] = "single, multiple and quote";
            Optionsd[9] = "execute";
            Optionsd[10] = "None of the Mentioned";
            Optionsd[11] = "produces java Interpreter";
            Optionsd[12] = "package";
            Optionsd[13] = "You call the start method";
            Optionsd[14] = "resume()";
            if (PageCount <= 14) {
                String value = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                Check(value, PageCount);
                Question.setText(str[PageCount]);
                a.setText(Optionsa[PageCount]);
                b.setText(Optionsb[PageCount]);
                c.setText(Optionsc[PageCount]);
                d.setText(Optionsd[PageCount]);
                a.setChecked(false);
                b.setChecked(false);
                c.setChecked(false);
                d.setChecked(false);
                String x = (String.valueOf(PageCount));
                currentpage.setText(x);
//                Toast.makeText(this, "u corrected " + value + "    " + PageCount + "  one", Toast.LENGTH_LONG).show();
            } else {
                Intent i = new Intent(this, FinalResult.class);
                int c = count;
                String strI = String.valueOf(c);
                i.putExtra("Message", strI);
                startActivity(i);
                finish();
            }
            PageCount++;
        }
    }

    private void Check(String value, int PageCount) {
        String a = value;
        int b = PageCount;

        String[] Correct = new String[15];
        Correct[1] = "Throwable";
        Correct[2] = "Package, Import, Class";
        Correct[3] = "‘\\u0000’";
        Correct[4] = "Unicode";
        Correct[5] = "Global variables";
        Correct[6] = "Platform-independent";
        Correct[7] = "obtains memory for a new variable";
        Correct[8] = "For positive numbers, result of operators >> and >>> are same ";
        Correct[9] = "block, line and javadoc ";
        Correct[10] = "java";
        Correct[11] = "Packages";
        Correct[12] = "translates java source code to byte code";
        Correct[13] = "synchronized";
        Correct[14] = "You invoke the Run method";
        if (a == Correct[b]){
            count = count+1;
        }
    }

    private void Quit() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to close this session?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(MainFrame1.this,Quize.class);
                        startActivity(i);
                        finish();
                        return;
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.dismiss();
                    }
                });

        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        //alert.setTitle("AlertDialogExample");
        alert.show();
    }
}
