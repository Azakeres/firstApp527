package com.example.testapp;


import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {



    public static final String item_id = "com.example.testapp.item_id";
    public static final String memo_Text = "com.example.testapp.memo_Text";


    ListView listView;

    List<Items> listItem;
    String[] title_txt;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView = (ListView) findViewById(R.id.listView);

        final DatabaseClass db = new DatabaseClass(this);

        TextView phoneNumView = (TextView) findViewById(R.id.phoneNumView);
        TextView nameView = (TextView) findViewById(R.id.nameView);
        TextView emailView = (TextView) findViewById(R.id.emailView);
        Button addBTN = (Button) findViewById(R.id.addBTN);

        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.Name_Text);
        String email = intent.getStringExtra(MainActivity.Email_Text);
        String phone = intent.getStringExtra(MainActivity.Phone_Num);

        nameView.setText(name);
        emailView.setText(email);
        phoneNumView.setText(phone);

        listItem = db.getAllItems();
        Log.d("WE AREW HERE", "getall item Main2");
        title_txt = new String[listItem.size()];

        for(int j=0;j<listItem.size();j++)
        {
            title_txt[j]=listItem.get(j).getTitle();
            Log.d("WE AREW HERE", title_txt[j]);

        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,title_txt);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);

                Long text = listView.getItemIdAtPosition(i);



                Toast.makeText(Main2Activity.this, Long.toString(text), Toast.LENGTH_SHORT).show();

                int item_db_id = db.getItemID(Long.toString(text));
                Log.d("The row id is", Long.toString(item_db_id));


                String item_id_s = String.valueOf(item_db_id);
                intent.putExtra(item_id, item_id_s);
                startActivity(intent);



            }
        });

        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(in);
            }
        });
    }


}
