package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class DetailsObservation extends AppCompatActivity {
    TextView name, date, comment;
    String id;
    private DbHelperHike DbHelperHike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_observation);

        //Init database
        DbHelperHike = new DbHelperHike(this);


        //Get data from intent
        Intent intent = getIntent();

        //Init variables
        id = intent.getStringExtra("OBSID");
        name = findViewById(R.id.ObsName);
        date = findViewById(R.id.ObsDate);
        comment = findViewById(R.id.ObsComment);

        loadObservation();
    }
    public void loadObservation()
    {
        String selectQuery = "SELECT * FROM " + Constants.OBS_TABLE_NAME + " WHERE " + Constants.O_ID + " =\"" + id + "\"";

        SQLiteDatabase db = DbHelperHike.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst())
        {
            do{
                //Date data from database to local variables
                String Name = "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.O_OBSERVATION));
                String Date = "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.O_TIME));
                String Comment = "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.O_COMMENT));

                //Set data to textview
                name.setText(Name);
                date.setText(Date);
                comment.setText(Comment);

            } while(cursor.moveToNext());
        }
        db.close();
    }
}