package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
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








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        final DatabaseClass db = new DatabaseClass(this);



        final EditText title = (EditText) findViewById(R.id.TitleEditText);
        final EditText memo = (EditText) findViewById(R.id.MemoEditText);
        final Button saveBTN = (Button) findViewById(R.id.SaveBtn);

        Intent intent = getIntent();
        String id = intent.getStringExtra(Main2Activity.item_id);


        Cursor item = db.getItem(id);

        if(item!=null && item.getCount()>0){

            item.moveToFirst();
            title.setText(item.getString(item.getColumnIndex("Title")));
            memo.setText(item.getString(item.getColumnIndex("Memo")));
            Log.d("In getItemID and sucess", "found an item");

        }else{
            title.setHint("Titleee");
            memo.setHint("Memooo");
            Log.d("We are in activity3", "Couldnot find an item");

        }








        if (id == null){

            memo.setHint("Memo");
        } else {
            memo.setHint("Memooo");
        }

        //String[] listItem = new String[2];

       // listItem = item(item_id_inT);



        //mDatabaseHelper = new DatabaseClass(this);

        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Items item = new Items(title.getText().toString(), memo.getText().toString());

                // Trying to insert the user
                db.insertItem(item);
                showDbContent();
                Intent intent = new Intent(Main3Activity.this, Main2Activity.class);
                startActivity(intent);

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
