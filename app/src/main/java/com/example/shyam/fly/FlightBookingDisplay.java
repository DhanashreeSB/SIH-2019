package com.example.shyam.fly;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightBookingDisplay extends AppCompatActivity {

    private static final String FLIGHT_URL = "http://10.192.6.166/fly/Api.php";
    public HashMap<String, Integer> operatorNames;
    public String arrival_from_code, departure_to_code, departure_time, reach_at, duration, operator_name;
    //public TextView btextfromtime, btexttotime, btextfrom, btextto,bduration,bprice;

    //Take dates from and to from FlightBookingFragment
    public static int passengerno;
    public static String date_from,date_to;

    public String from_city_name,to_city_name;
    RecyclerView recyclerView;
    FlightBookingDisplayResultsAdapter flightBookingResultsAdapter;
    List<FlightBookingDisplayResults> flightBookingResults;
    ProgressDialog progressDialog;
    public String existance;

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_booking_display);

        //Get No. of passengers from the FlightBookingFragment
        passengerno = getIntent().getIntExtra("passengerno", 2);
        date_from = getIntent().getStringExtra("final_date_from");
        date_to = getIntent().getStringExtra("final_date_to");

        Log.w("INSIDE FLIGHTBOOKING DISPLAY ", String.valueOf(passengerno));

        getSupportActionBar().setTitle("Hello");
        flightBookingResults = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        operatorNames = new HashMap<String, Integer>();
        operatorNames.put("Indigo", R.drawable.indigo);
        operatorNames.put("SpiceJet", R.drawable.spicejet);
        operatorNames.put("Trujet", R.drawable.trujet);
        operatorNames.put("Others", R.drawable.airplane4);
        operatorNames.put("Alliance Air", R.drawable.airindialogo);
        operatorNames.put("Deccan Charters", R.drawable.deccan);
        operatorNames.put("Air Odisha", R.drawable.airodhisa);
        from_city_name = getIntent().getStringExtra("from_city_name");
        System.out.print("inside bookingDisplay"+from_city_name);
        to_city_name = getIntent().getStringExtra("to_city_name");
        progressDialog = new ProgressDialog(FlightBookingDisplay.this);


        /*
         * Statically add flight cards
         * */
        //flightBookingResults.add(new FlightBookingResults(1,"00:15","1:15","Chennai","Goa","2","2500","2hr",R.drawable.airplane4));
        //flightBookingResults.add(new FlightBookingResults(2,"01:15","2:15","Aurangabad","Goa","2","2500","2hr",R.drawable.airplane4));
        /*
         *Dynamically add cards
         */
        loadFlights();
        //creating recyclerview adapter
        FlightBookingDisplayResultsAdapter adapter = new FlightBookingDisplayResultsAdapter(this, flightBookingResults);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

    }

    public void loadFlights() {
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, FLIGHT_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i=0; i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String arrival_from = jsonObject.getString("arrival_from");
                        String departure_to = jsonObject.getString("departure_to");
                        String flight_id = jsonObject.getString("Flight_No");
                        String date_field = jsonObject.getString("date_field");
                        arrival_from_code = jsonObject.getString("arrival_from_code");
                        departure_to_code = jsonObject.getString("departure_to_code");
                        departure_time = jsonObject.getString("departure_time");
                        reach_at = jsonObject.getString("reach_at");
                        duration = jsonObject.getString("duration");
                        operator_name = jsonObject.getString("operator_name");
                        int operatorimageid = operatorNames.get(operator_name);
                        Log.w("operater name",operator_name+" "+operatorNames.get(operator_name));

                        // Print keys and values
                        /*for (String operator : operatorNames.keySet()) {
                            if(operator.equals(operator_name))

                            else
                            {
                                existance = "notexist";
                            }
                            Log.w("==========existance",existance);
                            //System.out.println("key: " + i + " value: " + operatorNames.get(operator));
                       }*/

                        Log.w("--------------from_city",from_city_name+" "+arrival_from);
                        Log.w("--------------from_city",to_city_name+" "+departure_to);

                        if(from_city_name.equals(arrival_from)&&to_city_name.equals(departure_to)&&HomeActivity.airlineName.equals(operator_name)&&date_from.equals(date_field))
                        {
                            Log.w("INSIDE FILTERING","Inside filtering");
                            FlightBookingDisplayResults flightBookingResults_local = new FlightBookingDisplayResults(1, departure_time, reach_at, arrival_from_code, departure_to_code, "2".concat(" stops"),"₹2500", duration, operatorimageid, arrival_from,flight_id);
                            flightBookingResults.add(flightBookingResults_local);
                            existance = "exists";
                            Log.w("======@#45",existance);
                        }
                        else
                        {
                            if(!existance.equals("exists")) {
                                existance = "notexist";
                            }
                        }
                    }
                    if(existance.equals("exists")) {
                        //creating recyclerview adapter
                        FlightBookingDisplayResultsAdapter adapter = new FlightBookingDisplayResultsAdapter(FlightBookingDisplay.this, flightBookingResults);

                        //setting adapter to recyclerview
                        recyclerView.setAdapter(adapter);
                    }

                    else
                    {
                        //creating recyclerview adapter
                        //FlightBookingDisplayResultsAdapter adapter = new FlightBookingDisplayResultsAdapter(FlightBookingDisplay.this, flightBookingResults);

                        //setting adapter to recyclerview
                        //recyclerView.setAdapter(adapter);
                        Toast.makeText(getApplicationContext(),"No Flights available",Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FlightBookingDisplay.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(FlightBookingDisplay.this);
        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);

    }
}

                /*for GET request
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i=0; i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String arrival_from = jsonObject.getString("arrival_from");
                        arrival_from_code = jsonObject.getString("arrival_from_code");
                        departure_to_code = jsonObject.getString("departure_to_code");
                        departure_time = jsonObject.getString("departure_time");
                        reach_at = jsonObject.getString("reach_at");
                        duration = jsonObject.getString("duration");
                        operator_name = jsonObject.getString("operator_name");
                        int operatorimageid = operatorNames.get(operator_name);
                        Log.w("operater name",operator_name+" "+operatorNames.get(operator_name));
                        FlightBookingDisplayResults flightBookingResults_local = new FlightBookingDisplayResults(1, departure_time, reach_at, arrival_from_code, departure_to_code, "2".concat(" stops"),"₹2500", duration, operatorimageid, arrival_from);
                        flightBookingResults.add(flightBookingResults_local);

                        // Print keys and values
                        /*for (String operator : operatorNames.keySet()) {
                            if(operator.equals(operator_name))

                            else
                            {
                                existance = "notexist";
                            }
                            Log.w("==========existance",existance);
                            //System.out.println("key: " + i + " value: " + operatorNames.get(operator));
                        }*/

                    /*}



                    //creating recyclerview adapter
                    FlightBookingDisplayResultsAdapter adapter = new FlightBookingDisplayResultsAdapter(FlightBookingDisplay.this, flightBookingResults);

                    //setting adapter to recyclerview
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FlightBookingDisplay.this,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });*/

                //Volley.newRequestQueue(this).add(stringRequest);
