package com.example.progettoapplicazionimobili.ui;

import android.graphics.BitmapFactory;
import android.icu.text.DateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.progettoapplicazionimobili.R;

import java.util.Date;

import io.realm.RealmResults;

public class DispensaFragment extends Fragment {
    private RealmResults<ProdottoDisp> prodotti;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Dispensa");
        RealmDispensa dispManager=RealmDispensa.getRealmInstance(getContext());
        this.prodotti=dispManager.getAllProdotti();
        return inflater.inflate(R.layout.fragment_dispensa,container,false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        //layout dispensa fragment
        LinearLayout layoutDispensa = getView().findViewById(R.id.dispensa);
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        for (int i = 0; i <prodotti.size(); i++){
            //layout righe dispensa
            View viewDispensa = layoutInflater.inflate(R.layout.item_dispensa, layoutDispensa, false);
            LinearLayout layout_riga_dispensa = viewDispensa.findViewById(R.id.riga_dispensa);


            layout_riga_dispensa.setBackgroundColor(getResources().getColor(R.color.secondaryColor));


            //img prodotto
            ImageView imgProdotto = viewDispensa.findViewById(R.id.imgProdotto);
            byte[] img=prodotti.get(i).getImg();
            imgProdotto.setImageBitmap(BitmapFactory.decodeByteArray(img, 0, img.length));

            //nome prodotto
            TextView textProdotto = viewDispensa.findViewById(R.id.textProdotto);
            textProdotto.setText(prodotti.get(i).getNomeProdotto());

            //quantità prodotto
            EditText etProdotto = viewDispensa.findViewById(R.id.et_prodotto);
            etProdotto.setText(prodotti.get(i).getQuatita().toString());

            layoutDispensa.addView(viewDispensa);
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
     * Gestisce il bottone di aggiunta elementi alla dispensa
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch(id) {
            case R.id.aggiungiElemento:
                //gestione add Element
                System.out.println("Aggiungi elemento a dispensa");
                break;
        }
        return false;
    }

} //end class
