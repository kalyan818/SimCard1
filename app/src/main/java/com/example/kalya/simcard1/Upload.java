package com.example.kalya.simcard1;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class Upload extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
       // Toast.makeText(context,"power connected",Toast.LENGTH_LONG).show();
        Intent i = new Intent(context,MyService.class);
        context.startService(i);
       /* Intent i = new Intent(context,Can.class);
        context.startActivity(i);*/
    }
}
