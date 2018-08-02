package com.fleury.marc.mynews.controllers.activities;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.fleury.marc.mynews.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // FOR DESIGN
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    public static final int FRAGMENT_ARTS = 0, FRAGMENT_BOOKS = 1, FRAGMENT_BUSINESS = 2, FRAGMENT_POLITICS = 3,
                            FRAGMENT_SCIENCE = 4, FRAGMENT_SPORTS = 5, FRAGMENT_TECH = 6, FRAGMENT_TRAVEL = 7;

    public final static String KEY_CATEGORY = "KEY_CATEGORY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureToolbar();
        this.configureDrawerLayout();
        this.configureNavigationView();
        this.configureViewPagerAndTabs();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Intent categoryActivityIntent = new Intent(MainActivity.this, CategoryActivity.class);

        // Handle Navigation Item Click
        int id = item.getItemId();

        switch (id){
            case R.id.activity_main_drawer_art:
                categoryActivityIntent.putExtra(KEY_CATEGORY, FRAGMENT_ARTS);
                break;
            case R.id.activity_main_drawer_books:
                categoryActivityIntent.putExtra(KEY_CATEGORY, FRAGMENT_BOOKS);
                break;
            case R.id.activity_main_drawer_business:
                categoryActivityIntent.putExtra(KEY_CATEGORY, FRAGMENT_BUSINESS);
                break;
            case R.id.activity_main_drawer_politics:
                categoryActivityIntent.putExtra(KEY_CATEGORY, FRAGMENT_POLITICS);
                startActivity(categoryActivityIntent);
                break;
            case R.id.activity_main_drawer_science:
                categoryActivityIntent.putExtra(KEY_CATEGORY, FRAGMENT_SCIENCE);
                break;
            case R.id.activity_main_drawer_sport:
                categoryActivityIntent.putExtra(KEY_CATEGORY, FRAGMENT_SPORTS);
                break;
            case R.id.activity_main_drawer_tech:
                categoryActivityIntent.putExtra(KEY_CATEGORY, FRAGMENT_TECH);
                break;
            case R.id.activity_main_drawer_travel:
                categoryActivityIntent.putExtra(KEY_CATEGORY, FRAGMENT_TRAVEL);
                break;
            default:
                break;
        }
        startActivity(categoryActivityIntent);

        this.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.activity_main_menu_toolbar, menu);
        return true;
    }


    //----------------------
    // CONFIGURATION
    //----------------------

    // 1 - Configure Toolbar
    private void configureToolbar(){
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    // 2 - Configure Drawer Layout
    private void configureDrawerLayout(){
        this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // 3 - Configure NavigationView
    private void configureNavigationView(){
        this.navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    // 4 - Configure ViewPager
    private void configureViewPagerAndTabs(){
        //Get ViewPager from layout
        ViewPager pager = findViewById(R.id.activity_main_viewpager);
        //Set Adapter PageAdapter and glue it together
        pager.setAdapter(new com.fleury.marc.mynews.adapters.PageAdapter(getSupportFragmentManager()));

        //Get TabLayout from layout
        TabLayout tabs = findViewById(R.id.activity_main_tabs);
        //Glue TabLayout and ViewPager together
        tabs.setupWithViewPager(pager);
        //Design purpose. Tabs have the same width
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }


    //----------------------
    // TOOLBAR'S BUTTONS
    //----------------------

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //3 - Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.activity_main_menu_params:
                Toast.makeText(this, "Bouton 'plus'", Toast.LENGTH_LONG).show();
                return true;
            case R.id.activity_main_menu_search:
                Toast.makeText(this, "Bouton 'recherche'", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
