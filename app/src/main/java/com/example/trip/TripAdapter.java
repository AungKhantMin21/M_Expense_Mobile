package com.example.trip;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class TripAdapter extends ArrayAdapter<Trip> implements View.OnClickListener {
    private ArrayList<Trip> dataSet;
    Context mContext;
    Intent intent;

    private static class ViewHolder{
        TextView name;
        TextView destination;
        TextView date;
        ImageButton edit;
        ImageButton delete;
    }

    public TripAdapter(ArrayList<Trip> trip, Context context){
        super(context,R.layout.trip_row,trip);
        this.dataSet = trip;
        this.mContext =context;
    }

    @Override
    public void onClick(View view) {

        int position =(Integer) view.getTag();
        Object object = getItem(position);
        Trip trip = (Trip)object;

        switch (view.getId())
        {
            case R.id.tripEditBtn:
                intent = new Intent(mContext,EditTripActivity.class);
                mContext.startActivity(intent);
                break;
        }

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Trip trip = getItem(position);

        ViewHolder viewHolder;

        final View result;


        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.trip_row,parent,false);
            viewHolder.name  = (TextView) convertView.findViewById(R.id.trip_name);
            viewHolder.destination = (TextView) convertView.findViewById(R.id.trip_destination);
            viewHolder.date = (TextView) convertView.findViewById(R.id.trip_date);
            viewHolder.edit = (ImageButton) convertView.findViewById(R.id.tripEditBtn);
            viewHolder.delete = (ImageButton) convertView.findViewById(R.id.tripDeleteBtn);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        lastPosition = position;

        viewHolder.name.setText(trip.getTrip_name());
        viewHolder.destination.setText(trip.getTrip_destination());
        viewHolder.date.setText(trip.getTrip_date());
        viewHolder.edit
    }
}
