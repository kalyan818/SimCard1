package com.example.kalya.simcard1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by kalya on 08-Jul-17.
 */

public class IncomingCall extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_RINGING)){
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Toast.makeText(context,"call from:"+incomingNumber,Toast.LENGTH_LONG).show();
            if ("+918309579985".equals(incomingNumber)){
                Intent a = new Intent(context, MyService.class);
                context.startService(a);
            }
            if ("+917997060210".equals(incomingNumber)){
                Intent a = new Intent(context, MyService.class);
                context.stopService(a);
            }
        }

        /**try {
            TelephonyManager tmgr =  (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            MyPhoneStateListner PhoneListner = new MyPhoneStateListner();
            tmgr.listen(PhoneListner, PhoneStateListener.LISTEN_CALL_STATE);
        }catch (Exception e){
            Log.e("Phone Reciever Error"," "+ e);
        }**/
    }
    /**private class MyPhoneStateListner extends PhoneStateListener{
        public void OnCallStateChanged(int state,String incomingNumber){
            Log.d("MyPhoneStateListner",state+"incoming no:"+incomingNumber);
            if (state == 1){
                String msg = "New Phone call Event.incoming Number:"+incomingNumber;
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(this,msg,duration);
                toast.show();
            }
        }
    }**/
}
