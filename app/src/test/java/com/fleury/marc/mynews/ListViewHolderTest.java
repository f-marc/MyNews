package com.fleury.marc.mynews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fleury.marc.mynews.controllers.activities.MainActivity;
import com.fleury.marc.mynews.controllers.activities.SearchActivity;
import com.fleury.marc.mynews.views.NYTimesViewHolder;
import com.fleury.marc.mynews.views.NYTimesViewHolder_ViewBinding;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListViewHolderTest {

    @Test
    public void listNull() throws Exception {

        List<String> list = new ArrayList<>();

        Boolean listTest = NYTimesViewHolder.listTest(list);

        assertEquals(listTest, false);
    }

    @Test
    public void listNonNull() throws Exception {

        List<String> list = new ArrayList<>();
        list.add("test");

        Boolean listTest = NYTimesViewHolder.listTest(list);

        assertEquals(listTest, true);
    }
}