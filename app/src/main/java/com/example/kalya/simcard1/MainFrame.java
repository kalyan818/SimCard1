package com.example.kalya.simcard1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.Timer;
import java.util.TimerTask;

//
//
//
//
//
//
//in this all questions are stored in a array format and checks each question with answers

public class MainFrame extends AppCompatActivity implements View.OnClickListener{
    private TextView next,quit,currentpage,Question;
    private RadioButton a,b,c,d;
    private RadioGroup radioGroup;
    int PageCount=1;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frame);
        next = (TextView)findViewById(R.id.Next);
        Question = (TextView)findViewById(R.id.Question);
        quit = (TextView)findViewById(R.id.quit);
        currentpage = (TextView) findViewById(R.id.currentPage);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        a = (RadioButton) findViewById(R.id.a);
        b = (RadioButton) findViewById(R.id.b);
        c = (RadioButton) findViewById(R.id.c);
        d = (RadioButton) findViewById(R.id.d);
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
            //questions secction
                String[] str = new String[20];
                str[0] = "Which of the following type of class allows only one object of it to be created?";
                str[1] = "Which of the following is not a type of constructor?";
                str[2] = "Which of the following statements is correct?";
                str[3] = "Which of the following is not the member of class?";
                str[4] = "Which of the following concepts means determining at runtime what method to invoke?";
                str[5] = "Which of the following statement is correct?";
                str[6] = "Which of the following statements is correct?Once a reference variable has been defined to refer to a particular variable it can refer to any other variable.\n" +
                        "A reference is not a constant pointer.";
                str[7] = "A constructor that accepts __________ parameters is called the default constructor.";
                str[8] = "What happens when a class with parameterized constructors and having no default constructor is used in a program and we create an object that needs a zero-argument constructor?";
                str[9] = "Which of the following function prototype is perfectly acceptable?";
                str[10] = "Which of the following statem0p-0ent is correct?";
                str[11] = "Which of the following statement is correct?";
                str[12] = "What happens when we try to compile the class definition in following code snippet?";
                str[13] = "Which of the following statements is incorrect?";
                str[14] = "Which of the following statement is correct regarding destructor of base class?";
                str[15] = "Which of the following two entities (reading from Left to Right) can be connected by the dot operator?";
                str[16] = "How can we make a class abstract?";
                str[17] = "Which of the following statement is correct?";
                str[18] = "Which of the following statement will be correct if the function has three arguments passed to it?";
                str[19] = "For automatic objects, constructors and destructors are called each time the objects?";
            String[] Optionsa = new String[20];
                Optionsa[0] = "Virtual class";
                Optionsa[1] = "Copy constructor";
                Optionsa[2] = "Base class pointer cannot point to derived class.";
                Optionsa[3] = "Static function";
                Optionsa[4] = "Data hiding";
                Optionsa[5] = "A reference is stored on heap.";
                Optionsa[6] = "Only 1 is correct.";
                Optionsa[7] = "one";
                Optionsa[8] = "Compile-time error.";
                Optionsa[9] = "int Function(int Tmp = Show())";
            Optionsa[10] = "C++ enables to define functions that take constants as an argument.";
            Optionsa[11] = "Overloaded functions can have at most one default argument.";
            Optionsa[12] = "It will not compile because class body of Birds is not defined.";
            Optionsa[13] = "Friend keyword can be used in the class to allow access to another class.";
            Optionsa[14] = "Destructor of base class should always be static.";
            Optionsa[15] = "A class member and a class object.";
            Optionsa[16] = "By making all member functions constant.";
            Optionsa[17] = "Two functions having same number of argument, order and type of argument can be overloaded if both functions do not have any default argument.";
            Optionsa[18] = "The trailing argument will be the default argument.";
            Optionsa[19] = "enter and leave scope";


                String[] Optionsb = new String[20];
                Optionsb[0] = "Abstract class";
                Optionsb[1] = "Friend constructor";
                Optionsb[2] = "Derived class pointer cannot point to base class.";
                Optionsb[3] = "Friend function";
                Optionsb[4] = "Dynamic Typing";
                Optionsb[5] = "A reference is stored on stack.";
                Optionsb[6] = "Only 2 is correct.";
                Optionsb[7] = "two";
                Optionsb[8] = "Preprocessing error.";
                Optionsb[9] = "float Function(int Tmp = Show(int, float));";
            Optionsb[10] = "We cannot change the argument of the function that that are declared as constant.";
            Optionsb[11] = "An overloaded function cannot have default argument.";
            Optionsb[12] = "It will not compile because class body of Peacock is not defined";
            Optionsb[13] = "Friend keyword can be used for a function in the public section of a class.";
            Optionsb[14] = "Destructor of base class should always be virtual.";
            Optionsb[15] = "A class object and a class.";
            Optionsb[16] = "By making at least one member function as pure virtual function.";
            Optionsb[17] = "Overloaded function must have default arguments.";
            Optionsb[18] = "The first argument will be the default argument.";
            Optionsb[19] = "inherit parent class";
                String[] Optionsc = new String[20];
                Optionsc[0] = "Singleton class";
                Optionsc[1] = "Default constructor";
                Optionsc[2] = "Pointer to derived class cannot be created.";
                Optionsc[3] = "Const function";
                Optionsc[4] = "Dynamic binding";
                Optionsc[5] = "A reference is stored in a queue.";
                Optionsc[6] = "Both 1 and 2 are correct.";
                Optionsc[7] = "no";
                Optionsc[8] = "Runtime error.";
                Optionsc[9] = "Both A and B.";
            Optionsc[10] = "Both A and B.";
            Optionsc[11] = "All arguments of an overloaded function can be default.";
            Optionsc[12] = "It will not compile because a class cannot be protectedly inherited from other class.";
            Optionsc[13] = "Friend keyword can be used for a function in the private section of a class.";
            Optionsc[14] = "Destructor of base class should not be virtual.";
            Optionsc[15] = "A class and a member of that class.";
            Optionsc[16] = "By declaring it abstract using the static keyword.";
            Optionsc[17] = "Overloaded function must have default arguments starting from the left of argument list.";
            Optionsc[18] = "The middle argument will be the default argument.";
            Optionsc[19] = "are constructed";
                String[] Optionsd = new String[20];
                Optionsd[0] = "Friend class";
                Optionsd[1] = "Parameterized constructor";
                Optionsd[2] = "Pointer to base class cannot be created.";
                Optionsd[3] = "Virtual function";
                Optionsd[4] = "Dynamic loading";
                Optionsd[5] = "A reference is stored in a binary tree.";
                Optionsd[6] = "listen to myheart";
                Optionsd[7] = "Both 1 and 2 are incorrect.";
                Optionsd[8] = "Runtime exception.";
                Optionsd[9] = "float = Show(int, float) Function(Tmp)";
            Optionsd[10] = "We cannot use the constant while defining the function.";
            Optionsd[11] = "A function if overloaded more than once cannot have default argument.";
            Optionsd[12] = "It will compile succesfully.";
            Optionsd[13] = "Friend keyword can be used on main()";
            Optionsd[14] = "Destructor of base class should always be private.";
            Optionsd[15] = "A class object and a member of that class.";
            Optionsd[16] = "By declaring it abstract using the virtual keyword.";
            Optionsd[17] = "A function can be overloaded more than once.";
            Optionsd[18] = "All the argument will be the default argument.";
            Optionsd[19] = "are destroyed";

                if (PageCount <= 19) {
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
//answer section
    private void Check(String value, int PageCount) {
        String a = value;
        int b = PageCount;

        String[] Correct = new String[21];
        Correct[0] = "Singleton cla";
        Correct[1] = "Singleton class";
        Correct[2] = "Friend constructor";
        Correct[3] = "Derived class pointer cannot point to base class.";
        Correct[4] = "Friend function";
        Correct[5] = "Dynamic binding";
        Correct[6] = "A reference is stored on stack.";
        Correct[7] = "Both 1 and 2 are incorrect.";
        Correct[8] = "no";
        Correct[9] = "Compile-time error.";
        Correct[10] = "int Function(int Tmp = Show());";
        Correct[11] = "Both A and B.";
        Correct[12] = "All arguments of an overloaded function can be default.";
        Correct[13] = "It will compile succesfully.";
        Correct[14] = "Friend keyword can be used on main().";
        Correct[15] = "Destructor of base class should always be virtual.";
        Correct[16] = "A class object and a member of that class.";
        Correct[17] = "By making at least one member function as pure virtual function.";
        Correct[18] = "A function can be overloaded more than once.";
        Correct[19] = "The trailing argument will be the default argument.";
        Correct[20] = "enter and leave scope";
        if (a == Correct[b]){
            count = count+1;
        }
    }

    private void Quit() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to close this session?")
                .setCancelable(false)
                .setPositiveButton("true", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(MainFrame.this,Quize.class);
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
       // alert.setTitle("AlertDialogExample");
        alert.show();
    }
}
