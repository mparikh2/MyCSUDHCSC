package com.example.milind.mycsudhcsc.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.milind.mycsudhcsc.R;
import com.example.milind.mycsudhcsc.fragment.AcademicCalendarFragment;
import com.example.milind.mycsudhcsc.fragment.HomeFragment;
import com.example.milind.mycsudhcsc.fragment.FacultyDirectoryFragment;
import com.example.milind.mycsudhcsc.fragment.GraduateProgramsListFragment;
import com.example.milind.mycsudhcsc.fragment.CollegeWebSiteFragment;
import com.example.milind.mycsudhcsc.fragment.FeedbackFragment;
import com.example.milind.mycsudhcsc.other.CircleTransform;

public class MainActivity extends AppCompatActivity {

    String phone = "213-275-8617";

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    // urls to load navigation header background image
    // and profile image
    private static final String urlNavHeaderBg = "#860038";
    //private static final String urlProfileImg = "https://lh5.googleusercontent.com/--RAz-gyeCuw/AAAAAAAAAAI/AAAAAAAAALs/tD-7rDz4oi8/photo.jpg?sz=50";
    private static final String urlProfileImg = "https://www.csudh.edu/Assets/CSUDH-Sites/Brand/images/CSUDH_Seal_BW.gif";

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_PHOTOS = "photos";
    private static final String TAG_MOVIES = "movies";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_SETTINGS = "settings";
    private static final String TAG_CALENDAR = "academicCalendar";
    public static String CURRENT_TAG = TAG_HOME;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        //fab = (FloatingActionButton) findViewById(R.id.fab);

        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
        //txtName = (TextView) navHeader.findViewById(R.id.name);
        txtWebsite = (TextView) navHeader.findViewById(R.id.website);
        imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
        imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        final FloatingActionButton plusFab = (FloatingActionButton) findViewById(R.id.plusButton);
        FloatingActionButton callFab = (FloatingActionButton) findViewById(R.id.callButton);
        FloatingActionButton smsFab = (FloatingActionButton) findViewById(R.id.smsButton);
        final LinearLayout callLayout = (LinearLayout) findViewById(R.id.callLayout);
        final LinearLayout smsLayout = (LinearLayout) findViewById(R.id.messageLayout);
        final Animation show_fab_button = AnimationUtils.loadAnimation(getApplication(), R.anim.show_button);
        final Animation hide_fab_button = AnimationUtils.loadAnimation(getApplication(), R.anim.hide_button);
        final Animation show_layout = AnimationUtils.loadAnimation(getApplication(), R.anim.show_layout);
        final Animation hide_layout = AnimationUtils.loadAnimation(getApplication(), R.anim.hide_layout);

        plusFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(callLayout.getVisibility()== View.VISIBLE && smsLayout.getVisibility()== View.VISIBLE )
                {
                    callLayout.setVisibility(View.GONE);
                    smsLayout.setVisibility(View.GONE);
                    callLayout.startAnimation(hide_layout);
                    smsLayout.startAnimation(hide_layout);
                    plusFab.startAnimation(hide_fab_button);
                }
                else
                {
                    callLayout.setVisibility(View.VISIBLE);
                    smsLayout.setVisibility(View.VISIBLE);
                    callLayout.startAnimation(show_layout);
                    smsLayout.startAnimation(show_layout);
                    plusFab.startAnimation(show_fab_button);
                }
            }
        });

        callFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                callLayout.setVisibility(View.GONE);
                smsLayout.setVisibility(View.GONE);
                callLayout.startAnimation(hide_layout);
                smsLayout.startAnimation(hide_layout);
                plusFab.startAnimation(hide_fab_button);
                Intent mIntent = new Intent(Intent.ACTION_DIAL);
                mIntent.setData(Uri.parse("tel:" + phone));
                if(mIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(mIntent);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"There is no app that support this action",Toast.LENGTH_SHORT).show();
                }
            }
        });

        smsFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                callLayout.setVisibility(View.GONE);
                smsLayout.setVisibility(View.GONE);
                callLayout.startAnimation(hide_layout);
                smsLayout.startAnimation(hide_layout);
                plusFab.startAnimation(hide_fab_button);
                Intent mIntent = new Intent(Intent.ACTION_VIEW);
                mIntent.setData(Uri.parse("sms:" + phone));
                if(mIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(mIntent);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"There is no app that support this action",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // load nav menu header data
        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }
    }
    /***
     * Load navigation menu header information
     * like background image, profile image
     * name, website, notifications action view (dot)
     */
    private void loadNavHeader() {
        // name, website
        //txtName.setText("Computer Science Department");
        txtWebsite.setText("Computer Science Department");

        // loading header background image
        Glide.with(this).load(urlNavHeaderBg)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgNavHeaderBg);

        // Loading profile image
        Glide.with(this).load(urlProfileImg)
                .crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProfile);

//        // showing dot next to notifications label
//        navigationView.getMenu().getItem(3).setActionView(R.layout.menu_dot);
    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            // show or hide the fab button
//            toggleFab();
//            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button
//        toggleFab();

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                // photos
                CollegeWebSiteFragment collegeWebSiteFragment = new CollegeWebSiteFragment();
                return collegeWebSiteFragment;
            case 2:
                // movies fragment
                FacultyDirectoryFragment facultyDirectoryFragment = new FacultyDirectoryFragment();
                return facultyDirectoryFragment;
            case 3:
                // notifications fragment
                GraduateProgramsListFragment graduateProgramsListFragment = new GraduateProgramsListFragment();
                return graduateProgramsListFragment;
            case 4:
                // academic calendar fragment
                AcademicCalendarFragment academicCalendarFragment = new AcademicCalendarFragment();
                return academicCalendarFragment;
            case 5:
                // settings fragment
                FeedbackFragment feedbackFragment = new FeedbackFragment();
                return feedbackFragment;
            default:
                return new HomeFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_photos:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_PHOTOS;
                        break;
                    case R.id.nav_movies:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_MOVIES;
                        break;
                    case R.id.nav_notifications:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_NOTIFICATIONS;
                        break;
                    case R.id.nav_academicCalendar:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_CALENDAR;
                        break;
                    case R.id.nav_settings:
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_SETTINGS;
                        break;
                    case R.id.nav_about_us:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_privacy_policy:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, PrivacyPolicyActivity.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_maps:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, MapsActivity.class));
                        drawer.closeDrawers();
                        return true;
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        // show menu only when home fragment is selected
        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.main, menu);
        }

        // when fragment is notifications, load the menu created for notifications
        if (navItemIndex == 3) {
            getMenuInflater().inflate(R.menu.notifications, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_logout) {
//            Toast.makeText(getApplicationContext(), "Logout user!", Toast.LENGTH_LONG).show();
//            return true;
//        }

//        // user is in notifications fragment
//        // and selected 'Mark all as Read'
//        if (id == R.id.action_mark_all_read) {
//            Toast.makeText(getApplicationContext(), "All notifications marked as read!", Toast.LENGTH_LONG).show();
//        }
//
//        // user is in notifications fragment
//        // and selected 'Clear All'
//        if (id == R.id.action_clear_notifications) {
//            Toast.makeText(getApplicationContext(), "Clear all notifications!", Toast.LENGTH_LONG).show();
//        }

        return super.onOptionsItemSelected(item);
    }

//    // show or hide the fab
//    private void toggleFab() {
//        if (navItemIndex == 0)
//            fab.show();
//        else
//            fab.hide();
//    }

}
