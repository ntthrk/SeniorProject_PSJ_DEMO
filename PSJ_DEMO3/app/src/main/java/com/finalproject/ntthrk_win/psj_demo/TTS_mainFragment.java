package com.finalproject.ntthrk_win.psj_demo;


import android.app.Activity;
import android.content.DialogInterface;
import android.gesture.GestureOverlayView;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Locale;



public class TTS_mainFragment extends Fragment {
    private Button symbolConfirmBT ;
    GestureOverlayView overlay;

    public TTS_mainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tts_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ///////////////////////////////////////////////////////////////////////
        //////Gesture Draw
        overlay = (GestureOverlayView) view.findViewById(R.id.gestures_overlay);


        ///////////////////////////////////////////////////////////////////////
        symbolConfirmBT = (Button) view.findViewById(R.id.symbolConfirm_button);



        symbolConfirmBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, new TTS_groupListFragment()).commit();
            }
        });
    }
}
