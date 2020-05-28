package com.example.modelpaper2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class ProfileManagement extends AppCompatActivity {

    Button updateProfBtn;
    EditText editTextEditProfUsername, editTextEditProfDateofBirth, editTextEditProfPassword;
    RadioButton radioButtonEditProfMale, radioButtonEditProfFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        updateProfBtn = findViewById(R.id.buttonEditProf);
        editTextEditProfUsername = findViewById(R.id.editTextEditProfUsername);
        editTextEditProfPassword = findViewById(R.id.editTextEditProfPassword);
        editTextEditProfDateofBirth = findViewById(R.id.editTextEditProfDateofBirth);
        radioButtonEditProfFemale = findViewById(R.id.radioButtonEditProfFemale);
        radioButtonEditProfMale = findViewById(R.id.radioButtonEditProfMale);

        Intent intent = getIntent();
        final String usernameExtra = intent.getStringExtra("usernameExtra");
        String passwordExtra = intent.getStringExtra("passwordExtra");
        String dobExtra = intent.getStringExtra("dobExtra");
        String genderExtra = intent.getStringExtra("genderExtra");

        editTextEditProfUsername.setText(usernameExtra);
        editTextEditProfPassword.setText(passwordExtra);
        editTextEditProfDateofBirth.setText(dobExtra);


        if (genderExtra.equals("female"))
            radioButtonEditProfFemale.setChecked(true);
        else if (genderExtra.equals(""))
            radioButtonEditProfMale.setChecked(true);
        else radioButtonEditProfMale.setChecked(true);

        updateProfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditProfile.class);
                intent.putExtra("usernameExtra", usernameExtra);
                startActivity(intent);
            }
        });
    }
}
