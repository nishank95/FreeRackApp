package com.example.dell.freerackapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dell.freerackapp.adapter.ProductListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.product_list)RecyclerView productListRecyclerView;
    ProductListAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<String> pname = new ArrayList<String>(){{
        add("Sofa");
        add("Table");
        add("Chair");
        add("Bed");
        add("Sofa");
        add("Table");
        add("Chair");
        add("Bed");
    }};

    List<String> ploc = new ArrayList<String>(){{
        add("Clayton, VIC");
        add("Caulfield, VIC");
        add("Melbourne,VIC");
        add("Huntindale,VIC");
        add("Caulfield, VIC");
        add("Melbourne,VIC");
        add("Huntindale,VIC");
        add("Clayton, VIC");

    }};

    List<String> pCategory = new ArrayList<String>(){{
        add("Home");
        add("Electronics");
        add("Kids");
        add("Scrap");
        add("Electronics");
        add("Home");
        add("Kids");
        add("Scrap");
    }};


    List<String> pdesc = new ArrayList<String>(){{
        add("Moving house and I have a 3 seat lounge chair.  In good condition. No rips or holes at all. Leather in nice condition. Must be gone by Monday ");
        add("Moving house and I have a 3 seat lounge chair.  In good condition. No rips or holes at all. Leather in nice condition. Must be gone by Monday ");
        add("Moving house and I have a 3 seat lounge chair.  In good condition. No rips or holes at all. Leather in nice condition. Must be gone by Monday ");
        add("Moving house and I have a 3 seat lounge chair.  In good condition. No rips or holes at all. Leather in nice condition. Must be gone by Monday ");
        add("Moving house and I have a 3 seat lounge chair.  In good condition. No rips or holes at all. Leather in nice condition. Must be gone by Monday ");
        add("Moving house and I have a 3 seat lounge chair.  In good condition. No rips or holes at all. Leather in nice condition. Must be gone by Monday ");
        add("Moving house and I have a 3 seat lounge chair.  In good condition. No rips or holes at all. Leather in nice condition. Must be gone by Monday ");
        add("Moving house and I have a 3 seat lounge chair.  In good condition. No rips or holes at all. Leather in nice condition. Must be gone by Monday ");
    }};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,PostProduct.class);
                startActivity(i);
            }
        });


        String post_name = getIntent().getStringExtra("name");
        String post_desc = getIntent().getStringExtra("desc");
        String post_loc = getIntent().getStringExtra("loc");
        String post_cat = getIntent().getStringExtra("cat");
        pname.add(post_name);
        ploc.add(post_loc);
        pCategory.add(post_cat);
        pdesc.add(post_desc);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mLayoutManager = new LinearLayoutManager(this);
        productListRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ProductListAdapter(this,pname,ploc,pCategory,pdesc);
        productListRecyclerView.setAdapter(mAdapter);
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

    @OnClick(R.id.fabBtn)
    void onFabClick(View v){
        Intent i = new Intent(MainActivity.this,PostProduct.class);
        startActivity(i);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
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
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
