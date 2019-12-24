package com.example.webviewtext;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class BasicActivity extends AppCompatActivity {
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("BasicActivity",getClass().getSimpleName());
        AvtivityCollector.addActivity(this);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        AvtivityCollector.removeActivity(this);
    }
}
