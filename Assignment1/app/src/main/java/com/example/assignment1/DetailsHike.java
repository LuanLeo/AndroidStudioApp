package com.example.assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class DetailsHike extends AppCompatActivity {
    TextView  name, location, date, duration, length, difficulty, parkingAva, fquantity, description;
    private String id;
    private int obsId;
    private DbHelperHike DbHelperHike;
    public AdapterObservation AdapterObservation;
    private RecyclerView ObsList;
    private FloatingActionButton ObsCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_hike);

        //Init database
        DbHelperHike = new DbHelperHike(this);

        //Get data from intent
        Intent intent = getIntent();

        //Init variables
        id = intent.getStringExtra("HIKEID");
        name = findViewById(R.id.NameDetails);
        location = findViewById(R.id.LocationDetails);
        date = findViewById(R.id.DateDetails);
        duration = findViewById(R.id.DurDetails);
        length = findViewById(R.id.LengthDetails);
        difficulty = findViewById(R.id.DiffDetails);
        parkingAva = findViewById(R.id.ParkingDetails);
        fquantity = findViewById(R.id.FquantityDetails);
        description = findViewById(R.id.DescriptionDetails);

        ObsList = findViewById(R.id.obsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        ObsList.setLayoutManager(linearLayoutManager);
        ObsList.setHasFixedSize(false);

        ObsCreate = findViewById(R.id.createObservation_button);
        ObsCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Move to add_hike view
                Intent intent = new Intent(DetailsHike.this, AddEditObservationActivity.class);
                intent.putExtra("isAddObservation", false);
                intent.putExtra("HIKEID", id);
                startActivity(intent);
            }
        });

        loadHikebyId();
        loadObs();
    }

    public void loadObs()
    {
        AdapterObservation = new AdapterObservation(this, DbHelperHike.getAllObservation(id));
        ObsList.setAdapter(AdapterObservation);
    }

    public void loadHikebyId()
    {
        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME + " WHERE " + Constants.H_ID + " =\"" + id + "\"";

        SQLiteDatabase db = DbHelperHike.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst())
        {
            do{
                //Date data from database to local variables
                String Name = "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_Name));
                String Location = "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_Location));
                String Date = "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_Date));
                String Duration = "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_Duration));
                String Length = "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_Length));
                String Difficulty = "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_Difficulty));
                String Parking = "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_ParkingAva));
                String Fquantity = "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_FQuantity));
                String Description = "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_Description));

                //Set data to textview
                name.setText(Name);
                location.setText(Location);
                date.setText(Date);
                duration.setText(Duration);
                length.setText(Length);
                difficulty.setText(Difficulty);
                parkingAva.setText(Parking);
                fquantity.setText(Fquantity);
                description.setText(Description);
            } while(cursor.moveToNext());
        }
        db.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadHikebyId();
        loadObs();
    }
}