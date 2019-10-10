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
    protected void onResume() {
        super.onResume();
        listView.setEmptyView(listView);
        puplateListview();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        DatabaseClass db = new DatabaseClass(this);

        listView = (ListView) findViewById(R.id.listView);



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


        Log.d("WE AREW HERE", "getall item Main2");

        listView.setEmptyView(listView);
        puplateListview();







        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                int index = Integer.valueOf(Long.toString(l));
                int index1 = index + 1;
                Toast.makeText(Main2Activity.this, Long.toString(l), Toast.LENGTH_SHORT).show();
                String item_id_s = String.valueOf(index1);
                Log.d("The item db is", item_id_s);
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

    public void puplateListview(){
        final DatabaseClass db = new DatabaseClass(this);

        listItem = db.getAllItems();

        if (listItem != null) {
            title_txt = new String[listItem.size()];
            for(int j=0;j<listItem.size();j++)
            {
                title_txt[j]=listItem.get(j).getTitle();
                Log.d("WE AREW HERE", title_txt[j]);
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,title_txt);
            listView.setAdapter(adapter);
        }
    }

}
