package com.finalproject.ntthrk_win.psj_demo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    private TextView NameSymbol;
    private ImageView SymbolImage;
    private TextView symbolDetail;
    private ListView textList;
    private Button deleteBT;
    private Button editBT;

    private MyGesture myGesture = new MyGesture();
    //private ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.);

    public MEMO_detailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_memo_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NameSymbol = (TextView) view.findViewById(R.id.name_smybol);
        SymbolImage = (ImageView) view.findViewById((R.id.image_symbol));
        symbolDetail = (TextView) view.findViewById(R.id.symbol_detail);
        textList = (ListView) view.findViewById(R.id.text_list);
        deleteBT = (Button) view.findViewById(R.id.delete_BT);
        editBT = (Button) view.findViewById(R.id.edit_BT);


        //dapter = new ArrayAdapter<String>(
        // this,
        // android.R.layout.simple_list_item_1,
        // myGesture.getTextGesture().listIterator());



        //deleteRecord(symbolId);



    }
}
