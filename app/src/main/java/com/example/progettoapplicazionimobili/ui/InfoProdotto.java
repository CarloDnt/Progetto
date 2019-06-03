package com.example.progettoapplicazionimobili.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.icu.text.DateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.progettoapplicazionimobili.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.RealmResults;

public class InfoProdotto extends AppCompatActivity {
    private CircleImageView immagineProdotto;
    private TextView nomeProdotto;
    private TextView quantità;
    private TextView scadenza;
    private TextView prezzo;
    private TextView info_quantità;
    private TextView info_scadenza;
    private TextView info_prezzo;
    private Button add_listaSpesa;

    private RealmResults<ProdottoDisp> prodotti;
    private RealmDispensa dispManager;
    private RealmLista listManager;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Info Prodotto");
        super.onCreate(savedInstanceState);
        this.dispManager=RealmDispensa.getRealmInstance(this);
        this.listManager=RealmLista.getRealmInstance(this);
        this.prodotti=dispManager.getAllProdotti();
        setContentView(R.layout.info_prodotto);

        final int indirizzo = getIntent().getExtras().getInt("indirizzo");

        immagineProdotto = findViewById(R.id.info_foto);
        byte[] img=prodotti.get(indirizzo).getImg();
        immagineProdotto.setImageBitmap(BitmapFactory.decodeByteArray(img, 0, img.length));


        nomeProdotto = findViewById(R.id.info_nome);
        nomeProdotto.setText(prodotti.get(indirizzo).getNomeProdotto());

        info_quantità = findViewById(R.id.info_quantità);
        info_quantità.setText(prodotti.get(indirizzo).getQuatita().toString());

        info_scadenza = findViewById(R.id.info_scadenza);
        Date scadenza = prodotti.get(indirizzo).getScadenza();
        String scadenzatxt = DateFormat.getDateInstance(DateFormat.LONG).format(scadenza);
        info_scadenza.setText(scadenzatxt);

        info_prezzo = findViewById(R.id.info_prezzo);
        info_prezzo.setText(prodotti.get(indirizzo).getPrezzo().toString());

        add_listaSpesa = findViewById(R.id.info_add);
        add_listaSpesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotaLista notaLista = new NotaLista(nomeProdotto.getText().toString(), Integer.parseInt(prodotti.get(indirizzo).getQuatita().toString()));
                listManager.addOrUpdateRealmList(notaLista);

                Intent intent=new Intent(InfoProdotto.this, Home.class);
                intent.putExtra("indirizzo", "lista");
                startActivity(intent);
            }
        });

    }

}