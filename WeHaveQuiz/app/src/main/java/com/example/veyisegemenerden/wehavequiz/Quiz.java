package com.example.veyisegemenerden.wehavequiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by veyisegemenerden on 21.12.2016.
 */

public class Quiz extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    TextView screen,time,trueAnswer,falseAnswer;
    private ProgressBar progress;
    boolean clicked;
    Button optionOne,optionTwo,optionThree,optionFour,optionFive;
    ArrayList<Questions> questionForDatabase= new ArrayList<Questions>();

    static int questionCount,trueCount,falseCount,realCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        questionCount=0;
        trueCount=0;
        falseCount=0;
        realCount=0;
        time=(TextView) findViewById(R.id.nextQuestionTime);
        progress=(ProgressBar) findViewById(R.id.progressBar);
        screen=(TextView) findViewById(R.id.question_textview);
        optionOne=(Button) findViewById(R.id.answer_one_button);
        optionTwo=(Button)findViewById(R.id.answer_two_button);
        optionThree=(Button) findViewById(R.id.answer_three_button);
        optionFour=(Button) findViewById(R.id.answer_four_button);
        optionFive=(Button) findViewById(R.id.answer_five_button);
        trueAnswer=(TextView) findViewById(R.id.trueAnswer);
        falseAnswer=(TextView)findViewById(R.id.falseAnswer);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


        pullQuestion();


    }

    public void setQuestion() {

        optionTwo.setBackgroundColor(Color.WHITE);
        optionOne.setBackgroundColor(Color.WHITE);
        optionThree.setBackgroundColor(Color.WHITE);
        optionFour.setBackgroundColor(Color.WHITE);
        optionFive.setBackgroundColor(Color.WHITE);



        screen.setText(questionForDatabase.get(questionCount).getQuestion());
        optionOne.setText(questionForDatabase.get(questionCount).getA());
        optionTwo.setText(questionForDatabase.get(questionCount).getB());
        optionThree.setText(questionForDatabase.get(questionCount).getC());
        optionFour.setText(questionForDatabase.get(questionCount).getD());
        optionFive.setText(questionForDatabase.get(questionCount).getE());
        startProgress();
    }

    public void passController(){

        questionForDatabase.remove(questionCount);
        Random rnd =new Random();
        questionCount= rnd.nextInt(questionForDatabase.size());
        realCount++;
        if(realCount==10){
            Intent intent=new Intent(Quiz.this,Finish.class);
            finish();
            startActivity(intent);
        }

    }
    public void answerControllerOnClick(View v){

        clicked=true;


        if(v.getId()==optionOne.getId()){
            if(optionOne.getText().equals(questionForDatabase.get(questionCount).getAnswer())){
                trueCount++;
                trueAnswer.setText(String.valueOf(trueCount));
                optionOne.setBackgroundColor(Color.GREEN);


            }else{

                falseCount++;
                falseAnswer.setText(String.valueOf(falseCount));
                if(optionTwo.getText().equals(questionForDatabase.get(questionCount).getAnswer())){
                    optionTwo.setBackgroundColor(Color.GREEN);
                    optionOne.setBackgroundColor(Color.RED);
                    optionThree.setBackgroundColor(Color.RED);
                    optionFour.setBackgroundColor(Color.RED);
                    optionFive.setBackgroundColor(Color.RED);

                }
                if(optionThree.getText().equals(questionForDatabase.get(questionCount).getAnswer())){
                    optionOne.setBackgroundColor(Color.RED);
                    optionTwo.setBackgroundColor(Color.RED);
                    optionFive.setBackgroundColor(Color.RED);
                    optionFour.setBackgroundColor(Color.RED);
                    optionThree.setBackgroundColor(Color.GREEN);
                }
                if(optionFour.getText().equals(questionForDatabase.get(questionCount).getAnswer())){
                    optionFour.setBackgroundColor(Color.GREEN);
                    optionOne.setBackgroundColor(Color.RED);
                    optionTwo.setBackgroundColor(Color.RED);
                    optionThree.setBackgroundColor(Color.RED);
                    optionFive.setBackgroundColor(Color.RED);

                }

                if(optionFive.getText().equals(questionForDatabase.get(questionCount).getAnswer())){
                    optionFour.setBackgroundColor(Color.RED);
                    optionOne.setBackgroundColor(Color.RED);
                    optionTwo.setBackgroundColor(Color.RED);
                    optionThree.setBackgroundColor(Color.RED);
                    optionFive.setBackgroundColor(Color.GREEN);

                }



            }



        }

        if(v.getId()==optionTwo.getId()){
            if(optionTwo.getText().equals(questionForDatabase.get(questionCount).getAnswer())){
                trueCount++;
                trueAnswer.setText(String.valueOf(trueCount));
                optionTwo.setBackgroundColor(Color.GREEN);



            }else{
                falseCount++;
                falseAnswer.setText(String.valueOf(falseCount));
                if(optionOne.getText().equals(questionForDatabase.get(questionCount).getAnswer())){
                    optionTwo.setBackgroundColor(Color.RED);
                    optionOne.setBackgroundColor(Color.GREEN);
                    optionThree.setBackgroundColor(Color.RED);
                    optionFour.setBackgroundColor(Color.RED);
                    optionFive.setBackgroundColor(Color.RED);

                }
                if(optionThree.getText().equals(questionForDatabase.get(questionCount).getAnswer())){
                    optionOne.setBackgroundColor(Color.RED);
                    optionTwo.setBackgroundColor(Color.RED);

                    optionFour.setBackgroundColor(Color.RED);
                    optionThree.setBackgroundColor(Color.GREEN);
                    optionFive.setBackgroundColor(Color.RED);
                }
                if(optionFour.getText().equals(questionForDatabase.get(questionCount).getAnswer())){
                    optionFour.setBackgroundColor(Color.GREEN);
                    optionOne.setBackgroundColor(Color.RED);
                    optionTwo.setBackgroundColor(Color.RED);
                    optionThree.setBackgroundColor(Color.RED);
                    optionFive.setBackgroundColor(Color.RED);

                }

                if(optionFive.getText().equals(questionForDatabase.get(questionCount).getAnswer())){
                    optionFour.setBackgroundColor(Color.RED);
                    optionOne.setBackgroundColor(Color.RED);
                    optionTwo.setBackgroundColor(Color.RED);
                    optionThree.setBackgroundColor(Color.RED);
                    optionFive.setBackgroundColor(Color.GREEN);

                }

            }

        }

        if(v.getId()==optionThree.getId()){
            if(optionThree.getText().equals(questionForDatabase.get(questionCount).getAnswer())){
                trueCount++;
                trueAnswer.setText(String.valueOf(trueCount));
                optionThree.setBackgroundColor(Color.GREEN);

            }else{
                falseCount++;
                falseAnswer.setText(String.valueOf(falseCount));
                if(optionTwo.getText().equals(questionForDatabase.get(questionCount).getAnswer())){
                    optionTwo.setBackgroundColor(Color.GREEN);
                    optionOne.setBackgroundColor(Color.RED);
                    optionThree.setBackgroundColor(Color.RED);
                    optionFour.setBackgroundColor(Color.RED);
                    optionFive.setBackgroundColor(Color.RED);
                }
                if(optionOne.getText().equals(questionForDatabase.get(questionCount).getAnswer())){
                    optionOne.setBackgroundColor(Color.GREEN);
                    optionTwo.setBackgroundColor(Color.RED);

                    optionFour.setBackgroundColor(Color.RED);
                    optionThree.setBackgroundColor(Color.RED);
                    optionFive.setBackgroundColor(Color.RED);
                }
                if(optionFour.getText().equals(questionForDatabase.get(questionCount).getAnswer())){
                    optionFour.setBackgroundColor(Color.GREEN);
                    optionOne.setBackgroundColor(Color.RED);
                    optionTwo.setBackgroundColor(Color.RED);
                    optionThree.setBackgroundColor(Color.RED);
                    optionFive.setBackgroundColor(Color.RED);

                }
                if(optionFive.getText().equals(questionForDatabase.get(questionCount).getAnswer())){
                    optionFour.setBackgroundColor(Color.RED);
                    optionOne.setBackgroundColor(Color.RED);
                    optionTwo.setBackgroundColor(Color.RED);
                    optionThree.setBackgroundColor(Color.RED);
                    optionFive.setBackgroundColor(Color.GREEN);

                }

            }

        }

        if(v.getId()==optionFour.getId()){
            if(optionFour.getText().equals(questionForDatabase.get(questionCount).getAnswer())){
                trueCount++;
                trueAnswer.setText(String.valueOf(trueCount));
                optionFour.setBackgroundColor(Color.GREEN);


            }else{
                falseCount++;
                falseAnswer.setText(String.valueOf(falseCount));
                if(optionTwo.getText().equals(questionForDatabase.get(questionCount).getAnswer())){
                    optionTwo.setBackgroundColor(Color.GREEN);
                    optionOne.setBackgroundColor(Color.RED);
                    optionThree.setBackgroundColor(Color.RED);
                    optionFour.setBackgroundColor(Color.RED);
                    optionFive.setBackgroundColor(Color.RED);
                }
                if(optionThree.getText().equals(questionForDatabase.get(questionCount).getAnswer())){
                    optionOne.setBackgroundColor(Color.RED);
                    optionTwo.setBackgroundColor(Color.RED);

                    optionFour.setBackgroundColor(Color.RED);
                    optionThree.setBackgroundColor(Color.GREEN);
                    optionFive.setBackgroundColor(Color.RED);
                }
                if(optionOne.getText().equals(questionForDatabase.get(questionCount).getAnswer())){
                    optionFour.setBackgroundColor(Color.RED);
                    optionOne.setBackgroundColor(Color.GREEN);
                    optionTwo.setBackgroundColor(Color.RED);
                    optionThree.setBackgroundColor(Color.RED);
                    optionFive.setBackgroundColor(Color.RED);

                }
                if(optionFive.getText().equals(questionForDatabase.get(questionCount).getAnswer())){
                    optionFour.setBackgroundColor(Color.RED);
                    optionOne.setBackgroundColor(Color.RED);
                    optionTwo.setBackgroundColor(Color.RED);
                    optionThree.setBackgroundColor(Color.RED);
                    optionFive.setBackgroundColor(Color.GREEN);

                }

            }

            if(v.getId()==optionFive.getId()) {
                if (optionFour.getText().equals(questionForDatabase.get(questionCount).getAnswer())) {
                    trueCount++;
                    trueAnswer.setText(String.valueOf(trueCount));
                    optionFive.setBackgroundColor(Color.GREEN);


                } else {
                    falseCount++;
                    falseAnswer.setText(String.valueOf(falseCount));
                    if (optionTwo.getText().equals(questionForDatabase.get(questionCount).getAnswer())) {
                        optionTwo.setBackgroundColor(Color.GREEN);
                        optionOne.setBackgroundColor(Color.RED);
                        optionThree.setBackgroundColor(Color.RED);
                        optionFour.setBackgroundColor(Color.RED);
                        optionFive.setBackgroundColor(Color.RED);
                    }
                    if (optionThree.getText().equals(questionForDatabase.get(questionCount).getAnswer())) {
                        optionOne.setBackgroundColor(Color.RED);
                        optionTwo.setBackgroundColor(Color.RED);

                        optionFour.setBackgroundColor(Color.RED);
                        optionThree.setBackgroundColor(Color.GREEN);
                        optionFive.setBackgroundColor(Color.RED);
                    }
                    if (optionOne.getText().equals(questionForDatabase.get(questionCount).getAnswer())) {
                        optionFour.setBackgroundColor(Color.RED);
                        optionOne.setBackgroundColor(Color.GREEN);
                        optionTwo.setBackgroundColor(Color.RED);
                        optionThree.setBackgroundColor(Color.RED);
                        optionFive.setBackgroundColor(Color.RED);

                    }
                    if (optionFour.getText().equals(questionForDatabase.get(questionCount).getAnswer())) {
                        optionFour.setBackgroundColor(Color.GREEN);
                        optionOne.setBackgroundColor(Color.RED);
                        optionTwo.setBackgroundColor(Color.RED);
                        optionThree.setBackgroundColor(Color.RED);
                        optionFive.setBackgroundColor(Color.RED);

                    }

                }
            }

        }

        questionForDatabase.remove(questionCount);
        Random rnd =new Random();
        questionCount= rnd.nextInt(questionForDatabase.size());


        realCount++;



        if(realCount==10){
            Intent intent=new Intent(Quiz.this,Finish.class);
            finish();
            startActivity(intent);
        }




    }
    public void pullQuestion(){



            databaseReference.child("questions").child(Categories.category).addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> children=dataSnapshot.getChildren();

                    for(DataSnapshot child:children){

                        Questions p = child.getValue(Questions.class);
                        questionForDatabase.add(p);



                    }

                    setQuestion();


                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }


    public void startProgress() {
        // do something long
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i <= 10; i++) {
                    final int value = i;
                    doFakeWork();

                    progress.post(new Runnable() {
                        @Override
                        public void run() {
                            progress.setProgress(value);
                            time.setText(10-value +"");


                            if(value==10){
                                progress.setProgress(0);
                                time.setText("");
                                if(clicked){
                                    setQuestion();
                                    clicked=false;
                                }else{

                                    passController();
                                    setQuestion();
                                }



                            }



                        }
                    });
                }
            }
        };
        new Thread(runnable).start();
    }

    // Simulating something timeconsuming
    private void doFakeWork() {
        try {
            Thread.sleep(1000);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
