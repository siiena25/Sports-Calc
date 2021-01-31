package com.example.sportscalc.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sportscalc.R;
import com.example.sportscalc.activities.MainActivity;
import com.example.sportscalc.dialogs.HeightDialog;
import com.example.sportscalc.dialogs.WeightDialog;

import java.util.Calendar;

public class OptionsFragment extends Fragment implements View.OnClickListener {

    private static final int FRAGMENT_CALC_NAME = R.string.fragment_calc_name;
    private String currentFragment = null;

    private RelativeLayout btnDatePicker, btnHeightDialog, btnWeightDialog;
    private TextView textViewDate;

    private int mYear, mMonth, mDay;

    private Button continueButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_options, container, false);
        continueButton = view.findViewById(R.id.continue_button);

        btnDatePicker = (RelativeLayout) view.findViewById(R.id.button_birth_date);
        btnHeightDialog = (RelativeLayout) view.findViewById(R.id.button_height);
        btnWeightDialog = (RelativeLayout) view.findViewById(R.id.button_weight);

        textViewDate = (TextView) view.findViewById(R.id.tv_birth_date);

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

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalcFragment calcFragment = new CalcFragment();
                currentFragment = getString(FRAGMENT_CALC_NAME);
                ((MainActivity) getActivity()).setCurrentFragment(currentFragment);
                ((MainActivity) getActivity()).getToolbar().setVisibility(View.VISIBLE);
                ((MainActivity) getActivity()).getBottomNavigation().setVisibility(View.VISIBLE);
                ((MainActivity) getActivity()).clearBackStack();
                ((MainActivity) getActivity()).loadFragment(calcFragment, currentFragment);
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
        if (id == R.id.button_birth_date) {
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
                        textViewDate.setText(editTextDateParam);
                        ((MainActivity) getActivity()).getEditor().putString("birthDate", editTextDateParam);
                        ((MainActivity) getActivity()).getEditor().commit();
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}