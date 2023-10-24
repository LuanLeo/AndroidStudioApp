package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
public class AddEditHike extends AppCompatActivity {
    private EditText name, location, date, duration, length, difficulty, description, fquantity;
    private String parking;
    private RadioGroup parkingGroup;
    private Boolean isEditMode;
    private FloatingActionButton fbutton;
    private String Id, Name, Location, Date, Duration, Length, Difficulty, Description, Parking, Fquantity ;
    private ActionBar actionBar;
    private DbHelperHike DbHelperHike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_hike);

        //Call database
        DbHelperHike = new DbHelperHike(this);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Add new Hike");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        //Take info from view
        name = findViewById(R.id.HikeName);
        location = findViewById(R.id.HikeLocation);
        date = findViewById(R.id.HikeDate);
        length = findViewById(R.id.HikeLength);
        difficulty = findViewById(R.id.HikeDiff);
        duration = findViewById(R.id.HikeDur);
        fquantity = findViewById(R.id.HikeFriendQ);
        description = findViewById(R.id.HikeDes);

        //Take radio button group
        parkingGroup = findViewById(R.id.ParkingRadio);
        parkingGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.Ryes) {
                    parking = "Yes";
                } else if (i == R.id.Rno) {
                    parking = "No";
                } else {

                }
            }
        });


        Intent intent = getIntent();
        isEditMode = intent.getBooleanExtra("isEditMode", false);
        if(isEditMode)
        {
            actionBar.setTitle("Update Hike");

            Id = intent.getStringExtra("ID");
            Name = intent.getStringExtra("NAME");
            Location = intent.getStringExtra("LOCATION");
            Date = intent.getStringExtra("DATE");
            Duration = intent.getStringExtra("DURATION");
            Length = intent.getStringExtra("LENGTH");
            Difficulty = intent.getStringExtra("DIFFICULTY");
            Fquantity = intent.getStringExtra("FRIQUANTITY");
            Description = intent.getStringExtra("DESCRIPTION");

            name.setText(Name);
            location.setText(Location);
            date.setText(Date);
            duration.setText(Duration);
            length.setText(Length);
            difficulty.setText(Difficulty);
            description.setText(Description);
            fquantity.setText(Fquantity);
        } else{
            actionBar.setTitle("Create Hike");
        }

        //Call button
        fbutton =  findViewById(R.id.createHike_button);
        fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    public void saveData() {

        //Take data from view
        Name = name.getText().toString();
        Location = location.getText().toString();
        Date = date.getText().toString();
        Duration = duration.getText().toString();
        Length = length.getText().toString();
        Difficulty = difficulty.getText().toString();
        if(parking == null)
        {
            Parking = "";
        }
        else{
            Parking = parking;
        }
        Fquantity = fquantity.getText().toString();
        Description = description.getText().toString();
        if(Description.isEmpty() == true)
        {
            Description = "None";
        }

        //Add and Edit functions
        if (!Name.isEmpty() && !Location.isEmpty() && !Date.isEmpty() && !Duration.isEmpty() && !Length.isEmpty() && !Difficulty.isEmpty() && !Parking.isEmpty() && !Fquantity.isEmpty()) {
            if(isEditMode){
                DbHelperHike.updateHike(
                        "" + Id,
                        "" + Name,
                        "" + Location,
                        "" + Date,
                        "" + Duration,
                        "" + Length,
                        "" + Difficulty,
                        "" + Parking,
                        "" + Fquantity,
                        "" + Description);

                Toast.makeText(getApplicationContext(),"Update successfully ", Toast.LENGTH_SHORT).show();
            } else {
                long id = DbHelperHike.createHike(
                    "" + Name,
                    "" + Location,
                    "" + Date,
                    "" + Duration,
                    "" + Length,
                    "" + Difficulty,
                    "" + Parking,
                    "" + Fquantity,
                    "" + Description
            );
            Toast.makeText(getApplicationContext(),"Add " + id, Toast.LENGTH_SHORT).show();
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