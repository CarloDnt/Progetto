package com.example.progettoapplicazionimobili.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progettoapplicazionimobili.R;

import io.realm.RealmResults;

public class DispensaFragment extends Fragment {
    private RecyclerView recyclerView;
    private DispensaAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private RealmResults<ProdottoDisp> prodotti;
    private RealmDispensa dispManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Dispensa");
        this.dispManager=RealmDispensa.getRealmInstance(getContext());
        this.prodotti=dispManager.getAllProdotti();
        return inflater.inflate(R.layout.fragment_dispensa,container,false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        //layout dispensa fragment
        recyclerView = (RecyclerView) getView().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new DispensaAdapter(prodotti,dispManager,getContext());
        recyclerView.setAdapter(mAdapter);
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new DispSwipeToDelete(mAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        mAdapter.riordina("nomeProdotto");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu2, menu);
        MenuItem item = menu.findItem(R.id.spinner);
        final Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.spinner_list_item_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String opzione=spinner.getSelectedItem().toString();
                System.out.println(opzione);
                switch(opzione){
                    case "Nome":
                        mAdapter.riordina("nomeProdotto");
                        break;

                    case "Data":
                        mAdapter.riordina("scadenza");
                        break;

                    case "Prezzo":
                        mAdapter.riordina("prezzo");
                        break;

                    case "Quantita":
                        mAdapter.riordina("quatita");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
                Intent intent=new Intent(getContext(), AggiungiProdotto.class);
                startActivity(intent);
                break;
        }
        return false;
    }

} //end class