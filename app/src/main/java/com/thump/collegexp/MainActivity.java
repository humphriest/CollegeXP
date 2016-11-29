package com.thump.collegexp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView listview;
    ArrayAdapter<String> adapter;
    ArrayList<String> courseList = new ArrayList<>();
    //MyDBHandler dbHandler;
    CustomAdapter customAdapter;
    //CourseAdapter courseAdapter;

    ArrayList<College> collegeList = new ArrayList<>();
    public String result;
    public boolean collegeAdapter= true;

    //private Button speakNowButton;
    //private EditText editText;
    TextToSpeechActivity ttsManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, courseList);
        //courseAdapter = new CourseAdapter(MainActivity.this,0, collegeList);
        System.out.println(collegeList);
        customAdapter = new CustomAdapter(MainActivity.this, 0, collegeList);
        listview = (ListView) findViewById(R.id.mainList);
        listview.setAdapter(customAdapter);

        ttsManager = new TextToSpeechActivity();
        ttsManager.init(this);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(collegeAdapter == true){
                    ArrayList<String> moreCourses = customAdapter.getItem(position).getCourses();
                    ArrayAdapter adapter2 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, moreCourses);
                    System.out.println(moreCourses);
                    listview.setAdapter(adapter2);
                    adapter2.notifyDataSetChanged();
                }
            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(collegeAdapter==true){
                    String name = customAdapter.getItem(position).getName();
                    ttsManager.initQueue(name);
                } else{
                    String name = adapter.getItem(position);
                    ttsManager.initQueue(name);
                }
                return true;
            }
        });

        JSONArray array;
        final ArrayList<College> colleges = new ArrayList<>();


        new MyAsyncTask().execute();
        try {
            //to allow the other Thread to set the value
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("RESULT IN MAIN THREAD IS "+result);
        try {
            array = new JSONArray(result);
            for(int n = 0; n < array.length(); n++) {
                JSONObject object = array.getJSONObject(n);
                JSONObject college = object.getJSONObject("college");
                String name = college.getString("name");
                String address = college.getString("address");
                String imageUrl = college.getString("imageUrl");
                JSONArray courseArray = college.getJSONArray("orders");

                ArrayList<String> courses = new ArrayList<String>();
                for (int i = 0; i < courseArray.length(); i++) {
                    //courses = new ArrayList<>();
                    JSONObject courseObject = courseArray.getJSONObject(i);
                    String coursename = courseObject.getString("name");

                    courses.add(coursename);
                    //System.out.println("-----------------------------"+courses);
                }
                College college1 = new College(n, name, address, courses, imageUrl);/**/
                //System.out.println(college1.getCourses()+"-----------------------------");
                //dbHandler.addProduct(college1);
                colleges.add(college1);

                for(int a=0;a<courses.size();a++){
                    courseList.add(courses.get(a));
                }
                //

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for(int i=0;i<colleges.size();i++){

            collegeList.add(colleges.get(i));
        }

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void setResult(String result){
        this.result = result;
    }


    // AsyncTask inner class
    class MyAsyncTask extends AsyncTask {
        @Override
        protected String doInBackground(Object... params) {
            StringBuffer string = new StringBuffer("");
            URL url = null;
            try {
                //url = new URL("http://services.groupkt.com/country/get/all");
                //Working on emulator --
                url = new URL("http://localhost:8082/json/colleges");
                //url = new URL("http://api.geonames.org/earthquakesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&username=demo");
                //url = new URL("http://178.167.255.123:8082/restful-services/sampleservice/json/users");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                conn.setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }

// read the response
            try {
                System.out.println("Response Code: " + conn.getResponseCode());
                InputStream in = new BufferedInputStream(conn.getInputStream());

                BufferedReader rd = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    string.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            setResult(string.toString());
            System.out.println("RESPONSE JSON: "+string.toString());
            return string.toString();
        }
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




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.colleges) {
            listview.setAdapter(customAdapter);
            customAdapter.notifyDataSetChanged();
            collegeAdapter = true;

        } else if (id == R.id.courses) {
            listview.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            collegeAdapter=false;

        } else if (id == R.id.locations) {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        ttsManager.shutDown();
    }
}
