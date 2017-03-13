package com.example.veyisegemenerden.wehavequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by veyisegemenerden on 21.12.2016.
 */

public class Categories extends AppCompatActivity {
   static String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);



    }
    public void categoriesOnClicked(View v){

        if(v.getId()==R.id.generalknowledge){
       category ="generalknowledge";
        finish();
        startActivity(new Intent(this, Quiz.class));

        }

        if(v.getId()==R.id.science){
            category ="science";
            finish();
            startActivity(new Intent(this, Quiz.class));
        }

        if(v.getId()==R.id.geography){
            category ="geography";
            finish();
            startActivity(new Intent(this, Quiz.class));
        }

        if(v.getId()==R.id.history){
            category ="history";
            finish();
            startActivity(new Intent(this, Quiz.class));
        }

        if(v.getId()==R.id.art){
            category ="art";
            finish();
            startActivity(new Intent(this, Quiz.class));
        }
        if(v.getId()==R.id.sport){
            category ="sport";
            finish();
            startActivity(new Intent(this, Quiz.class));
        }

    }

}
