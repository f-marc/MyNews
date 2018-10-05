package com.fleury.marc.mynews.controllers.activities;

import android.app.AlarmManager;
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
import android.text.TextWatcher;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.fleury.marc.mynews.R;
import com.fleury.marc.mynews.utils.AlarmReceiver;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;

    @BindView(R.id.activity_search_edit_text) EditText mEditText;
    @BindView(R.id.search_check_arts) CheckBox check_arts;
    @BindView(R.id.search_check_books) CheckBox check_books;
    @BindView(R.id.search_check_business) CheckBox check_business;
    @BindView(R.id.search_check_politics) CheckBox check_politics;
    @BindView(R.id.search_check_science) CheckBox check_science;
    @BindView(R.id.search_check_sports) CheckBox check_sports;
    @BindView(R.id.search_check_tech) CheckBox check_tech;
    @BindView(R.id.search_check_travel) CheckBox check_travel;

    private ArrayList<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

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

}
