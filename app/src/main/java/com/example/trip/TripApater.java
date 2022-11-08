package com.example.trip;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TripApater extends RecyclerView.Adapter<TripViewHolder> implements Filterable {

    private Context context;
    private ArrayList<Trip> listTrips;
    private ArrayList<Trip> mArrayList;
    private TripDBHelper mDatabase;

    TripApater(Context context, ArrayList<Trip> listTrips){
        this.context = context;
        this.listTrips = listTrips;
        this.mArrayList = listTrips;
        mDatabase = new TripDBHelper(context);
    }

    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_row, parent, false);
        return new TripViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        final Trip trip = listTrips.get(position);
        holder.tvName.setText(trip.getTrip_name());
        holder.tvDestination.setText(trip.getTrip_destination());
        holder.tvDate.setText(trip.getTrip_date());
        holder.tvLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailTripActivity.class);
                long value = trip.getTid();
                String risk;
                if(trip.getTrip_risk()){
                    risk = "Yes";
                } else {
                    risk = "No";
                }
                String _value = String.valueOf(value);
                intent.putExtra("id",_value);
                intent.putExtra("name", trip.getTrip_name());
                intent.putExtra("destination", trip.getTrip_destination());
                intent.putExtra("date", trip.getTrip_date());
                intent.putExtra("risk", risk);
                intent.putExtra("method", trip.getTrip_method());
                intent.putExtra("description", trip.getTrip_description());
                context.startActivity(intent);
            }
        });
        holder.editBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditTripActivity.class);
                long value = trip.getTid();
                String _value = String.valueOf(value);
                String risk;
                if(trip.getTrip_risk()){
                    risk = "Yes";
                } else {
                    risk = "No";
                }
                intent.putExtra("id",_value);
                intent.putExtra("name", trip.getTrip_name());
                intent.putExtra("destination", trip.getTrip_destination());
                intent.putExtra("date", trip.getTrip_date());
                intent.putExtra("risk",risk);
                intent.putExtra("method", trip.getTrip_method());
                intent.putExtra("description", trip.getTrip_description());
                context.startActivity(intent);
            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.deleteTrip(trip.getTid());
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTrips.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
