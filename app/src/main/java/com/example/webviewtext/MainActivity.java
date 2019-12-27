package com.example.webviewtext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends BasicActivity {
    private MyDatabase myDatabase;
    Button bn,bn2,bn3,bn4;
    EditText input;
//    private EditText edit_account,edit_password;
//    private Button login;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabase=new MyDatabase(this,"BookStore.db",null,2);
        bn=findViewById(R.id.button);
        bn2=findViewById(R.id.button2);
        bn3=findViewById(R.id.button3);
        bn4=findViewById(R.id.button4);
        input=findViewById(R.id.input_text);
        bn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
//                Intent i=new Intent(MainActivity.this,SecondActivity.class);
//                startActivity(i);
                /*使用这种方法只需知道传的参数类型和个数*/
                SecondActivity.actionStart(MainActivity.this,"1111","2222");
            }
        });
        bn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //发送一条强制下线广播
                Intent intent=new Intent("com.example.webviewtext.FORCE_OFFINE");
                sendBroadcast(intent);
            }
        });
        String str=load();
        if(TextUtils.isEmpty(str)){
            input.setText(str);
            input.setSelection(str.length());
            Toast.makeText(this,"Empty!!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
        }
        bn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onDestroy();
            }
        });
        //创建数据库
        bn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                myDatabase.getWritableDatabase();
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
        //保存editText输入的内容，点击保存后重新登录后用toast输出
    protected void onDestroy(){
        String text=input.getText().toString();
        save(text);
        Toast.makeText(MainActivity.this,"Save successed!",Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
    //用文件流保存editText输入内容
    public void save(String inputText){
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try{
            out=openFileOutput("data", Context.MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(writer != null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    //读出保存内容
    public String load(){
        FileInputStream inputStream=null;
        BufferedReader reader=null;
        StringBuilder content=new StringBuilder();
        try{
            inputStream =openFileInput("data");
            reader =new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while((line=reader.readLine())!=null){
                content.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(reader != null){
                try {
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}
