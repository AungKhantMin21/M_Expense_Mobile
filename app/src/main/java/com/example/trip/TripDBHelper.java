package com.example.trip;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class TripDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "m_expense_db";
    private static final String TABLE_NAME = "trip";

    private static final String ID_COLUMN = "tid";
    private static final String NAME_COLUMN = "trip_name";
    private static final String DESTINATION_COLUMN = "trip_destination";
    private static final String DATE_COLUMN = "trip_date";
    private static final String RISK_COLUMN = "trip_risk";
    private static final String METHOD_COLUMN = "trip_method";
    private static final String DESCRIPTION_COLUMN = "trip_description";


    public TripDBHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tableCreate="create table "+TABLE_NAME+" ("+ID_COLUMN+" integer primary key autoincrement NOT NULL,"+
                NAME_COLUMN+" text NOT NULL, "+
                DESTINATION_COLUMN+" text NOT NULL," +
                DATE_COLUMN + " text NOT NULL," +
                RISK_COLUMN + " boolean NOT NULL,"+
                METHOD_COLUMN + " text," +
                DESCRIPTION_COLUMN + " text" +
                ")";
        sqLiteDatabase.execSQL(tableCreate); //create table trip
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldversion, int newversion) {
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

    public long addTrip(Trip trip){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COLUMN,trip.getTrip_name());
        values.put(DESTINATION_COLUMN,trip.getTrip_destination());
        values.put(DATE_COLUMN,trip.getTrip_date());
        values.put(RISK_COLUMN,trip.getTrip_risk());
        values.put(METHOD_COLUMN,trip.getTrip_method());
        values.put(DESCRIPTION_COLUMN,trip.getTrip_description()
        );

        return database.insert(TABLE_NAME,null,values);
    }


    @SuppressLint("Range")
    public ArrayList<Trip> getAllTrips()
    {
        SQLiteDatabase database = this.getReadableDatabase();

        ArrayList<Trip> tripList = new ArrayList<>();
        String query = "SELECT * FROM "+ TABLE_NAME;

        Cursor results = database.rawQuery(query,null);

        if(results.moveToFirst()){
            do {
                long id = Long.parseLong(results.getString(0));
                String name = results.getString(1);
                String destinaiton = results.getString(2);
                String date = results.getString(3);
                boolean risk = results.getInt(4) > 0;
                String method = results.getString(5);
                String description = results.getString(6);
                tripList.add(new Trip(id,name,destinaiton,date,risk,method,description));
            } while (results.moveToNext());
        }
        results.close();

//        while(results.moveToNext()){
//            HashMap<String,String> trip = new HashMap<>();
//            trip.put("trip_name",results.getString(results.getColumnIndex(NAME_COLUMN)));
//            trip.put("trip_destination",results.getString(results.getColumnIndex(DESTINATION_COLUMN)));
//            trip.put("trip_date",results.getString(results.getColumnIndex(DATE_COLUMN)));
//            trip.put("trip_risk",results.getString(results.getColumnIndex(RISK_COLUMN)));
//            trip.put("trip_method",results.getString(results.getColumnIndex(METHOD_COLUMN)));
//            trip.put("trip_description",results.getString(results.getColumnIndex(DESCRIPTION_COLUMN)));
//
//            tripList.add(trip);
//        }


        return tripList;

    }

    public void updateTrip (Trip trip){
        ContentValues values = new ContentValues();
        values.put(ID_COLUMN,trip.getTid());
        values.put(NAME_COLUMN,trip.getTrip_name());
        values.put(DESTINATION_COLUMN,trip.getTrip_destination());
        values.put(DATE_COLUMN,trip.getTrip_date());
        values.put(RISK_COLUMN,trip.getTrip_risk());
        values.put(METHOD_COLUMN,trip.getTrip_method());
        values.put(DESCRIPTION_COLUMN,trip.getTrip_description());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME, values, ID_COLUMN + " = ?", new String[]{String.valueOf(trip.getTid())});
    }

    public void deleteTrip(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID_COLUMN + " = ?", new String[]{String.valueOf(id)});
    }
}
