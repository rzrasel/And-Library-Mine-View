package com.rz.armorpagination;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Rz Rasel on 2018-02-18.
 */

public class Utils {
    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param argDp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param argContext Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float dpToPixel(Context argContext, float argDp) {
        Resources resources = argContext.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = argDp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param argPx      A value in px (pixels) unit. Which we need to convert into db
     * @param argContext Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float pixelsToDp(Context argContext, float argPx) {
        Resources resources = argContext.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = argPx / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    //----------------------------------------------------------------------------------------------
//  public static double ScWth(Context context)
//----------------------------------------------------------------------------------------------
    //return the screen width of the device
    public static double ScWth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        return display.getWidth();
    }

    //----------------------------------------------------------------------------------------------
//  public static double ScHgt(Context context)
//----------------------------------------------------------------------------------------------
    //return the screen height of the device
    public static double ScHgt(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        return display.getHeight();
    }
}
