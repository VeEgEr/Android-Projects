package com.example.veyisegemenerden.wehavequiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by veyisegemenerden on 21.12.2016.
 */

public class Profil extends AppCompatActivity {
    private FirebaseUser currentUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;


    EditText profile_name,profile_surname,profile_email;
    RadioGroup genderRadio;
    RadioButton femaleButton,maleButton;
    String gender;
    Button setProfile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil);
        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,SignIn.class));
        }
        //define component
        currentUser = firebaseAuth.getCurrentUser();
        profile_name=(EditText) findViewById(R.id.profile_name_editText);
        profile_surname=(EditText) findViewById(R.id.profile_lastname_editText);
        profile_email=(EditText) findViewById(R.id.profile_email_editText);
        setProfile=(Button) findViewById(R.id.profile_set_button);
        maleButton=(RadioButton) findViewById(R.id.profile_male_button);
        femaleButton=(RadioButton) findViewById(R.id.profile_female_button);
        genderRadio=(RadioGroup) findViewById(R.id.profile_gender_radioGroup);
        genderRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==R.id.profile_female_button){
                    gender="female";
                }

                if(checkedId==R.id.profile_male_button){
                    gender="male";
                }
            }
        });


       setField();

    }

    public void setField() {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();








            databaseReference.child("users").child(currentUser.getUid()).addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                try {


                    UserInformation p = dataSnapshot.getValue(UserInformation.class);


                        profile_name.setText(p.getName());
                        profile_surname.setText(p.getSurname());
                        profile_email.setText(p.getEmail());
                        if (p.getSex().equalsIgnoreCase(maleButton.getText().toString())) {
                            genderRadio.check(maleButton.getId());
                        }else if(p.getSex().equalsIgnoreCase(femaleButton.getText().toString())) {
                            genderRadio.check(femaleButton.getId());
                        }


                }catch (NullPointerException sa){

                }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });





    }

    public void setProfileUsersOnClick(View v){
        databaseReference= FirebaseDatabase.getInstance().getReference();
        firebaseAuth=FirebaseAuth.getInstance();
        if(TextUtils.isEmpty(profile_name.getText())){
            Toast.makeText(getApplication(),"Please write name.",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(profile_surname.getText())){
            Toast.makeText(getApplication(),"Please write surname.",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(profile_email.getText())){
            Toast.makeText(getApplication(),"Please write name.",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(gender)){
            Toast.makeText(getApplication(),"Please write surname.",Toast.LENGTH_LONG).show();
            return;
        }

        UserInformation info = new UserInformation(profile_name.getText().toString(),
                profile_surname.getText().toString(),profile_email.getText().toString(),gender);

        databaseReference.child("users").child(currentUser.getUid()).setValue(info);
    }

        public void backMainFromProfile(View v){

            Intent intent= new Intent(Profil.this,MainActivity.class);
            finish();
            startActivity(intent);
        }

}
