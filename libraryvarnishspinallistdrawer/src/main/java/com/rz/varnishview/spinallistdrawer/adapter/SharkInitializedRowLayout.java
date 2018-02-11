package com.rz.varnishview.spinallistdrawer.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rz Rasel on 2017-12-26.
 */

public class SharkInitializedRowLayout {
    public static void onSetRowFields(Context argContext, View argRootView, ArrayList<SharkModelRowViewFields> argListRowLayoutFields) {
        for (int i = 0; i < argListRowLayoutFields.size(); i++) {
            Object objField = argListRowLayoutFields.get(i).getFieldObject();
            String textResourceID = argListRowLayoutFields.get(i).getFieldResourceId();
            int resourceID = argContext.getResources().getIdentifier(textResourceID, "id", argContext.getPackageName());
            if (objField instanceof TextView) {
                TextView fieldTextView = (TextView) argRootView.findViewById(resourceID);
                argListRowLayoutFields.set(i, SharkModelRowViewFields.onGetSetModelRow(fieldTextView, textResourceID));
                //System.out.println("‒‒‒‒|‑‑‑‑|――――――――――――|TEXT_VIEW|");
            } else if (objField instanceof ImageView) {
                ImageView fieldImageView = (ImageView) argRootView.findViewById(resourceID);
                argListRowLayoutFields.set(i, SharkModelRowViewFields.onGetSetModelRow(fieldImageView, textResourceID));
            }
        }
    }
}
