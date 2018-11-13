package com.fleury.marc.mynews;

import com.fleury.marc.mynews.controllers.activities.SearchActivity;

import org.junit.Test;

import static org.junit.Assert.*;

public class DateModificationTest {

    @Test
    public void beginDate_modification() throws Exception {

        String beginDate1 = "06/02/2010";

        SearchActivity searchActivity = new SearchActivity();
        String beginDate2 = searchActivity.updateBeginDate(beginDate1);

        assertEquals(beginDate2, "20100206");
    }

    @Test
    public void endDate_modification() throws Exception {

        String endDate1 = "06/02/2010";

        SearchActivity searchActivity = new SearchActivity();
        String endDate2 = searchActivity.updateEndDate(endDate1);

        assertEquals(endDate2, "20100206");
    }
}