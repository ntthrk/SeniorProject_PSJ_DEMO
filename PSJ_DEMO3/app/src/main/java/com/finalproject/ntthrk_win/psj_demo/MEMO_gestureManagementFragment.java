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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import static android.R.attr.fragment;
import static android.R.attr.name;


public class MEMO_gestureManagementFragment extends Fragment {
    private Button nextBT;
    private Button backBT;
    private String groupNameGest;

    private Gesture mGesture;


    public MEMO_gestureManagementFragment(String groupNameGest) {
        this.groupNameGest = groupNameGest;

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
        Log.e("msg" , groupNameGest);

        nextBT = (Button) view.findViewById(R.id.next_TV);
        nextBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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



}
