package com.example.progettoapplicazionimobili.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.progettoapplicazionimobili.R;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Date;

import io.realm.RealmResults;
import io.realm.internal.Context;

import static com.example.progettoapplicazionimobili.R.layout.activity_aggiungi_prodotto;

public class AggiungiProdotto extends AppCompatActivity {
    EditText nome;
    DatePicker scadenza;
    EditText quantita;
    EditText prezzo;
    Button add;
    ImageButton camera;
    private Bitmap img= BitmapFactory.decodeResource(getResources(), R.drawable.ic_diet);
    private RealmDispensa realmManipulator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_aggiungi_prodotto);
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
                        ,Integer.parseInt(quantita.getText().toString()),Integer.parseInt(prezzo.getText().toString()),add.getBackground(),realmManipulator);
            }
        });

        camera=findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
    }
    public void aggiungiProdotto(String nome, Date scadenza,Integer quantita,Integer prezzo,Drawable img,RealmDispensa realm){
        RealmResults<ProdottoDisp> r =realm.getNomeProdotto(nome);
        if(r.size()==0) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            this.img.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] bitmapdata = stream.toByteArray();
            ProdottoDisp prodotto = new ProdottoDisp(nome, quantita, prezzo, scadenza,bitmapdata);
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
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            this.img=imageBitmap;
            ImageButton a=findViewById(R.id.camera);
            a.setImageBitmap(imageBitmap);
        }
    }
}
