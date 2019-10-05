package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String Name_Text = "com.example.testapp.Name_Text";
    public static final String Email_Text = "com.example.testapp.Email_Text";
    public static final String  Phone_Num  = "com.example.testapp.Phone_Num";


    String name, email;
    int phoneNum;

    EditText nameInput;
    EditText emailInput;
    EditText phoneInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = (EditText) findViewById(R.id.nameInput);
        emailInput = (EditText) findViewById(R.id.emailInput);
        phoneInput = (EditText) findViewById(R.id.phoneInput);
        Button submitButton =  (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityTwo();
            }
        });



    }

    public void openActivityTwo(){
        Intent intent = new Intent(this, Main2Activity.class);

        String nameTxt = nameInput.getText().toString();
        String emailTxt = emailInput.getText().toString();
        String  phoneNum = phoneInput.getText().toString();

        intent.putExtra(Name_Text, nameTxt);
        intent.putExtra(Email_Text, emailTxt);
        intent.putExtra(Phone_Num, phoneNum);
        startActivity(intent);

    }
}
