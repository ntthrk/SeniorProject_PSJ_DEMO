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

public class MEMO_listviewAdapter extends ArrayAdapter<MEMO_listviewAdapter.gestureArrView> {
    private Context context;
    private static List<gestureArrView> gestureArr ;

    public MEMO_listviewAdapter(Context context, ArrayList<gestureArrView> gestureArr  ){
        super(context, R.layout.fragment_memo_main, gestureArr);
        this.context = context;
        this.gestureArr = gestureArr;
    }

    @Override
    public int getCount() {
        return gestureArr.size();
    }

    @Override
    public gestureArrView getItem(int position) {
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
        gestureArrView g = new gestureArrView();

        if(view == null){

            v = mInflater.inflate(R.layout.memo_listview_layout, parent, false);

            TextView txtNameGroup = (TextView) v.findViewById(R.id.group_name1);
            TextView txtDetailGroup = (TextView) v.findViewById(R.id.group_name1);
            ImageView imageView = (ImageView) v.findViewById(R.id.gesture_image1);

            g.resGestureID = null;
            g.groupName = txtNameGroup;
            g.groupDetail = txtDetailGroup;
            g.gestureImage = imageView;

            v.setTag(g);
        }
        else{
            g = (gestureArrView) v.getTag();

        }
        return v;
    }

    class gestureArrView{
        public String resGestureID;
        public TextView groupName;
        public TextView groupDetail;
        public ImageView gestureImage;

    }
}
