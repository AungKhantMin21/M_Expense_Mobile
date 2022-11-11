package com.example.trip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    EditText searchEditText;
    ImageButton searchBtn;
    Button cancelBtn;
    TextView resultCount;

    TripDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchEditText = findViewById(R.id.searchEditText);
        searchBtn = findViewById(R.id.searchTripBtn);
        cancelBtn = findViewById(R.id.searchCancelBtn);
        resultCount = findViewById(R.id.resultCount);

        dbHelper = new TripDBHelper(this);

        RecyclerView searchView = (RecyclerView) findViewById(R.id.searchList);

        cancelBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                resultCount.setText("Search the name of trip");
                searchEditText.setText("");
                searchView.setVisibility(View.GONE);
            }
        });


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(searchEditText.length() == 0){
                    resultCount.setText("The search box is empty.");
                    searchView.setVisibility(View.GONE);
                } else {
                    searchTrip();
                }

            }
        });


    }

    private void searchTrip() {
        RecyclerView searchView = (RecyclerView) findViewById(R.id.searchList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        searchView.setLayoutManager(linearLayoutManager);
        searchView.setHasFixedSize(true);

        String name = searchEditText.getText().toString();

        //resultCount.setText(name);

        ArrayList<Trip> searchList =  dbHelper.advanceSearch(name);
//
//
        if (searchList.size() > 0) {

            searchView.setVisibility(View.VISIBLE);
            AdvanceSearchAdapter sAdapter = new AdvanceSearchAdapter(this, searchList);
            searchView.setAdapter(sAdapter);
            resultCount.setText("Results ("+String.valueOf(searchList.size())+")");
        }
        else {
            resultCount.setText("No Result");
            searchView.setVisibility(View.GONE);
        }

    }
}