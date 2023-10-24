package com.example.assignment1;

public class Constants {
    public static final String DATABASE_NAME = "HIKE_DB";
    public static final int DATABASE_VERSION = 1;

    //Create Table Hike
        //Create table name
        public static final String TABLE_NAME = "HIKE";

        //Create table attributes
        public static final String H_ID = "ID";
        public static final String H_Name = "NAME";
        public static final String H_Location ="LOCATION";
        public static final String H_Date ="DATE";
        public static final String H_Duration ="DURATION";
        public static final String H_Length ="LENGTH";
        public static final String H_Difficulty ="DIFFICULTY";
        public static final String H_ParkingAva ="PARKINGAVA";
        public static final String H_FQuantity = "FRIQUANTITY";
        public static final String H_Description ="DESCRIPTION";

    //Create Table Observation
        //Create table name
        public static final String OBS_TABLE_NAME = "OBSERVATION";

        //Create table attributes
        public static final String O_ID = "OBSERVATIONID";
        public static final String O_OBSERVATION = "OBSERVATION";
        public static final String O_TIME ="TIME";
        public static final String O_COMMENT ="COMMENT";
        public static final String O_HIKEID ="HIKEID";

        //Query table
        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "( "
                + H_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + H_Name + " TEXT, "
                + H_Location + " TEXT, "
                + H_Date + " TEXT, "
                + H_Duration + " TEXT, "
                + H_Length + " TEXT, "
                + H_Difficulty + " TEXT, "
                + H_ParkingAva + " TEXT, "
                + H_FQuantity + " TEXT, "
                + H_Description + " TEXT"
                + " );";

        //Query table
        public static final String OBS_CREATE_TABLE = "CREATE TABLE " + OBS_TABLE_NAME + "( "
                + O_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + O_OBSERVATION + " TEXT, "
                + O_TIME + " TEXT, "
                + O_COMMENT + " TEXT, "
                + O_HIKEID + " TEXT"
                + " );";
}
