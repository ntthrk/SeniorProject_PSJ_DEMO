package com.finalproject.ntthrk_win.psj_demo;

import android.gesture.Gesture;
import android.media.Image;

import java.util.ArrayList;

public class MyGesture {
    protected String id;
    protected Gesture gesture;
    protected String gestureName;
    protected String detailGesture;
    protected ArrayList<Gesture> gArr;
    protected Image image;
    protected ArrayList<String> textGesture;

    public MyGesture(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Gesture getGesture() {
        return gesture;
    }

    public void setGesture(Gesture gesture) {
        this.gesture = gesture;
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

    public ArrayList<Gesture> getgArr() {
        return gArr;
    }

    public void setgArr(ArrayList<Gesture> gArr) {
        this.gArr = gArr;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ArrayList<String> getTextGesture() {
        return textGesture;
    }

    public void setTextGesture(ArrayList<String> textGesture) {
        this.textGesture = textGesture;
    }
}
