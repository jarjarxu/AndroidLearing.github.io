package com.example.webviewtext;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.prefs.PreferenceChangeEvent;

public class LoginActivity2 extends BasicActivity{
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPass;
    private EditText edit_account,edit_password;
    private Button login;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        edit_account=findViewById(R.id.edit_account);
        edit_password=findViewById(R.id.edit_password);
        login=findViewById(R.id.login);
        rememberPass=findViewById(R.id.remember_pass);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember=pref.getBoolean("rememberPass",true);
        //如果勾选，把输入内容存进SharePerenfences
        if(isRemember){
            String account=pref.getString("account","");
            String password=pref.getString("password","");
            edit_account.setText(account);
            edit_password.setText(password);
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String account=edit_account.getText().toString();
                    String password=edit_password.getText().toString();
                    if(account.equals("a") && password.equals("123")){
                        editor=pref.edit();
                        //如果勾选了保存密码框，则把输入内容读出
                        if(rememberPass.isChecked()){
                            editor.putBoolean("rememberPass",true);
                            editor.putString("account",account);
                            editor.putString("password",password);
                        }else{
                            editor.clear();
                        }
                        editor.apply();
                        Intent i=new Intent(LoginActivity2.this,MainActivity.class);
                        startActivity(i);
                        finish();
                        /*使用这种方法只需知道传的参数类型和个数*/
//                   MainActivity.actionStart(login.this,"admin","123456");
                    }else{
                        Toast.makeText(LoginActivity2.this,"admin or password is unavailable!",Toast.LENGTH_SHORT).show();
                    }
                }
    });
    }
}
