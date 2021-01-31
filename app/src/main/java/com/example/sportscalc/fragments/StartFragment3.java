package com.example.sportscalc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sportscalc.R;
import com.example.sportscalc.activities.MainActivity;

public class StartFragment3 extends Fragment {

    private static final int FRAGMENT_OPTIONS_NAME = R.string.fragment_options_name;
    private String currentFragment = null;

    ImageButton nextButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start3, container, false);
        nextButton = view.findViewById(R.id.next_button3);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionsFragment optionsFragment = new OptionsFragment();
                currentFragment = getString(FRAGMENT_OPTIONS_NAME);
                ((MainActivity) getActivity()).setCurrentFragment(currentFragment);
                ((MainActivity) getActivity()).loadFragment(optionsFragment, currentFragment);
            }
        });

        return view;
    }
}