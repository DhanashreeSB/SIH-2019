package com.example.shyam.fly;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FlightBookingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FlightBookingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FlightBookingFragment extends Fragment implements View.OnClickListener {

    public static TextView fromText, toText, fromCityText, toCityText, departondatemonth,
            departondayyear, returnondatemonth, returnondayyear;
    public LinearLayout fromLinearLayout, toLinearLayout;
    public int mYear,mMonth,mDay;
    public int total_passengers;
    public String currentDate;
    public String fromTextString , toTextString , fromCityTextString, toCityTextString;
    public Button search_button;
    public LinearLayout departlinearlayout, returnlinearlayout;
    public Dialog passengerDialog;
    public Button okbutton;
    public CardView passenger_inc;
    public TextView minusadults,plusadults,countadults, minuschildren, pluschildren, countchildren,
            minusinfants, plusinfants, countinfants, total_adults,total_children, total_infants;
    public int total_adults1,total_children1,total_infants1;
    public String selected_calender_date_depart,selected_calender_date_arrive;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public ImageView swap_image;
    public RadioButton one,round;

    private OnFragmentInteractionListener mListener;

    public FlightBookingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FlightBookingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FlightBookingFragment newInstance(String param1, String param2) {
        FlightBookingFragment fragment = new FlightBookingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_flight_booking, container, false);
        //View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.passenger_increment, null);
        //For popping passenger increment
        passengerDialog = new Dialog(getActivity());

        fromText = (TextView) view.findViewById(R.id.from_city);
        toText = (TextView) view.findViewById(R.id.to_city);
        fromCityText = (TextView) view.findViewById(R.id.from_city_name);
        toCityText = (TextView) view.findViewById(R.id.to_city_name);
        swap_image = (ImageView) view.findViewById(R.id.swapimage);
        one = (RadioButton) view.findViewById(R.id.one);
        round = (RadioButton) view.findViewById(R.id.round);
        //multi = (RadioButton) view.findViewById(R.id.multi);
        search_button = (Button) view.findViewById(R.id.search);
        departondatemonth = (TextView) view.findViewById(R.id.homedepartdatemonth);
        departondayyear = (TextView) view.findViewById(R.id.homedepartdayyear);
        returnondatemonth = (TextView) view.findViewById(R.id.returnondatemonth);
        returnondayyear = (TextView) view.findViewById(R.id.returnondayyear);
        departlinearlayout = (LinearLayout) view.findViewById(R.id.bdeparton);
        returnlinearlayout = (LinearLayout) view.findViewById(R.id.breturnon);
        passenger_inc = (CardView) view.findViewById(R.id.increment_passenger);
        total_adults = (TextView) view.findViewById(R.id.adultcount1);
        total_children = (TextView) view.findViewById(R.id.childrencount1);
        total_infants = (TextView) view.findViewById(R.id.infantscount1);
        fromLinearLayout = (LinearLayout) view.findViewById(R.id.from_linear_lay);
        toLinearLayout = (LinearLayout) view.findViewById(R.id.to_linear_lay);

        swap_image.setOnClickListener(this);
        one.setOnClickListener(this);
        round.setOnClickListener(this);
        //multi.setOnClickListener(this);
        search_button.setOnClickListener(this);
        departlinearlayout.setOnClickListener(this);
        returnlinearlayout.setOnClickListener(this);
        passenger_inc.setOnClickListener(this);
        fromLinearLayout.setOnClickListener(this);
        toLinearLayout.setOnClickListener(this);


        //onclick on pop-up
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.one:
                one_way();
                break;
            case R.id.round:
                round_trip();
                break;
            /*case R.id.multi:
                multi_city();
                break;*/
            case R.id.swapimage:
                swap();
                break;
            case R.id.search:
                setSearch_button();
                break;
            case R.id.bdeparton:
                setCalendarDepartOn();
                break;
            case R.id.breturnon:
                setCalendarReturnOn();
                break;
            case R.id.increment_passenger:
                incrementPassenger_popup();
                break;
            case R.id.minusadult:
                minusadult();
                break;
            case R.id.adultplus:
                plusadult();
                break;
            case R.id.minuschildren:
                minuschildrens();
                break;
            case R.id.pluschildren:
                pluschildrens();
                break;
            case R.id.minusinfants:
                minusinfant();
                break;
            case R.id.plusinfants:
                plusinfant();
                break;
            case R.id.ok:
                setokbutton();
                break;
            case R.id.from_linear_lay:
                search_from_flights();
                break;
            case R.id.to_linear_lay:
                search_to_flights();
                break;
            default:
                break;

        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    //Radio Button Handling Methods
    public void one_way()
    {
        Toast.makeText(getActivity(),"Inside one_way",Toast.LENGTH_SHORT).show();
    }

    public void round_trip()
    {
        Toast.makeText(getActivity(),"Inside Round Trip",Toast.LENGTH_SHORT).show();
    }

    public void multi_city()
    {
        Toast.makeText(getActivity(),"Inside multi_city",Toast.LENGTH_SHORT).show();
    }

    public void swap()
    {
        fromTextString = fromText.getText().toString();
        toTextString = toText.getText().toString();
        fromCityTextString = fromCityText.getText().toString();
        toCityTextString = toCityText.getText().toString();
        fromText.setText(toTextString);
        toText.setText(fromTextString);
        fromCityText.setText(toCityTextString);
        toCityText.setText(fromCityTextString);
    }

    //set Calendar open method
    public void setCalendarDepartOn()
    {

        SimpleDateFormat month_date;
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @SuppressLint("LongLogTag")
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        String filter_date,filter_month;
                        SimpleDateFormat month_date = new SimpleDateFormat("MMM");
                        Date date = c.getTime();
                        String month_name = month_date.format(c.getTime());
                        departondatemonth.setText(dayOfMonth+" "+month_name);
                        String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
                        departondayyear.setText(dayOfWeek+", "+year);
                        if(String.valueOf(dayOfMonth).length()==1)
                        {
                            filter_date = "0"+String.valueOf(dayOfMonth);
                        }
                        else
                        {
                            filter_date = String.valueOf(dayOfMonth);
                        }

                        if(String.valueOf(monthOfYear).length()==1)
                        {
                            filter_month = "0"+String.valueOf(monthOfYear+1);
                        }
                        else
                        {
                            filter_month = String.valueOf(monthOfYear+1);
                        }
                        selected_calender_date_depart = String.valueOf(year)+"-"+filter_month+"-"+filter_date;
                        Log.w("SSSSSSSSSSSSSSSSSSSSSSSSSSS",selected_calender_date_depart);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void setCalendarReturnOn() {

        if(round.isChecked()) {
            SimpleDateFormat month_date;
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    new DatePickerDialog.OnDateSetListener() {

                        @SuppressLint("LongLogTag")
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            SimpleDateFormat month_date = new SimpleDateFormat("MMM");
                            Date date = c.getTime();
                            String filter_date,filter_month;
                            if(String.valueOf(dayOfMonth).length()==1)
                            {
                                filter_date = "0"+String.valueOf(dayOfMonth);
                            }
                            else
                            {
                                filter_date = String.valueOf(dayOfMonth);
                            }

                            if(String.valueOf(monthOfYear).length()==1)
                            {
                                filter_month = "0"+String.valueOf(monthOfYear+1);
                            }
                            else
                            {
                                filter_month = String.valueOf(monthOfYear+1);
                            }
                            selected_calender_date_arrive = String.valueOf(year)+"-"+filter_month+"-"+filter_date;
                            Log.w("RRRRRRRRRRRRRRRR",selected_calender_date_arrive);
                            String month_name = month_date.format(c.getTime());
                            returnondatemonth.setText(dayOfMonth + " " + month_name);
                            String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
                            returnondayyear.setText(dayOfWeek + ", " + year);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        else
        {
            //round.setChecked(true);
            Toast.makeText(getActivity(),"Select round trip",Toast.LENGTH_SHORT).show();
        }
    }

    /*public void incrementPassenger(View view)
    {
        passengerDialog.setContentView(R.layout.passenger_increment);
        passengerDialog.show();
    }*/

    @SuppressLint("LongLogTag")
    public void setSearch_button()
    {
        //See whether to and from data is available in database or not
        Intent intent = new Intent(getContext(),FlightBookingDisplay.class);
        intent.putExtra("passengerno",total_passengers);
        intent.putExtra("from_city_name",fromCityText.getText().toString());
        intent.putExtra("final_date_from",selected_calender_date_depart);
        intent.putExtra("final_date_to",selected_calender_date_arrive);
        Log.w("------------------from_city",fromCityText.getText().toString());
        intent.putExtra("to_city_name",toCityText.getText().toString());
        startActivity(intent);
    }

    public void incrementPassenger_popup()
    {
        passengerDialog.setContentView(R.layout.passenger_increment);
        passengerDialog.show();
        minusadults = passengerDialog.findViewById(R.id.minusadult);
        minuschildren = passengerDialog.findViewById(R.id.minuschildren);
        minusinfants = passengerDialog.findViewById(R.id.minusinfants);
        pluschildren = passengerDialog.findViewById(R.id.pluschildren);
        plusinfants = passengerDialog.findViewById(R.id.plusinfants);
        plusadults = passengerDialog.findViewById(R.id.adultplus);
        countinfants = passengerDialog.findViewById(R.id.infantscount);
        countchildren = passengerDialog.findViewById(R.id.childrencount);
        countadults = passengerDialog.findViewById(R.id.adultcount);
        okbutton = passengerDialog.findViewById(R.id.ok);

        minusadults.setOnClickListener(this);
        minuschildren.setOnClickListener(this);
        minusinfants.setOnClickListener(this);
        plusadults.setOnClickListener(this);
        pluschildren.setOnClickListener(this);
        plusinfants.setOnClickListener(this);
        okbutton.setOnClickListener(this);
    }

    public void minusadult()
    {
        if(Integer.valueOf(countadults.getText().toString())>1)
        {
            int temp = Integer.valueOf(countadults.getText().toString());
            countadults.setText(String.valueOf(temp-1));
        }
        else if(Integer.valueOf(countadults.getText().toString())==1)
        {
            Toast.makeText(getActivity(),"At least one Adult is required",Toast.LENGTH_LONG).show();
        }
    }

    public void plusadult()
    {
        int temp = Integer.valueOf(countadults.getText().toString());
        if(temp < 9)
        {
            countadults.setText(String.valueOf(temp+1));
        }
        else
        {
            Log.w("cant","cant");
        }
    }

    public void minuschildrens()
    {
        if(Integer.valueOf(countchildren.getText().toString())>0)
        {
            int temp = Integer.valueOf(countchildren.getText().toString());
            countchildren.setText(String.valueOf(temp-1));
        }
        else if(Integer.valueOf(countchildren.getText().toString())==0)
        {
            //Toast.makeText(getActivity(),"At least one Adult is required",Toast.LENGTH_LONG).show();
            Log.w("cant","cant");
        }
    }

    public void pluschildrens()
    {
        int temp = Integer.valueOf(countchildren.getText().toString());
        if(temp < 9)
        {
            countchildren.setText(String.valueOf(temp+1));
        }
        else
        {
            Log.w("cant","cant");
        }
    }

    public void minusinfant()
    {
        if(Integer.valueOf(countinfants.getText().toString())>0)
        {
            int temp = Integer.valueOf(countinfants.getText().toString());
            countinfants.setText(String.valueOf(temp-1));
        }
        else if(Integer.valueOf(countinfants.getText().toString())==0)
        {
            //Toast.makeText(getActivity(),"At least one Adult is required",Toast.LENGTH_LONG).show();
            Log.w("cant","cant");
        }
    }

    public void plusinfant()
    {
        int temp = Integer.valueOf(countinfants.getText().toString());
        if(temp < 9)
        {
            countinfants.setText(String.valueOf(temp+1));
        }
        else
        {
            Log.w("cant","cant");
        }
    }

    public void setokbutton()
    {

        total_adults1 = Integer.valueOf(countadults.getText().toString());
        total_children1 = Integer.valueOf(countchildren.getText().toString());
        total_infants1 = Integer.valueOf(countinfants.getText().toString());
        total_passengers = total_adults1+total_children1+total_infants1;
        //Log.w("===========", String.valueOf(total_passengers));
        reflect_passengers();
        passengerDialog.dismiss();
    }

    public void  reflect_passengers()
    {
        total_children.setText(String.valueOf(total_children1));
        total_adults.setText(String.valueOf(total_adults1));
        total_infants.setText(String.valueOf(total_infants1));
    }

    public void search_from_flights()
    {
        Intent intent = new Intent(getContext(),SearchByCitynameActivity.class);
        intent.putExtra("resolve","from");
        startActivity(intent);
    }

    public void search_to_flights()
    {
        Intent intent = new Intent(getContext(),SearchByCitynameActivity.class);
        intent.putExtra("resolve","to");
        startActivity(intent);
    }
}
