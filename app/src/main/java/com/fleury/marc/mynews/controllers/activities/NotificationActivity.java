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

public class NotificationActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;

    @BindView(R.id.activity_notification_edit_text) EditText mEditText;
    @BindView(R.id.activity_notification_switch) Switch mSwitch;
    @BindView(R.id.notif_check_arts) CheckBox check_arts;
    @BindView(R.id.notif_check_books) CheckBox check_books;
    @BindView(R.id.notif_check_business) CheckBox check_business;
    @BindView(R.id.notif_check_politics) CheckBox check_politics;
    @BindView(R.id.notif_check_science) CheckBox check_science;
    @BindView(R.id.notif_check_sports) CheckBox check_sports;
    @BindView(R.id.notif_check_tech) CheckBox check_tech;
    @BindView(R.id.notif_check_travel) CheckBox check_travel;

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);

        mPreferences = this.getSharedPreferences("pref", MODE_PRIVATE);

        updateEditText();
        updateCheckBox();
        updateSwitch();

        configureToolbar();
    }

    private void configureToolbar() {
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notifications");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void updateEditText() {
        mEditText.setText(mPreferences.getString("keyWord", ""));
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mPreferences.edit().putString("keyWord", mEditText.getText().toString()).apply();
            }
        });
    }

    private void updateCheckBox() {
        check_arts.setChecked(mPreferences.getBoolean("checkArts", false));
        check_books.setChecked(mPreferences.getBoolean("checkBooks", false));
        check_business.setChecked(mPreferences.getBoolean("checkBusiness", false));
        check_politics.setChecked(mPreferences.getBoolean("checkPolitics", false));
        check_science.setChecked(mPreferences.getBoolean("checkScience", false));
        check_sports.setChecked(mPreferences.getBoolean("checkSports", false));
        check_tech.setChecked(mPreferences.getBoolean("checkTech", false));
        check_travel.setChecked(mPreferences.getBoolean("checkTravel", false));

        check_arts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mPreferences.edit().putBoolean("checkArts", true).apply();
                    mSwitch.setChecked(false);
                } else {
                    mPreferences.edit().putBoolean("checkArts", false).apply();
                }
            }
        });

        check_books.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mPreferences.edit().putBoolean("checkBooks", true).apply();
                    mSwitch.setChecked(false);
                } else {
                    mPreferences.edit().putBoolean("checkBooks", false).apply();
                }
            }
        });

        check_business.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mPreferences.edit().putBoolean("checkBusiness", true).apply();
                    mSwitch.setChecked(false);
                } else {
                    mPreferences.edit().putBoolean("checkBusiness", false).apply();
                }
            }
        });

        check_politics.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mPreferences.edit().putBoolean("checkPolitics", true).apply();
                    mSwitch.setChecked(false);
                } else {
                    mPreferences.edit().putBoolean("checkPolitics", false).apply();
                }
            }
        });

        check_science.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mPreferences.edit().putBoolean("checkScience", true).apply();
                    mSwitch.setChecked(false);
                } else {
                    mPreferences.edit().putBoolean("checkScience", false).apply();
                }
            }
        });

        check_sports.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mPreferences.edit().putBoolean("checkSports", true).apply();
                    mSwitch.setChecked(false);
                } else {
                    mPreferences.edit().putBoolean("checkSports", false).apply();
                }
            }
        });

        check_tech.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mPreferences.edit().putBoolean("checkTech", true).apply();
                    mSwitch.setChecked(false);
                } else {
                    mPreferences.edit().putBoolean("checkTech", false).apply();
                }
            }
        });

        check_travel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mPreferences.edit().putBoolean("checkTravel", true).apply();
                    mSwitch.setChecked(false);
                } else {
                    mPreferences.edit().putBoolean("checkTravel", false).apply();
                }
            }
        });
    }

    private void updateSwitch() {
        mSwitch.setChecked(mPreferences.getBoolean("switchCheck", false));

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if ((mPreferences.getBoolean("checkArts", false) ||
                            mPreferences.getBoolean("checkBooks", false) ||
                            mPreferences.getBoolean("checkBusiness", false) ||
                            mPreferences.getBoolean("checkPolitics", false) ||
                            mPreferences.getBoolean("checkScience", false) ||
                            mPreferences.getBoolean("checkSports", false) ||
                            mPreferences.getBoolean("checkTech", false) ||
                            mPreferences.getBoolean("checkTravel", false)) && !mEditText.getText().toString().matches("")) {
                        alarmMgr = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                        alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
                        // Set the alarm to start at 11:59 p.m.
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        calendar.set(Calendar.HOUR_OF_DAY, 23);
                        calendar.set(Calendar.MINUTE, 59);
                        // Set the alarm to repeat everyday
                        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);

                        mPreferences.edit().putBoolean("switchCheck", true).apply();
                    } else {
                        Toast.makeText(getApplicationContext(), "You must define at least one keyword and one category", Toast.LENGTH_LONG).show();
                        mSwitch.setChecked(false);
                    }
                } else {
                    alarmMgr = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                    alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
                    alarmMgr.cancel(alarmIntent);

                    mEditText.getText().clear();
                    mPreferences.edit().remove("keyWord").apply();
                    check_arts.setChecked(false);
                    check_books.setChecked(false);
                    check_business.setChecked(false);
                    check_politics.setChecked(false);
                    check_science.setChecked(false);
                    check_sports.setChecked(false);
                    check_tech.setChecked(false);
                    check_travel.setChecked(false);

                    mPreferences.edit().putBoolean("switchCheck", false).apply();
                }
            }
        });
    }
}
