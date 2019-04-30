package com.example.progettoapplicazionimobili.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.*;
import com.example.progettoapplicazionimobili.R;

import org.w3c.dom.Text;

public class ListaFragment extends Fragment {
    Context ctx;
    private int addImage = R.drawable.add;
    private int deleteImage = R.drawable.delete;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        getActivity().setTitle("Lista della Spesa");
        return inflater.inflate(R.layout.fragment_lista,container,false);
    }

    @Nullable
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        setHasOptionsMenu(true);

        //layout lista fragment
        LinearLayout linearLayout = getView().findViewById(R.id.gallery);
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        for (int i = 0; i <= 20; i++){
            //layout righe prodotti lista
            View item_view = layoutInflater.inflate(R.layout.item_list, linearLayout, false);
            LinearLayout llayout = item_view.findViewById(R.id.linear_layout);

            if (i%2 == 0){
                llayout.setBackgroundColor(getResources().getColor(R.color.colorBackground));
            } else {
                llayout.setBackgroundColor(getResources().getColor(R.color.colorSecondary));
            }

            //text
            TextView textView = item_view.findViewById(R.id.text);
            textView.setText("Testo "+i);
            //quantita
            EditText quantita = item_view.findViewById(R.id.quantita);
            quantita.setText("...");
            //add
            ImageButton add = item_view.findViewById(R.id.add);
            add.setImageResource(addImage);
            //delete
            ImageButton delete = item_view.findViewById(R.id.delete);
            delete.setImageResource(deleteImage);

            linearLayout.addView(item_view);
        }

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu2, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * Gestisce il bottone di aggiunta elementi alla lista della spesa
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch(id)
        {
            case R.id.aggiungiElemento:
			/*
			 	Codice di gestione aggiunta lista
			 */
			    System.out.println("OK");
                break;

        }
        return false;
    }

}
