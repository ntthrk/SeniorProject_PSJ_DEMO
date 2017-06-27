package com.finalproject.ntthrk_win.psj_demo;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class TTS_groupListFragment extends Fragment {
    private ArrayAdapter<String> adapter;
    private String gestureName = null;
    private String[] txtList = {"ไปเชียงใหม่กันนะ", "ไปยะลาดีกว่า", "สวัสดีประเทศไทย",
            "ทดสอบระบบ", "หิวข้าวจัง", "ง่วงนอนแล้วนะ", "ปั่นงานวนไป" ,"ผัดกะเพรา 3 ถุง" ,"ยายมี ขายหอย ยายมอย ขายหมี"};
    private TextView symbolName;
    private ListView listView;
    private String[] textList;
    private GesturesDBHelper gesturesDBHelper;

    public TTS_groupListFragment(String gestureName) {
        // Required empty public constructor
        this.gestureName = gestureName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tts_group_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        symbolName = (TextView) view.findViewById(R.id.symbol_name);
        listView = (ListView) view.findViewById(R.id.listText);

        gesturesDBHelper = new GesturesDBHelper(getContext());
        symbolName.setText(gestureName);
        try {
            textList = gesturesDBHelper.loadListText(gestureName) ;
            Log.e("loadListText ss: ", textList.length+"");
        }catch (Exception e){
            Log.e("loadListText : ", e.getMessage());
        }

        adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, textList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
               //Toast.makeText(getContext(),txtList[position],Toast.LENGTH_LONG).show();

                MyTTS.getInstance(getContext())
//                      .setEngine("com.google.android.tts")​
                        .setLocale(new Locale("th"))
                        .speak(textList[position]);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, new TTS_textShowFragment(textList[position])).commit();

                /*AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(txtList[position]);
                builder.setPositiveButton("โต้ตอบ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.content, new SST_mainFragment()).commit();
                    }
                });
                builder.setNegativeButton("ปฏิเสธ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.dismiss();
                    }
                });
                builder.show();*/
            }
        });
    }

}
