package com.fleury.marc.mynews.controllers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.fleury.marc.mynews.R;
import com.fleury.marc.mynews.controllers.fragments.categories.CategoryFragment;

public class CategoryActivity extends AppCompatActivity {

    public final static String KEY_CATEGORY_TWO = "KEY_CATEGORY_TWO";
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Bundle mExtra = getIntent().getExtras();
        int mKey = mExtra.getInt("KEY_CATEGORY");

        Log.i("TEST_BUNDLE", String.valueOf(mExtra));
        Log.i("TEST_KEY", String.valueOf(mKey));

        configureToolbar();
        runFragment(mKey);
    }


    private void configureToolbar(){
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    private void runFragment(int mKey) {

        CategoryFragment mFrag = new CategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_CATEGORY_TWO, mKey);   //parameters are (key, value).
        mFrag.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_category_frame_layout, mFrag).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.activity_main_menu_toolbar, menu);
        return true;
    }


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
