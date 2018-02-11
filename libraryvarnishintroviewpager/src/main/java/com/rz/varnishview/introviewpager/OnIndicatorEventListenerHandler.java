package com.rz.varnishview.introviewpager;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Rz Rasel on 2017-12-17.
 */

public interface OnIndicatorEventListenerHandler {
    public boolean onIsViewFromObject(View argView, Object argObject);
    public void onDestroyItem(ViewGroup argContainer, int argPosition, Object argObject);
    //public Object onInstantiateItem(ViewGroup argContainer, int argPosition);
    public void onSetFieldsValue(ArrayList<ModelRowViewHolder> argListRowViewFields, Object argObject);
}
