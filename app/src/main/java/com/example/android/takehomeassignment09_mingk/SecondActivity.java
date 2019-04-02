package com.example.android.takehomeassignment09_mingk;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class SecondActivity extends AppCompatActivity {

    private TextView displayText;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference bookRef = database.getReference("My Book");
    private DatabaseReference booksRef = database.getReference("Many books");
    private DatabaseReference dateRef = database.getReference("date");
    Calendar calendar = Calendar.getInstance();
    final String date = DateFormat.getDateInstance().format(calendar.getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        displayText = (TextView)findViewById(R.id.display_text);



        dateRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    displayText.setText(date);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SecondActivity.this, "Error loading Firebase",Toast.LENGTH_SHORT).show();

            }
        });

        }


    public void setBook(View view){
        bookRef.setValue(new Book("Harry Potter", "J.K.Rowling", "fiction",true));
    }



    public void addBook(View view){
        booksRef.push().setValue(new Book("Jane Eyre","Charlotte Bronte", "fiction",false));
    }

    public void addDate(View view){
        dateRef.push().setValue(calendar);
    }
}
