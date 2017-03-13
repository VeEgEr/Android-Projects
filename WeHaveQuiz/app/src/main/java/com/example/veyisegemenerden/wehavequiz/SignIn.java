package com.example.veyisegemenerden.wehavequiz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by veyisegemenerden on 21.12.2016.
 */

public class SignIn extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    EditText signIn_email_editText,signIn_password_editText;
    Button signIn_login_button;
    TextView signIn_goSignUp_textView;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        //set firebaseAuth
        firebaseAuth=FirebaseAuth.getInstance();

        //to check for Is user online?

        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }


        //define components

        signIn_email_editText=(EditText) findViewById(R.id.signIn_email_editText);
        signIn_password_editText=(EditText) findViewById(R.id.signIn_password_editText);
        signIn_goSignUp_textView=(TextView) findViewById(R.id.signIn_goSignUp_textView);
        signIn_login_button=(Button) findViewById(R.id.signIn_login_button);

        //define Progress Dialog
        progressDialog=new ProgressDialog(this);

    }
    //onClick for Login button
    public void signInLoginOnClick(View v){
        if(TextUtils.isEmpty(signIn_email_editText.getText())){
            Toast.makeText(getApplication(),"Please write email.",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(signIn_password_editText.getText())){
            Toast.makeText(getApplication(),"Please write password.",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Login...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(signIn_email_editText.getText().toString(),
                signIn_password_editText.getText().toString()).addOnCompleteListener
                (this, new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull Task<AuthResult> task){
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }else{
                    Toast.makeText(getApplication(),"Register is unsuccessful.",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    //transfer activity to signUP
    public void signInGoSignUpOnclick(View v){
        Intent intent = new Intent(this,SignUp.class);
        finish();
        startActivity(intent);
    }

}



