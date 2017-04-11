package com.ruanorz.meteor.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruano on 10/04/2017.
 */

public class TutorialFragmentPageAdapter extends FragmentPagerAdapter {

    // List of fragments which are going to set in the view pager widget
    public final static float BIG_SCALE = 1.0f;
    public final static float SMALL_SCALE = 0.7f;
    public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;
    List<Fragment> fragments;
    private String[] strings;


    public TutorialFragmentPageAdapter(FragmentManager fm, String[] stringsToDisplay) {
        super(fm);
        this.fragments = new ArrayList<Fragment>();
        this.strings = stringsToDisplay;
    }


    @Override
    public Fragment getItem(int pos) {
        return TutorialSlidePageFragment.newInstance(strings[pos]);
    }

    @Override
    public int getCount() {
        return this.strings.length;
    }

}