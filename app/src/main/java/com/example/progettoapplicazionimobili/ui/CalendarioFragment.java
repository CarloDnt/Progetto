package com.example.progettoapplicazionimobili.ui;

import android.app.Dialog;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.progettoapplicazionimobili.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.RealmResults;
import me.relex.circleindicator.CircleIndicator;


@RequiresApi(api = Build.VERSION_CODES.N)
public class CalendarioFragment extends Fragment {

    //VIEWPAGER
    private ViewPager viewPager;
    private CustomSwipe customSwipe;
    private ProgressBar bar;
    private RealmResults<ProdottoDisp> prodotti;
    private CompactCalendarView customCalendar;
    private Dialog devento;
    private static final String TAG = "Calendario";
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMMM yyyy" , Locale.getDefault());
    private TextView toolbar;

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
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState){
        //VIEWPAGER
        viewPager = (ViewPager) getView().findViewById(R.id.viewpager);
        customSwipe = new CustomSwipe(getContext());
        viewPager.setAdapter(customSwipe);

        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        customCalendar = (CompactCalendarView) getView().findViewById(R.id.compactcalendar_view);
        toolbar=getView().findViewById(R.id.calendarmonth);
        toolbar.setText(dateFormatForMonth.format(customCalendar.getFirstDayOfCurrentMonth()));

        for(int i=0;i<prodotti.size();i++) {
            Long time=prodotti.get(i).getScadenza().getTime();
            Event ev1 = new Event(Color.GREEN, time, i);
            customCalendar.addEvent(ev1);
        }
        devento=new Dialog(getContext());
        devento.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        customCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = customCalendar.getEvents(dateClicked);
                if(events.size()>0) {
                    int numero=Integer.parseInt(events.get(0).getData().toString());
                    byte[] img=prodotti.get(numero).getImg();
                    Date scadenza=prodotti.get(numero).getScadenza();
                    String scadenzatxt= DateFormat.getDateInstance(DateFormat.LONG).format(scadenza);
                    showDialog(view,prodotti.get(numero).getNomeProdotto(),scadenzatxt,img);
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                toolbar.setText(dateFormatForMonth.format(customCalendar.getFirstDayOfCurrentMonth()));
            }
        });

    }
    public void showDialog(View v,String nomep,String scadenza,byte[] img){
        TextView nomeprodotto;
        TextView datascadenza;
        ImageView imgprodotto;
        devento.setContentView(R.layout.showevento);
        nomeprodotto=devento.findViewById(R.id.npDialog);
        datascadenza=devento.findViewById(R.id.scaDialog);
        imgprodotto=devento.findViewById(R.id.imgDialog);
        nomeprodotto.setText(nomep);
        datascadenza.setText(scadenza);
        imgprodotto.setImageBitmap(BitmapFactory.decodeByteArray(img, 0, img.length));
        devento.setCanceledOnTouchOutside(true);
        devento.show();
    }
}
