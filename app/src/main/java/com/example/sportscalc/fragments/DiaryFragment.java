package com.example.sportscalc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sportscalc.R;
import com.example.sportscalc.activities.MainActivity;

public class DiaryFragment extends Fragment {
    private static final int FRAGMENT_DIARY_NAME = R.string.fragment_diary_name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setToolbar(getString(FRAGMENT_DIARY_NAME));
        return inflater.inflate(R.layout.fragment_diary_no_note, container, false);
    }
}
