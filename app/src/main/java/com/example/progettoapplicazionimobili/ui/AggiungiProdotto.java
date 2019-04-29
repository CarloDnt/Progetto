package com.example.progettoapplicazionimobili.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.progettoapplicazionimobili.R;

import java.util.Calendar;
import java.util.Date;

import io.realm.RealmResults;

public class AggiungiProdotto extends AppCompatActivity {
    EditText nome;
    DatePicker scadenza;
    EditText quantita;
    EditText prezzo;
    Button add;
    private RealmDispensa realmManipulator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_prodotto);
        realmManipulator=RealmDispensa.getRealmInstance(this);
        nome=(EditText)findViewById(R.id.etPNome);
        scadenza=(DatePicker)findViewById(R.id.etPScadenza);
        quantita=(EditText)findViewById(R.id.etPQuantita);
        prezzo=(EditText)findViewById(R.id.etPPrezzo);
        add=findViewById(R.id.addPro);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aggiungiProdotto(nome.getText().toString(),getDateFromDatePicker(scadenza)
                        ,Integer.parseInt(quantita.getText().toString()),Integer.parseInt(prezzo.getText().toString()),realmManipulator);
            }
        });
    }
    public void aggiungiProdotto(String nome, Date scadenza,Integer quantita,Integer prezzo,RealmDispensa realm){
        RealmResults<ProdottoDisp> r =realm.getNomeProdotto(nome);
        if(r.size()==0) {
            ProdottoDisp prodotto = new ProdottoDisp(nome, quantita, prezzo, scadenza);
            realm.addOrUpdateRealmList(prodotto);
        }else{
            EditText a=findViewById(R.id.etPNome);
            a.setTextColor(Color.rgb(255,0,0));
        }

    }
    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}
