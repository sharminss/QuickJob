package com.example.asus_x454l.quickjob;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.Html;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
public class navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,GoogleApiClient.OnConnectionFailedListener  {


    NavigationView navigationView = null;
    Toolbar toolbar = null;

    public static final String JOB_ID = "net.simplifiedcoding.firebasedatabaseexample.id2";
    public static final String JOB_ID2 = "net.simplifiedcoding.firebasedatabaseexample.id3";
    public static final String JOB_NAME1 = "net.simplifiedcoding.firebasedatabaseexample.name2";
    public static final String SALARY2 = "net.simplifiedcoding.firebasedatabaseexample.salary2";
    public static final String TIME = "net.simplifiedcoding.firebasedatabaseexample.time2";
    public static final String EXP = "net.simplifiedcoding.firebasedatabaseexample.experience2";
    public static final String EDU = "net.simplifiedcoding.firebasedatabaseexample.educaton2";
    public static final String REQ = "net.simplifiedcoding.firebasedatabaseexample.requirement2";
    public static final String CON = "net.simplifiedcoding.firebasedatabaseexample.contact2";
    public static final String ADDR = "net.simplifiedcoding.firebasedatabaseexample.address2";

    private FirebaseAuth firebaseAuth;
    DatabaseReference databasejob2;
    ListView listViewjobnew;
    //  List<Job> jobListnew;

    // private static final String TAG = "SignInActivity";
    // private GoogleApiClient mGoogleApiClient;

    private navigation.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    //   private static final String TAG = "MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private static final String TAG = "SignInActivity";
    private GoogleApiClient mGoogleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        String t="<font color=#800080>HOME....</font>";
        // textView.setText(Html.fromHtml(t));
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);




        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        Log.d(TAG, "onCreate: Starting.");
        // Set up the ViewPager with the sections adapter.


        firebaseAuth = FirebaseAuth.getInstance();
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);















        getSupportActionBar().setTitle(Html.fromHtml(t));



//profile activity
        // databasejob2 = FirebaseDatabase.getInstance().getReference("jobs");

        // listViewjobnew = (ListView) findViewById(R.id.listview3);

        // jobListnew = new ArrayList<>();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(Scopes.DRIVE_APPFOLDER))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

       /* listViewjobnew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the selected artist
                Job job5 = jobListnew.get(i);

                //creating an intent
                Intent intent = new Intent(getApplicationContext(), AddTrack.class);

                //putting artist name and id to intent
                intent.putExtra(JOB_ID, job5.getId());
                intent.putExtra(JOB_ID2, job5.getId3());
                intent.putExtra(JOB_NAME1, job5.getName2());
                intent.putExtra(SALARY2, job5.getSalary2());
                intent.putExtra(TIME, job5.getTime2());
                intent.putExtra(EXP, job5.getExperience2());
                intent.putExtra(EDU, job5.getEducation2());
                intent.putExtra(REQ, job5.getRequirment2());


                intent.putExtra(CON, job5.getContact2());
                intent.putExtra(ADDR, job5.getAddress2());


                //starting the activity with intent
                startActivity(intent);
            }
        });*/



        //profile activity



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);




        //How to change elements in the header programatically
        View headerView = navigationView.getHeaderView(0);
        TextView  tx2 = (TextView) headerView.findViewById(R.id.profile_email);
        Intent  j = getIntent();
        String val =j.getStringExtra("nameID1");

        tx2.setText(val);


        navigationView.setNavigationItemSelectedListener(this);
    }

   /* protected void onStart() {
        super.onStart();
        databasejob2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                jobListnew.clear();
                for (DataSnapshot jobSnapshot : dataSnapshot.getChildren()) {
                    Job jobnew = jobSnapshot.getValue(Job.class);

                    jobListnew.add(jobnew);
                }


                Joblist adapter = new Joblist(navigation.this, jobListnew);
                listViewjobnew.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }





    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            Intent inr=new Intent(navigation.this,New_Edit_profile.class);
            startActivity(inr);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {


            Intent inr=new Intent(navigation.this,ADD_JOB3.class);
            startActivity(inr);


        } else if (id == R.id.nav_slideshow) {
            Intent inr=new Intent(navigation.this,MyApp.class);
            startActivity(inr);

        } else if (id == R.id.nav_manage) {

            Intent inr=new Intent(navigation.this,viewusers.class);
            startActivity(inr);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

   /* public void strorage(View view) {
        Intent i = new Intent(navigation.this,ImageListActivity.class);
        startActivity(i);
    }*/


    /*public void searchf(View view) {
        //Intent i = new Intent(Main2Activity.this,Advanced_search.class);
        //startActivity(i);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alert_layout, null);
        dialogBuilder.setView(dialogView);

        final Button location1 = (Button) dialogView.findViewById(R.id.location);
        final Button salary1 = (Button) dialogView.findViewById(R.id.salary);

        final Button  name1 = (Button) dialogView.findViewById(R.id.name);

        dialogBuilder.setTitle("choice");
        final AlertDialog b = dialogBuilder.create();
        b.show();


        location1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(navigation.this, Data_Retrieve_2.class);
                startActivity(i);
            }
        });


        salary1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(navigation.this, Data_Retrieve_3.class);
                startActivity(i);
            }
        });


        name1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(navigation.this,Data_Retrieve_1.class);
                startActivity(i);
            }
        });








    }*/


    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.commonmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.mnulog) {

            Toast.makeText(this, "edit menu is Clicked", Toast.LENGTH_SHORT).show();

            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(navigation.this, MainActivity.class));

        }


        return super.onOptionsItemSelected(item);
    }
    private void setLoginState(boolean status) {
        SharedPreferences sp = getSharedPreferences("LoginState",
                MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putBoolean("setLoggingOut", status);
        ed.commit();
    }
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }
    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "ADD job");
        adapter.addFragment(new Tab2Fragment(), "All jobs");
        adapter.addFragment(new Tab3Fragment(), "My jobs");
        viewPager.setAdapter(adapter);
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static Main2Activity.PlaceholderFragment newInstance(int sectionNumber) {
            Main2Activity.PlaceholderFragment fragment = new Main2Activity.PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main2, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return Main2Activity.PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}