package com.finalproject.ntthrk_win.psj_demo;


import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MEMO_listviewAdapter extends CursorAdapter{


    public MEMO_listviewAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.memo_listview_layout,parent,false);
        bindView(view, context, cursor);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String SymbolId = cursor.getString(cursor.getColumnIndex("_id"));
        MyGesture myGesture = new MyGesture();
        ImageView img = (ImageView) view.findViewById(R.id.symbol_image);
        TextView nameSymbol = (TextView) view.findViewById(R.id.symbol_name);
        TextView detailSymbol = (TextView) view.findViewById(R.id.symbol_detail);

       /* Bitmap image = myGesture.getGesture().toBitmap(30, 30, 6, Color.RED);
        img.setImageBitmap(image);*/

        nameSymbol.setText(cursor.getString(cursor.getColumnIndex("symbol_name")));
        detailSymbol.setText(cursor.getString(cursor.getColumnIndex("symbol_detail")));

    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    /*public class Holder{
        ImageView imageViewIV;
        TextView nameSymbolTV;
        TextView detailSymbolTV;

    }*/
}

/*
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
        return position;
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
*/
