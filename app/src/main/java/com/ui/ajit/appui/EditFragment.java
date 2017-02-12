package com.ui.ajit.appui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;


/**
 * Created by SAM on 4/9/2016.
 */
public class EditFragment extends DialogFragment {

LayoutInflater inflater;
    View view;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.edit_profile, null);
        AlertDialog.Builder  builder = new AlertDialog.Builder(getActivity());
        builder.setView(view).setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setTitle("Edit Your Profile");
        return builder.create();
    }
}
