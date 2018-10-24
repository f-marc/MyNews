package com.fleury.marc.mynews.controllers.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.fleury.marc.mynews.R;
import com.fleury.marc.mynews.controllers.fragments.others.SearchResultFragment;

import java.util.ArrayList;

public class NotificationResultActivity extends AppCompatActivity {

    public final static String KEY_CATEGORY_LIST_THREE = "KEY_CATEGORY_LIST";
    public final static String KEY_KEYWORD_THREE = "KEY_KEYWORD";

    private Toolbar toolbar;

    private SharedPreferences mPreferences;
    private ArrayList<String> mList = new ArrayList<>();
    private String mKeyKeyword;
    private String mKeyCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        mPreferences = this.getSharedPreferences("pref", MODE_PRIVATE);

        mKeyKeyword = mPreferences.getString("keyWord", "");

        configureToolbar();
        updateList();
        runFragment();
    }


    private void configureToolbar(){
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void updateList(){
        if(mPreferences.getBoolean("checkArts", false)){
            mList.add("\"Arts\"");
        }
        if(mPreferences.getBoolean("checkBooks", false)){
            mList.add("\"Books\"");
        }
        if(mPreferences.getBoolean("checkBooks", false)){
            mList.add("\"Business Day\"");
        }
        if(mPreferences.getBoolean("checkBooks", false)){
            mList.add("\"Politics\"");
        }
        if(mPreferences.getBoolean("checkBooks", false)){
            mList.add("\"Science\"");
        }
        if(mPreferences.getBoolean("checkBooks", false)){
            mList.add("\"Sports\"");
        }
        if(mPreferences.getBoolean("checkBooks", false)){
            mList.add("\"Technology\"");
        }
        if(mPreferences.getBoolean("checkBooks", false)){
            mList.add("\"Travel\"");
        }

        String sList = TextUtils.join(", ", mList).replace(", ", " ");
        mKeyCategory = "news_desk:("+sList+")";
    }


    private void runFragment() {
        // Transfer current bundle to the fragment
        SearchResultFragment mFrag = new SearchResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_CATEGORY_LIST_THREE, mKeyCategory);
        bundle.putString(KEY_KEYWORD_THREE, mKeyKeyword);
        // Display the fragment on FrameLayout
        mFrag.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_search_result_frame_layout, mFrag).commit();
    }
}