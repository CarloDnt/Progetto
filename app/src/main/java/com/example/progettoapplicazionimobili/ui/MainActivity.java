package com.example.progettoapplicazionimobili.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.progettoapplicazionimobili.R;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button login;
    private RealmUtenti realmManipulator=RealmUtenti.getRealmInstance(getApplicationContext());
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
                validate(username.getText().toString(),password.getText().toString(),realmManipulator);
            }
        });
    }
    private void validate(String userName,String userPassword,RealmUtenti realm){
        RealmResults<UtentiApp> utente=realm.getUtentiPrecisi(userName);
        if(utente.size()!=0){
            String password=utente.get(1).getPasswordUtente();
            if(userPassword.equals(password)){
                Intent intent=new Intent(MainActivity.this, Home.class);
                startActivity(intent);
            }
        }
    };
    protected void onDestroy() {
        super.onDestroy();
        realmManipulator.chiudi();
    }
}
