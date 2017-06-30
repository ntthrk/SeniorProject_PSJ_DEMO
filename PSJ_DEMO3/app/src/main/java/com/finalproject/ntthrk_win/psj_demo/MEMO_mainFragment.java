package com.finalproject.ntthrk_win.psj_demo;


import android.gesture.GestureLibrary;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MEMO_mainFragment extends Fragment {
    private android.support.design.widget.FloatingActionButton addData;
    private Spinner searchSN;
    private android.support.v7.widget.SearchView groupTextSearch;
    private static ArrayList<MyGesture> gestureList;

    private MEMO_listviewAdapter memoListviewAdapter;

    private static ArrayList<MyGesture> myGesturesList;
    private GestureLibrary gestureLibrary;
    ListView listView;

    public MEMO_mainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_memo_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addData= (android.support.design.widget.FloatingActionButton) view.findViewById(R.id.group_add);
        listView = (ListView) view.findViewById(R.id.groupList_view1);

        myGesturesList = new ArrayList<>();

        MEMO_listviewAdapter adapter = new MEMO_listviewAdapter(getContext(), myGesturesList);
        listView.setAdapter(adapter);


        //listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                /*getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, new MEMO_detailFragment()).commit();*/
            }
        });

        //add Symbol Data
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, new MEMO_managementFragment()).commit();
            }
        });


    }
}
