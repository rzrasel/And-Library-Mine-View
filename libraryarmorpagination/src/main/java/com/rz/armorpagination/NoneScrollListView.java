package com.rz.armorpagination;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Rz Rasel on 2018-02-19.
 */

public class NoneScrollListView extends ListView {

    public NoneScrollListView(Context argContext) {
        super(argContext);
    }

    public NoneScrollListView(Context argContext, AttributeSet argAttrs) {
        super(argContext, argAttrs);
    }

    public NoneScrollListView(Context argContext, AttributeSet argAttrs, int argDefStyle) {
        super(argContext, argAttrs, argDefStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMeasureSpec_custom = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST) + 50;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec_custom);
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = getMeasuredHeight();
    }
}