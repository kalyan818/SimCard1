package com.example.kalya.simcard1;

import android.app.Service;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class RecorderService extends Service {
    private static final String TAG = "RecorderService";
    private static android.hardware.camera2.CameraDevice mServiceCamera;
    private boolean mRecordingStatus;
    private MediaRecorder mMediaRecorder;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate() {
        mRecordingStatus = false;
        mServiceCamera = MainActivity.mCamera;

        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        if (mRecordingStatus == false)
            startRecording();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        stopRecording();
        mRecordingStatus = false;

        super.onDestroy();
    }

    public boolean startRecording(){
        try {
            int cameraId = -1;
            Toast.makeText(getBaseContext(), "Recording Started", Toast.LENGTH_SHORT).show();
            mMediaRecorder = new MediaRecorder();
            mMediaRecorder.setCamera(Camera.open());
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mMediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
            mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
            mMediaRecorder.setOutputFile(Environment.getExternalStorageDirectory().getPath() + "/video.mp4");

            mMediaRecorder.prepare();
            mMediaRecorder.start();

            mRecordingStatus = true;

            return true;

        } catch (IllegalStateException e) {
            Log.d(TAG, e.getMessage());
            e.printStackTrace();
            return false;

        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void stopRecording() {
        Toast.makeText(getBaseContext(), "Recording Stopped", Toast.LENGTH_SHORT).show();
        /*try {
           // mServiceCamera.reconnect();

       // } catch (IOException e) {
            e.printStackTrace();
        }

        mMediaRecorder.stop();
        mMediaRecorder.reset();

        mMediaRecorder.release();

       // mServiceCamera.release();
        mServiceCamera = null;
    }*/
    }
}
