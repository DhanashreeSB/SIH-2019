package com.example.shyam.fly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }
    public void proceed(View view)
    {
        Intent intent = new Intent(FirstActivity.this, SelectAirline.class);
        startActivity(intent);
    }
}
