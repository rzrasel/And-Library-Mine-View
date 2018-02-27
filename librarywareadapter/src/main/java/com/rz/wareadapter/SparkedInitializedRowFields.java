package com.rz.wareadapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rz Rasel on 2018-02-27.
 */

public class SparkedInitializedRowFields {
    public static void onSetRowFields(Context argContext, View argRootView, ArrayList<SparkedModelRowViewFields> argListRowLayoutFields) {
        for (int forCount = 0; forCount < argListRowLayoutFields.size(); forCount++) {
            Object objField = argListRowLayoutFields.get(forCount).getFieldObject();
            String textResourceID = argListRowLayoutFields.get(forCount).getFieldResourceId();
            int resourceID = argContext.getResources().getIdentifier(textResourceID, "id", argContext.getPackageName());
            if (objField instanceof TextView) {
                TextView fieldTextView = (TextView) argRootView.findViewById(resourceID);
                argListRowLayoutFields.set(forCount, SparkedModelRowViewFields.onGetSetModelRow(fieldTextView, textResourceID));
                //System.out.println("‒‒‒‒|‑‑‑‑|――――――――――――|TEXT_VIEW|");
            } else if (objField instanceof ImageView) {
                ImageView fieldImageView = (ImageView) argRootView.findViewById(resourceID);
                argListRowLayoutFields.set(forCount, SparkedModelRowViewFields.onGetSetModelRow(fieldImageView, textResourceID));
            }
        }
    }
}