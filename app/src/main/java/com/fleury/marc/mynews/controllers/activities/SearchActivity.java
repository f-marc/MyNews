package com.fleury.marc.mynews.controllers.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.fleury.marc.mynews.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;

    @BindView(R.id.activity_search_edit_text) EditText mEditText;
    @BindView(R.id.activity_search_button) Button mButton;
    @BindView(R.id.search_check_arts) CheckBox check_arts;
    @BindView(R.id.search_check_books) CheckBox check_books;
    @BindView(R.id.search_check_business) CheckBox check_business;
    @BindView(R.id.search_check_politics) CheckBox check_politics;
    @BindView(R.id.search_check_science) CheckBox check_science;
    @BindView(R.id.search_check_sports) CheckBox check_sports;
    @BindView(R.id.search_check_tech) CheckBox check_tech;
    @BindView(R.id.search_check_travel) CheckBox check_travel;
    @BindView(R.id.beginDate) EditText beginDate;
    @BindView(R.id.endDate) EditText endDate;

    private ArrayList<String> mList = new ArrayList<>();
    private String sBegin;
    private String sEnd;
    private String sListFinal;

    public final static String KEY_CATEGORY_LIST = "KEY_CATEGORY_LIST";
    public final static String KEY_KEYWORD = "KEY_KEYWORD";
    public final static String KEY_BEGIN_DATE = "KEY_BEGIN_DATE";
    public final static String KEY_END_DATE = "KEY_END_DATE";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mList.isEmpty() && !mEditText.getText().toString().matches("")){
                    Intent searchResultActivityIntent = new Intent(SearchActivity.this, SearchResultActivity.class);

                    updateList();
                    searchResultActivityIntent.putExtra(KEY_CATEGORY_LIST, sListFinal);
                    searchResultActivityIntent.putExtra(KEY_KEYWORD, mEditText.getText().toString());

                    if(!beginDate.getText().toString().matches("") && endDate.getText().toString().matches("")){
                        // if only beginDate is completed
                        searchResultActivityIntent.putExtra(KEY_BEGIN_DATE, updateBeginDate(beginDate.getText().toString()));
                    } else if (beginDate.getText().toString().matches("") && !endDate.getText().toString().matches("")) {
                        // if only endDate is completed
                        searchResultActivityIntent.putExtra(KEY_END_DATE, updateEndDate(endDate.getText().toString()));
                    } else if (!beginDate.getText().toString().matches("") && !endDate.getText().toString().matches("")) {
                        // if beginDate and endDate are both completed
                        searchResultActivityIntent.putExtra(KEY_BEGIN_DATE, updateBeginDate(beginDate.getText().toString()));
                        searchResultActivityIntent.putExtra(KEY_END_DATE, updateEndDate(endDate.getText().toString()));
                    }

                    startActivity(searchResultActivityIntent);

                } else {
                    Toast.makeText(getApplicationContext(), "You must define at least one keyword and one category", Toast.LENGTH_LONG).show();
                }
            }
        });

        updateCheckBox();
        configureToolbar();
    }

    private void configureToolbar() {
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void updateCheckBox() {

        check_arts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mList.add("\"Arts\"");
                } else {
                    mList.remove("\"Arts\"");
                }
            }
        });

        check_books.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mList.add("\"Books\"");
                } else {
                    mList.remove("\"Books\"");
                }
            }
        });

        check_business.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mList.add("\"Business Day\"");
                } else {
                    mList.remove("\"Business Day\"");
                }
            }
        });

        check_politics.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mList.add("\"Politics\"");
                } else {
                    mList.remove("\"Politics\"");
                }
            }
        });

        check_science.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mList.add("\"Science\"");
                } else {
                    mList.remove("\"Science\"");
                }
            }
        });

        check_sports.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mList.add("\"Sports\"");
                } else {
                    mList.remove("\"Sports\"");
                }
            }
        });

        check_tech.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mList.add("\"Technology\"");
                } else {
                    mList.remove("\"Technology\"");
                }
            }
        });

        check_travel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mList.add("\"Travel\"");
                } else {
                    mList.remove("\"Travel\"");
                }
            }
        });
    }

    public void updateList(){
        String sList = TextUtils.join(", ", mList).replace(", ", " ");
        sListFinal = "news_desk:("+sList+")";
    }

    public String updateBeginDate(String sBegin){
        SimpleDateFormat formatEditText = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date dBegin;
        try {
            dBegin = formatEditText.parse(sBegin);
            SimpleDateFormat formatAPI = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            sBegin = formatAPI.format(dBegin);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sBegin;
    }

    public String updateEndDate(String sEnd) {
        SimpleDateFormat formatEditText = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date dEnd;
        try {
            dEnd = formatEditText.parse(sEnd);
            SimpleDateFormat formatAPI = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            sEnd = formatAPI.format(dEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sEnd;
    }

}

