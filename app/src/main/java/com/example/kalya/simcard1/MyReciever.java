package com.example.kalya.simcard1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by kalya on 29-Jun-17.
 */

public class MyReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
    Intent a = new Intent(context, MyService.class);
        context.startService(a);
    }
}
