package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class HomeActivity extends AppCompatActivity {


    EditText name, age;
    Button save,view;
    DatabaseReference reff;

    Member member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name= findViewById(R.id.name);
        age=  findViewById(R.id.age);
        save= findViewById(R.id.save);
        view= findViewById(R.id.view);
        member= new Member();
        reff= FirebaseDatabase.getInstance().getReference().child("Member");

        //insertion

        save.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view)
            {
                String  age_var= age.getText().toString().trim();
                String name_var= name.getText().toString().trim();

                member.setAge(age_var);
                member.setName(name_var);

                //push data

                reff.child(name_var).setValue(member);
                Toast.makeText(HomeActivity.this, "successful entry", Toast.LENGTH_SHORT).show();
            }
        });

        //view data
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), view_data.class));
            }
        });





    }
}