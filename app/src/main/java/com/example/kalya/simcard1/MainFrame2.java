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

public class MainFrame2 extends AppCompatActivity implements View.OnClickListener{
    private TextView next,quit,currentpage,Question;
    private RadioButton a,b,c,d;
    private RadioGroup radioGroup;
    int PageCount=1;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frame2);
        next = (TextView)findViewById(R.id.Next);
        Question = (TextView)findViewById(R.id.Question2);
        quit = (TextView)findViewById(R.id.quit);
        currentpage = (TextView) findViewById(R.id.currentPage);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        a = (RadioButton) findViewById(R.id.a2);
        b = (RadioButton) findViewById(R.id.b2);
        c = (RadioButton) findViewById(R.id.c2);
        d = (RadioButton) findViewById(R.id.d2);
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
            String[] str = new String[20];
            str[0] = "Which of the following correctly shows the hierarchy of arithmetic operations in C?";
            str[1] = " The type name/reserved word ‘short’ is ___";
            str[2] = "Which of the following is the correct usage of conditional operators used in C? ";
            str[3] = "The keyword used to transfer control from a function back to the calling function is";
            str[4] = "C is the successor of ___ programming language.";
            str[5] = "Which of the following function sets first n characters of a string to a given character?";
            str[6] = " Similarity between a structure, union and enumeration,";
            str[7] = "If the two strings are identical, then strcmp() function returns";
            str[8] ="Choose the correct order from given below options for the calling function of the code “a = f1(23, 14) * f2(12/4) + f3();”? ";
            str[9] = "extern int fun(); - The declaration indicates the presence of a global function defined outside the current module or in another file.";
            str[10]="If the two strings are identical, then strcmp() function returns";
            str[11]="What will the function rewind() do?";
            str[12]="Function fopen() with the mode tries to open the file for __ ";
            str[13]="How will you print \n on the screen? ";
            str[14]="Which standard library function will you use to find the last occurance of a character in a string in C?";
            str[15]="A Variable name in C includes which special symbols? ";
            str[16]="  For a structure, if a variable behave as a pointer then from the given below operators which operator can be used to access data of the structure via the variable pointer?";
            str[17]="Why to use fflush() library function? ";
            str[18]="The library function used to find the last occurrence of a character in a string is ";
            str[19]="The library function strrchr() finds the first occurrence of a substring in another string. ";
            String[] Optionsa = new String[20];
            Optionsa[0] = "	/ + * -";
            Optionsa[1] = "short long";
            Optionsa[2] = "a>b ? c=30 : c=40;";
            Optionsa[3] = "switch";
            Optionsa[4] = "C++";
            Optionsa[5] = " strinit()";
            Optionsa[6] = " All are helpful in defining new variables ";
            Optionsa[7] = "-1";
            Optionsa[8] = "  f1, f2, f3";
            Optionsa[9] = "True ";
            Optionsa[10]="-1";
            Optionsa[11]=" Garbage character";
            Optionsa[12]="reading and writing";
            Optionsa[13]="printf braceopen backSlash N braceclose";
            Optionsa[14]="  10 20 30";
            Optionsa[15]=" * (asterisk) ";
            Optionsa[16]=". ";
            Optionsa[17]=" To flush all streams and specified streams";
            Optionsa[18]="	strnstr()";
            Optionsa[19]="yes ";
            String[] Optionsb = new String[20];
            Optionsb[0] = "	* - / +";
            Optionsb[1] = "short char";
            Optionsb[2] = "a>b ? c=30;";
            Optionsb[3] = "	goto";
            Optionsb[4] = "B++ ";
            Optionsb[5] = "strnset()";
            Optionsb[6] = "All are helpful in defining new data types";
            Optionsb[7] = "1";
            Optionsb[8] = "f3, f2, f1";
            Optionsb[9] = "False";
            Optionsb[10]="1 ";
            Optionsb[11]=" A";
            Optionsb[12]="reading and adding new content";
            Optionsb[13]="	echo \\n";
            Optionsb[14]="7 20";
            Optionsb[15]="# (Hash)";
            Optionsb[16]="%";
            Optionsb[17]="To flush only specified stream";
            Optionsb[18]="laststr()";
            String[] Optionsc = new String[20];
            Optionsc[0] = "+ - / *";
            Optionsc[1] = "short float";
            Optionsc[2] = "max = a>b ? a>c?a:c:b>c?b:c";
            Optionsc[3] = "	go back";
            Optionsc[4] = "B";
            Optionsc[5] = "strset()";
            Optionsc[6] = "All are helpful in defining new pointers";
            Optionsc[7] = "0";
            Optionsc[8] = "f2, f1, f3 ";
            Optionsc[9] = "both";
            Optionsc[10]="0";
            Optionsc[11]="65";
            Optionsc[12]="only for reading";
            Optionsc[13]="printf('\n');";
            Optionsc[14]="5 20";
            Optionsc[15]="+ (Addition)";
            Optionsc[16]="->";
            Optionsc[17]="To flush input/output buffer ";
            Optionsc[18]="strrchr()";
            Optionsc[19]="strchr()";


            String[] Optionsd = new String[20];
            Optionsd[0] = "	/ * + -";
            Optionsd[1] = "short int ";
            Optionsd[2] = "return (a>b)?(a:b";
            Optionsd[3] = "	return";
            Optionsd[4] =" Mini C";
            Optionsd[5] = "strcset()";
            Optionsd[6] = "All are helpful in defining new structures";
            Optionsd[7] = "yes ";
            Optionsd[8] = " Order may vary from one compiler to another ";
            Optionsd[9] = "none";
            Optionsd[10]="yes ";
            Optionsd[11]="Compile error";
            Optionsd[12]="it works only for directories";
            Optionsd[13]="printf Braceopen back slash n braceClose";
            Optionsd[14]="8 20";
            Optionsd[15]="_ (underscore)";
            Optionsd[16]=" #";
            Optionsd[17]="Invalid library function";
            Optionsd[18]="strstr()";
            Optionsd[19]="strnset()";
            if (PageCount <=19) {
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
  //              Toast.makeText(this, "u corrected " + value + "    " + PageCount + "  one", Toast.LENGTH_LONG).show();
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

        String[] Correct = new String[20];
        Correct[0] = "ghdf";
        Correct[1] = "/ * + -";
        Correct[2] = "short int";
        Correct[3] = "max = a>b ? a>c?a:c:b>c?b:c";
        Correct[4] = "return";
        Correct[5] = "B";
        Correct[6] = "strnset()";
        Correct[7] = "All are helpful in defining new data types";
        Correct[8] = "0";
        Correct[9] = "Order may vary from one compiler to another";
        Correct[10] = "True";
        Correct[11]="0";
        Correct[12]="A";
        Correct[13]=" reading and writing";
        Correct[14]="printf('\n');";
        Correct[15]="10 20 30";
        Correct[16]=" _ (underscore)";
        Correct[17]="->";
        Correct[18]="To flush all streams and specified streams";
        Correct[19]="	strrchr()";
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
                        Intent i = new Intent(MainFrame2.this,Quize.class);
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
//        alert.setTitle("AlertDialogExample");
        alert.show();
    }
}
