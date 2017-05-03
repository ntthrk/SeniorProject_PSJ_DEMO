package com.finalproject.ntthrk_win.psj_demo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class TTS_textShowFragment extends Fragment {
    private String txtText;
    private Button againBT;
    private Button speckBT;
    private TextView showText;

    public TTS_textShowFragment(String txtText) {
        // Required empty public constructor
        this.txtText = txtText;
    }
//
//    public TTS_textShowFragment(String txtText) {
//        // Required empty public constructor
//        this.txtText = txtText;
//    }
//


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tts_text_show, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showText = (TextView) view.findViewById(R.id.textShow);
        showText.setText(txtText);

        againBT = (Button) view.findViewById(R.id.again_button);

        againBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTTS.getInstance(getContext())
//                      .setEngine("com.google.android.tts")​
                        .setLocale(new Locale("th"))
                        .speak(txtText);
            }
        });

        speckBT = (Button) view.findViewById(R.id.talk_Button);

        speckBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, new SST_mainFragment()).commit();
            }
        });
    }
}
