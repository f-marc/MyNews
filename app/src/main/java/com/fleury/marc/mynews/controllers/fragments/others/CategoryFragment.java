package com.fleury.marc.mynews.controllers.fragments.others;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fleury.marc.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CategoryFragment extends Fragment {

    @BindView(R.id.fragment_category_rootview) LinearLayout mLayout;
    @BindView(R.id.fragment_page_category_title) TextView mTextView;

    public static final int FRAGMENT_ARTS = 0, FRAGMENT_BOOKS = 1, FRAGMENT_BUSINESS = 2, FRAGMENT_POLITICS = 3,
            FRAGMENT_SCIENCE = 4, FRAGMENT_SPORTS = 5, FRAGMENT_TECH = 6, FRAGMENT_TRAVEL = 7;

    public static CategoryFragment newInstance() {
        return (new CategoryFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, v);

        Bundle bundle = getArguments();
        int mKey = bundle.getInt("KEY_CATEGORY_TWO");

        Log.i("TEST_KEY", String.valueOf(mKey));

        updateLayout(mKey, mLayout, mTextView);

        return v;
    }


    // Method for displaying the correct components.
    public void updateLayout(int mKey, LinearLayout mLayout, TextView mTitle) {

        switch(mKey) {
            case FRAGMENT_ARTS:
                mLayout.setBackgroundColor(getResources().getColor(R.color.artsColor));
                mTitle.setText("Arts Fragment");
                break;
            case FRAGMENT_BOOKS:
                mLayout.setBackgroundColor(getResources().getColor(R.color.booksColor));
                mTitle.setText("Books Fragment");
                break;
            case FRAGMENT_BUSINESS:
                mLayout.setBackgroundColor(getResources().getColor(R.color.businessColor));
                mTitle.setText("Business Fragment");
                break;
            case FRAGMENT_POLITICS:
                mLayout.setBackgroundColor(getResources().getColor(R.color.politicsColor));
                mTitle.setText("Politics Fragment");
                break;
            case FRAGMENT_SCIENCE:
                mLayout.setBackgroundColor(getResources().getColor(R.color.scienceColor));
                mTitle.setText("Science Fragment");
                break;
            case FRAGMENT_SPORTS:
                mLayout.setBackgroundColor(getResources().getColor(R.color.sportsColor));
                mTitle.setText("Sports Fragment");
                break;
            case FRAGMENT_TECH:
                mLayout.setBackgroundColor(getResources().getColor(R.color.techColor));
                mTitle.setText("Tech Fragment");
                break;
            case FRAGMENT_TRAVEL:
                mLayout.setBackgroundColor(getResources().getColor(R.color.travelColor));
                mTitle.setText("Travel Fragment");
                break;
        }
    }
}
