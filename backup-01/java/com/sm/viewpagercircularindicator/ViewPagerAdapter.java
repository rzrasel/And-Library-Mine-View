package com.sm.viewpagercircularindicator;

import android.content.Context;
import android.provider.Settings;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by developer on 12/14/17.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private String[] mResources;

    public ViewPagerAdapter(Context mContext, String[] mResources) {
        this.mContext = mContext;
        this.mResources = mResources;
    }

    @Override
    public int getCount() {
        System.out.println("GET_COUNT: " + mResources.length);
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.pager_item, container, false);

        System.out.println("TEXT: " + mResources[position]);
        TextView sysInstruction = (TextView) itemView.findViewById(R.id.sysInstruction);
        sysInstruction.setText(mResources[position]);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
