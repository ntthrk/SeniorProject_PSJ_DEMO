package com.finalproject.ntthrk_win.psj_demo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MEMO_listviewAdapter extends ArrayAdapter<MyGesture> {
    private Context context;
    private static ArrayList<MyGesture> gestureArr ;
    private TextView nameSymbolTV;
    private TextView detailSymbolTV;
    private ImageView imageSymbolTV;


    public MEMO_listviewAdapter(Context context, ArrayList<MyGesture> gestureArr  ){
        super(context, R.layout.fragment_memo_main, gestureArr);
        this.context = context;
        this.gestureArr = gestureArr;


    }

    @Override
    public int getCount() {
        return gestureArr.size();
    }

    @Override
    public MyGesture getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View v = view;
        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        MyGesture myGesture = new MyGesture();

        if(view == null){

            v = mInflater.inflate(R.layout.memo_listview_layout, null);

            nameSymbolTV = (TextView) v.findViewById(R.id.symbol_name1);
            detailSymbolTV = (TextView) v.findViewById(R.id.symbol_detail1);
            imageSymbolTV = (ImageView) v.findViewById(R.id.symbol_image1);


            try{

            }catch (Exception e){


            }

            v.setTag(myGesture);
        }
        else{
            myGesture = (MyGesture) v.getTag();

        }
        return v;
    }

}
