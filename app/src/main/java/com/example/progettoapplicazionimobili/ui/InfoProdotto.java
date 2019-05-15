package com.example.progettoapplicazionimobili.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.progettoapplicazionimobili.R;

public class InfoProdotto extends Fragment {
    private ImageView immagineProdotto;
    private TextView nomeProdotto;
    private TextView quantità;
    private TextView scadenza;
    private TextView prezzo;
    private EditText info_quantità;
    private EditText info_scadenza;
    private EditText info_prezzo;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        getActivity().setTitle("Info Prodotto");
        return inflater.inflate(R.layout.info_prodotto,container,false);
    }

    @Nullable
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        setHasOptionsMenu(true);
        LinearLayout linearLayout = getView().findViewById(R.id.info_prodotto);
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        immagineProdotto = (ImageView) getView().findViewById(R.id.info_foto);
        nomeProdotto = (TextView) getView().findViewById(R.id.info_nome);
        quantità = (TextView) getView().findViewById(R.id.text_quantità);
        scadenza = (TextView) getView().findViewById(R.id.text_scadenza);
        prezzo = (TextView) getView().findViewById(R.id.text_prezzo);
        info_quantità = (EditText) getView().findViewById(R.id.info_quantità);
        info_scadenza = (EditText) getView().findViewById(R.id.info_scadenza);
        info_prezzo = (EditText) getView().findViewById(R.id.info_prezzo);
        linearLayout.addView(linearLayout);
    }
}