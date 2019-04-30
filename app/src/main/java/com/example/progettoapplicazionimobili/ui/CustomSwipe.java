package com.example.progettoapplicazionimobili.ui;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.icu.text.DateFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.PagerAdapter;
import com.example.progettoapplicazionimobili.R;
import java.util.*;
import io.realm.RealmResults;

public class CustomSwipe extends PagerAdapter {

    private Context ctx;
    private LayoutInflater layoutInflater;
    private RealmResults<ProdottoDisp> prodotti;

    public CustomSwipe(Context ctx){
        this.ctx = ctx;
        RealmDispensa dispManager=RealmDispensa.getRealmInstance(this.ctx);
        this.prodotti=dispManager.getAllProdotti().sort("scadenza");
    }

    @Override
    public int getCount() {
        if(prodotti.size()<10){
        return prodotti.size();}else{return 10;}
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == o);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
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
        TextView data = item_view.findViewById(R.id.et_date);

        byte[] img=prodotti.get(position).getImg();
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(img, 0, img.length));
        nomeProdotto.setText("Prodotto: ");
        nome.setText(prodotti.get(position).getNomeProdotto());
        dataScadenza.setText("Data scadenza: ");
        Date scadenza=prodotti.get(position).getScadenza();
        String scadenzatxt= DateFormat.getDateInstance(DateFormat.LONG).format(scadenza);
        data.setText(scadenzatxt);
        container.addView(item_view);

        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){

        container.removeView( (LinearLayout) object );

    }
}
