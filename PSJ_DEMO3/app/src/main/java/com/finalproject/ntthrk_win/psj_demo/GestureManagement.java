package com.finalproject.ntthrk_win.psj_demo;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;

import java.io.File;
import java.util.ArrayList;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import android.content.Context;
public class GestureManagement  {

    private GestureLibrary mGestureLibrary;
    private boolean mDrawningStatus ;
    private File pathFile = new File(Environment.getExternalStorageDirectory() , "/gesturesPSJ");
    private  GesturesDBHelper gesturesDBHelper;

    public void saveGesture(String mNameGesture, Gesture mGesture, String detail, ArrayList<String> textList, FragmentActivity activity){


        final String path = new File(Environment.getExternalStorageDirectory(), "gesturesPSJ").getAbsolutePath();
        //final String path2 = new File(Environment.getExternalStorageDirectory(), "gesture").getAbsolutePath();
        Log.i("Gesture Path : ", path );
        //Log.e("Gesture",path2);

        if(mGestureLibrary == null){
            Log.i("Gesture : ","GestureLibrary is not null.");
            mGestureLibrary = GestureLibraries.fromFile(pathFile);
        }

        String ckAddData = null ;
        gesturesDBHelper = new GesturesDBHelper(activity.getBaseContext());
        try {
            if(mNameGesture != null){
                ckAddData = gesturesDBHelper.addData(mNameGesture, detail, textList);
            }
            if(ckAddData != null){
                mGestureLibrary.addGesture(mNameGesture , mGesture);
                mGestureLibrary.save();
            }
        } catch (Exception e) {
            Log.e("Error SaveData!!!!", e.getMessage());
        }
        //ex==============================================

        // First method
/*        try {
            myDb.getWritableDatabase();
            myDb.addData("BUKnut","WTF");
            ArrayList<String> a = myDb.getFriend("BUKnut");

            for (int i=0;i<=a.size();i++) {
                Log.e("resalut", a.get(i));
            }
        } catch (Exception e) {
            Log.e("Error qurey", e.getMessage());
        }*/
        //==================================================
    }

    private void resetDrawGesture(String mNameGesture,Gesture mGesture){

        mGestureLibrary.addGesture(mNameGesture, mGesture);

    }

    private void resetAll(){

    }
    private MyGesture getSmybolData(String symbolId,String mNameGesture, Gesture mGesture, FragmentActivity activity){
        gesturesDBHelper = new GesturesDBHelper(activity.getBaseContext());
        MyGesture myGesture = gesturesDBHelper.getValues(symbolId);
       // myGesture.setGesture( mGestureLibrary.getGestures(mNameGesture) );
        return myGesture;
    }
    private boolean deleteGesture(String symbolId,String mNameGesture, Gesture mGesture, FragmentActivity activity){
        boolean result = false;
        gesturesDBHelper = new GesturesDBHelper(activity.getBaseContext());

        if(symbolId != null){
            result = gesturesDBHelper.deleteData(symbolId);
            if(result){
                mGestureLibrary.removeGesture(mNameGesture , mGesture);
                mGestureLibrary.save();
            }
            result = true;
        }

        return result;
    }




}
