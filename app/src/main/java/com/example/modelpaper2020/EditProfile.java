package com.example.modelpaper2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.modelpaper2020.database.DBHelper;

public class EditProfile extends AppCompatActivity {

EditText editTextEditProfUsername, editTextEditProfDateofBirth, editTextEditProfPassword;
RadioButton radioButtonEditProfMale, radioButtonEditProfFemale;
Button buttonEditProf, buttonEditSearch, buttonEditDelete;
RadioGroup genderGroup;
DBHelper userDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        final DBHelper userDb = new DBHelper(this);
        SQLiteDatabase db = userDb.getReadableDatabase();

        editTextEditProfUsername = findViewById(R.id.editTextEditProfUsername);
        editTextEditProfPassword= findViewById(R.id.editTextEditProfPassword);
        editTextEditProfDateofBirth = findViewById(R.id.editTextEditProfDateofBirth);
        radioButtonEditProfFemale = findViewById(R.id.radioButtonEditProfFemale);
        radioButtonEditProfMale = findViewById(R.id.radioButtonEditProfMale);
        buttonEditDelete = findViewById(R.id.buttonEditDelete);
        buttonEditProf = findViewById(R.id.buttonEditProf);
        buttonEditSearch= findViewById(R.id.buttonEditSearch);
        genderGroup = findViewById(R.id.genderGroup);


        buttonEditSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String usernameExtra = intent.getStringExtra("usernameExtra");
                User ob = userDb.readAllInfo(usernameExtra);
                editTextEditProfUsername.setText(ob.getUsername());
                editTextEditProfPassword.setText(ob.getPassword());
                editTextEditProfDateofBirth.setText(ob.getDob());
                String gender = ob.getGender();

                if(gender.equals("female")){
                    radioButtonEditProfFemale.setChecked(true);

                }
                else if (gender.equals(""))
                    radioButtonEditProfMale.setChecked(true);
                else{
                    radioButtonEditProfMale.setChecked(true);

                }
            }
        });



        buttonEditProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = editTextEditProfUsername.getText().toString();
                String password = editTextEditProfPassword.getText().toString();
                String dob = editTextEditProfDateofBirth.getText().toString();
                String gender;
                int selectedGender =genderGroup.getCheckedRadioButtonId();
                if (selectedGender == radioButtonEditProfFemale.getId())
                    gender = "female";
                else
                    gender = "male";


                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(dob) && !TextUtils.isEmpty(gender)){
                    boolean status = userDb.updateInfo(username,password,dob, gender);
                    if (status)
                    Toast.makeText(EditProfile.this, "user updated successfully", Toast.LENGTH_SHORT).show();
                    else
                    Toast.makeText(getApplicationContext(), "error occurred", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "all the fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonEditDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextEditProfUsername.getText().toString();
                if (!TextUtils.isEmpty(username) ){
                    boolean status = userDb.deleteInfo(username);
                    if (status){
                        Toast.makeText(EditProfile.this, "user deleted successfully", Toast.LENGTH_SHORT).show();
                     editTextEditProfUsername.setText("");}
                    else
                        Toast.makeText(getApplicationContext(), "error occurred", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "username field are required", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}
