package com.example.assignment1;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.Locale;

public class AddEditObservationActivity  extends AppCompatActivity {

    private EditText name, comment;
    private Boolean isEditObservation;
    private FloatingActionButton fbutton;
    private String id, Id, Name, Date, Comment, HikeId;
    private ActionBar actionBar;
    private DbHelperHike DbHelperHike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_observation);

        DbHelperHike = new DbHelperHike(this);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Add new Observation");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        id = intent.getStringExtra("HIKEID");
        name = findViewById(R.id.ObsName);
        comment = findViewById(R.id.ObsComment);

        isEditObservation = intent.getBooleanExtra("isEditObservation", false);
        if(isEditObservation)
        {
            actionBar.setTitle("Update Observation");

            Id = intent.getStringExtra("OBSERVATIONID");
            Name = intent.getStringExtra("OBSERVATIONNAME");
            Comment = intent.getStringExtra("COMMENT");

            name.setText(Name);
            comment.setText(Comment);
        }
        else
        {
            actionBar.setTitle("Create Observation");
        }

        fbutton =  findViewById(R.id.createObsbutton);
        fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveObsData();
            }
        });
    }
    public void saveObsData()
    {

        String currentTime = ""+System.currentTimeMillis();

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(currentTime));

        android.text.format.DateFormat df = new android.text.format.DateFormat();
        String dateFormated = df.format("dd/MM/yyyy hh:mm:ss", calendar.getTime()).toString();

        HikeId = id;
        Name = name.getText().toString();
        Date = dateFormated;
        Comment = comment.getText().toString();
        if(Comment.isEmpty() == true)
        {
            Comment = "None";
        }

        if (!Name.isEmpty() && !Date.isEmpty()) {
            if(isEditObservation){
                DbHelperHike.updateObservation(
                        "" + Id,
                        "" + Name,
                        "" + Date,
                        "" + Comment);

                Toast.makeText(getApplicationContext(),"Update successfully", Toast.LENGTH_SHORT).show();
            } else {
                long id = DbHelperHike.createOservation(
                        "" + Name,
                        "" + Date,
                        "" + Comment,
                        "" + HikeId);
                Toast.makeText(getApplicationContext(),"Add Observation " + id, Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Lack of information", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}