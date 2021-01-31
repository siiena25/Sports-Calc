package com.example.sportscalc.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.sportscalc.R;
import com.example.sportscalc.activities.MainActivity;

public class WeightDialog extends AppCompatDialogFragment {

    private EditText editTextWeight;

    private TextView textViewWeight;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_weight, null);

        String currentFragment = ((MainActivity) getActivity()).getCurrentFragment();
        if (currentFragment.equals("options")) {
            textViewWeight = ((MainActivity) getActivity()).findViewById(R.id.tv_weight);
        }
        else if (currentFragment.equals("settings")) {
            textViewWeight = ((MainActivity) getActivity()).findViewById(R.id.tv_change_weight);
        }

        builder.setView(view)
                .setTitle("Вес")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int weight = Integer.parseInt(editTextWeight.getText().toString());
                        textViewWeight.setText(Integer.toString(weight));
                        ((MainActivity) getActivity()).getEditor().putInt("weight", weight);
                        ((MainActivity) getActivity()).getEditor().commit();
                    }
                });

        editTextWeight = view.findViewById(R.id.et_weight);

        return builder.create();
    }
}