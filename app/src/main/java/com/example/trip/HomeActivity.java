package com.example.trip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    TripDBHelper dbHelper;

    Intent intent;

    ListView list;

    ListAdapter adapter;

    Button addBtn;

    Integer tripCount;

    TextView totalTripsText, backToAll;

    TextInputEditText searchInput;

    ImageButton searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView tripView = findViewById(R.id.trip_list_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        tripView.setLayoutManager(linearLayoutManager);
        tripView.setHasFixedSize(true);
        
        searchBtn = findViewById(R.id.basicSeachBtn);
        searchInput = findViewById(R.id.basicSearchInput);
        backToAll = findViewById(R.id.backToAll);

        dbHelper = new TripDBHelper(this);


        //===================================== Trip List Adapter ============================================
        ArrayList<Trip> tripList = dbHelper.getAllTrips();

        if (tripList.size() > 0) {
            tripView.setVisibility(View.VISIBLE);
            TripApater mAdapter = new TripApater(this, tripList);
            tripView.setAdapter(mAdapter);
        }
        else {
            tripView.setVisibility(View.GONE);
            Toast.makeText(this, "There is no trip in the database. Start adding now", Toast.LENGTH_LONG).show();
        }

//        list =  (ListView) findViewById(R.id.trip_list_view);
//
//        adapter = new SimpleAdapter(HomeActivity.this,tripList,R.layout.trip_row, new String[]{"trip_name","trip_destination","trip_date"}, new int[]{R.id.trip_name,R.id.trip_destination,R.id.trip_date});
//
//        list.setAdapter(adapter);


        if(tripList.size() > 0){
            tripCount = tripView.getAdapter().getItemCount();

            totalTripsText = findViewById(R.id.totalTripsText);

            totalTripsText.setText("Total Trips : "+tripCount);
        }




        //===================================== Goes to Add Trip Form (Btn) =======================================
        addBtn = findViewById(R.id.addTripBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(searchInput.length() != 0){
                    backToAll.setVisibility(View.VISIBLE);
                    String name = searchInput.getText().toString();

                    ArrayList<Trip> searchList =  dbHelper.basicSearch(name);
//
//
                    if (searchList.size() > 0) {

                        BasicSearchAdapter sAdapter = new BasicSearchAdapter(HomeActivity.this, searchList);
                        tripView.setAdapter(sAdapter);
                        totalTripsText.setText("Results ("+String.valueOf(searchList.size())+")");
                    }
                    else {
                        totalTripsText.setText("No Result");
                        BasicSearchAdapter sAdapter = new BasicSearchAdapter(HomeActivity.this, searchList);
                        tripView.setAdapter(sAdapter);
                    }
                }
            }
        });

        backToAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TripApater mAdapter = new TripApater(HomeActivity.this, tripList);
                tripView.setAdapter(mAdapter);
                totalTripsText.setText("Total Trips : "+tripCount);
                backToAll.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull  Menu menu) {
        getMenuInflater().inflate(R.menu.homemenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        long tid;
        if(item.getItemId() == R.id.search_item){
            intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}