package com.example.vlad.costproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.vlad.costproject.bd.DBHelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


Button buttonBuyList;
Button buttonFastBuy;


Button buttonNameList;
Button buttonClearList;
Button buttonDeleteList;

EditText nameList;

DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        buttonBuyList=(Button) findViewById(R.id.MainbuttonBuyList);
        buttonBuyList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBuyList=new Intent(MainActivity.this,BuyListActivity.class);
                startActivity(intentBuyList);
            }
        });
        buttonFastBuy= (Button) findViewById(R.id.MainbuttonFastBuy);
        buttonFastBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFastBuy=new Intent(MainActivity.this,FastBuyActivity.class);
                startActivity(intentFastBuy);



                //название и удаление листов с покупками
                setContentView(R.layout.content_buy_list_name);

                buttonNameList = (Button) findViewById(R.id.buttonNameList);
                buttonNameList.setOnClickListener(this);

                buttonClearList = (Button) findViewById(R.id.buttonClearList);
                buttonClearList.setOnClickListener(this);

                buttonDeleteList= (Button) findViewById(R.id.buttonDeleteList);
                buttonDeleteList.setOnClickListener(this);

                nameList = (EditText) findViewById(R.id.nameList);

                dbHelper = new DBHelper(this);
                //

            }
        });
    }



    //Buttin_on_Back
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //боковое меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        } else if (id == R.id.nav_fastbuy) {
            Intent intentFastBuy=new Intent(MainActivity.this,FastBuyActivity.class);
            startActivity(intentFastBuy);
        } else if (id == R.id.nav_buylist) {

            Intent intentBuyList=new Intent(MainActivity.this,BuyListActivity.class);
            startActivity(intentBuyList);

        } else if (id == R.id.nav_archive) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    @Override
    public void onClick(View v){

        String nameListEdit = nameList.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        switch (v.getId()) {

            case R.id.buttonNameList:
                //дествия по нажатию buttonNameList

                contentValues.put(DBHelper.KEY_NAME, nameListEdit);
                database.insert(DBHelper.TABLE_NAMES, null, contentValues);

                break;

            case R.id.buttonClearList:
                //дествия по нажатию buttonClearList

                database.delete(DBHelper.TABLE_NAMES, null, null);

                break;

            case R.id.buttonDeleteList:
                //дествия по нажатию кнопки buttonDeleteList

                int delCount = database.delete(DBHelper.TABLE_NAMES, DBHelper.KEY_NAME + " = ", new String[]{nameListEdit});
                Log.d("mLog", "delete list = " + delCount);

                break;

        }
        dbHelper.close();
    }


}












