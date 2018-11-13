package com.fleury.marc.mynews.controllers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.fleury.marc.mynews.R;
import com.fleury.marc.mynews.controllers.fragments.others.SearchResultFragment;

import static com.fleury.marc.mynews.controllers.activities.SearchActivity.KEY_BEGIN_DATE;
import static com.fleury.marc.mynews.controllers.activities.SearchActivity.KEY_CATEGORY_LIST;
import static com.fleury.marc.mynews.controllers.activities.SearchActivity.KEY_END_DATE;
import static com.fleury.marc.mynews.controllers.activities.SearchActivity.KEY_KEYWORD;

public class SearchResultActivity extends AppCompatActivity {

    private Toolbar toolbar;

    public final static String KEY_CATEGORY_LIST_TWO = "KEY_CATEGORY_LIST_TWO";
    public final static String KEY_KEYWORD_TWO = "KEY_KEYWORD_TWO";
    public final static String KEY_BEGIN_DATE_TWO = "KEY_BEGIN_DATE_TWO";
    public final static String KEY_END_DATE_TWO = "KEY_END_DATE_TWO";

    private String mKeyCategory;
    private String mKeyKeyword;
    private String mKeyBeginDate;
    private String mKeyEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Bundle mExtra = getIntent().getExtras();
        mKeyCategory = mExtra.getString(KEY_CATEGORY_LIST);
        mKeyKeyword = mExtra.getString(KEY_KEYWORD);

        if(mExtra.getString(KEY_BEGIN_DATE) != null) {
            mKeyBeginDate = mExtra.getString(KEY_BEGIN_DATE);
            Log.i("DateTest_begin", mKeyBeginDate);
        }

        if(mExtra.getString(KEY_END_DATE) != null) {
            mKeyEndDate = mExtra.getString(KEY_END_DATE);
            Log.i("DateTest_end", mKeyEndDate);
        }

        configureToolbar();
        runFragment();
    }


    private void configureToolbar(){
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void runFragment() {
        // Transfer current bundle to the fragment
        SearchResultFragment mFrag = new SearchResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_CATEGORY_LIST_TWO, mKeyCategory);
        bundle.putString(KEY_KEYWORD_TWO, mKeyKeyword);
        bundle.getString(KEY_BEGIN_DATE_TWO, mKeyBeginDate);
        bundle.getString(KEY_END_DATE_TWO, mKeyEndDate);
        // Display the fragment on FrameLayout
        mFrag.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_search_result_frame_layout, mFrag).commit();
    }
}