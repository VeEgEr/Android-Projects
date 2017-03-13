package com.example.veyisegemenerden.wehavequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by veyisegemenerden on 24.12.2016.
 */

public class Finish extends AppCompatActivity {
    private FirebaseUser currentUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    TextView scorePlayer;
    EditText name;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish);
        name= (EditText) findViewById(R.id.nameTaker) ;
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        currentUser = firebaseAuth.getCurrentUser();

        scorePlayer = (TextView) findViewById(R.id.finalQuiz_textView);
        scorePlayer.setText(fillTextView());


        fillTextView();
    }

    public String fillTextView() {

        String post = "Question Count is :" + " 10" + "\n" +
                "True answer Count is : " + Quiz.trueCount + "\n" +
                "False answer Count is : " + Quiz.falseCount + "\n" +
                "Your Score is : " + Quiz.trueCount * 10;
        return post;



    }
    public void submitScore(View v){
        pushScore(String.valueOf(name.getText()));
    }
    public void pushScore(String name) {
        databaseReference = FirebaseDatabase.getInstance().getReference();

            String id= currentUser.getUid();


            ScoreData s = new ScoreData(Quiz.trueCount * 10,name);

            databaseReference.child("score").child(id).setValue(s);



    }

    public void goTop10FromFinish(View v){

        Intent intent= new Intent(Finish.this,ScoreBoard.class);
        finish();
        startActivity(intent);

    }

    public void goMainFromFinish(View v){

        Intent intent= new Intent(Finish.this,MainActivity.class);
        finish();
        startActivity(intent);

    }


    public void goQuizFromFinish(View v){

        Intent intent= new Intent(Finish.this,Categories.class);
        finish();
        startActivity(intent);

    }
}
