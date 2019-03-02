package com.example.shyam.fly;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabPagerAdapter extends FragmentPagerAdapter {

    //String tabArray[] = new String[]{"Flight","Tourism","Nearby Airports"};

    //Integer tabnumber = 3;
    public final List<Fragment> mFragmentList = new ArrayList<>();

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public Fragment getItem(int position){
        return mFragmentList.get(position);
    }


    /*@Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabArray[position];
    }*/

    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }



    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}

/*
* @Override
    public Fragment getItem(int i)
    {
        switch (i)
        {
            case 0:
                FlightBookingFragment flightFragment = new FlightBookingFragment();
                return flightFragment;
            case 1:
                TourismFragment tourFragment = new TourismFragment();
                return tourFragment;
            case 2:
                NearbyAirportsFragment nearbyFragment = new NearbyAirportsFragment();
                return nearbyFragment;
            case 3:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
        }
        return null;
    }
* */
