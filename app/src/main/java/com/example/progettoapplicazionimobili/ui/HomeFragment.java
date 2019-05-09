package com.example.progettoapplicazionimobili.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.progettoapplicazionimobili.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Objects;

import io.realm.RealmResults;

public class HomeFragment extends Fragment {
    private ImageButton add;
    private Button current_date;
    private TextView text;
    private Button lista_spesa;
    private TextView anteprima_spesa;

    private RealmResults <NotaLista> note;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        RealmLista manager = RealmLista.getRealmInstance(getContext());
        note=manager.getAllNotes();
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Home Page");
        super.onViewCreated(view, savedInstanceState);
        add=(ImageButton) getView().findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getContext()!= null){
                    Intent intent=new Intent(getContext(), AggiungiProdotto.class);
                    startActivity(intent);
                }
            }
        });

        //Set current date
        Date date = new Date();
        SimpleDateFormat format_day = new SimpleDateFormat("dd");
        String day = format_day.format(date);
        SimpleDateFormat format_month = new SimpleDateFormat("MMMM");
        String month = format_month.format(date);

        //set font date
        SpannableString ss1 = new SpannableString(day+"\n"+month);
        ss1.setSpan(new RelativeSizeSpan(2f), 0, 2, 0);

        current_date = (Button) getView().findViewById(R.id.current_date);
        text = getView().findViewById(R.id.text_date_home);
        text.setText(ss1);
        //click on calendar
        current_date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(getContext()!= null){
                    getFragmentManager().beginTransaction().replace(R.id.fragment_home, new CalendarioFragment()).commit();
                }
            }
        });

        //set lista spesa button
        lista_spesa = (Button) getView().findViewById(R.id.button_lista_home);

        //lista spesa text
        anteprima_spesa = getView().findViewById(R.id.anteprima_lista_home);
        anteprima_spesa.setText(""+note.size());
        //click on shopping cart
        anteprima_spesa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(getContext()!= null){
                    getFragmentManager().beginTransaction().replace(R.id.fragment_home, new ListaFragment()).commit();
                }
            }
        });
    } //end onViewCreated

}
