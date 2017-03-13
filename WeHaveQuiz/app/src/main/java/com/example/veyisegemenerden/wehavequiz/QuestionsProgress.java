package com.example.veyisegemenerden.wehavequiz;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static java.security.AccessController.getContext;

/**
 * Created by veyisegemenerden on 29.12.2016.
 */

public class QuestionsProgress extends AppCompatActivity {
    private FirebaseUser currentUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    TabHost tabhostMain;
    EditText question_editText,optionA,optionB,optionC,optionD,answer,questionNumber;
    Spinner spinerForCategoryAdding,spinnerForCategoryDelete,spinnerForDeletingQuestionNumber;
    String categoryForAdding,categoryForDelete,questionNumberForDelete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_operation);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        currentUser = firebaseAuth.getCurrentUser();
        question_editText=(EditText)findViewById(R.id.question_editText);
        optionA=(EditText) findViewById(R.id.add_question_optionOne);
        optionB=(EditText) findViewById(R.id.add_question_optionTwo);
        optionC=(EditText) findViewById(R.id.add_question_optionThree);
        optionD=(EditText) findViewById(R.id.add_question_optionFour);
        answer=(EditText) findViewById(R.id.add_question_answer);
        questionNumber=(EditText) findViewById(R.id.questionNumber_editText);
        spinerForCategoryAdding=(Spinner) findViewById(R.id.category_spinner_add);
        spinnerForCategoryDelete=(Spinner) findViewById(R.id.delete_question_spinnercatergory);
        spinnerForDeletingQuestionNumber=(Spinner) findViewById(R.id.delete_question_spinner);


        final ArrayAdapter<String> questionNumberForDeleting= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line);
        for(int i=0; i<30; i++){
            questionNumberForDeleting.add(i+"");
        }
        questionNumberForDeleting.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerForDeletingQuestionNumber.setAdapter(questionNumberForDeleting);

        spinnerForDeletingQuestionNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                questionNumberForDelete=questionNumberForDeleting.getItem(i).trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line);
        adapter.add("art");
        adapter.add("geography");
        adapter.add("history");
        adapter.add("knowledge");
        adapter.add("science");

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerForCategoryDelete.setAdapter(adapter);
        spinerForCategoryAdding.setAdapter(adapter);

        spinerForCategoryAdding.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categoryForAdding=adapter.getItem(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerForCategoryDelete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categoryForDelete=adapter.getItem(i).trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



       // LoadData("art.txt");


        tabhostMain=(TabHost)findViewById(android.R.id.tabhost);
        tabhostMain.setup();


        TabHost.TabSpec tabSpec;

//tabhost installing

        tabSpec= tabhostMain.newTabSpec("A");

        tabSpec.setContent(R.id.add_question_layout);
        tabSpec.setIndicator("ADD");

        tabhostMain.addTab(tabSpec);

        tabSpec= tabhostMain.newTabSpec("R");
        tabSpec.setContent(R.id.delete_question_layout);
        tabSpec.setIndicator("DELETE");
        tabhostMain.addTab(tabSpec);

    }
    public void addSingleQuestion(View v){
        databaseReference= FirebaseDatabase.getInstance().getReference();

        if(TextUtils.isEmpty(question_editText.getText())){
            Toast.makeText(getApplication(),"Please write question.",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(optionA.getText())){
            Toast.makeText(getApplication(),"Please write optionOne.",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(optionB.getText())){
            Toast.makeText(getApplication(),"Please write optionTwo.",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(optionC.getText())){
            Toast.makeText(getApplication(),"Please write optionThree.",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(optionD.getText())){
            Toast.makeText(getApplication(),"Please write optionFour.",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(answer.getText())){
            Toast.makeText(getApplication(),"Please write answer.",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(questionNumber.getText())){
            Toast.makeText(getApplication(),"Please write question number.",Toast.LENGTH_LONG).show();
            return;
        }

        String questionNumbers=questionNumber.getText().toString();
        String question=question_editText.getText().toString();
        String optionOne=optionA.getText().toString();
        String optionTwo=optionB.getText().toString();
        String optionThree=optionC.getText().toString();
        String optionFour=optionD.getText().toString();
        String optionFive=optionD.getText().toString();

        String asw=answer.getText().toString();


        Questions clone = new Questions(question,optionOne,optionTwo,optionThree,optionFour,optionFive,asw);








        databaseReference.child("questions").child(categoryForAdding).child("question"+questionNumbers).setValue(clone);
    }



    public void LoadData(String inFile) {





        databaseReference= FirebaseDatabase.getInstance().getReference();
        BufferedReader reader;

        try{
            final InputStream file = getAssets().open(inFile);
            reader = new BufferedReader(new InputStreamReader(file));
            String line = reader.readLine();
            int i =2;
            while(line != null){

                line = reader.readLine();

                if(line != null){

                            String question = line.split("-")[0].trim();
                            String optionA = line.split("-")[1].trim();
                            String optionB = line.split("-")[2].trim();
                            String optionC = line.split("-")[3].trim();
                            String optionD = line.split("-")[4].trim();

                            String answer = line.split("-")[5].trim();
                    String optionE= line.split("-")[6].trim();


                    Questions clone = new Questions(question,optionA,optionB,optionC,optionD,optionE,answer);








                    databaseReference.child("questions").child("art").child("question"+i).setValue(clone);

                    i++;

                }


                }




        } catch(IOException ioe){
            ioe.printStackTrace();
        }

    }


    public void deleteQuestion(View v){
        databaseReference= FirebaseDatabase.getInstance().getReference();
        if(TextUtils.isEmpty(categoryForDelete)){
            Toast.makeText(getApplication(),"Please choose one category.",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(questionNumberForDelete)){
            Toast.makeText(getApplication(),"Please choose one questionNumber.",Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(this, questionNumberForDelete +" From " +categoryForDelete +" Removed",Toast.LENGTH_LONG).show();

        databaseReference.child("questions").child(categoryForDelete).child("question"+questionNumberForDelete).removeValue();
    }


}

