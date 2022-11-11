package com.example.trip;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchViewHolder  extends RecyclerView.ViewHolder{
    TextView searchName,searchDestination,searchDate;

    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
        searchName = itemView.findViewById(R.id.searchTripName);
        searchDestination = itemView.findViewById(R.id.searchTripDestination);
        searchDate = itemView.findViewById(R.id.searchTripDate);

    }

}
