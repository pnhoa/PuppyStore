package com.example.app_ban_hang.login_SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.example.app_ban_hang.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText mUsername,mEmail,mPassword,mConfirmpass;
    Button mRegisterbtn,mCancelbtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mUsername=findViewById(R.id.userName);
        mEmail = findViewById(R.id.Email);
        mPassword=findViewById(R.id.password);
        mConfirmpass=findViewById(R.id.confirm_password);
        mRegisterbtn=findViewById(R.id.btn_register);
        mCancelbtn=findViewById(R.id.btn_sign_up_cancle);

        fAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);

//        if (fAuth.getCurrentUser()!=null){
//            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//            finish();;
//        }

        mRegisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String confirmpass=mConfirmpass.getText().toString().trim();
                String user=mUsername.getText().toString().trim();
                if (TextUtils.isEmpty((user))){
                    mUsername.setError("Username is Required");
                    return;
                }
                if (TextUtils.isEmpty((email))){
                    mEmail.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required.");
                    return;
                }
                if (TextUtils.isEmpty(confirmpass)){
                    mConfirmpass.setError("ConfirmPass is Required.");
                    return;
                }
                if (password.length()<6){
                    mPassword.setError("Password must be >- 6 Characters");
                    return;
                }
                if (!password.equals(confirmpass)){
                    mConfirmpass.setError("No matched");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                // register the user in firabase
                fAuth.createUserWithEmailAndPassword(email,confirmpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Register.this,"User Created.",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainLogin.class));
                        }
                        else{
                            Toast.makeText(Register.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        mCancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainLogin.class));
            }
        });
    }
}