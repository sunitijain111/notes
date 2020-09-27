package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    private TextView signup;

    private EditText email;
    private EditText pass;
    private Button btnlogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth= FirebaseAuth.getInstance();
        signup= findViewById(R.id.signup_text);
        email= findViewById(R.id.email_login);
        pass= findViewById(R.id.password_login);
        btnlogin=findViewById(R.id.btn_login);





        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail= email.getText().toString().trim();
                String mpass= pass.getText().toString().trim();

                if(TextUtils.isEmpty(mEmail))
                {
                    email.setError("Required field");
                    return ;
                }

                if(TextUtils.isEmpty(mpass))
                {
                    pass.setError("Required field");
                    return ;
                }
                mAuth.signInWithEmailAndPassword(mEmail, mpass).addOnCompleteListener( MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(getApplicationContext(),"login successfull", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent( getApplicationContext(), HomeActivity.class));
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"login failed", Toast.LENGTH_SHORT).show();
                                    // return;
                                }
                            }
                        }


                );

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), registration.class));
            }
        });


    }
}