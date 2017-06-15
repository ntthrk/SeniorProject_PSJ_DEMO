package com.finalproject.ntthrk_win.psj_demo;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.LogWriter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class MEMO_managementFragment extends Fragment {
    private MyGesture myGesture;
    private Button nextBT;
    private EditText gestureName;
    private EditText detailGest;
    private EditText textInput;
    private Button addTextBT;
    private ListView listTxtLV;
    private ArrayList<TextModel> textModelArr = new ArrayList<TextModel>();
    private CustomAdapter adapter;



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
        textInput = (EditText) view.findViewById(R.id.textInput);
        listTxtLV = (ListView) view.findViewById(R.id.list_txt);
        addTextBT = (Button) view.findViewById(R.id.addTextBT);
        nextBT = (Button) view.findViewById(R.id.next_TV);
        adapter = new CustomAdapter(getActivity(),textModelArr);
        listTxtLV.setAdapter(adapter);

        addTextBT.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String text = textInput.getText().toString();
                addText(text);
                Toast.makeText(getContext(), text,
                        Toast.LENGTH_SHORT).show();

            }
        });


        nextBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> textGestureArr = new ArrayList<String>();
                myGesture.setGestureName(gestureName.getText().toString());
                myGesture.setDetailGesture(detailGest.getText().toString());

                for (int i = 0; i< textModelArr.size(); i++){
                    textGestureArr.add(textModelArr.get(i).getText());
                }
                myGesture.setTextGesture(textGestureArr);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, new MEMO_gestureManagementFragment()).commit();
            }
        });
    }
    public void addText(String text) {

        if (text.isEmpty()) {
            Toast.makeText(getContext(), "กรุณาระบุข้อความ",
                    Toast.LENGTH_SHORT).show();
        } else {
            TextModel textModel = new TextModel(text);
            textModelArr.add(textModel);
            adapter.notifyDataSetChanged();
            textInput.setText("");
        }
    }

    private static class TextModel {
        String text;

        public TextModel(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    private class CustomAdapter extends BaseAdapter{
        Context context;
        ArrayList<TextModel> textList;

        public CustomAdapter(Context context, ArrayList<TextModel> textList) {
            this.context = context;
            this.textList = textList;
        }

        @Override
        public int getCount() {
            return textList.size();
        }

        @Override
        public Object getItem(int position) {
            return textList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = null;

            if (convertView == null) {

                LayoutInflater mInflater = (LayoutInflater) context
                        .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = mInflater.inflate(R.layout.text_group_layout, null);

                TextView txtGesture = (TextView) convertView.findViewById(R.id.txtGesture);
                Button removeBT = (Button) convertView.findViewById(R.id.removeBT);

                final TextModel textModel;
                textModel = textList.get(position);
                txtGesture.setText(textModel.getText());

                removeBT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textList.remove(position);
                        notifyDataSetChanged();
                    }
                });
            }
            return convertView;
        }
    }
}
