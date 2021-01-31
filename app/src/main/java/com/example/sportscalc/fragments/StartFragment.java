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

public class StartFragment extends Fragment {

    private static final int FRAGMENT_START1_NAME = R.string.fragment_start1_name;
    private String currentFragment = null;

    ImageButton nextButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        nextButton = view.findViewById(R.id.next_button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartFragment1 startFragment1 = new StartFragment1();
                currentFragment = getString(FRAGMENT_START1_NAME);
                ((MainActivity) getActivity()).setCurrentFragment(currentFragment);
                ((MainActivity) getActivity()).loadFragment(startFragment1, currentFragment);
            }
        });

        return view;
    }
}