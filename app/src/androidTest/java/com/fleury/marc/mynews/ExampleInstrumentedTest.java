package com.fleury.marc.mynews;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.fleury.marc.mynews.controllers.activities.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    /*@Test
    public boolean internetAccess() {
        ConnectivityManager connectivityManager = (ConnectivityManager) MainActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            NetworkInfo.State networkState = networkInfo.getState();
            if (networkState.compareTo(NetworkInfo.State.CONNECTED) == 0) {
                return true;
            } else return false;
        } else return false;
    }*/

}

