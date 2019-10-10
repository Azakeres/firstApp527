package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {


    private EditText title, memo;
    List<Items> listItem;
    String id = "";
    String title_s;
    String memo_s;
    Cursor item = null;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        final DatabaseClass db = new DatabaseClass(this);

        final EditText title = (EditText) findViewById(R.id.TitleEditText);
        final EditText memo = (EditText) findViewById(R.id.MemoEditText);
        final Button saveBTN = (Button) findViewById(R.id.SaveBtn);

        Intent intent = getIntent();
        id = intent.getStringExtra(Main2Activity.item_id);
        if (id != null){
            item = db.getItem(id);
        }


        if(item!=null && item.getCount()>0){
            title_s = item.getString(item.getColumnIndex("Title"));
            memo_s = item.getString(item.getColumnIndex("Memo"));
            title.setText(title_s);
            memo.setText(memo_s);
            Log.d("Item returned Main3", "This many items" + Integer.toString(item.getCount()));

        }else {
            title.setHint("Title");
            memo.setHint("Memo");
            Log.d("We are in activity3", "Couldnot find an item");
        }


        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                title_s= title.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });
        memo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                memo_s=memo.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });



 /*       if(item!=null && item.getCount()>0){
            item.moveToFirst();
            title.setHint(item.getString(item.getColumnIndex("Title")));
            memo.setHint(item.getString(item.getColumnIndex("Memo")));
        }else{
            title.setHint("Titleee");
            memo.setHint("Memooo");
            Log.d("We are in activity3", "Couldnot find an item");

        }*/

        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id == null || id.length() == 0 ){
                    Items item = new Items(title_s, memo_s);
                    db.insertItem(item);
                    showDbContent();
                    Log.d("activity3", "inserting");
                    Intent intent = new Intent(Main3Activity.this, Main2Activity.class);
                    startActivity(intent);
                }else {
                    db.updateitem(title_s, memo_s, id);
                    Log.d("activity3", "updating");
                    Intent intent = new Intent(Main3Activity.this, Main2Activity.class);
                    id = null;
                    startActivity(intent);
                }


            }
        });

    }

    public void showDbContent(){

        DatabaseClass db = new DatabaseClass(this);
        // Fetching all the users from the list
        List<Items> items = db.getAllItems();
        int flag;

        // Iterating through each user in the list and printing the details of user
        for(flag=0; flag<items.size(); flag++) {

            Log.d("Title",   "<------>" + items.get(flag).getTitle()+"<----->"+"\t" + items.get(flag).getMemo());
        }

    }





}
