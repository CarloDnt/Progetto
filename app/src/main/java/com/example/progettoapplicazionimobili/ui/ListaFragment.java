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

import com.example.progettoapplicazionimobili.R;

import org.w3c.dom.Text;

public class ListaFragment extends Fragment {
    Context ctx;
    private int addImage = R.drawable.add;
    private int deleteImage = R.drawable.delete;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista,container,false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){

        LinearLayout linearLayout = getView().findViewById(R.id.gallery);


        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        for (int i = 0; i <= 20; i++){
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

}
