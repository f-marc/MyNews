package com.fleury.marc.mynews.controllers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.fleury.marc.mynews.R;
import com.fleury.marc.mynews.controllers.fragments.others.CategoryFragment;

public class CategoryActivity extends AppCompatActivity {

    public final static String KEY_CATEGORY_TWO = "KEY_CATEGORY_TWO";
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Bundle mExtra = getIntent().getExtras();
        int mKey = mExtra.getInt("KEY_CATEGORY");

        configureToolbar();
        runFragment(mKey);
    }


    private void configureToolbar(){
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void runFragment(int mKey) {
        // Transfer current bundle to the fragment
        CategoryFragment mFrag = new CategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_CATEGORY_TWO, mKey);
        // Display the fragment on FrameLayout
        mFrag.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_category_frame_layout, mFrag).commit();
    }

}
