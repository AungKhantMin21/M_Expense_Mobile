package com.example.trip;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdvanceSearchAdapter extends RecyclerView.Adapter<AdvanceSearchViewHolder> {

    private Context context;
    private ArrayList<Trip> listTrips;
    private ArrayList<Trip> mArrayList;

    AdvanceSearchAdapter(android.content.Context context, ArrayList<Trip> listTrips) {
        this.context = context;
        this.listTrips = listTrips;
        this.mArrayList = listTrips;
    }


    @Override
    public AdvanceSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.advanced_search_row, parent, false);
        return new AdvanceSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdvanceSearchViewHolder holder, int position) {
        final Trip trip = listTrips.get(position);
        holder.searchName.setText(trip.getTrip_name());
        holder.searchDestination.setText(trip.getTrip_destination());
        holder.searchDate.setText(trip.getTrip_date());
        holder.linkBtn.setOnClickListener(new View.OnClickListener() {
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
    }

    @Override
    public int getItemCount() {
        return listTrips.size();
    }
}