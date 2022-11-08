package com.example.trip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailTripActivity extends AppCompatActivity {

    TextView tvName,tvDate,tvDestination,tvRisk,tvDescription;

    ImageView imgCar,imgTrain,imgBus,imgFlight,imgShip;

    TripDBHelper dbHelper;

    Intent intent;

    long _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_trip);

        tvName = findViewById(R.id.detailTripName);
        tvDate = findViewById(R.id.detailTripDate);
        tvDestination = findViewById(R.id.detailTripDestination);
        tvRisk = findViewById(R.id.detailTripRisk);
        imgCar  = findViewById(R.id.detailTripCar);
        imgTrain = findViewById(R.id.detailTripTrain);
        imgBus = findViewById(R.id.detailTripBus);
        imgFlight = findViewById(R.id.detailTripFlight);
        imgShip = findViewById(R.id.detailTripShip);
        tvDescription = findViewById(R.id.detailTripDescription);

        dbHelper = new TripDBHelper(this);

        intent = getIntent();
//        String id = intent.getStringExtra("id");
        String name =intent.getStringExtra("name");
        String destination =intent.getStringExtra("destination");
        String date =intent.getStringExtra("date");
        String risk =intent.getStringExtra("risk");
        String method =intent.getStringExtra("method");
        String description =intent.getStringExtra("description");

//        _id= Long.parseLong(id);

        Boolean _risk = Boolean.parseBoolean(risk);

        tvName.setText(name);
        tvDestination.setText(destination);
        tvDate.setText(date);

        if(risk.equals("Yes")){
            tvRisk.setText("Yes");
        } else {
            tvRisk.setText("No");
        }

        tvDescription.setText(description);

        if(method.equals("Car")){
            imgCar.setBackgroundResource(R.drawable.activeroundedborder);
            imgCar.setColorFilter(getResources().getColor(R.color.white));
        } else if (method.equals("Train")){
            imgTrain.setBackgroundResource(R.drawable.activeroundedborder);
            imgTrain.setColorFilter(getResources().getColor(R.color.white));
        } else if (method.equals("Bus")){
            imgBus.setBackgroundResource(R.drawable.activeroundedborder);
            imgBus.setColorFilter(getResources().getColor(R.color.white));
        } else if (method.equals("Flight")){
            imgFlight.setBackgroundResource(R.drawable.activeroundedborder);
            imgFlight.setColorFilter(getResources().getColor(R.color.white));
        } else if (method.equals("Ship")) {
            imgShip.setBackgroundResource(R.drawable.activeroundedborder);
            imgShip.setColorFilter(getResources().getColor(R.color.white));
        }
    }
}