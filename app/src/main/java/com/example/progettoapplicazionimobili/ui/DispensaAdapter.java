package com.example.progettoapplicazionimobili.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.progettoapplicazionimobili.R;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.RealmResults;

public class DispensaAdapter extends RecyclerView.Adapter<DispensaAdapter.MyViewHolder> {
    private RealmResults<ProdottoDisp> mDataset;
    private RealmDispensa dispManager;
    private Context cxt;

    public Context getContext() {
        return cxt;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public LinearLayout foreground;
        public CircleImageView immagineprodotto;
        public TextView quantitaprodotto;
        public TextView nomeprodotto;
        public MyViewHolder(View v) {
            super(v);
            nomeprodotto=v.findViewById(R.id.textProdotto);
            quantitaprodotto=v.findViewById(R.id.et_prodotto);
            immagineprodotto=v.findViewById(R.id.imgProdotto);
            foreground=v.findViewById(R.id.riga_dispensa);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, AggiungiProdotto.class);
                    context.startActivity(intent);
                }
            });

        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public DispensaAdapter(RealmResults<ProdottoDisp> myDataset,RealmDispensa dispManagert,Context cxt) {
        mDataset = myDataset;
        dispManager=dispManagert;
        this.cxt=cxt;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DispensaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dispensa, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        //img prodotto
        byte[] img = mDataset.get(position).getImg();
        Bitmap immagine= BitmapFactory.decodeByteArray(img, 0, img.length);
        holder.immagineprodotto.setImageBitmap(immagine);

        //nome prodotto
        String nomep=mDataset.get(position).getNomeProdotto();
        holder.nomeprodotto.setText(nomep);

        //quantità prodotto
        String quantitap= mDataset.get(position).getQuatita().toString();
        holder.quantitaprodotto.setText(quantitap);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
    public void removeItem(int position) {
        dispManager.deleteProdotto(mDataset.get(position));
        mDataset=dispManager.getAllProdotti();
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }
}