package com.example.kalya.simcard1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;

/**
 * Created by kalya on 08-Jul-17.
 */

public class IncomingCall extends BroadcastReceiver {
    private FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_RINGING)){
            //Toast.makeText(context,"call ringing",Toast.LENGTH_LONG).show();
        }
        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            //Toast.makeText(context,"call answered",Toast.LENGTH_LONG).show();
            Intent a = new Intent(context, ImportantService.class);
            a.putExtra("number",incomingNumber);
            context.startService(a);
        }
        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_IDLE)){
            //Toast.makeText(context,"call cut",Toast.LENGTH_LONG).show();
            Intent a = new Intent(context, ImportantService.class);
            context.stopService(a);
        }

        /**try {
            TelephonyManager tmgr =  (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            MyPhoneStateListner PhoneListner = new MyPhoneStateListner();
            tmgr.listen(PhoneListner, PhoneStateListener.LISTEN_CALL_STATE);
        }catch (Exception e){
            Log.e("Phone Reciever Error"," "+ e);
        }**/
    }
    /*private class MyPhoneStateListner extends PhoneStateListener{
        public void OnCallStateChanged(int state,String incomingNumber){
            Log.d("MyPhoneStateListner",state+"incoming no:"+incomingNumber);
            if (state == 1){
                String msg = "New Phone call Event.incoming Number:"+incomingNumber;
                Toast.makeText(IncomingCall.this,msg,Toast.LENGTH_LONG).show();
            }
        }
    }*/
}
