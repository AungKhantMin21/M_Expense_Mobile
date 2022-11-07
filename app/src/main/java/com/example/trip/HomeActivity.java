package com.example.trip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    TripDBHelper dbHelper;

    Intent intent;

    ListView list;

    ListAdapter adapter;

    Button addBtn;

    Integer tripCount;

    TextView totalTripsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbHelper = new TripDBHelper(this);


        //===================================== Trip List Adapter ============================================
        ArrayList<HashMap<String, String>> tripList = dbHelper.getAllTrips();

        list =  (ListView) findViewById(R.id.trip_list_view);

        adapter = new SimpleAdapter(HomeActivity.this,tripList,R.layout.trip_row, new String[]{"trip_name","trip_destination","trip_date"}, new int[]{R.id.trip_name,R.id.trip_destination,R.id.trip_date});

        list.setAdapter(adapter);

        tripCount = list.getAdapter().getCount();

        totalTripsText = findViewById(R.id.totalTripsText);

        totalTripsText.setText("Total Trips : "+tripCount);


        //===================================== Goes to Add Trip Form (Btn) =======================================
        addBtn = findViewById(R.id.addTripBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}