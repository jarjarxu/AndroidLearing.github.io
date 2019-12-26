package com.example.webviewtext;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity2 extends BasicActivity{
    private EditText edit_account,edit_password;
    private Button login;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        edit_account=findViewById(R.id.edit_account);
        edit_password=findViewById(R.id.edit_password);
        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String account=edit_account.getText().toString();
                    String password=edit_password.getText().toString();
                    if(account.equals("a") && password.equals("123")){
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
