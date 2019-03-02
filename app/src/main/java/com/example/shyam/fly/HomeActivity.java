package com.example.shyam.fly;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public String no_ofpassengers;
    public BottomNavigationView bottomNavigationView;
    public FrameLayout mainFrame;
    public Fragment tripFragment, notificationFragment, profileFragment, flightbookingfragment,
            tourismfragment, bookingHistoryNavigation, drawerAboutAppFragment, drawertermsconditions,
            drawerHelpFragment, drawerLegalRights;
    public TabLayout tabLayout;
    public ViewPager viewPager1;
    public TabPagerAdapter tabPagerAdapter;
    public MenuItem prevMenuItem;
    public Toolbar toolbar;
    public ActionBarDrawerToggle toggle;
    public DrawerLayout drawer;
    public SharedPreferenceConfig sharedPreferenceConfig;
    private int[] tabicons = {R.drawable.airplane4, R.drawable.torism2, R.drawable.nearby1};

    //Get from SelectAirline
    public static String airlineName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        airlineName = getIntent().getStringExtra("Airline");

        //For tablayout settings
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager1 = (ViewPager) findViewById(R.id.viewpager);

        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        //TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
        //viewPager1.setAdapter(tabPagerAdapter);
        //abLayout.setupWithViewPager(viewPager1);
        //For tablayout settings over

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition())
                {
                    case 0:
                        viewPager1.setCurrentItem(0);
                        break;
                    case 1:
                        viewPager1.setCurrentItem(1);
                        break;
                    case 2:
                        //viewPager1.setCurrentItem(2);
                        //Intent intent = new Intent(HomeActivity.this,NearbyAirportsActivity.class);
                        //startActivity(intent);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //For tablayout settings
        setTabicons(tabicons);

        //For Bottom Navigation
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        mainFrame = (FrameLayout) findViewById(R.id.main_frame);

        //homeFragment = new HomeFragment();
        tripFragment = new TripsFragment();
        notificationFragment = new NotificationFragment();
        profileFragment = new ProfileFragment();
        flightbookingfragment = new FlightBookingFragment();
        tourismfragment = new TourismFragment();
        //nearbyAirportsfragment = new NearbyAirportsFragment();
        bookingHistoryNavigation = new BookingHistoryNavigation();
        drawerAboutAppFragment = new DrawerAboutAppFragment();
        drawerHelpFragment = new DrawerHelpFragment();
        drawerLegalRights = new DrawerLegalRightsFragment();
        drawertermsconditions = new TermsConditions();


        //add home fragment at starting

        //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.add(R.id.main_frame, homeFragment);
        //fragmentTransaction.commit();

        removePaddingFromNavigationItem();

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_trip:
                        //setFragment(tripFragment);
                        viewPager1.setCurrentItem(2);
                        return true;

                    case R.id.nav_notification:
                        // setFragment(notificationFragment);
                        viewPager1.setCurrentItem(3);
                        return true;

                    case R.id.nav_profile:
                        //`setFragment(profileFragment);
                        viewPager1.setCurrentItem(4);
                        return true;

                    default:
                        return false;

                }
            }
        });

        viewPager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(position >= 2 && position < 5)
                {
                    if (prevMenuItem != null) {
                        prevMenuItem.setChecked(false);
                    }
                    else
                    {
                        bottomNavigationView.getMenu().getItem(0).setChecked(false);
                    }
                    Log.d("page", "onPageSelected: "+position);

                    switch (position)
                    {
                        /*case 2:
                            bottomNavigationView.getMenu().getItem(0).setChecked(true);
                            prevMenuItem = bottomNavigationView.getMenu().getItem(0);
                            break;*/
                        case 2:
                            bottomNavigationView.getMenu().getItem(0).setChecked(true);
                            prevMenuItem = bottomNavigationView.getMenu().getItem(0);
                            break;
                        case 3:
                            bottomNavigationView.getMenu().getItem(1).setChecked(true);
                            prevMenuItem = bottomNavigationView.getMenu().getItem(1);
                            break;
                        case 4:
                            bottomNavigationView.getMenu().getItem(2).setChecked(true);
                            prevMenuItem = bottomNavigationView.getMenu().getItem(2);
                            break;
                        default:
                            break;
                    }


                }

                else if(position <=1)
                {
                    switch (position)
                    {
                        case 0:
                            tabLayout.getTabAt(0).select();
                            break;
                        case 1:
                            tabLayout.getTabAt(1).select();
                            break;
                        /*case 2:
                            tabLayout.getTabAt(2).select();
                            Intent intent = new Intent(HomeActivity.this,NearbyAirportsActivity.class);
                            startActivity(intent);
                            break;*/
                        default:
                            break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        /*for (int i = 0; i < tabLayout.getTabCount(); i++) {
            final TabLayout.Tab tab = tabLayout.getTabAt(i);
            final View tabView = LayoutInflater.from(this).inflate(
                    R.layout.app_bar_home, (ViewGroup) tab.getCustomView(), false);
            //tabLayout.setCustomView(tabView);

            final TextView customView = (TextView) tab.getCustomView();
            customView.setText(tabPagerAdapter.getPageTitle(i));
            final int index = i;
            customView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tabLayout.getSelectedTabPosition() == index) {
                        // change edittext value
                    } else {
                        tab.select();
                    }
                }
            });
        }*/

        setupViewPager(viewPager1);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_bookingHistory) {
            // Handle the camera action
            viewPager1.setCurrentItem(5);

        } else if (id == R.id.nav_rights) {
            viewPager1.setCurrentItem(6);

        } else if (id == R.id.nav_about) {
            viewPager1.setCurrentItem(7);

        } else if (id == R.id.nav_help) {
            viewPager1.setCurrentItem(8);
        }else if (id == R.id.nav_terms) {
            viewPager1.setCurrentItem(9);
        }

        else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "your body here";
            String shareSubject = "your subject here";
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
            startActivity(Intent.createChooser(sharingIntent, "Share Using"));
        } else if (id == R.id.nav_logout) {
            sharedPreferenceConfig.writeLoginStatus(false);
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setTabicons(int[] tabicons) {
        tabLayout.getTabAt(0).setIcon(tabicons[0]);
        tabLayout.getTabAt(1).setIcon(tabicons[1]);
        tabLayout.getTabAt(2).setIcon(tabicons[2]);
    }

    public void removePaddingFromNavigationItem() {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);

        for (int i = 0; i < menuView.getChildCount(); i++) {
            BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
            View activeLabel = item.findViewById(R.id.largeLabel);
            if (activeLabel instanceof TextView) {
                activeLabel.setPadding(0, 0, 0, 0);
            }
        }
    }

    /*private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }*/

    private void setupViewPager(ViewPager viewPager) {
        tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
        tabPagerAdapter.addFragment(flightbookingfragment);
        tabPagerAdapter.addFragment(tourismfragment);
       // tabPagerAdapter.addFragment(nearbyAirportsfragment);
       // tabPagerAdapter.addFragment(homeFragment);
        tabPagerAdapter.addFragment(tripFragment);
        tabPagerAdapter.addFragment(notificationFragment);
        tabPagerAdapter.addFragment(profileFragment);
        tabPagerAdapter.addFragment(bookingHistoryNavigation);
        tabPagerAdapter.addFragment(drawerLegalRights);
        tabPagerAdapter.addFragment(drawerAboutAppFragment);
        tabPagerAdapter.addFragment(drawerHelpFragment);
        tabPagerAdapter.addFragment(drawertermsconditions);
        viewPager.setAdapter(tabPagerAdapter);
    }
}