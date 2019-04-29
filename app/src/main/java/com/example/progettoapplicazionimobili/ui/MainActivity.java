package com.example.progettoapplicazionimobili.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.progettoapplicazionimobili.R;

import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private TextView registrati;
    private Button login;
    private RealmUtenti realmManipulator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realmManipulator=RealmUtenti.getRealmInstance(this);
        username=(EditText) findViewById(R.id.etUsername);
        password=(EditText) findViewById(R.id.etPasswordM);
        login=(Button) findViewById(R.id.btLogin);
        registrati=(TextView) findViewById((R.id.twregistrati));
        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Registrazione.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(username.getText().toString(),password.getText().toString(),realmManipulator);
            }
        });
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText a=findViewById(R.id.etUsername);
                a.setTextColor(Color.rgb(0,0,0));
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText a=findViewById(R.id.etPasswordM);
                a.setTextColor(Color.rgb(0,0,0));
            }
        });
    }
    private void validate(String userName,String userPassword,RealmUtenti realm){
        RealmResults<UtentiApp> utente=realm.getUtentiPrecisi(userName);
        if(utente.size()>0){
            String password=utente.get(0).getPasswordUtente();
            if(userPassword.equals(password)){
                Intent intent=new Intent(MainActivity.this, Home.class);
                startActivity(intent);
            }else{
                EditText a=findViewById(R.id.etPasswordM);
                a.setTextColor(Color.rgb(255,0,0));
            }
        }else{
            EditText a=findViewById(R.id.etUsername);
            a.setTextColor(Color.rgb(255,0,0));
        }
    };
    protected void onDestroy() {
        super.onDestroy();
        realmManipulator.chiudi();
    }
}
