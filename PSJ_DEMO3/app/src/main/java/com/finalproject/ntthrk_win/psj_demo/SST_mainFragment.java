package com.finalproject.ntthrk_win.psj_demo;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class SST_mainFragment extends Fragment {

    private TextView voiceText;
    private android.support.design.widget.FloatingActionButton talkButton;
    private android.support.design.widget.FloatingActionButton resetButton;
    private final int REQUEST_VOIC_RECOGINITION = 10101;

    public SST_mainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sst_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        voiceText = (TextView) view.findViewById(R.id.textShow);

        talkButton = (android.support.design.widget.FloatingActionButton) view.findViewById(R.id.talk_Button);
        talkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSpeechInput();
            }
        });

        resetButton = (android.support.design.widget.FloatingActionButton) view.findViewById(R.id.reset_Button);
        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                voiceText.setText("Show Text");
            }
        });
    }

    public void startSpeechInput(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "th-TH");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "พูดที่นี่");
        try{
            startActivityForResult(intent, REQUEST_VOIC_RECOGINITION);
        }catch (ActivityNotFoundException a){


        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_VOIC_RECOGINITION &&
                resultCode == RESULT_OK &&
                data != null) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String text = result.get(0);
            voiceText.setText(text);

        }
    }
}
