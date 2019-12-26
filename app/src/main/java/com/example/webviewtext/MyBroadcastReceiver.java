package com.example.webviewtext;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    String str="Receive frome MyBroadcast";

    @Override
    public void onReceive(Context context, Intent intent){
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
        Log.d("str",str);
    }
}
