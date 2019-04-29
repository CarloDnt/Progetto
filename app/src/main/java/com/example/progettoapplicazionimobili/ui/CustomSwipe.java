package com.example.progettoapplicazionimobili.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import com.example.progettoapplicazionimobili.R;

import java.util.*;

public class CustomSwipe extends PagerAdapter {

    private int[] image_resources = {R.drawable.fake1, R.drawable.fake2, R.drawable.fake3};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public CustomSwipe(Context ctx){
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return image_resources.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == o);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){

        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.viewpager_layout, container,false);
        //photocamera
        ImageView imageView = (ImageView) item_view.findViewById(R.id.image_view);
        //NomeProdotto
        TextView nomeProdotto = item_view.findViewById(R.id.nome_prodotto);
        //Nome
        TextView nome = item_view.findViewById(R.id.nome);
        //DataScadenza
        TextView dataScadenza = item_view.findViewById(R.id.data_scadenza);
        //Date
        EditText data = item_view.findViewById(R.id.et_date);

        imageView.setImageResource(image_resources[position]);
        nomeProdotto.setText("Prodotto: ");
        nome.setText("...");
        dataScadenza.setText("Data scadenza: ");
        data.setText("...");
        container.addView(item_view);

        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){

        container.removeView( (LinearLayout) object );

    }
}
