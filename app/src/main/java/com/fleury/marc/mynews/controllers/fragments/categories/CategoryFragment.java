package com.fleury.marc.mynews.controllers.fragments.categories;


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
            case 0:
                mLayout.setBackgroundColor(getResources().getColor(R.color.artsColor));
                mTitle.setText("Arts Fragment");
                break;
            case 1:
                mLayout.setBackgroundColor(getResources().getColor(R.color.booksColor));
                mTitle.setText("Books Fragment");
                break;
            case 2:
                mLayout.setBackgroundColor(getResources().getColor(R.color.businessColor));
                mTitle.setText("Business Fragment");
                break;
            case 3:
                mLayout.setBackgroundColor(getResources().getColor(R.color.politicsColor));
                mTitle.setText("Politics Fragment");
                break;
            case 4:
                mLayout.setBackgroundColor(getResources().getColor(R.color.scienceColor));
                mTitle.setText("Science Fragment");
                break;
            case 5:
                mLayout.setBackgroundColor(getResources().getColor(R.color.sportsColor));
                mTitle.setText("Sports Fragment");
                break;
            case 6:
                mLayout.setBackgroundColor(getResources().getColor(R.color.techColor));
                mTitle.setText("Tech Fragment");
                break;
            case 7:
                mLayout.setBackgroundColor(getResources().getColor(R.color.travelColor));
                mTitle.setText("Travel Fragment");
                break;
        }
    }
}
