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

public class HeightDialog extends AppCompatDialogFragment {

    private EditText editTextHeight;

    private TextView textViewHeight;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_height, null);

        String currentFragment = ((MainActivity) getActivity()).getCurrentFragment();
        if (currentFragment.equals("options")) {
            textViewHeight = ((MainActivity) getActivity()).findViewById(R.id.tv_height);
        }
        else if (currentFragment.equals("settings")) {
            textViewHeight = ((MainActivity) getActivity()).findViewById(R.id.tv_change_height);
        }

        builder.setView(view)
                .setTitle("Рост")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int height = Integer.parseInt(editTextHeight.getText().toString());
                        textViewHeight.setText(Integer.toString(height));
                        ((MainActivity) getActivity()).getEditor().putInt("height", height);
                        ((MainActivity) getActivity()).getEditor().commit();
                    }
                });

        editTextHeight = view.findViewById(R.id.et_height);

        return builder.create();
    }
}
