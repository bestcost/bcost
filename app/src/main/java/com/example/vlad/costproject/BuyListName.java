package com.example.vlad.costproject;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vlad.costproject.bd.DBHelper;

public class BuyListName extends AppCompatActivity {
    Button buttonNameList;
    Button buttonClearList;
    Button buttonDeleteList;

    EditText nameList;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_buy_list_name);
        nameList = (EditText) findViewById(R.id.nameList);
        dbHelper = new DBHelper(this);

        buttonNameList = (Button) findViewById(R.id.buttonNameList);
        buttonNameList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameListEdit = nameList.getText().toString();
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put(DBHelper.KEY_NAME, nameListEdit);
                database.insert(DBHelper.TABLE_NAMES, null, contentValues);

                dbHelper.close();

            }
        });

        buttonClearList = (Button) findViewById(R.id.buttonClearList);
        buttonClearList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameListEdit = nameList.getText().toString();
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                database.delete(DBHelper.TABLE_NAMES, null, null);

                dbHelper.close();

            }
        });

        buttonDeleteList= (Button) findViewById(R.id.buttonDeleteList);
        buttonDeleteList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameListEdit = nameList.getText().toString();
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                int delCount = database.delete(DBHelper.TABLE_NAMES, DBHelper.KEY_NAME + " = ", new String[]{nameListEdit});
                Log.d("mLog", "delete list = " + delCount);

                dbHelper.close();
            }
        });


        //


    }

    }
