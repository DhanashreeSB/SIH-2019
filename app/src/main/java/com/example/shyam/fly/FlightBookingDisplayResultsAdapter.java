package com.example.shyam.fly;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class FlightBookingDisplayResultsAdapter extends RecyclerView.Adapter<FlightBookingDisplayResultsAdapter.FlightViewHolder>{


    private static final String FLIGHT_URL = "http://10.192.6.166/fly/Api.php";
    public String arrival_from, depart_to;
    public Context mCtx;
    public List<FlightBookingDisplayResults> flightList;

    public FlightBookingDisplayResultsAdapter(Context mCtx, List<FlightBookingDisplayResults> flightList) {
        this.mCtx = mCtx;
        this.flightList = flightList;
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(mCtx);
        View view = layoutInflater.inflate(R.layout.available_flight_list,null);
        return new FlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FlightViewHolder flightViewHolder, final int i) {

        final FlightBookingDisplayResults flightBookingResults = flightList.get(i);
        flightViewHolder.textfromTime.setText(flightBookingResults.getFrom_time());
        flightViewHolder.texttoTime.setText(flightBookingResults.getTo_time());
        flightViewHolder.textfrom.setText(flightBookingResults.getFrom_city_booking());
        flightViewHolder.textto.setText(flightBookingResults.getTo_city_booking());
        flightViewHolder.textstop.setText(flightBookingResults.getStops());
        flightViewHolder.texttotalTime.setText(flightBookingResults.getTotal_time());
        flightViewHolder.textprice.setText(flightBookingResults.getPrice());
        flightViewHolder.imageView.setImageDrawable(mCtx.getResources().getDrawable(flightBookingResults.getImage_booking()));
        flightViewHolder.flightid.setText(flightBookingResults.getFlight_id());

        //setting OnClickListener to Recycler's cardViews

        /*flightViewHolder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String bduration = flightViewHolder.texttotalTime.getText().toString();
                final String bfromcitycode = flightViewHolder.textfrom.getText().toString();
                final String btocitycode = flightViewHolder.textto.getText().toString();
                final String bfromtime =  flightViewHolder.textfromTime.getText().toString();
                final String btotime = flightViewHolder.texttoTime.getText().toString();
                //final String bfromcity = flightBookingResults.getArrival_from();

                Log.w("____=========",flightBookingResults.getArrival_from());

                final Intent intent = new Intent(mCtx,ReviewActivity.class);
                intent.putExtra("duration",bduration);
                intent.putExtra("fromcitycode",bfromcitycode);
                intent.putExtra("tocitycode",btocitycode);
                intent.putExtra("fromtime",bfromtime);
                intent.putExtra("totime",btotime);
                intent.putExtra("passengerno2",FlightBookingDisplay.passengerno);
                //intent.putExtra("fromcity1",bfromcity);
                /*StringRequest stringRequest = new StringRequest(Request.Method.GET, FLIGHT_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i=0; i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String arrivalfrom_city = jsonObject.getString("arrival_from");
                                String departureto_city = jsonObject.getString("departure_to");
                                String arrival_from = jsonObject.getString("arrival_from_code");
                                String departure_to = jsonObject.getString("departure_to_code");
                                String departure_time = jsonObject.getString("departure_time");
                                String reach_at = jsonObject.getString("reach_at");
                                String duration = jsonObject.getString("duration");
                                String date_field = jsonObject.getString("date_field");
                                String operator_name = jsonObject.getString("operator_name");
                                //String title = ((TextView) recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.title)).getText().toString();

                                if(bduration.equals(duration)&&
                                        bfromcitycode.equals(arrival_from)&&
                                        btocitycode.equals(departure_to)&&
                                        bfromtime.equals(departure_time)&&
                                        btotime.equals(reach_at))
                                {
                                    Log.w("===========",bduration+" "+bfromcitycode+" "+arrivalfrom_city+" "+departureto_city+" "+date_field);
                                    intent.putExtra("arrivalfrom_city",arrivalfrom_city);
                                    intent.putExtra("departureto_city",departureto_city);
                                    intent.putExtra("dateoftravel",date_field);
                                    break;
                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mCtx,error.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });*/

                //Volley.newRequestQueue(mCtx).add(stringRequest);

              /*  mCtx.startActivity(intent);
                //mCtx.startActivity(intent);

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

    class FlightViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView textfromTime, texttoTime, textfrom, textto, textstop, texttotalTime, textprice, flightid;
        LinearLayout parentlayout;

        public FlightViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.airline);
            textfromTime = itemView.findViewById(R.id.textview_fromtime);
            texttoTime = itemView.findViewById(R.id.textview_totime);
            textfrom = itemView.findViewById(R.id.textview_fromcity);
            textto = itemView.findViewById(R.id.textview_tocity);
            textstop = itemView.findViewById(R.id.textview_stop);
            texttotalTime = itemView.findViewById(R.id.textview_totaltime);
            textprice = itemView.findViewById(R.id.textview_price);
            parentlayout = itemView.findViewById(R.id.parent_layout_recycler);
            flightid = itemView.findViewById(R.id.flightid);
        }
    }
}
