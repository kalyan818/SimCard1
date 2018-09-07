package com.example.kalya.simcard1;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImportantService extends Service {

    private MediaRecorder mediaRecorder;
    TelephonyManager telephonyManager;
    final String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

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
        String  extras = intent.getStringExtra("number");
        //Toast.makeText(this,extras,Toast.LENGTH_LONG).show();
        String AudioSavePathInDevice = null;
        AudioSavePathInDevice = Environment.getExternalStorageDirectory().getAbsolutePath() + getFile() + "/." + extras + timestamp + ".amr";
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);
            try {
                mediaRecorder.prepare();
                mediaRecorder.start();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        return START_STICKY;
    }
    private String getFile(){
        File folder = new File("sdcard/.myfiles");
        String dir = "/.myfiles/";
        if (!folder.exists())
        {
            folder.mkdir();
        }
        return dir;
    }
}
