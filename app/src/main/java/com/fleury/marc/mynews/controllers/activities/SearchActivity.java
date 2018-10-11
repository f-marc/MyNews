package com.fleury.marc.mynews.controllers.activities;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.fleury.marc.mynews.R;
import com.fleury.marc.mynews.controllers.fragments.others.CategoryFragment;
import com.fleury.marc.mynews.controllers.fragments.others.ResultFragment;
import com.fleury.marc.mynews.utils.AlarmReceiver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
    @BindView(R.id.endDate) TextView endDate;

    private ArrayList<String> mList = new ArrayList<>();
    private SimpleDateFormat dateFormat;
    private DatePickerDialog beginDatePicker;
    private DatePickerDialog endDatePicker;
    private Calendar newCalendar;

    public final static String KEY_CATEGORY_LIST = "KEY_CATEGORY_LIST";
    public final static String KEY_KEYWORD = "KEY_KEYWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        newCalendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mList.isEmpty() && !mEditText.getText().toString().matches("")){
                    String mListString = TextUtils.join(", ", mList);
                    Intent searchResultActivityIntent = new Intent(SearchActivity.this, SearchResultActivity.class);
                    searchResultActivityIntent.putExtra(KEY_CATEGORY_LIST, mListString);
                    searchResultActivityIntent.putExtra(KEY_KEYWORD, mEditText.getText().toString());
                    startActivity(searchResultActivityIntent);

                } else {
                    Toast.makeText(getApplicationContext(), "You must define at least one keyword and one category", Toast.LENGTH_LONG).show();
                }
            }
        });

        updateCheckBox();
        updateDate();

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
                    mList.add("Arts");
                } else {
                    mList.remove("Arts");
                }
            }
        });

        check_books.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mList.add("Books");
                } else {
                    mList.remove("Books");
                }
            }
        });

        check_business.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mList.add("Business Day");
                } else {
                    mList.remove("Business Day");
                }
            }
        });

        check_politics.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mList.add("Politics");
                } else {
                    mList.remove("Politics");
                }
            }
        });

        check_science.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mList.add("Science");
                } else {
                    mList.remove("Science");
                }
            }
        });

        check_sports.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mList.add("Sports");
                } else {
                    mList.remove("Sports");
                }
            }
        });

        check_tech.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mList.add("Technology");
                } else {
                    mList.remove("Technology");
                }
            }
        });

        check_travel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mList.add("Travel");
                } else {
                    mList.remove("Travel");
                }
            }
        });
    }

    public void updateDate() {
        beginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginDatePicker = new DatePickerDialog(getApplicationContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, month, dayOfMonth);
                        beginDate.setText(dateFormat.format(newDate.getTime()));
                    }
                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                beginDatePicker.show();
            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endDatePicker = new DatePickerDialog(getApplicationContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, month, dayOfMonth);
                        endDate.setText(dateFormat.format(newDate.getTime()));
                    }
                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                endDatePicker.show();
            }
        });
    }

}

