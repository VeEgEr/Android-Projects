package com.example.veyisegemenerden.wehavequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by veyisegemenerden on 21.12.2016.
 */

public class ScoreBoard extends AppCompatActivity {
    TextView scoreBoard;
    Button goBackMain;
    private FirebaseUser currentUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    ArrayList<ScoreData> scoreDatas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_board);
        scoreBoard = (TextView) findViewById(R.id.top10);
        goBackMain = (Button) findViewById(R.id.goMainFromScoreBoard);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        currentUser = firebaseAuth.getCurrentUser();


      pullScore();


    }

    public void pullScore() {


        databaseReference.child("score").addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child : children) {

                    ScoreData p = child.getValue(ScoreData.class);

                    scoreDatas.add(p);


                }

                sortScore();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    public void sortScore() {
        for (int j = 1; j < scoreDatas.size(); j++) {
            int Score_key = scoreDatas.get(j).getScore();

            String name_key = scoreDatas.get(j).getName();
            int i = j - 1;

            while ((i > -1) && (scoreDatas.get(i).getScore() > Score_key)) {
                scoreDatas.get(i + 1).setScore(scoreDatas.get(i).getScore());

                scoreDatas.get(i + 1).setName(scoreDatas.get(i).getName());

                scoreDatas.get(i).setScore(Score_key);

                scoreDatas.get(i).setName(name_key);


                i--;

            }


        }
        scoreBoard.setText(postScoreBoard());

    }

    public void display50Down(View v){

        String post = "";
        int j = scoreDatas.size();
        for (int i = 0; i < 5; i++) {
            if(scoreDatas.get(i).getScore()<50){


                post =  (j-i) +
                        ". " + scoreDatas.get(i).getName() +
                        " " + scoreDatas.get(i).getScore() + "\n" + post;
            }

        }
        scoreBoard.setText(post);

    }
    public void display50Up(View v){

        String post = "";
        int j = scoreDatas.size();
        for (int i = 0; i < 5; i++) {
    if(scoreDatas.get(i).getScore()>=50){


            post =  (j-i) +
                    ". " + scoreDatas.get(i).getName() +
                    " " + scoreDatas.get(i).getScore() + "\n" + post;
        }

        }
        scoreBoard.setText(post);


    }
    public String postScoreBoard() {

        String post = "";
            int j = scoreDatas.size();
        for (int i = 0; i < 5; i++) {

            post =  (j-i) +
                    ". " + scoreDatas.get(i).getName() +
                    " " + scoreDatas.get(i).getScore() + "\n" + post;
        }


        return post;
    }
    public void goMainFromScoreBoard(View v){
        Intent intent=new Intent(ScoreBoard.this,MainActivity.class);
        finish();
        startActivity(intent);
    }
}