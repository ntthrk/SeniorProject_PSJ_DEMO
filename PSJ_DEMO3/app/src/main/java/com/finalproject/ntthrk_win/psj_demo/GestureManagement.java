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

import android.gesture.Prediction;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import android.content.Context;
public class GestureManagement  {

    public static  GestureManagement gestureManagement;
    private Context context;
    private boolean mDrawningStatus ;
    private static final File pathFile = new File(Environment.getExternalStorageDirectory() , "/gesturesPSJ");
    private static final String pathAddress = pathFile.getAbsolutePath();
    private static GestureLibrary mGestureLibrary ;
    private GesturesDBHelper gesturesDBHelper;


    protected File getPathGestureFile(){
        return pathFile;
    }

    protected String saveGesture(String mNameGesture, Gesture mGesture, String detail, ArrayList<String> textList, FragmentActivity activity){
        String id = null ;
        mGestureLibrary = GestureLibraries.fromFile(pathFile);
        Log.e("GestureManagement --> Gesture Path : ", pathAddress );

        gesturesDBHelper = new GesturesDBHelper(activity.getBaseContext());
        try {
            if(mNameGesture != null){
                id = gesturesDBHelper.addData(mNameGesture, detail, textList);
            }
            if(id != null){
                mGestureLibrary.addGesture(mNameGesture, mGesture);
                mGestureLibrary.save();
            }
        } catch (Exception e) {
            Log.e("Error SaveData!!!!", e.getMessage());
        }

        return  id;
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

    protected MyGesture loadGesture(String id, FragmentActivity activity){
        MyGesture myGesture = new MyGesture();
        gesturesDBHelper = new GesturesDBHelper(activity.getBaseContext());
        mGestureLibrary.load();
        if(id != null){
            myGesture = gesturesDBHelper.getValues(id);

            myGesture.setGesture( mGestureLibrary
                    .getGestures(myGesture
                            .getGestureName() )
                    .get(Integer.parseInt(id.trim()) )
                    );

        }

        return myGesture;
    }

    public String matchGesture(Gesture gesture) {
        if (mGestureLibrary.load()) {
            ArrayList<Prediction> predictions = mGestureLibrary.recognize(gesture);
            if (!predictions.isEmpty()) {
                Prediction prediction = predictions.get(0);
                if (prediction.score >= 1) {
                    Log.i( "mathGesture", prediction.name + "score > 1" );
                    return prediction.name;
                }
            }
        }
        return "";
    }



    private void resetDrawGesture(String mNameGesture,Gesture mGesture){

        mGestureLibrary.addGesture(mNameGesture, mGesture);

    }

    private void resetAll(){

    }
    /*private MyGesture getSmybolData(){
        gesturesDBHelper = new GesturesDBHelper(activity.getBaseContext());
        MyGesture myGesture = new MyGesture();
        myGesture = gesturesDBHelper.getValues(symbolId);
       // myGesture.setGesture( mGestureLibrary.getGestures(mNameGesture) );
        return myGesture;
    }*/
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
