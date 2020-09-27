package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Update extends AppCompatActivity {
Intent intent;
DatabaseReference reff;
EditText title, notes;
Button update;
String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        intent= getIntent();

        name = intent.getStringExtra("message_key");


    title= findViewById(R.id.title);
    notes= findViewById(R.id.notes);
    update= findViewById(R.id.update_db);

    //name has the title

        reff= FirebaseDatabase.getInstance().getReference().child("Member");

        reff.orderByChild("name").equalTo(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                if(dataSnapshot.exists()){
                    //username exist
                    Toast.makeText(Update.this, "found note", Toast.LENGTH_SHORT).show();
                    title.setText(name);
                    Member member_temp= new Member();
                    for (DataSnapshot child: dataSnapshot.getChildren()) {
                       member_temp= child.getValue(Member.class);
                    }
                    notes.setText(member_temp.getAge());;
                }
                else
                {
                    Toast.makeText(Update.this, " note not found", Toast.LENGTH_SHORT).show();

                   title.setText(name);
                   notes.setHint("enter the notes ");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               name= title.getText().toString();
               String content= notes.getText().toString();
                Member member= new Member();
                member.setAge(content);
                member.setName(name);

                //push data
                reff= FirebaseDatabase.getInstance().getReference().child("Member");
                reff.child(name).setValue(member);
                Toast.makeText(Update.this, "successful entry", Toast.LENGTH_SHORT).show();

            }
        });

    }
}