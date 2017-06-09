package com.finalproject.ntthrk_win.psj_demo;


import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MEMO_managementFragment extends Fragment {
    private MyGesture myGesture;
    private Button nextBT;
    private EditText gestureName;
    private EditText detailGest;
    private ArrayList<String> textGesture;



    public MEMO_managementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_memo_management, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myGesture = new MyGesture();

        gestureName = (EditText) view.findViewById(R.id.nameGest_txt);
        detailGest = (EditText) view.findViewById(R.id.detailGest_txt);

        nextBT = (Button) view.findViewById(R.id.next_TV);
        nextBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myGesture.setName(gestureName.getText().toString());
                myGesture.setDetailGesture(detailGest.getText().toString());
                myGesture.setTextGesture(textGesture);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, new MEMO_gestureManagementFragment()).commit();
            }
        });
    }

}
