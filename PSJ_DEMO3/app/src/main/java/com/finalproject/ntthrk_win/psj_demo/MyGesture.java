package com.finalproject.ntthrk_win.psj_demo;

import android.gesture.Gesture;

import java.util.ArrayList;

public class MyGesture {
    protected static String id;
    protected static Gesture gesture;
    protected static String gestureName;
    protected static String detailGesture;
    protected static ArrayList<String> textGesture;

    public MyGesture(){

    }


    public MyGesture(Gesture gesture, String gestname){
        this.gesture = gesture;
        this.gestureName = gestname;
    }

    public MyGesture(Gesture gesture, String gestureName, String detailGesture, ArrayList<String> textGesture) {
        this.gesture = gesture;
        this.gestureName = gestureName;
        this.detailGesture = detailGesture;
        this.textGesture = textGesture;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        MyGesture.id = id;
    }

    public String getGestureName() {
        return gestureName;
    }

    public void setGestureName(String gestureName) {
        this.gestureName = gestureName;
    }

    public String getDetailGesture() {
        return detailGesture;
    }

    public void setDetailGesture(String detailGesture) {
        this.detailGesture = detailGesture;
    }

    public ArrayList<String> getTextGesture() {
        return textGesture;
    }

    public void setTextGesture(ArrayList<String> textGesture) {
        this.textGesture = textGesture;
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
