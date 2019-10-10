package com.example.testapp;

//package com.mysql.demo.mysqldemo;

import android.content.ContentValues;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.HashMap;
import android.util.Log;
import android.widget.Toast;

public class DatabaseClass extends SQLiteOpenHelper{
    SQLiteDatabase database = this.getWritableDatabase();

    private static final String TABLE_NAME = "ACTIVITY_THREE";
    private static final String Item_db = "Item.db";


    private static final String Col1 = "Title";
    private static final String Col2 = "Memo";
    private static final String KEY_ID = "id";





    public DatabaseClass(Context context){
        super(context, Item_db, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL( "CREATE TABLE ACTIVITY_THREE (Title Text, Memo Text);");
        Log.d("DATABASE", "Database is created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Item_db);
        onCreate(db);
    }

    public void insertItem(Items items){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("Title", items.getTitle());
        contentValues.put("Memo", items.getMemo());

        db.insert("ACTIVITY_THREE", null, contentValues);
        Log.d("InsertItem", "inserted ");

        db.close();
    }


    public List<Items> getAllItems(){
        // Creating and opening a Readable SQLiteDatabase object
        Log.d("darabase", "getallitem ");

        SQLiteDatabase db = this.getReadableDatabase();

        // List to store all the Items
        List<Items> itemList = new ArrayList<Items>();

        // Querying the databse
        Cursor cursor = db.rawQuery("SELECT * FROM ACTIVITY_THREE ;", null);


        Log.d("WE AREW HERE", "Ran getallitem query");

        // Checking if the cursor's data is null
        if(cursor!=null && cursor.getCount()>0 && cursor.moveToFirst()){
            Log.d("Cursor", Integer.toString(cursor.getCount()));
            Integer course_count = cursor.getCount();
            for (int j = 0;j<course_count; j++ ){
                Items items = new Items();
                items.title = cursor.getString(0);
                items.memo = cursor.getString(1);
                itemList.add(items);
                cursor.moveToNext();
                Log.d("Cursor", "if passed");
            }

        }else {
            Log.d("Cursor", "else");
            itemList = null;
        }
        return itemList;
    }

    int getItemID(String name) {

        int item_id = -1;


        SQLiteDatabase db = this.getReadableDatabase();


        String query = "SELECT * FROM ACTIVITY_THREE WHERE rowid = " + name + ";";

        Log.d("getItemID", query);


        Cursor item = db.rawQuery(query, null);

        if(item!=null && item.getCount()>0){
            item.moveToFirst();
            item_id = item.getInt(0);
            Log.d("Database", "GetItemID");
        }else{
            Log.d("We are in getItemID", "Faild to get the id");

        }
        return item_id;

    }

    Cursor getItem(String id) {
        SQLiteDatabase db = this.getReadableDatabase();


        String query = "SELECT * FROM ACTIVITY_THREE WHERE rowid = " + id + ";";

        Log.d("database calss", query);

        Cursor item = db.rawQuery(query, null);

        if(item!=null && item.getCount()>0){
            item.moveToFirst();
            Log.d("sucess item id is", id);
        }else{
            Log.d("We are in getItem", "Faild to id");
        }
        return item;

    }

    public void updateitem(String title, String memo, String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        String titleUpdate = "UPDATE ACTIVITY_THREE SET Title = '"+title+"' WHERE rowid = '"+id+"' ;"  ;
        String memoUpdate = "UPDATE ACTIVITY_THREE SET Memo = '"+memo+"' WHERE rowid = '"+id+"' ;"  ;

        Log.d("Updateitem", "Updateing"+titleUpdate);
        Log.d("Updateitem", "Updateing"+memoUpdate);
        db.execSQL(titleUpdate);
        db.execSQL(memoUpdate);
        db.close();


    }

}

