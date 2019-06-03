package com.example.progettoapplicazionimobili.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.*;
import com.example.progettoapplicazionimobili.R;

import io.realm.RealmResults;

public class ListaFragment extends Fragment {
    private Context ctx;
    private RealmResults<NotaLista> lista;
    private Dialog addDialog;
    private RealmLista listManager;
    private EditText nomeN;
    private EditText nN;
    private Fragment act;
    private RecyclerView recyclerView;
    private ListaAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        getActivity().setTitle("Lista della Spesa");
        listManager=RealmLista.getRealmInstance(getContext());
        this.lista=listManager.getAllNotes();
        return inflater.inflate(R.layout.fragment_lista,container,false);
    }

    @Nullable
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        setHasOptionsMenu(true);

        //layout lista fragment
        recyclerView = (RecyclerView) getView().findViewById(R.id.my_recycler_view_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ListaAdapter(lista,listManager,getContext());
        recyclerView.setAdapter(mAdapter);
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new ListSwipeToDelete(mAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        act=this;
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
                if(getContext()!=null){
                    addDialog=new Dialog(getContext());
                    addDialog=new Dialog(getContext());
                    addDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    showDialog();
                }
                break;

        }
        return true;
    }

    public void showDialog(){
        addDialog.setContentView(R.layout.aggiunginota);
        Button add=addDialog.findViewById(R.id.addNote);
        Button ext=addDialog.findViewById(R.id.noNote);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getContext()!= null){
                    nomeN=addDialog.findViewById(R.id.nomeNota);
                    nN=addDialog.findViewById(R.id.quantitaNota);
                    String nomeNota=nomeN.getText().toString();
                    int quantita=Integer.parseInt(nN.getText().toString());
                    NotaLista nuovanota=new NotaLista(nomeNota,quantita);
                    listManager.addOrUpdateRealmList(nuovanota);
                    addDialog.dismiss();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    if (Build.VERSION.SDK_INT >= 26) {
                        ft.setReorderingAllowed(false);
                    }
                    ft.detach(act).attach(act).commit();
                }
            }
        });
        ext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getContext()!= null){
                    addDialog.dismiss();
                }
            }
        });
        addDialog.show();
    }

}
