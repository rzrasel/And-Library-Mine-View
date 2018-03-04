package com.rz.usagesexample;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.support.v7.view.ContextThemeWrapper;

/**
 * Created by Rz Rasel on 2018-03-01.
 */

public class NewLibYouTubeOne extends FrameLayout {
    private Activity activity;
    private Context context;
    private View mControlsFrame;
    private LinearLayout videoContainer;
    private LinearLayout mediaController;
    public int widthRatio = 0;
    public int heightRatio = 0;
    public int currentScreen = -1;
    public static final int SCREEN_WINDOW_FULLSCREEN = 2;
    public static final int SCREEN_WINDOW_TINY = 3;
    public static int FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR;
    private static final int HIDE_STATUS_BAR_FLAGS_IMMERSIVE = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
    private static final int HIDE_STATUS_BAR_FLAGS = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LOW_PROFILE;
    private static final int SEEK_BAR_UPDATE_INTERVAL = 1000;
    private static final int HIDE_STATUS_BAR_DELAY_MILLIS = 3000;
    private GestureDetectorCompat gestureDetector;

    public NewLibYouTubeOne(Context argContext) {
        super(argContext);
        init(argContext, null);
    }

    public NewLibYouTubeOne(Context argContext, AttributeSet argAttrs) {
        super(argContext, argAttrs);
        init(argContext, argAttrs);
    }

    public NewLibYouTubeOne(Context argContext, AttributeSet argAttrs, int argDefStyleAttr) {
        super(argContext, argAttrs, argDefStyleAttr);
        init(argContext, argAttrs);
    }

    private void init(Context argContext, AttributeSet argAttrs) {
        context = argContext;
        activity = (Activity) context;
        //gestureDetector = new GestureDetectorCompat(activity, onGestureListener);
        currentScreen = SCREEN_WINDOW_FULLSCREEN;
        setFullscreen(true);
    }

    private GestureDetector.OnGestureListener onGestureListener = new GestureDetector.OnGestureListener() {

        @Override
        public boolean onDown(MotionEvent e) {
            System.out.println("TOUCHED:------------------onDown");
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            System.out.println("TOUCHED:------------------onShowPress");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            //toggleControlsVisibility();
            System.out.println("TOUCHED:------------------onSingleTapUp");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            System.out.println("TOUCHED:------------------onScroll");
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            System.out.println("TOUCHED:------------------onLongPress");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            System.out.println("TOUCHED:------------------onFling");
            return false;
        }
    };

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (currentScreen == SCREEN_WINDOW_FULLSCREEN || currentScreen == SCREEN_WINDOW_TINY) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        if (widthRatio != 0 && heightRatio != 0) {
            int specWidth = MeasureSpec.getSize(widthMeasureSpec);
            int specHeight = (int) ((specWidth * (float) heightRatio) / widthRatio);
            setMeasuredDimension(specWidth, specHeight);

            int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(specWidth, MeasureSpec.EXACTLY);
            int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(specHeight, MeasureSpec.EXACTLY);
            getChildAt(0).measure(childWidthMeasureSpec, childHeightMeasureSpec);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }

    /*@Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //return super.dispatchTouchEvent(ev);
        //return gestureDetector.onTouchEvent(ev);
        return true;
    }*/

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void setFullscreen(boolean fullscreen) {
        boolean mAutoFullscreen = fullscreen;
        Activity activity = (Activity) context;


        final View decorView = activity.getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        String systemUiMode;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //Have to use Sticky Immersive mode on Lollipop devices, otherwise when the Status Bar is shown
            //the YoutubePlayer will throw the YouTubePlayer.ErrorReason.UNAUTHORIZED_OVERLAY error and stop playback.
            //This doesn't happen on KitKat but since KitKat has Immersive mode, we'll use it there as well
            decorView.setSystemUiVisibility(HIDE_STATUS_BAR_FLAGS_IMMERSIVE);
            //systemUiMode = "IMMERSIVE";
        } else {
            //For Jelly Bean, things are a little different. Here we're faking Sticky Immersive mode. The user can swipe down
            //from the top of the screen at any time and unhide the Status Bar, and it'll never go away. So detect
            //when the fullscreen flag gets removed, and post a delayed runnable that will hide the system bar again
            //just like Sticky Immersive mode
            decorView.setSystemUiVisibility(HIDE_STATUS_BAR_FLAGS);
            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override
                public void onSystemUiVisibilityChange(int visibility) {
                    if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                        decorView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                decorView.setSystemUiVisibility(HIDE_STATUS_BAR_FLAGS);
                            }
                        }, HIDE_STATUS_BAR_DELAY_MILLIS);
                    }
                }
            });
            systemUiMode = "FAKED IMMERSIVE";
        }
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.
        /*ActionBar actionBar = activity.getActionBar();
        actionBar.hide();*/


        //activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //setRequestedOrientation(context, FULLSCREEN_ORIENTATION);
    }

    public static void setRequestedOrientation(Context context, int orientation) {
        if (getAppCompActivity(context) != null) {
            getAppCompActivity(context).setRequestedOrientation(orientation);
        } else {
            scanForActivity(context).setRequestedOrientation(orientation);
        }
    }

    public static AppCompatActivity getAppCompActivity(Context context) {
        if (context == null) return null;
        if (context instanceof AppCompatActivity) {
            return (AppCompatActivity) context;
        } else if (context instanceof ContextThemeWrapper) {
            return getAppCompActivity(((ContextThemeWrapper) context).getBaseContext());
        }
        return null;
    }

    public static Activity scanForActivity(Context context) {
        if (context == null) return null;

        if (context instanceof Activity) {
            return (Activity) context;
        } else if (context instanceof ContextWrapper) {
            return scanForActivity(((ContextWrapper) context).getBaseContext());
        }

        return null;
    }
}
