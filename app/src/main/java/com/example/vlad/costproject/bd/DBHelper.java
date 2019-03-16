package com.example.vlad.costproject.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

public class DBHelper extends SQLiteOpenHelper{


    public static final int DATEBASE_VERSION = 1;
    public static final String DATEBASE_NAME = "nameListBD";
    public static final String TABLE_NAMES = "namesLists";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";


    public DBHelper(View.OnClickListener context) {
        super((Context) context,DATEBASE_NAME, null, DATEBASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( TABLE_NAMES +  KEY_ID  +  KEY_NAME );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(TABLE_NAMES);

        onCreate(db);

    }
}
