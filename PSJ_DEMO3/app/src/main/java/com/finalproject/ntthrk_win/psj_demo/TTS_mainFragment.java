package com.finalproject.ntthrk_win.psj_demo;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;



public class TTS_mainFragment extends Fragment {
    private GestureLibrary gestureLibrary;
    private GestureOverlayView overlay;
    private String gestureName;
    private TextView nameGestureTV;
    private Button symbolConfirmBT ;

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
        gestureName = "กรุณาลากนิ้วเป็นสัญลักษณ์บนหน้าจอ";
        nameGestureTV = (TextView) view.findViewById(R.id.text_tts);

        gestureLibrary = GestureLibraries.fromFile(new GestureManagement().getPathGestureFile());
        gestureLibrary.load();

        overlay = (GestureOverlayView) view.findViewById(R.id.gestures_overlay);
        gestureName =  "โดนัท";
        nameGestureTV.setText(gestureName);

        symbolConfirmBT = (Button) view.findViewById(R.id.symbolConfirm_button);
        symbolConfirmBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(getContext(),ContentActivity.class);
//                intent.putExtra("key",gestureName);
//                startActivity(intent);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, new TTS_groupListFragment(gestureName)).commit();
            }
        });
    }

    public String matchGesture(Gesture gesture) {
        if (gestureLibrary.load()) {
            ArrayList<Prediction> predictions = gestureLibrary.recognize(gesture);
            if (!predictions.isEmpty()) {
                Prediction prediction = predictions.get(0);
                if (prediction.score >= 1) {
                    Log.i( "mathGesture", prediction.name + "score > 1" );
                    return prediction.name;
                }
            }
        }
        return "";
    }

}
