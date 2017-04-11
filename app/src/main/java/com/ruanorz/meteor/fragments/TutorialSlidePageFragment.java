package com.ruanorz.meteor.fragments;

import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ruanorz.meteor.R;

/**
 * Created by ruano on 10/04/2017.
 */

public class TutorialSlidePageFragment extends Fragment{

    // Store instance variables
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static TutorialSlidePageFragment newInstance(String title) {
        TutorialSlidePageFragment fragment = new TutorialSlidePageFragment();
        Bundle args = new Bundle();
        args.putString("someTitle", title);
        fragment.setArguments(args);
        fragment.setRetainInstance(true);
        return fragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutorial_page, container, false);
        TextView tvLabel = (TextView) view.findViewById(R.id.text_page);
        tvLabel.setText(title);
        return view;
    }
}