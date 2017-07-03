package com.finalproject.ntthrk_win.psj_demo;


import android.database.Cursor;
import android.gesture.GestureLibrary;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
    private GesturesDBHelper helper;
    private ListView listView;
    private String idSymbol = null;

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
        helper = new GesturesDBHelper(this.getContext());
        addData= (android.support.design.widget.FloatingActionButton) view.findViewById(R.id.group_add);
        listView = (ListView) view.findViewById(R.id.groupList_view1);

        myGesturesList = new ArrayList<>();
/*

        MEMO_listviewAdapter adapter = new MEMO_listviewAdapter(getContext(), myGesturesList);
        listView.setAdapter(adapter);
*/


        Cursor cursor = helper.SelectData();
        Log.e("Cursor Count :", cursor.getCount()+"");
        listView.setAdapter(new MEMO_listviewAdapter(getContext(), cursor,true));

        idSymbol = cursor.getString(cursor.getColumnIndex("_id"));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                idSymbol = null;
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, new MEMO_detailFragment(idSymbol)).commit();
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
