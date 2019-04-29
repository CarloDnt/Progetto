package com.example.progettoapplicazionimobili.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.progettoapplicazionimobili.R;

public class CalendarioFragment extends Fragment {

    //VIEWPAGER
    ViewPager viewPager;
    CustomSwipe customSwipe;
    Context appContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calendario,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        //VIEWPAGER
        viewPager = (ViewPager) getView().findViewById(R.id.viewpager);
        customSwipe = new CustomSwipe(getContext());
        viewPager.setAdapter(customSwipe);
    }

}
