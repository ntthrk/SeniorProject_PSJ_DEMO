package com.finalproject.ntthrk_win.psj_demo;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class GesturesDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "database_gesture_symbol";
    private static final int DB_VERSION = 1;

    public static final String TABLE_NAME1 ="symbol_table";
    public static final String COL_NAME_SYMBOL = "symbol_name";
    public static final String COL_DETAIL_SYMBOL = "symbol_detail";
    public static final String COL_CREATE_DATE = "create_symbol_date";
    public static final String COL_UPDATE_DATE = "update_symbol_date";

    public static final String TABLE_NAME2 ="text_table";
    public static final String COL_TEXT = "symbol_name";
    public static final String COL_ID_SYMBOL = "symbol_id";


    public GesturesDBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE" + TABLE_NAME1
                + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME_SYMBOL + "TEXT NOT NULL,"
                + COL_DETAIL_SYMBOL + "TEXT,"
                + COL_CREATE_DATE + "INTEGER NOT NULL, "
                + COL_UPDATE_DATE + "INTEGER NOT NULL,"
                + ");");

        db.execSQL("CREATE TABLE" + TABLE_NAME2
                        + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_TEXT + "TEXT NOT NULL,"
                + COL_ID_SYMBOL + "TEXT NOT NULL,"
                + ");");
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


}
