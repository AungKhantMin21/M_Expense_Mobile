package com.example.trip;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdvanceSearchViewHolder extends RecyclerView.ViewHolder {
    TextView searchName, searchDestination, searchDate;

    ImageButton linkBtn;


    public AdvanceSearchViewHolder(@NonNull View itemView) {
        super(itemView);
        searchName = itemView.findViewById(R.id.advanceSearchTripName);
        searchDestination = itemView.findViewById(R.id.advanceSearchTripDestination);
        searchDate = itemView.findViewById(R.id.advanceSearchTripDate);
        linkBtn = itemView.findViewById(R.id.advanceSearchLink);
    }
}
