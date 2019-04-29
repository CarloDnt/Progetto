package com.example.progettoapplicazionimobili.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.progettoapplicazionimobili.R;

public class Registrazione extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private EditText password2;
    private Button registrati;
    private RealmUtenti realmManipulator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);
        realmManipulator=RealmUtenti.getRealmInstance(this);
        username=(EditText)findViewById(R.id.etUsername);
        password=(EditText)findViewById(R.id.etPassword);
        password2=(EditText)findViewById(R.id.etPassword2);
        registrati=(Button)findViewById(R.id.btAggiungi);
        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registraUtente(username.getText().toString(),password.getText().toString(),password2.getText().toString(),realmManipulator);
            }
        });
    }
    public void registraUtente(String username,String password,String password2,RealmUtenti realm){
        if(password.equals(password2)){
            UtentiApp utente=new UtentiApp(username,password);
            realm.addOrUpdateRealmList(utente);
            Intent intent=new Intent(Registrazione.this, MainActivity.class);
            startActivity(intent);
        }else{
            EditText a=findViewById(R.id.etPassword);
            EditText b=findViewById(R.id.etPassword2);
            a.setTextColor(Color.rgb(255,0,0));
            b.setTextColor(Color.rgb(255,0,0));
        }
    }
}
