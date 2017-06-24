package com.finalproject.ntthrk_win.psj_demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.provider.CallLog;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GesturesDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "DB_PSJ.db";
    private static final int DB_VERSION = 4;

    public static final String TABLE_NAME1 ="symbol_table";
    public static final String COL_NAME_SYMBOL = "symbol_name";
    public static final String COL_DETAIL_SYMBOL = "symbol_detail";
    public static final String COL_CREATE_DATE = "create_symbol_date";
    public static final String COL_UPDATE_DATE = "update_symbol_date";

    public static final String TABLE_NAME2 ="text_table";
    public static final String COL_TEXT = "symbol_text";
    public static final String COL_ID_SYMBOL = "symbol_id";

    private SQLiteDatabase db;


    public GesturesDBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("Path of Database : ",db.getPath().toString());

        db.execSQL("CREATE TABLE "+TABLE_NAME1+"(" +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME_SYMBOL + " TEXT NOT NULL, " +
                COL_DETAIL_SYMBOL + " TEXT, " +
                COL_CREATE_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                COL_UPDATE_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP " +
                ");");

/*        String CREATE_SYMBOL_TABLE =
                String.format("CREATE TABLE %s " +
                        "(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s TEXT)", DB_NAME,"_id", COL_NAME_SYMBOL, COL_DETAIL_SYMBOL);

          db.execSQL(CREATE_SYMBOL_TABLE);*/

/*        db.execSQL("CREATE TABLE " + TABLE_NAME1
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME_SYMBOL + "TEXT NOT NULL, "
                + COL_DETAIL_SYMBOL + "TEXT, "
                + COL_CREATE_DATE + "INTEGER NOT NULL, "
                + COL_UPDATE_DATE + "INTEGER NOT NULL, "
                + ")");*/

        db.execSQL("CREATE TABLE " + TABLE_NAME2 +
                "( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TEXT + " TEXT NOT NULL, " +
                COL_ID_SYMBOL + " TEXT NOT NULL" +
                ");");


        Log.i(getClass().getSimpleName(), "CREATE_TABLE");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(GesturesDBHelper.class.getName(),
                "Upgread database version from version" + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME2);
        onCreate(db);
    }

/*//    protected void insertSmybolTable(String name, String detail){
//        db.execSQL("INSERT INTO  "+ TABLE_NAME1 +
//                "("+ COL_NAME_SYMBOL +","+ COL_DETAIL_SYMBOL +") " +
//                "VALUES ('"+ name +"','"+ detail +"');");
//
//
//    }



/*
    protected void rawQueryDB(String TABLE_NAME,String Where, String[] value){
        mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME1 +
                " WHERE "+ COL_NAME_SYMBOL +" = ? ", new String[] {name},null);
    }
*/


    public String addData(String name,String detail,ArrayList<String> textList) {
        //boolean finish = false;

        db = this.getWritableDatabase();

        Cursor mCursor;
        String id_symbol = null;

        ArrayList<String> idSymbolList;
        //------------------------------------------------------------------------//

        // insert smybol_table

        db.execSQL("INSERT INTO  "+ TABLE_NAME1 +
                "("+ COL_NAME_SYMBOL +","+ COL_DETAIL_SYMBOL +") " +
                "VALUES ('"+ name +"','"+ detail +"');");

        //get id_smybol_table
        mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME1 +
                " WHERE "+ COL_NAME_SYMBOL +" = ? ", new String[] {name},null);

        idSymbolList = new ArrayList<String>();
        mCursor.moveToFirst();
        while ( !mCursor.isAfterLast() ){
            idSymbolList.add(mCursor.getString(0));
            Log.i("get_id_Symbol : ",mCursor.getString(0));
            mCursor.moveToNext();

        }
        mCursor.close();
        //insert text_table
        if(idSymbolList.size() == 1){
            id_symbol = idSymbolList.get(0);
            if(id_symbol != null){

                int index = 0;
                while (index < textList.size()){
                    Log.i("Textinsert : ",textList.get(index));
                    db.execSQL("INSERT INTO  "+ TABLE_NAME2 +
                           " ( "+COL_TEXT+" , "+ COL_ID_SYMBOL +" ) " +
                           "VALUES ('"+ textList.get(index) +"','"+ id_symbol +"');");
                    index++;
                }

            }

        }


        //ex=============================================
/*      ContentValues values = new ContentValues();
        values.put(COL_NAME_SYMBOL, name);
        values.put(COL_DETAIL_SYMBOL, detail);
        db.insert(TABLE_NAME1, null, values);*/

        db.close(); // Closing database connection
        return id_symbol;
    }

    public MyGesture getValues(String id){
        db = this.getReadableDatabase();
        MyGesture myGesture = new MyGesture();
        ArrayList<String> textList = new ArrayList<>();
        int count = -1 ;
        Cursor cursorOfSymbolTB;
        Cursor cursorOfTextTB;

        cursorOfSymbolTB = db.rawQuery(
                "SELECT * FROM " + TABLE_NAME1 +
                " WHERE _id = ? ", new String[] {id},null);

        String[] colOfSymbol =  cursorOfSymbolTB.getColumnNames();
        count = cursorOfSymbolTB.getCount();
        Log.i("Colum of Symbol Table : ", colOfSymbol.toString()+" count :"+ count);

        if(cursorOfSymbolTB != null && count == 1){
            myGesture.setId(cursorOfSymbolTB.getString(cursorOfSymbolTB.getColumnIndex("_id")));
            myGesture.setGestureName(cursorOfSymbolTB.getString(cursorOfSymbolTB.getColumnIndex(COL_NAME_SYMBOL)));
            myGesture.setDetailGesture(cursorOfSymbolTB.getString(cursorOfSymbolTB.getColumnIndex(COL_DETAIL_SYMBOL)));
        }else{

        }

        count = -1 ;
        cursorOfTextTB = db.rawQuery(
                "SELECT * FROM " + TABLE_NAME2 +
                        " WHERE "+ COL_ID_SYMBOL +" = ? ", new String[] {id},null);
        count = cursorOfTextTB.getCount();
        Log.i("Size of Text Table : ",count+"");
        if(count > -1 ){
            cursorOfTextTB.moveToFirst();
            while ( !cursorOfTextTB.isAfterLast() ){
                textList.add(cursorOfTextTB.getString(1));
                Log.i("Text : ",cursorOfTextTB.getString(1));
                cursorOfTextTB.moveToNext();
            }
        }


        myGesture.setTextGesture(textList);

        cursorOfSymbolTB.close();
        return myGesture;
    }

    public boolean deleteData(String id) {
        boolean result = false;
        db = this.getWritableDatabase();

        if(id != null){
            //delete row in symbol table
            db.execSQL("DELETE FROM "+TABLE_NAME1+" where _id = ? ", new String[] { id });
            db.execSQL("DELETE FROM "+TABLE_NAME2+" where "+COL_ID_SYMBOL+" = ? ", new String[] { id });
        }

        db.close();
        return  result;
    }

   /* public ArrayList<String> getFriend(String name) {
        Cursor mCursor;
        SQLiteDatabase db = this.getWritableDatabase();
        mCursor = db.rawQuery("SELECT * FROM database_gesture_symbolss",null);



        ArrayList<String> dirArray = new ArrayList<String>();


        mCursor.moveToFirst();

        while ( !mCursor.isAfterLast() ){
            dirArray.add(mCursor.getString(1));
            mCursor.moveToNext();
        }
        return dirArray;
    }*/



}
