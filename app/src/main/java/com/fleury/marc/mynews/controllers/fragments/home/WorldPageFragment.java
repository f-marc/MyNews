package com.fleury.marc.mynews.controllers.fragments.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fleury.marc.mynews.R;

public class WorldPageFragment extends Fragment {

    public static WorldPageFragment newInstance() {
        return (new WorldPageFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page_world, container, false);
    }
}