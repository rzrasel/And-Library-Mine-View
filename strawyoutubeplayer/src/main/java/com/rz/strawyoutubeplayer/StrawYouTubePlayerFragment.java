package com.rz.strawyoutubeplayer;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Rz Rasel on 2018-03-04.
 */

public class StrawYouTubePlayerFragment extends FrameLayout {
    private Activity activity;
    private Context context;
    public StrawYouTubePlayerFragment(Context argContext) {
        super(argContext);
        init(argContext, null);
    }

    public StrawYouTubePlayerFragment(Context argContext, AttributeSet argAttrs) {
        super(argContext, argAttrs);
        init(argContext, argAttrs);
    }

    public StrawYouTubePlayerFragment(Context argContext, AttributeSet argAttrs, int argDefStyleAttr) {
        super(argContext, argAttrs, argDefStyleAttr);
        init(argContext, argAttrs);
    }

    private void init(Context argContext, AttributeSet argAttrs) {
        context = argContext;
        activity = (Activity) context;
    }
}
