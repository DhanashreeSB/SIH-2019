package com.example.shyam.fly;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TourismFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TourismFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TourismFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    List<placesTourism> mplaceList;
    placesTourism mplaces;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TourismFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TourismFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TourismFragment newInstance(String param1, String param2) {
        TourismFragment fragment = new TourismFragment();
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
        View view =  inflater.inflate(R.layout.fragment_tourism, container, false);
       // mToolbar = view.findViewById(R.id.toolbar);
        //mToolbar.setTitle(getResources().getString(R.string.app_name));
        mRecyclerView = view.findViewById(R.id.recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        mplaceList = new ArrayList<>();
        mplaces = new placesTourism("Amritsar – A Historical Blend Of Culture, Food, & Religious Sites\n", getString(R.string.description_place_amritsar),
                R.drawable.amritsar);
        mplaceList.add(mplaces);
        mplaces = new placesTourism("Assam – Home Of One Horned Rhinos\n", getString(R.string.description_place_assam),
                R.drawable.assam);
        mplaceList.add(mplaces);
        mplaces = new placesTourism("Dalhousie – Witness The Old World Charm Of Victorian Architecture", getString(R.string.description_place_dalhousie),
                R.drawable.dalhousie);
        mplaceList.add(mplaces);
        mplaces = new placesTourism("Mahabaleshwar – Relish The Best Of Western Ghats", getString(R.string.description_place_mahabaleshwar),
                R.drawable.mahabaleshwar);
        mplaceList.add(mplaces);
        mplaces = new placesTourism("Mahabalipuram – An Epitome Of Art & Architecture", getString(R.string.description_place_mahabalipuram),
                R.drawable.mahabalipuram);
        mplaceList.add(mplaces);
        mplaces = new placesTourism("Manali – The Snowy Paradise", getString(R.string.description_place_manali),
                R.drawable.manali);
        mplaceList.add(mplaces);
        mplaces = new placesTourism("Mumbai – The City That Never Sleeps", getString(R.string.description_place_mumbai),
                R.drawable.mumbai);
        mplaceList.add(mplaces);
        mplaces = new placesTourism("Mysore – Of Silk, Sandalwood, And Sweets\n", getString(R.string.description_place_mysore),
                R.drawable.mysore);
        mplaceList.add(mplaces);
        mplaces = new placesTourism("Ooty – Meet The Queen Of The Nilgiri Mountains\n", getString(R.string.description_place_ooty),
                R.drawable.ooty);
        mplaceList.add(mplaces);
        mplaces = new placesTourism("Orissa – The Land Of Temples", getString(R.string.description_place_orissa),
                R.drawable.orissa);
        mplaceList.add(mplaces);

        MyAdapterTourism myAdapter = new MyAdapterTourism(getActivity(), mplaceList);
        mRecyclerView.setAdapter(myAdapter);
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
}
