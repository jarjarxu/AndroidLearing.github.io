package com.example.webviewtext;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class BasicActivity extends AppCompatActivity {
    private ForceOffineReceiver receiver;
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("BasicActivity",getClass().getSimpleName());
        AvtivityCollector.addActivity(this);
    }
    @Override
    protected void onResume(){
        super.onResume();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.example.webviewtext.FORCE_OFFINE");
        receiver=new ForceOffineReceiver();
        registerReceiver(receiver,intentFilter);
    }
    @Override
    protected void onPause(){
        super.onPause();
        if(receiver != null){
            unregisterReceiver(receiver);
            receiver=null;
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        AvtivityCollector.removeActivity(this);
    }
    class ForceOffineReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, Intent i){
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("You are forced to be offline,please try to login again!");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    AvtivityCollector.finishAll();
                    Intent intent =new Intent(context,LoginActivity2.class);
                    context.startActivity(intent);
                }
            });
            builder.show();
        }
    }
}
