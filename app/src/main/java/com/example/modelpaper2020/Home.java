package com.example.modelpaper2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.modelpaper2020.database.DBHelper;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void login (View view){
        DBHelper userDb = new DBHelper(this);
        SQLiteDatabase db = userDb.getReadableDatabase();

        EditText editUsername = (EditText) findViewById(R.id.editTextUsername);
        EditText editPassword = (EditText) findViewById(R.id.editTextPassword);

        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            User ob = userDb.readAllInfo(username);
            if (ob != null) {
                Toast.makeText(this, "user logged successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ProfileManagement.class);
                intent.putExtra("usernameExtra", ob.getUsername());
                intent.putExtra("passwordExtra", ob.getPassword());
                intent.putExtra("dobExtra", ob.getDob());
                intent.putExtra("genderExtra", ob.getGender());
                startActivity(intent);
            } else
                Toast.makeText(this, "error occurred", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "all the fields are required", Toast.LENGTH_SHORT).show();
        }
    }
    public void register(View view) {

        DBHelper userDb = new DBHelper(this);
        SQLiteDatabase db = userDb.getReadableDatabase();

        EditText editUsername = (EditText) findViewById(R.id.editTextUsername);
        EditText editPassword = (EditText) findViewById(R.id.editTextPassword);

        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {

            boolean status = userDb.addInfo(username, password, "", "");

            if (status) {
                Toast.makeText(this, "user registered successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ProfileManagement.class);
                intent.putExtra("usernameExtra", username);
                intent.putExtra("passwordExtra", password);
                intent.putExtra("dobExtra", "");
                intent.putExtra("genderExtra", "");
                startActivity(intent);
            } else
                Toast.makeText(this, "error occurred", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "all the fields are required", Toast.LENGTH_SHORT).show();
        }


    }
}
