package com.example.progettoapplicazionimobili.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.progettoapplicazionimobili.R;

import static com.example.progettoapplicazionimobili.R.id.swipe;

public class CustomSwipe extends PagerAdapter {

    private int[] image_resources = {R.drawable.photo};
    private int swipeImage = R.drawable.swipe;
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
        return (view == (LinearLayout)o);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){

        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.viewpager_layout, container,false);
        //photocamera
        ImageView imageView = item_view.findViewById(R.id.image_view);
        //swipe image
        ImageView swipeView = item_view.findViewById(swipe);
        //text prodotto scadenza
        TextView textView = (TextView) item_view.findViewById(R.id.image_count);
        //text info prodotto
        TextView infoProduct = (TextView) item_view.findViewById(R.id.info);

        imageView.setImageResource(image_resources[position]);
        swipeView.setImageResource(swipeImage);
        textView.setText("Prodotto in scadenza");
        infoProduct.setText("Nome prodotto: Pasta"+"\n"+"Data scadenza: 29/04/2019");
        container.addView(item_view);

        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){

        container.removeView( (LinearLayout) object );

    }
}
