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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by veyisegemenerden on 21.12.2016.
 */

public class SignUp extends AppCompatActivity {
    EditText signUp_email_editText,signUp_password_editText;
    Button signUp_register_button,signUp_goSignIn_button;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        //take authentication data.
        firebaseAuth=FirebaseAuth.getInstance();


        //define components
        signUp_email_editText= (EditText) findViewById(R.id.signUp_email_editText);
        signUp_password_editText=(EditText) findViewById(R.id.signUp_password_editText);
        signUp_register_button=(Button) findViewById(R.id.signUp_register_button);
        signUp_goSignIn_button=(Button) findViewById(R.id.signUp_goSignIn_button);

        //define progressDialog
        progressDialog= new ProgressDialog(this);



    }

    //register method
    public void signUpRegisterOnClick(View v){


        if(TextUtils.isEmpty(signUp_email_editText.getText())){
            Toast.makeText(getApplication(),"Please write email.",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(signUp_password_editText.getText())){
            Toast.makeText(getApplication(),"Please write password.",Toast.LENGTH_LONG).show();
            return;
        }

        if(signUp_password_editText.getText().length()<=5){
            Toast.makeText(getApplication(),"Password's length must be grater than 5.",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering user.");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(signUp_email_editText.getText().toString(),
                signUp_password_editText.getText().toString()).addOnCompleteListener
                (this,new OnCompleteListener<AuthResult>()
                {


                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){

                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(SignUp.this,"Register is successful.",Toast.LENGTH_LONG).show();
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(SignUp.this,"Register is unseccessful.",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
    // to transfer activity to signIN
    public void signUpGoSignInOnClick(View v){
        Intent intent = new Intent(SignUp.this,SignIn.class);
        startActivity(intent);
    }

}
