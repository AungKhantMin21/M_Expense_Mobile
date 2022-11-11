package com.example.trip;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BasicSearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private Context context;
    private ArrayList<Trip> listTrips;
    private ArrayList<Trip> mArrayList;

    BasicSearchAdapter(android.content.Context context, ArrayList<Trip> listTrips){
        this.context = context;
        this.listTrips = listTrips;
        this.mArrayList = listTrips;
    }



    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_row, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        final Trip trip = listTrips.get(position);
        holder.searchName.setText(trip.getTrip_name());
        holder.searchDestination.setText(trip.getTrip_destination());
        holder.searchDate.setText(trip.getTrip_date());
    }

    @Override
    public int getItemCount() {
        return listTrips.size();
    }

}
