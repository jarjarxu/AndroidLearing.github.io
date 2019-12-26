package com.example.webviewtext;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends BasicActivity {
    Button bn,bn2;
//    private EditText edit_account,edit_password;
//    private Button login;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bn=findViewById(R.id.button);
        bn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
//                Intent i=new Intent(MainActivity.this,SecondActivity.class);
//                startActivity(i);
                /*使用这种方法只需知道传的参数类型和个数*/
                SecondActivity.actionStart(MainActivity.this,"1111","2222");
            }
        });
        bn2=findViewById(R.id.button2);
        bn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent("com.example.webviewtext.FORCE_OFFINE");
                sendBroadcast(intent);
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(MainActivity.this, "U click add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(MainActivity.this, "U click remove", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
        }
}
