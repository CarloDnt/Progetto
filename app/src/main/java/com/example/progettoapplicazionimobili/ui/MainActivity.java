package com.example.progettoapplicazionimobili.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.progettoapplicazionimobili.R;

public class MainActivity extends AppCompatActivity {
    //LOGIN
    private EditText username;
    private EditText password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=(EditText) findViewById(R.id.etUsername);
        password=(EditText) findViewById(R.id.etPassword);
        login=(Button) findViewById(R.id.btLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(username.getText().toString(),password.getText().toString());
            }
        });
    }

    private void validate(String userName,String userPassword){
        if((userName.equals("Admin")) && (userPassword.equals("1234"))){
            Intent intent=new Intent(MainActivity.this, Home.class);
            startActivity(intent);
        }
    };

} //end MainActivity
