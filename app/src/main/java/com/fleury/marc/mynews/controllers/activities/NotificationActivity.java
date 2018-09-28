package com.fleury.marc.mynews.controllers.activities;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.fleury.marc.mynews.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;

    @BindView(R.id.activity_notification_edit_text) EditText mEditText;
    @BindView(R.id.activity_notification_switch) Switch mSwitch;
    @BindView(R.id.check_arts) CheckBox check_arts;
    @BindView(R.id.check_books) CheckBox check_books;
    @BindView(R.id.check_business) CheckBox check_business;
    @BindView(R.id.check_politics) CheckBox check_politics;
    @BindView(R.id.check_science) CheckBox check_science;
    @BindView(R.id.check_sports) CheckBox check_sports;
    @BindView(R.id.check_tech) CheckBox check_tech;
    @BindView(R.id.check_travel) CheckBox check_travel;

    private SharedPreferences mPreferences;
    private Map<Integer, String> mList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);

        mPreferences = this.getSharedPreferences("pref", MODE_PRIVATE);

        // --- CheckBox ---
        updateCheckBox();

        // --- Switch ---
        updateSwitch();

        if ((!mSwitch.isChecked()) && (mList.isEmpty())) {
            mSwitch.setEnabled(false);
        }

        configureToolbar();
    }


    private void configureToolbar() {
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notifications");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void sendNotification() {
        // TEST
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);

            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.outline_library_books_black_48)
                .setContentTitle("MyNews")
                .setContentText("De nouveaux articles sont disponibles !")
                .setPriority(Notification.PRIORITY_DEFAULT);

        notificationManager.notify(1, notificationBuilder.build());
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
                    mList.put(0, "arts");
                    mPreferences.edit().putBoolean("checkArts", true).apply();

                } else {
                    mList.remove(0);
                    mPreferences.edit().putBoolean("checkArts", false).apply();
                }
            }
        });

        check_books.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mList.put(1, "books");
                    mPreferences.edit().putBoolean("checkBooks", true).apply();

                } else {
                    mList.remove(1);
                    mPreferences.edit().putBoolean("checkBooks", false).apply();
                }
            }
        });

        check_business.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mList.put(2, "business");
                    mPreferences.edit().putBoolean("checkBusiness", true).apply();

                } else {
                    mList.remove(2);
                    mPreferences.edit().putBoolean("checkBusiness", false).apply();
                }
            }
        });

        check_politics.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mList.put(3, "politics");
                    mPreferences.edit().putBoolean("checkPolitics", true).apply();

                } else {
                    mList.remove(3);
                    mPreferences.edit().putBoolean("checkPolitics", false).apply();
                }
            }
        });

        check_science.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mList.put(4, "science");
                    mPreferences.edit().putBoolean("checkScience", true).apply();

                } else {
                    mList.remove(4);
                    mPreferences.edit().putBoolean("checkScience", false).apply();
                }
            }
        });

        check_sports.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mList.put(5, "sports");
                    mPreferences.edit().putBoolean("checkSports", true).apply();

                } else {
                    mList.remove(5);
                    mPreferences.edit().putBoolean("checkSports", false).apply();
                }
            }
        });

        check_tech.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mList.put(6, "tech");
                    mPreferences.edit().putBoolean("checkTech", true).apply();

                } else {
                    mList.remove(6);
                    mPreferences.edit().putBoolean("checkTech", false).apply();
                }
            }
        });

        check_travel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mList.put(7, "travel");
                    mPreferences.edit().putBoolean("checkTravel", true).apply();

                } else {
                    mList.remove(7);
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
                    //start alarm
                    mPreferences.edit().putBoolean("switchCheck", true).apply();

                } else {
                    //cancel alarm
                    mEditText.getText().clear();
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
