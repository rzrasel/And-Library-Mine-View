package com.rz.varnishview.introviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by developer on 12/14/17.
 */

public class AdapterViewPager<T> extends PagerAdapter {
    private Context context;
    private int layoutResourceId;
    //private String[] mResources;
    private ArrayList<T> adapterListItems; // = new ArrayList<ModelInit>();
    private ArrayList<ModelRowViewHolder> fieldsRowViewListItems = new ArrayList<ModelRowViewHolder>();
    private OnIndicatorEventListenerHandler onEventListenerHandler;

    public AdapterViewPager(Context argContext, int argLayoutResourceId, ArrayList<T> argAdapterListItems) {
        this.context = argContext;
        this.layoutResourceId = argLayoutResourceId;
        this.adapterListItems = argAdapterListItems;
    }

    public void onSetEventListenerHandler(OnIndicatorEventListenerHandler argEventListenerHandler, ArrayList<ModelRowViewHolder> argFieldsRowViewListItems) {
        this.onEventListenerHandler = argEventListenerHandler;
        this.fieldsRowViewListItems = argFieldsRowViewListItems;
    }

    @Override
    public int getCount() {
        //System.out.println("GET_COUNT: " + mResources.length);
        //return mResources.length;
        return this.adapterListItems.size();
    }

    @Override
    public boolean isViewFromObject(View argView, Object argObject) {
        if (onEventListenerHandler != null) {
            return onEventListenerHandler.onIsViewFromObject(argView, argObject);
        } else {
            return argView == ((LinearLayout) argObject);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup argContainer, int argPosition) {
        //View rootViewLayoutInflater = LayoutInflater.from(mContext).inflate(R.layout.pager_item, container, false);
        View rootViewLayoutInflater = LayoutInflater.from(context).inflate(layoutResourceId, argContainer, false);
        onInitializedLayoutFields(context, rootViewLayoutInflater);
        Object items = adapterListItems.get(argPosition);

        /*System.out.println("TEXT: " + mResources[argPosition]);
        TextView sysInstruction = (TextView) rootViewLayoutInflater.findViewById(R.id.sysInstruction);
        sysInstruction.setText(mResources[argPosition]);*/
        if (onEventListenerHandler != null) {
            onEventListenerHandler.onSetFieldsValue(fieldsRowViewListItems, items);
        }

        argContainer.addView(rootViewLayoutInflater);
        return rootViewLayoutInflater;
    }

    @Override
    public void destroyItem(ViewGroup argContainer, int argPosition, Object argObject) {
        if (onEventListenerHandler != null) {
            onEventListenerHandler.onDestroyItem(argContainer, argPosition, argObject);
        } else {
            argContainer.removeView((LinearLayout) argObject);
        }
    }

    /*private class RowViewHolder {
        TextView sysTvRowTitle, sysTvRowDesc;

        public RowViewHolder(View argRootView) {
        }
    }*/
    private void onInitializedLayoutFields(Context argContext, View argRootView) {
        for (int i = 0; i < fieldsRowViewListItems.size(); i++) {
            Object objField = fieldsRowViewListItems.get(i).getFieldObject();
            String textResourceID = fieldsRowViewListItems.get(i).getFieldResourceId();
            String textExtra = fieldsRowViewListItems.get(i).getExtra();
            int resourceID = context.getResources().getIdentifier(textResourceID, "id", argContext.getPackageName());
            if (objField instanceof TextView) {
                TextView fieldTextView = (TextView) argRootView.findViewById(resourceID);
                fieldsRowViewListItems.set(i, ModelRowViewHolder.onGetSetRow(fieldTextView, textResourceID, textExtra));
                //System.out.println("‒‒‒‒|‑‑‑‑|――――――――――――|TEXT_VIEW|");
            } else if (objField instanceof ImageView) {
                ImageView fieldImageView = (ImageView) argRootView.findViewById(resourceID);
                fieldsRowViewListItems.set(i, ModelRowViewHolder.onGetSetRow(fieldImageView, textResourceID, textExtra));
            } else if (objField instanceof WebView) {
                WebView fieldWebView = (WebView) argRootView.findViewById(resourceID);
                fieldsRowViewListItems.set(i, ModelRowViewHolder.onGetSetRow(fieldWebView, textResourceID, textExtra));
            }
        }
    }
}
