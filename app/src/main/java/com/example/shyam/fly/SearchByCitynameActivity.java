package com.example.shyam.fly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchByCitynameActivity extends AppCompatActivity {

    private ListView lv;
    public static String fromcitySearch, tocitySearch;

    public String resolve;

    // Listview Adapter
    ArrayAdapter<String> adapter;

    // Search EditText
    EditText inputSearch;
    TextView airport_name;
    // ArrayList for Listview
    public HashMap<String, String> searchnearby;
    ArrayList<HashMap<String, String>> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_cityname);
        // Listview Data
        String products[] = {"Bikaner","Bhatinda","Jammu","Kolkata","Delhi","Shimla","Gwalior","Indore",
                "Ludhiana","Agra","Jaipur","Pathankot","Pantnagar","Dehradun","Chennai","Hubli","Hyderabad",
                "Pakyong","Kisangarh","Guwhati","Ahmednagar","Jaisalmer","Surat","Lilabari","Kadapa","Nanded",
                "Mumbai","Bangalore","Vidyanagar","Vijaywada","Salem","Porbandar","Ozar","Pune","Kolhapur",
                "Agartala","Shillong","Dimapur","Jamnagar","Diu","Mundra","Bhavnagar","Jagdalpur","Raipur",
                "Vishakhapatnam","Jharsugada","Bhubneshwar","Cochin","Goa","Jorhat","Allahabad","Kannur"};
        //"Bikaner","Jaipur","Bhatinda","Jammu","Kolkata","Tezpur","Chennai","Hubli","Hydrabad","Allahabad","Luknow","Patna","Delhi","Ozar","Indore","NAGPUR","COCHIN","GOA","Ahemdabad","Jorhat","Pakyang","Kishangarh","Guwahati","Jaisalmer","Banglore","Surat","Kolhaopur","Porbandar","Lilabari","Dehradun","Pithoragarh","Pantnagar","Kannur","Tirupati"
        lv = (ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        airport_name=(TextView) findViewById(R.id.airport_name);

        resolve = getIntent().getStringExtra("resolve");

        searchnearby = new HashMap<String, String>();
        searchnearby.put("Bikaner","NAL");
        searchnearby.put("Bhatinda","BUP");
        searchnearby.put("Jammu","IXJ");
        searchnearby.put("Kolkata","CCU");
        searchnearby.put("Delhi","DEL");
        searchnearby.put("Shimla","SLV");
        searchnearby.put("Gwalior","GWL");
        searchnearby.put("Indore","IDR");
        searchnearby.put("Ludhiana","LUH");
        searchnearby.put("Agra","BUP");
        searchnearby.put("Jaipur","JAI");
        searchnearby.put("Pathankot","IXP");
        searchnearby.put("Pantnagar","PGH");
        searchnearby.put("Dehradun","DUN");
        searchnearby.put("Chennai","MAA");
        searchnearby.put("Hubli","HBX");
        searchnearby.put("Hyderabad","HYD");
        searchnearby.put("Pakyong","PYG");
        searchnearby.put("Kisangarh","KUH");
        searchnearby.put("Guwhati","GAU");
        searchnearby.put("Ahmednagar","Ahmednagar");
        searchnearby.put("Jaisalmer","JSA");
        searchnearby.put("Surat","STV");
        searchnearby.put("Lilabari","IXI");
        searchnearby.put("Kadapa","CDP");
        searchnearby.put("Nanded","NDC");
        searchnearby.put("Mumbai","BOM");
        searchnearby.put("Bangalore","BLR");
        searchnearby.put("Vidyanagar","VDY");
        searchnearby.put("Vijaywada","VGA");
        searchnearby.put("Porbandar","PBD");
        searchnearby.put("Ozar","ISK");
        searchnearby.put("Pune","PNQ");
        searchnearby.put("Kolhapur","KLH");
        searchnearby.put("Agartala","IXA");
        searchnearby.put("Shillong","SLH");
        searchnearby.put("Dimapur","DMU");
        searchnearby.put("Jamnagar","JGA");
        searchnearby.put("Diu","DIU");
        searchnearby.put("Mundra","Mundra");
        searchnearby.put("Jagdalpur","JGB");
        searchnearby.put("Bhavnagar","BHU");
        searchnearby.put("Vishakhapatnam","VTZ");
        searchnearby.put("Jharsugada","jharsugada");
        searchnearby.put("Bhubneshwar","BBI");
        searchnearby.put("Raipur","RPR");
        searchnearby.put("Cochin","COK");
        searchnearby.put("Goa","GOI");
        searchnearby.put("Jorhat","JRH");
        searchnearby.put("Allahabad","IXD");

        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.searcgby_city_name_listview, R.id.airport_name, products);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFromList = (String) lv.getItemAtPosition(position);
                //Toast.makeText(SearchByCitynameActivity.this,selectedFromList,Toast.LENGTH_SHORT).show();

                if(resolve.equals("from"))
                {
                    fromcitySearch = selectedFromList;
                    FlightBookingFragment.fromCityText.setText(fromcitySearch);
                    FlightBookingFragment.fromText.setText(searchnearby.get(fromcitySearch));
                    finish();
                }
                else if(resolve.equals("to"))
                {
                    tocitySearch = selectedFromList;
                    FlightBookingFragment.toCityText.setText(tocitySearch);
                    FlightBookingFragment.toText.setText(searchnearby.get(tocitySearch));
                    finish();
                }

            }
        });


        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                SearchByCitynameActivity.this.adapter.getFilter().filter(cs);
                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub

                }

                @Override
                public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                }
        }
        );
    }
    }

