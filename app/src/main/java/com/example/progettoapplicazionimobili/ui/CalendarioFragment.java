package com.example.progettoapplicazionimobili.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.progettoapplicazionimobili.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;


import java.util.Date;
import java.util.List;

import io.realm.RealmResults;
import me.relex.circleindicator.CircleIndicator;


public class CalendarioFragment extends Fragment {

    //VIEWPAGER
    private ViewPager viewPager;
    private CustomSwipe customSwipe;
    private ProgressBar bar;
    private RealmResults<ProdottoDisp> prodotti;
    private CompactCalendarView customCalendar;
    private TextView txtevento;
    private static final String TAG = "Calendario";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Calendario Scadenze");
        RealmDispensa dispManager=RealmDispensa.getRealmInstance(getContext());
        this.prodotti=dispManager.getAllProdotti();
        return inflater.inflate(R.layout.fragment_calendario,container,false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        //VIEWPAGER
        viewPager = (ViewPager) getView().findViewById(R.id.viewpager);
        customSwipe = new CustomSwipe(getContext());
        viewPager.setAdapter(customSwipe);

        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        customCalendar = (CompactCalendarView) getView().findViewById(R.id.compactcalendar_view);
        txtevento=getView().findViewById(R.id.Evento);

        for(int i=0;i<prodotti.size();i++) {
            Long time=prodotti.get(i).getScadenza().getTime();
            String nomeprodotto=prodotti.get(i).getNomeProdotto();
            Event ev1 = new Event(Color.GREEN, time, nomeprodotto);
            customCalendar.addEvent(ev1);
        }
        customCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = customCalendar.getEvents(dateClicked);
                if(events.size()>0) {
                    txtevento.setText("Scade: " + events.get(0).getData().toString());
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
            }
        });

    }
}
