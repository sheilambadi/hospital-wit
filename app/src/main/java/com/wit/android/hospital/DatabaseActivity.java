package com.wit.android.hospital;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DatabaseActivity extends AppCompatActivity {
    EditText forUsername, forPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
    }

    public void registerUser(View view) {
        forUsername = (EditText) findViewById (R.id.username);
        forPassword = (EditText) findViewById (R.id.password);

        //convert to string
        String uName = forUsername.getText().toString();
        String pWord = forPassword.getText().toString();
    }
}
