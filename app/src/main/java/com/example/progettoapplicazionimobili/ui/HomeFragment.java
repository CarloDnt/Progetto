package com.example.progettoapplicazionimobili.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.progettoapplicazionimobili.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.RealmResults;
import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment {
    private ImageButton add;
    private Button current_date;
    private TextView text;
    private Button lista_spesa;
    private TextView anteprima_spesa;

    //view pager
    private ViewPager viewPager;
    private CustomSwipe customSwipe;
    private RealmResults<ProdottoDisp> prodotti;
    private Dialog devento;

    private RealmResults <NotaLista> note;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        RealmLista manager = RealmLista.getRealmInstance(getContext());
        note=manager.getAllNotes();

        RealmDispensa dispManager=RealmDispensa.getRealmInstance(getContext());
        this.prodotti=dispManager.getAllProdotti();

        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Food Tracker");
        super.onViewCreated(view, savedInstanceState);
        add=(ImageButton) getView().findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getContext()!= null){
                    Intent intent=new Intent(getContext(), AggiungiProdotto.class);
                    startActivity(intent);
                }
            }
        });

        //Set current date
        Date date = new Date();
        SimpleDateFormat format_day = new SimpleDateFormat("dd");
        String day = format_day.format(date);
        SimpleDateFormat format_month = new SimpleDateFormat("MMMM");
        String month = format_month.format(date);

        //set font date
        SpannableString ss1 = new SpannableString(day+"\n"+month);
        ss1.setSpan(new RelativeSizeSpan(2f), 0, 2, 0);

        current_date = (Button) getView().findViewById(R.id.current_date);
        text = getView().findViewById(R.id.text_date_home);
        text.setText(ss1);
        //click on calendar
        current_date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(getContext()!= null){

                    BottomNavigationView navigation_bar = getActivity().findViewById(R.id.nav_view);
                    navigation_bar.setSelectedItemId(R.id.navigation_calendario);
                }
            }
        });

        //set lista spesa button
        lista_spesa = getView().findViewById(R.id.button_lista_home);

        //lista spesa text
        anteprima_spesa = getView().findViewById(R.id.anteprima_lista_home);
        anteprima_spesa.setText(""+note.size());
        //click on shopping cart
        anteprima_spesa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(getContext()!= null){
                    BottomNavigationView navigation_bar = getActivity().findViewById(R.id.nav_view);
                    navigation_bar.setSelectedItemId(R.id.navigation_lista);
                }
            }
        });

        //anteprima dispensa
        //VIEWPAGER
        viewPager = (ViewPager) getView().findViewById(R.id.viewpager_home);
        customSwipe = new CustomSwipe(getContext());
        viewPager.setAdapter(customSwipe);

        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator_home);
        indicator.setViewPager(viewPager);

        for(int i=0;i<prodotti.size();i++) {
            Long time = prodotti.get(i).getScadenza().getTime();
        }
        devento=new Dialog(getContext());

    } //end onViewCreated


}
