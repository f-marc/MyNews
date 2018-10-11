package com.fleury.marc.mynews.controllers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.fleury.marc.mynews.R;
import com.fleury.marc.mynews.controllers.fragments.others.CategoryFragment;
import com.fleury.marc.mynews.controllers.fragments.others.ResultFragment;

import static com.fleury.marc.mynews.controllers.activities.SearchActivity.KEY_CATEGORY_LIST;
import static com.fleury.marc.mynews.controllers.activities.SearchActivity.KEY_KEYWORD;

public class SearchResultActivity extends AppCompatActivity {

    public final static String KEY_CATEGORY_LIST_TWO = "KEY_CATEGORY_LIST_TWO";
    public final static String KEY_KEYWORD_TWO = "KEY_KEYWORD_TWO";
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Bundle mExtra = getIntent().getExtras();
        String mKeyCategory = mExtra.getString(KEY_CATEGORY_LIST);
        String mKeyKeyword = mExtra.getString(KEY_KEYWORD);

        configureToolbar();
        runFragment(mKeyCategory, mKeyKeyword);
    }


    private void configureToolbar(){
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void runFragment(String mKeyCategory, String mKeyKeyword) {
        // Transfer current bundle to the fragment
        ResultFragment mFrag = new ResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_CATEGORY_LIST_TWO, mKeyCategory);
        bundle.putString(KEY_KEYWORD_TWO, mKeyKeyword);
        // Display the fragment on FrameLayout
        mFrag.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_search_result_frame_layout, mFrag).commit();
    }

}