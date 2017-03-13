package com.example.veyisegemenerden.wehavequiz;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set firebaseAuth
        firebaseAuth=FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        //to check for Is user online?

        if(firebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(this,SignIn.class));
        }

        //to check profile information is exist.

        controlProfileField();



    }

    public void signOutOnClicked(View v){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this, SignIn.class));
    }

    public void profileOnClicked(View v){


        startActivity(new Intent(this, Profil.class));
    }
    public void playGameOnClicked(View v){


        startActivity(new Intent(this, Categories.class));
    }
    public void scoreOnClicked(View v){


        startActivity(new Intent(this, ScoreBoard.class));
    }

    public void controlProfileField() {

        databaseReference.child("users").child(currentUser.getUid()).addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                UserInformation pullInfo = dataSnapshot.getValue(UserInformation.class);
                String profileController = pullInfo.toString();
                }catch (NullPointerException a){
                    Intent intent= new Intent(MainActivity.this,Profil.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void goQuestionProgress(View v){

        Intent intent= new Intent(this,QuestionsProgress.class);
        startActivity(intent);

    }
}
