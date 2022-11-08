package com.example.trip;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class TripViewHolder extends RecyclerView.ViewHolder {
    TextView tvName, tvDestination, tvDate, tvLink;

    ImageButton editBtn, deleteBtn;

    public TripViewHolder(View itemView) {
        super(itemView);

        tvName = itemView.findViewById(R.id.trip_name);
        tvDestination = itemView.findViewById(R.id.trip_destination);
        tvDate = itemView.findViewById(R.id.trip_date);
        tvLink = itemView.findViewById(R.id.to_trip_details_link);
        editBtn = itemView.findViewById(R.id.tripEditBtn);
        deleteBtn = itemView.findViewById(R.id.tripDeleteBtn);


    }
}
