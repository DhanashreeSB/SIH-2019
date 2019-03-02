package com.example.shyam.fly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SelectAirline extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_airline);
    }

    public void alliance_air(View view)
    {
        Intent intent = new Intent(SelectAirline.this, HomeActivity.class);
        intent.putExtra("Airline","Alliance Air");
        startActivity(intent);
    }

    public void spicejet(View view)
    {
        Intent intent = new Intent(SelectAirline.this, HomeActivity.class);
        intent.putExtra("Airline","SpiceJet");
        startActivity(intent);
    }

    public void indigo(View view)
    {
        Intent intent = new Intent(SelectAirline.this, HomeActivity.class);
        intent.putExtra("Airline","Indigo");
        startActivity(intent);

    }

    public void trujet(View view)
    {
        Intent intent = new Intent(SelectAirline.this, HomeActivity.class);
        intent.putExtra("Airline","Trujet");
        startActivity(intent);
    }

    public void deccan_charters(View view)
    {
        Intent intent = new Intent(SelectAirline.this, HomeActivity.class);
        intent.putExtra("Airline","Deccan Charters");
        startActivity(intent);
    }

    public void air_odisha(View view)
    {
        Intent intent = new Intent(SelectAirline.this, HomeActivity.class);
        intent.putExtra("Airline","Air Odisha");
        startActivity(intent);
    }
}
