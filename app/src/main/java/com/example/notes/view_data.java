package com.example.notes;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class view_data extends AppCompatActivity {
    Button back_to_home, Back_to_home2;
    ListView listView;
    EditText update;
    Button go_to_update;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        //back_to_home= findViewById(R.id.back_to_home);
       Back_to_home2= findViewById(R.id.back_to_home_2);
        //back_to_home.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
          //      startActivity(new Intent(getApplicationContext(), HomeActivity.class));
          //  }
        //});

        Back_to_home2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });

        update = findViewById(R.id.update);
        go_to_update= findViewById(R.id.go_to_update);

        go_to_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= update.getText().toString();

                if(TextUtils.isEmpty(name))
                {
                    update.setError("Required field");
                    return ;
                }
                else
                    intent= new Intent(getApplicationContext(), Update.class);
                intent.putExtra("message_key", name);
                Log.d("suniti",name);
                startActivity(intent);
            }
        });


///*
        listView= findViewById(R.id.list_of_item);


        final ArrayList<String > my_list= new ArrayList<>();
        final ArrayAdapter adapter= new ArrayAdapter<String>(this, R.layout.text_view_file, my_list);

        listView.setAdapter(adapter);

        DatabaseReference reff=  FirebaseDatabase.getInstance().getReference().child("Member");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                my_list.clear();


                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    Member member=  snapshot.getValue(Member.class);
                    assert member != null;
                    String txt= member.getName()+ " : \n" + member.getAge().toString();
                    my_list.add(txt);
                    Log.d("suniti",txt);

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//*/
    }
}