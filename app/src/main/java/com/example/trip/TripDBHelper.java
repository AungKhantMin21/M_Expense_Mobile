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
    private static final int DATABASE_VERSION = 3;

    private static final String DATABASE_NAME = "m_expense_db";
    private static final String TABLE_NAME = "trip";

    private static final String ID_COLUMN = "tid";
    private static final String NAME_COLUMN = "trip_name";
    private static final String DESTINATION_COLUMN = "trip_destination";
    private static final String DATE_COLUMN = "trip_start_date";
    private static final String RISK_COLUMN = "trip_risk";
    private static final String METHOD_COLUMN = "trip_method";
    private static final String DESCRIPTION_COLUMN = "trip_description";


    private static final String END_DATE_COLUMN = "trip_end_date";
    private static final String BUDGET_COLUMN = "trip_budget";


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
                DESCRIPTION_COLUMN + " text, " +
                END_DATE_COLUMN + " text, " +
                BUDGET_COLUMN + " text " +
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
        values.put(DESCRIPTION_COLUMN,trip.getTrip_description());
        values.put(END_DATE_COLUMN,trip.getTrip_enddate());
        values.put(BUDGET_COLUMN,trip.getTrip_budget());


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
                String end_date = results.getString(7);
                String budget = results.getString(8);
                tripList.add(new Trip(id,name,destinaiton,date,risk,method,description,end_date,budget));
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

    public Trip getTripDetail(long tid){
        SQLiteDatabase database = this.getReadableDatabase();


        Trip tripDetail = null;
        String query = "SELECT * FROM "+ TABLE_NAME + " WHERE "+ ID_COLUMN + " = "+ tid;

        Cursor results = database.rawQuery(query,null);

        if(results.moveToFirst()){
            long id = Long.parseLong(results.getString(0));
            String name = results.getString(1);
            String destinaiton = results.getString(2);
            String date = results.getString(3);
            boolean risk = results.getInt(4) > 0;
            String method = results.getString(5);
            String description = results.getString(6);
            String end_date = results.getString(7);
            String budget = results.getString(8);
            tripDetail = new Trip(id,name,destinaiton,date,risk,method,description,end_date,budget);
        }
        results.close();

        return  tripDetail;
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
        values.put(END_DATE_COLUMN, trip.getTrip_enddate());
        values.put(BUDGET_COLUMN,trip.getTrip_budget());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME, values, ID_COLUMN + " = ?", new String[]{String.valueOf(trip.getTid())});
    }

    public void deleteTrip(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID_COLUMN + " = ?", new String[]{String.valueOf(id)});
    }

    public ArrayList<Trip> basicSearch(String input){
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<Trip> searchtripList = new ArrayList<>();
        input = "'%"+input+"%'";
        String query = "SELECT * FROM "+ TABLE_NAME + " WHERE "+ NAME_COLUMN + " LIKE  "+input;

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
                String end_date = results.getString(7);
                String budget = results.getString(8);
                searchtripList.add(new Trip(id,name,destinaiton,date,risk,method,description,end_date,budget));
            } while (results.moveToNext());
        }
        results.close();
//        searchtripList.add(new Trip(1,"Meeting","Edinburgh","Nov 1, 2022",false,"Train","Goest to london by train"));


//        Cursor results = database.query(TABLE_NAME,new String[]{ID_COLUMN,NAME_COLUMN,DESTINATION_COLUMN,DATE_COLUMN,RISK_COLUMN,METHOD_COLUMN,DESCRIPTION_COLUMN},
//                NAME_COLUMN+" LIKE ?",new String[]{"%"+input+"%"},
//                null,null,null);
//
//        if(results.getCount() != 0){
//            results.moveToFirst();
//            int i = 1;
//            while(!results.isAfterLast()){
//                long id = Long.parseLong(results.getString(0));
//                String name = results.getString(1);
//                String destinaiton = results.getString(2);
//                String date = results.getString(3);
//                boolean risk = results.getInt(4) > 0;
//                String method = results.getString(5);
//                String description = results.getString(6);
//                tripList.add(new Trip(id,name,destinaiton,date,risk,method,description));
//                i++;
//                results.moveToNext();
//            } ;
//        }
        //results.close();


        return searchtripList;
    }

    public ArrayList<Trip> advanceSearch(String input){
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<Trip> searchtripList = new ArrayList<>();
        input = "'%"+input+"%'";
        String query = "SELECT * FROM "+ TABLE_NAME + " WHERE "+ NAME_COLUMN + " LIKE  "+input + " OR " + DESTINATION_COLUMN + " LIKE  "+input + " OR " + DATE_COLUMN + " LIKE  "+input;

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
                String end_date = results.getString(7);
                String budget = results.getString(8);
                searchtripList.add(new Trip(id,name,destinaiton,date,risk,method,description,end_date,budget));
            } while (results.moveToNext());
        }
        results.close();
        return searchtripList;
    }




}
