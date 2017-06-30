package com.finalproject.ntthrk_win.psj_demo;


import android.content.DialogInterface;
import android.gesture.Gesture;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import static android.R.attr.fragment;
import static android.R.attr.name;


public class MEMO_gestureManagementFragment extends Fragment {
    private static final float LENGTH_THRESHOLD = 120.0f;
    private Button nextBT;
    private Button backBT;
    private Gesture mGesture;
    private GestureOverlayView overlay;
    private MyGesture myGesture = new MyGesture();
    private GestureManagement gestureManagement;

    public MEMO_gestureManagementFragment(MyGesture myGesture) {
        this.myGesture = myGesture;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_memo_gesture_management, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        overlay = (GestureOverlayView) view.findViewById(R.id.gestures_overlay);
        overlay.addOnGestureListener(new  GesturesProcessor());

        nextBT = (Button) view.findViewById(R.id.next_TV);
        nextBT.setEnabled(false);
        nextBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (myGesture.getGestureName() != null) {
                    String id = null;
                    gestureManagement = new GestureManagement();

                    if(overlay.toString() != null ){

                        id = gestureManagement.saveGesture(
                                myGesture.getGestureName(), mGesture,
                                myGesture.getDetailGesture(), myGesture.getTextGesture(),
                                getActivity());

                        Log.e("MEMO_gestureManagementFragment : " , overlay.toString() );

                        if(id != null){
                            myGesture = null;
                            //Log.e("ggooooooooooooooooo",myGesture.getGestureName());
                            //next Page
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.content, new MEMO_detailFragment(id)).commit();

                        }else{
                            Log.e("MEMO_gestureManagementFragment : ","id null!!!");
                            //dialog ไม่สามารถ save คุณต้องการบันทึกใหม่หรือไม่
                            AlertDialog.Builder builder;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                            } else {
                                builder = new AlertDialog.Builder(getContext());
                            }
                            builder.setTitle("บันทึกไม่สำเร็จ !!!")
                                    .setMessage("คุณไม่สามารถบันทึกสัญลักษณ์เพิ่มได้ คุณต้องการดำเนินการใหม่หรือไม่")
                                    .setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            getActivity().getSupportFragmentManager().beginTransaction()
                                                .replace(R.id.content, new MEMO_managementFragment()).commit();

                                        }
                                    })
                                    .setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            getActivity().getSupportFragmentManager().beginTransaction()
                                                .replace(R.id.content, new MEMO_mainFragment()).commit();

                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();

                        }


                    }else {
                        Log.e("MEMO_gestureManagementFragment : ", "Gesture Null!!!" );
                    }

                } else {

                }

            }
        });


        backBT= (Button) view.findViewById(R.id.back_TV);
        backBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, new MEMO_managementFragment()).commit();*/
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
