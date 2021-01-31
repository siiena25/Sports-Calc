package com.example.sportscalc.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sportscalc.R;
import com.example.sportscalc.activities.MainActivity;
import com.example.sportscalc.dialogs.HeightDialog;
import com.example.sportscalc.dialogs.WeightDialog;

import java.util.Calendar;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    private static final int FRAGMENT_SETTINGS_NAME = R.string.fragment_settings_name;

    private RelativeLayout btnDatePicker, btnHeightDialog, btnWeightDialog;
    private TextView textViewChangeBirthDate;

    private int mYear, mMonth, mDay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setToolbar(getString(FRAGMENT_SETTINGS_NAME));
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        String currentBirthDate = ((MainActivity) getActivity()).getSharedPreferences().getString("birthDate", "01.01.2001");
        int currentHeight = ((MainActivity) getActivity()).getSharedPreferences().getInt("height", 160);
        int currentWeight = ((MainActivity) getActivity()).getSharedPreferences().getInt("weight", 60);

        textViewChangeBirthDate = view.findViewById(R.id.tv_change_birth_date);
        TextView textViewChangeHeight = view.findViewById(R.id.tv_change_height);
        TextView textViewChangeWeight = view.findViewById(R.id.tv_change_weight);

        textViewChangeBirthDate.setText(currentBirthDate);
        textViewChangeHeight.setText(Integer.toString(currentHeight));
        textViewChangeWeight.setText(Integer.toString(currentWeight));

        btnDatePicker = (RelativeLayout) view.findViewById(R.id.button_change_birth_date);
        btnHeightDialog = (RelativeLayout) view.findViewById(R.id.button_change_height);
        btnWeightDialog = (RelativeLayout) view.findViewById(R.id.button_change_weight);

        btnDatePicker.setOnClickListener(this);

        btnHeightDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHeightDialog();
            }
        });

        btnWeightDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeightDialog();
            }
        });

        return view;
    }

    public void openHeightDialog() {
        HeightDialog heightDialog = new HeightDialog();
        heightDialog.show(((MainActivity) getActivity()).getSupportFragmentManager(), "height dialog");
    }

    public void openWeightDialog() {
        WeightDialog weightDialog = new WeightDialog();
        weightDialog.show(((MainActivity) getActivity()).getSupportFragmentManager(), "weight dialog");
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button_change_birth_date) {
            callDatePicker();
        }
    }

    private void callDatePicker() {
        final Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(((MainActivity) getActivity()).getThis(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String editTextDateParam = dayOfMonth + "." + (monthOfYear + 1) + "." + year;
                        textViewChangeBirthDate.setText(editTextDateParam);
                        ((MainActivity) getActivity()).getEditor().putString("birthDate", editTextDateParam);
                        ((MainActivity) getActivity()).getEditor().commit();
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
