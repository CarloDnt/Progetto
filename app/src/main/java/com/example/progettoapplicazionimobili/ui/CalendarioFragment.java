package com.example.progettoapplicazionimobili.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.progettoapplicazionimobili.R;

public class CalendarioFragment extends Fragment {

    //VIEWPAGER
    private ViewPager viewPager;
    private CustomSwipe customSwipe;
    private ProgressBar bar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Calendario Scadenze");
        return inflater.inflate(R.layout.fragment_calendario,container,false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        //VIEWPAGER
        viewPager = (ViewPager) getView().findViewById(R.id.viewpager);
        customSwipe = new CustomSwipe(getContext());
        viewPager.setAdapter(customSwipe);
        bar=getView().findViewById(R.id.barprogress);
        bar.setMax(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                bar.setProgress(viewPager.getCurrentItem(),true);
            }
            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bar.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }
}
