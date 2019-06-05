package com.example.progettoapplicazionimobili.ui;

import android.content.Context;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.progettoapplicazionimobili.R;
import com.google.android.material.snackbar.Snackbar;

import io.realm.RealmResults;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.MyViewHolder> {
    private RealmResults<NotaLista> mDataset;
    private RealmLista dispManager;
    private Context cxt;

    public Context getContext() {
        return cxt;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView quantitaprodotto;
        public TextView nomeprodotto;
        public MyViewHolder(View v) {
            super(v);
            nomeprodotto=v.findViewById(R.id.text_list);
            quantitaprodotto=v.findViewById(R.id.quantita);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListaAdapter(RealmResults<NotaLista> myDataset,RealmLista dispManagert,Context cxt) {
        mDataset = myDataset;
        dispManager=dispManagert;
        this.cxt=cxt;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

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
        dispManager.deleteNote(mDataset.get(position));
        mDataset=dispManager.getAllNotes();

        notifyItemRemoved(position);
    }
    public void riordina(String parametro){
        mDataset=dispManager.getAllNotes().sort(parametro);
        notifyDataSetChanged();
    }

}