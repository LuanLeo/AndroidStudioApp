package com.example.assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelperHike extends SQLiteOpenHelper {

    public DbHelperHike(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TABLE);
        db.execSQL(Constants.OBS_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        //Drop table if exists
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.OBS_TABLE_NAME);
        onCreate(db);
    }

    //Create hike
    public long createHike(String Name, String Location, String Date, String Duration, String Length, String Difficulty, String Parking, String Fquantity, String Description) {

        //Call database
        SQLiteDatabase db = this.getWritableDatabase();

        //Create object to save data
        ContentValues content = new ContentValues();

        //Add content to table's attributes
        content.put(Constants.H_Name, Name);
        content.put(Constants.H_Location, Location);
        content.put(Constants.H_Date, Date);
        content.put(Constants.H_Duration, Duration);
        content.put(Constants.H_Length, Length);
        content.put(Constants.H_Difficulty, Difficulty);
        content.put(Constants.H_ParkingAva, Parking);
        content.put(Constants.H_FQuantity, Fquantity);
        content.put(Constants.H_Description, Description);

        //Push data to database with id
        long id = db.insert(Constants.TABLE_NAME, null, content);

        db.close();
        return id;
    }

    //Update hike
    public void updateHike(String Id, String Name, String Location, String Date, String Duration, String Length, String Difficulty, String Parking, String Fquantity, String Description) {

        //Call database
        SQLiteDatabase db = this.getWritableDatabase();

        //Create object to save data
        ContentValues content = new ContentValues();

        //Add content to table's attributes
        content.put(Constants.H_Name, Name);
        content.put(Constants.H_Location, Location);
        content.put(Constants.H_Date, Date);
        content.put(Constants.H_Duration, Duration);
        content.put(Constants.H_Length, Length);
        content.put(Constants.H_Difficulty, Difficulty);
        content.put(Constants.H_ParkingAva, Parking);
        content.put(Constants.H_FQuantity, Fquantity);
        content.put(Constants.H_Description, Description);

        //Push data to database with id
        db.update(Constants.TABLE_NAME,content,Constants.H_ID + " =? ", new String[]{Id});

        db.close();
    }

    //Delete specific hike
    public void deleteHike(String id){
        SQLiteDatabase db = getWritableDatabase();

        db.delete(Constants.TABLE_NAME,"ID =? ",new String[]{id});
        db.delete(Constants.OBS_TABLE_NAME,"HIKEID =? ",new String[]{id});
        db.close();
    }

    //Delete all hikes
    public void deleteAllHike(){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM "+Constants.TABLE_NAME);
        db.execSQL("DELETE FROM "+Constants.OBS_TABLE_NAME);
        db.close();
    }

    //Get all hike
    public ArrayList<ModelHike> getAllHike(){
        ArrayList<ModelHike> hikeList = new ArrayList<>();

        //Query database
        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME;

        //Take data from database to list
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst())
        {
            do {
                ModelHike model = new ModelHike(
                        "" + cursor.getInt(cursor.getColumnIndexOrThrow(Constants.H_ID)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_Name)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_Location)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_Date)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_Duration)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_Length)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_Difficulty)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_ParkingAva)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_FQuantity)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_Description))
                );
                hikeList.add(model);
            } while (cursor.moveToNext());
        }
        db.close();
        return hikeList;
    }

    //Search hike
    public ArrayList<ModelHike> getSearchHike(String query) {
        ArrayList<ModelHike> searchhikeList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String searchQuery = "SELECT * FROM " + Constants.TABLE_NAME + " WHERE " + Constants.H_Name + " LIKE '%" + query + "%'";
        Cursor cursor = db.rawQuery(searchQuery, null);
        if(cursor.moveToFirst())
        {
            do {
                ModelHike model = new ModelHike(
                        "" + cursor.getInt(cursor.getColumnIndexOrThrow(Constants.H_ID)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_Name)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_Location)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_Date)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_Duration)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_Length)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_Difficulty)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_ParkingAva)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_FQuantity)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_Description))
                );
                searchhikeList.add(model);
            } while (cursor.moveToNext());
        }
        db.close();
        return searchhikeList;
    }

    //Create observation
    public long createOservation(String Name, String Date, String Comment, String HikeId) {

        //Call database
        SQLiteDatabase db = this.getWritableDatabase();

        //Create object to save data
        ContentValues content = new ContentValues();

        //Add content to table's attributes
        content.put(Constants.O_OBSERVATION, Name);
        content.put(Constants.O_TIME, Date);
        content.put(Constants.O_COMMENT, Comment);
        content.put(Constants.O_HIKEID, HikeId);

        //Push data to database with id
        long id = db.insert(Constants.OBS_TABLE_NAME, null, content);

        db.close();
        return id;
    }

    //Update observation
    public void updateObservation(String Id, String Name, String Date, String Comment) {

        //Call database
        SQLiteDatabase db = this.getWritableDatabase();

        //Create object to save data
        ContentValues content = new ContentValues();

        //Add content to table's attributes
        content.put(Constants.O_OBSERVATION, Name);
        content.put(Constants.O_TIME, Date);
        content.put(Constants.O_COMMENT, Comment);

        //Push data to database with id
        db.update(Constants.OBS_TABLE_NAME,content,Constants.O_ID + " =? ", new String[]{Id});
        db.close();
    }

    //Get all observations by specific hike
    public ArrayList<ModelObservation> getAllObservation(String id){
        ArrayList<ModelObservation> obsList = new ArrayList<>();

        //Query database
        String selectQuery = "SELECT * FROM " + Constants.OBS_TABLE_NAME + " WHERE " + Constants.O_HIKEID + " =\"" + id + "\"";

        //Take data from database to list
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst())
        {
            do {
                ModelObservation model = new ModelObservation(
                        "" + cursor.getInt(cursor.getColumnIndexOrThrow(Constants.O_ID)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.O_OBSERVATION)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.O_TIME)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.O_COMMENT)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.O_HIKEID))
                );
                obsList.add(model);
            } while (cursor.moveToNext());
        }
        db.close();
        return obsList;
    }

    public void DeleteObservation(String id){
        SQLiteDatabase db = getWritableDatabase();

        db.delete(Constants.OBS_TABLE_NAME," OBSERVATIONID =? ",new String[]{id});
        db.close();
    }
}
