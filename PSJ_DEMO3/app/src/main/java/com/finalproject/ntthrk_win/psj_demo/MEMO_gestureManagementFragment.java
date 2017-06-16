package com.finalproject.ntthrk_win.psj_demo;


import android.gesture.Gesture;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import static android.R.attr.fragment;
import static android.R.attr.name;


public class MEMO_gestureManagementFragment extends Fragment {
    private static final float LENGTH_THRESHOLD = 120.0f;
    private Button nextBT;
    private Button backBT;
    private Gesture mGesture;
    private MyGesture myGesture;
    private GestureOverlayView overlay;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_memo_gesture_management, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myGesture = new MyGesture();

        overlay = (GestureOverlayView) view.findViewById(R.id.gestures_overlay);
        overlay.addOnGestureListener(new  GesturesProcessor());

        nextBT = (Button) view.findViewById(R.id.next_TV);
        nextBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Gesture" , overlay.toString() );
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, new MEMO_detailFragment()).commit();
            }
        });


        backBT= (Button) view.findViewById(R.id.back_TV);
        backBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, new MEMO_managementFragment()).commit();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if(mGesture != null){
            outState.putParcelable("gesture" , mGesture);
        }
    }

    private class GesturesProcessor implements GestureOverlayView.OnGestureListener {


        @Override
        public void onGestureStarted(GestureOverlayView overlay, MotionEvent event) {
            nextBT.setEnabled(false);
            mGesture = null;
        }

        @Override
        public void onGesture(GestureOverlayView overlay, MotionEvent event) {

        }

        @Override
        public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) {
            mGesture = overlay.getGesture();
            if(mGesture.getLength() < LENGTH_THRESHOLD){
                overlay.clear(false);
            }
            nextBT.setEnabled(true);
        }

        @Override
        public void onGestureCancelled(GestureOverlayView overlay, MotionEvent event) {

        }
    }
}
