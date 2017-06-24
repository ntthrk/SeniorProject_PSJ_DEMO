package com.finalproject.ntthrk_win.psj_demo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*GesturesDBHelper myDb = new GesturesDBHelper(this);*/
        //GesturesDBHelper gDB = new GesturesDBHelper(getApplicationContext());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void ttsPages(View view){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content,new TTS_mainFragment()).commit();
    }


    public void sttPages(View view){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, new SST_mainFragment()).commit();
    }

    public void  memoPages(View view){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, new MEMO_mainFragment()).commit();
    }
}
