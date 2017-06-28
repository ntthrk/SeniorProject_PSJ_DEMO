package com.finalproject.ntthrk_win.psj_demo;


import android.gesture.Gesture;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class MEMO_detailFragment extends Fragment {

    private TextView NameSymbolTV;
    private ImageView SymbolImageIV;
    private TextView symbolDetailTV;
    private ListView textListLV;
    private Button deleteBT;
    private Button editBT;

    private MyGesture myGesture = new MyGesture();
    private ArrayAdapter<String> adapter ;
    private GesturesDBHelper dbHelper;
    //private GestureManagement gestureManagement;

    private String idSymbol;
    private String nameSymbol ;
    private String detailSymbol;
    private String [] txtList = {};

    public MEMO_detailFragment(String idSymbol){
        this.idSymbol = idSymbol;
    }
    /*public MEMO_detailFragment(String idSymbol,String nameSymbol,String detailSymbol,String[] txtList) {
        // Required empty public constructor
        this.idSymbol = idSymbol;
        this.nameSymbol = nameSymbol;
        this.detailSymbol = detailSymbol;
        this.txtList = txtList;

    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_memo_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NameSymbolTV = (TextView) view.findViewById(R.id.name_smybol);
        SymbolImageIV = (ImageView) view.findViewById((R.id.image_symbol));
        symbolDetailTV = (TextView) view.findViewById(R.id.symbol_detail);
        textListLV = (ListView) view.findViewById(R.id.text_list);
        deleteBT = (Button) view.findViewById(R.id.delete_BT);
        editBT = (Button) view.findViewById(R.id.edit_BT);
        Log.i("detailFr",idSymbol);
        dbHelper =  new GesturesDBHelper(getContext());
        myGesture = dbHelper.getValues(idSymbol);
        NameSymbolTV.setText(myGesture.getGestureName());
        //SymbolImageIV
        symbolDetailTV.setText(myGesture.getDetailGesture());

        txtList = myGesture.getTextGesture().toArray(new String[0]);
        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,txtList);
        textListLV.setAdapter(adapter);


        //deleteRecord(symbolId);

    }
}
