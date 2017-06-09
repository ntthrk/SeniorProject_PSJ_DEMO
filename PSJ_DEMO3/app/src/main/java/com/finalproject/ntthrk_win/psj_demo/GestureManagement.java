package com.finalproject.ntthrk_win.psj_demo;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;

import java.io.File;
import java.util.ArrayList;
import android.os.Environment;

public class GestureManagement extends MyGesture {
    private GestureLibrary mGestureLibrary;

    private boolean mDrawningStatus ;
    private Gesture mGesture;
    private String mNameGesture;

    private File pathFile = new File(Environment.getExternalStorageDirectory() , "gesturePSJ");
    public GestureManagement(Gesture gesture, String gestname) {
        super(gesture, gestname);
    }


    private void saveGesture(){
        if(mGestureLibrary == null){
            mGestureLibrary = GestureLibraries.fromFile(pathFile);
        }

        mGestureLibrary.addGesture(mNameGesture , mGesture);
        mGestureLibrary.save();

    }

    private void resetDrawGesture(){

        mGestureLibrary.addGesture(mNameGesture, mGesture);

    }

    private void resetAll(){

    }
    private void deleteGesture(String mNameGesture ,Gesture mGesture){
        mGestureLibrary.removeGesture(mNameGesture , mGesture);
    }




}
