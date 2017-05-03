package com.finalproject.ntthrk_win.psj_demo;

import android.gesture.Gesture;

public class MyGesture {
    private Gesture gesture;
    private String gestureName;

    public MyGesture(Gesture gesture, String gestname){
        this.gesture = gesture;
        this.gestureName = gestname;
    }

    public Gesture getGesture(){
        return gesture;
    }

    public void setGesture(Gesture gesture){
        this.gesture = gesture;
    }

    public String getName(){
        return gestureName;
    }

    public void setName(String name){
        this.gestureName = name;
    }

}
