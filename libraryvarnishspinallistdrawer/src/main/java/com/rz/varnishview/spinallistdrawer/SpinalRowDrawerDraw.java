package com.rz.varnishview.spinallistdrawer;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.rz.varnishview.spinallistdrawer.adapter.SharkArrayAdapter;
import com.rz.varnishview.spinallistdrawer.adapter.SharkModelRowScope;
import com.rz.varnishview.spinallistdrawer.adapter.SharkModelRowViewFields;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rasel on 2017-12-26.
 */

public class SpinalRowDrawerDraw {
    //|------------------------------------------------------------|
    private Activity activity;
    private Context context;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ViewGroup frameLayoutParent;
    private int gravity;
    public SpinalToolBar spinalToolBar = new SpinalToolBar();
    public SpinalDrawerMenu spinalDrawerMenu = new SpinalDrawerMenu();

    //|------------------------------------------------------------|
    public SpinalRowDrawerDraw(Activity argActivity, Context argContext) {
        this.activity = argActivity;
        this.context = argContext;
    }

    //|------------------------------------------------------------|
    public SpinalRowDrawerDraw onSetFrameLayoutParent(ViewGroup argFrameLayoutParent) {
        frameLayoutParent = argFrameLayoutParent;
        return this;
    }

    public SpinalRowDrawerDraw onSetGravity(int argGravity) {
        gravity = argGravity;
        return this;
    }

    //|------------------------------------------------------------|
    public SpinalRowDrawerDraw onConfigureDrawer() {
        //|------------------------------------------------------------|
        int drawerOpenStringId;
        int drawerCloseStringId;
        drawerOpenStringId = R.string.spinal_list_nav_drawer_open;
        drawerCloseStringId = R.string.spinal_list_nav_drawer_close;
        actionBarDrawerToggle = new ActionBarDrawerToggle(activity, spinalDrawerMenu.drawerLayout, drawerOpenStringId, drawerCloseStringId) {
            /* Called when drawer is closed */
            public void onDrawerClosed(View view) {
                //Put your code here
                activity.invalidateOptionsMenu();
                //supportInvalidateOptionsMenu();
            }

            /* Called when a drawer is opened */
            public void onDrawerOpened(View drawerView) {
                //Put your code here
                activity.invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View argDrawerView, float argSlideOffset) {
                super.onDrawerSlide(argDrawerView, argSlideOffset);
                    /*mainView.setTranslationX(slideOffset * drawerView.getWidth());
                    mDrawerLayout.bringChildToFront(drawerView);
                    mDrawerLayout.requestLayout();*/
                //
                /*if (frameLayoutParent != null) {
                    frameLayoutParent.setTranslationX(argSlideOffset * argDrawerView.getWidth());
                    spinalDrawerMenu.drawerContainerLayout.bringChildToFront(argDrawerView);
                    spinalDrawerMenu.drawerContainerLayout.requestLayout();
                }*/
                if (frameLayoutParent != null) {
                    if (gravity == Gravity.RIGHT) {
                        frameLayoutParent.setTranslationX(-1 * argSlideOffset * argDrawerView.getWidth());
                        spinalDrawerMenu.drawerContainerLayout.bringChildToFront(argDrawerView);
                        spinalDrawerMenu.drawerContainerLayout.requestLayout();
                    } else {
                        frameLayoutParent.setTranslationX(argSlideOffset * argDrawerView.getWidth());
                        spinalDrawerMenu.drawerContainerLayout.bringChildToFront(argDrawerView);
                        spinalDrawerMenu.drawerContainerLayout.requestLayout();
                    }
                }
            }
        };
            /*sysToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sysDrawerLayout.openDrawer(GravityCompat.START);
                }
            });*/
        actionBarDrawerToggle.syncState();
        spinalDrawerMenu.drawerLayout.addDrawerListener(actionBarDrawerToggle);
        //|------------------------------------------------------------|
        return this;
    }

    public boolean onOptionsItemSelected(MenuItem argMenuItem) {
        if (gravity == Gravity.RIGHT) {
            if (spinalDrawerMenu.drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                spinalDrawerMenu.drawerLayout.closeDrawer(Gravity.RIGHT);
            } else {
                spinalDrawerMenu.drawerLayout.openDrawer(Gravity.RIGHT);
            }
        } else {
            if (actionBarDrawerToggle.onOptionsItemSelected(argMenuItem)) {
                return true;
            }
        }
        /*switch (argMenuItem.getItemId()) {
            case android.R.id.home:
                sysDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }*/
        /*switch (argMenuItem.getItemId()) {
            case android.R.id.home:
                if (sysDrawerLayout.isDrawerOpen(drawerList)) {
                    sysDrawerLayout.closeDrawer(drawerList);
                } else {
                    sysDrawerLayout.openDrawer(drawerList);
                }
                return true;
            default:
                return super.onOptionsItemSelected(argMenuItem);
        }*/
        return false;
    }

    public void onConfigurationChanged(Configuration argNewConfig) {
        actionBarDrawerToggle.onConfigurationChanged(argNewConfig);
    }

    public boolean onSetBackPressed() {
        if (spinalDrawerMenu.drawerLayout.isDrawerOpen(spinalDrawerMenu.drawerContainerLayout)) {
            spinalDrawerMenu.drawerLayout.closeDrawer(spinalDrawerMenu.drawerContainerLayout);
            return true;
        }
        /*if (spinalDrawerMenu.drawerLayout.isDrawerOpen(GravityCompat.END)) {
            spinalDrawerMenu.drawerLayout.closeDrawer(GravityCompat.END);
            System.out.println("");
            return true;
        }*/
        return false;
    }

    //|------------------------------------------------------------|
    //|------------------------------------------------------------|
    public class SpinalToolBar {
        /*
        //|USAGES: SpinalRowDrawerDraw.SpinalToolBar spinalToolBar = spinalRowDrawerDraw.new SpinalToolBar();
        */
        private Toolbar spinalToolBar;
        private ActionBar actionBar;

        public SpinalToolBar initToolBar(Toolbar argSpinalToolBar) {
            this.spinalToolBar = argSpinalToolBar;
            return this;
        }

        public SpinalToolBar onHideActionBar() {
            ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
            if (actionBar != null)
                actionBar.hide();
            return this;
        }

        public SpinalToolBar onSetActionBar() {
            if (spinalToolBar != null) {
                ((AppCompatActivity) activity).setSupportActionBar(spinalToolBar);
            }
            actionBar = ((AppCompatActivity) activity).getSupportActionBar();
            return this;
        }

        public SpinalToolBar onSetTitleText(String argTitle) {
            /*if (spinalToolBar != null) {
                spinalToolBar.setTitle(argTitle);
            } else {
                ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setTitle(argTitle);
                }
            }*/
            if (actionBar != null) {
                actionBar.setTitle(argTitle);
            }
            return this;
        }

        public SpinalToolBar onSetTitleTextColor(String argHtmlColorCode) {
            //"#ffffff"
            //spinalToolBar.setTitleTextColor(Color.parseColor(argHtmlColorCode));
            if (spinalToolBar != null) {
                spinalToolBar.setTitleTextColor(Color.parseColor(argHtmlColorCode));
            }
            return this;
        }

        public SpinalToolBar onSetSubTitleText(String argSubTitle) {
            /*if (spinalToolBar != null) {
                //spinalToolBar.setTitle(argTitle);
                spinalToolBar.setSubtitle(argSubTitle);
            } else {
                ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
                if (actionBar != null) {
                    //actionBar.setTitle(argTitle);
                    actionBar.setSubtitle(argSubTitle);
                }
            }*/
            if (actionBar != null) {
                //actionBar.setTitle(argTitle);
                actionBar.setSubtitle(argSubTitle);
            }
            return this;
        }

        public SpinalToolBar onSetSubTitleTextColor(String argHtmlColorCode) {
            //"#ffffff"
            if (spinalToolBar != null) {
                spinalToolBar.setSubtitleTextColor(Color.parseColor(argHtmlColorCode));
            }
            return this;
        }

        public SpinalToolBar onShowHomeButton() {
            /*((AppCompatActivity) activity).getSupportActionBar().setHomeButtonEnabled(true);
            ((AppCompatActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
            if (actionBar != null) {
                actionBar.setHomeButtonEnabled(true);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
            return this;
        }

        public SpinalToolBar onSetBackgroundColor(String argHtmlColorCode) {
            //"#80000000"
            ((AppCompatActivity) activity).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(argHtmlColorCode)));
            return this;
        }

        public SpinalToolBar onSetElevation(int argElevation) {
            //0
            ((AppCompatActivity) activity).getSupportActionBar().setElevation(argElevation);
            return this;
        }

        public SpinalToolBar onSetStatusBarDark(boolean argIsDark) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                View decor = activity.getWindow().getDecorView();
                if (!argIsDark) {
                    decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                } else {
                    // We want to change tint color to white again.
                    // You can also record the flags in advance so that you can turn UI back completely if
                    // you have set other flags before, such as translucent or full screen.
                    decor.setSystemUiVisibility(0);
                }
            }
            return this;
        }

        public SpinalToolBar onSetNavigationOnClickListener(View.OnClickListener argOnClickListener) {
            if (spinalToolBar != null && argOnClickListener != null) {
                spinalToolBar.setNavigationOnClickListener(argOnClickListener);
                //System.out.println("DEBUG");
            }
            return this;
        }

        public SpinalToolBar onSetTitleFont(String argAssetsFontPath) {
            if (argAssetsFontPath.isEmpty()) {
                return this;
            }
            if (spinalToolBar != null) {
                TextView titleTextView = null;

                try {
                    Typeface typeface = Typeface.createFromAsset(activity.getAssets(), argAssetsFontPath);
                    Field field = spinalToolBar.getClass().getDeclaredField("mTitleTextView");
                    field.setAccessible(true);
                    titleTextView = (TextView) field.get(spinalToolBar);
                    //titleTextView.setText("titleTextView");
                    titleTextView.setTypeface(typeface);
                } catch (NoSuchFieldException e) {
                } catch (IllegalAccessException e) {
                }
            }
            return this;
        }

        public SpinalToolBar onSetSubTitleFont(String argAssetsFontPath) {
            if (argAssetsFontPath.isEmpty()) {
                return this;
            }
            if (spinalToolBar != null) {
                TextView titleTextView = null;

                try {
                    Typeface typeface = Typeface.createFromAsset(activity.getAssets(), argAssetsFontPath);
                    Field field = spinalToolBar.getClass().getDeclaredField("mSubtitleTextView");
                    field.setAccessible(true);
                    titleTextView = (TextView) field.get(spinalToolBar);
                    //titleTextView.setText("titleTextView");
                    titleTextView.setTypeface(typeface);
                } catch (NoSuchFieldException e) {
                } catch (IllegalAccessException e) {
                }
            }
            return this;
        }
    }

    //|------------------------------------------------------------|
    public class SpinalDrawerMenu {
        /*
        //|USAGES: SpinalRowDrawerDraw.SpinalDrawerMenu spinalDrawerMenu = spinalRowDrawerDraw.new SpinalDrawerMenu();
        */
        private ArrayList<SharkModelRowScope> modelDrawerMenuDataItems = new ArrayList<SharkModelRowScope>();
        private ArrayList<SharkModelRowViewFields> rowViewFieldListItems = new ArrayList<SharkModelRowViewFields>();
        private DrawerLayout drawerLayout;
        private ViewGroup drawerContainerLayout;
        private int frameLayResourceId;

        public SpinalDrawerMenu onSetItemData(HashMap<String, String> argRowDataItems) {
            modelDrawerMenuDataItems.add(SharkModelRowScope.onGetSetRow(argRowDataItems, (String) null, SharkModelRowScope.LISTENER_TYPE.NONE));
            return this;
        }

        public SpinalDrawerMenu onSetItemData(HashMap<String, String> argRowDataItems, Class argListenerClass) {
            modelDrawerMenuDataItems.add(SharkModelRowScope.onGetSetRow(argRowDataItems, argListenerClass, SharkModelRowScope.LISTENER_TYPE.CLASS));
            return this;
        }

        public SpinalDrawerMenu onSetItemData(HashMap<String, String> argRowDataItems, String argListenerValue) {
            modelDrawerMenuDataItems.add(SharkModelRowScope.onGetSetRow(argRowDataItems, argListenerValue, SharkModelRowScope.LISTENER_TYPE.STRING));
            return this;
        }

        public ArrayList<SharkModelRowScope> onGetDataList() {
            return modelDrawerMenuDataItems;
        }

        public SpinalDrawerMenu onSetRowViewField(FIELD_TYPE argFieldType, String argFieldResourceId) {
            if (argFieldType == FIELD_TYPE.TEXT_VIEW) {
                TextView textView = new TextView(context);
                rowViewFieldListItems.add(SharkModelRowViewFields.onGetSetModelRow(textView, argFieldResourceId));
            } else if (argFieldType == FIELD_TYPE.IMAGE_VIEW) {
                ImageView imageView = new ImageView(context);
                rowViewFieldListItems.add(SharkModelRowViewFields.onGetSetModelRow(imageView, argFieldResourceId));
            }
            return this;
        }

        public ArrayList<SharkModelRowViewFields> onGetRowViewFieldsList() {
            return rowViewFieldListItems;
        }

        public SpinalDrawerMenu onSetAdapterListener(SharkArrayAdapter argSharkArrayAdapter) {
            argSharkArrayAdapter.onSetRowViewFieldList(rowViewFieldListItems)
                    .onSetRowViewFieldListenerHandler(onFieldListenerHandler);
            return this;
        }

        public SpinalDrawerMenu onSetDrawerLayout(DrawerLayout argDrawerLayout, ViewGroup argDrawerContainerLayout, int argFrameLayResourceId) {
            drawerLayout = argDrawerLayout;
            drawerContainerLayout = argDrawerContainerLayout;
            frameLayResourceId = argFrameLayResourceId;
            return this;
        }

        public SpinalDrawerMenu onSetDefaultDrawerLayout(int argPosition) {
            onDisplayView(argPosition);
            return this;
        }


        SharkArrayAdapter.OnFieldListenerHandler onFieldListenerHandler = new SharkArrayAdapter.OnFieldListenerHandler() {
            @Override
            public void onSetFieldValue(ArrayList<SharkModelRowViewFields> argRowViewFieldList, Object argObject) {
                SharkModelRowScope itemScope = (SharkModelRowScope) argObject;
                HashMap<String, String> hashMapRowIdValueItem = itemScope.getHashMapRowIdValueItems();
                for (SharkModelRowViewFields itemField : argRowViewFieldList) {
                    Object object = itemField.getFieldObject();
                    String fieldResourceId = itemField.getFieldResourceId();
                    if (object instanceof TextView) {
                        TextView rowField = null;
                        rowField = (TextView) itemField.getFieldObject();
                        if (hashMapRowIdValueItem.containsKey(fieldResourceId)) {
                            rowField.setText(hashMapRowIdValueItem.get(fieldResourceId));
                        }
                        System.out.println(itemField.getFieldResourceId());
                    } else if (object instanceof ImageView) {
                        ImageView rowField = null;
                        rowField = (ImageView) itemField.getFieldObject();
                        if (hashMapRowIdValueItem.containsKey(fieldResourceId)) {
                            int resourceId = Integer.valueOf(hashMapRowIdValueItem.get(fieldResourceId));
                            rowField.setImageDrawable(context.getResources().getDrawable(resourceId));
                        }
                        System.out.println(itemField.getFieldResourceId());
                    }
                }
            }
        };

        public void onSetDrawerItemClickListener(ListView argDrawerListView) {
            argDrawerListView.setOnItemClickListener(new OnDrawerItemClickListener());
        }

        private class OnDrawerItemClickListener implements ListView.OnItemClickListener {
            @Override
            public void onItemClick(AdapterView<?> argParent, View argView, int argPosition, long argId) {
                //System.out.println("Position: " + modelDrawerMenuDataItems.get(argPosition).getHashMapRowIdValueItems().get("sysDrawerTitle"));
                onDisplayView(argPosition);
            }
        }

        private void onDisplayView(int argPosition) {
            if (modelDrawerMenuDataItems.size() <= 0) {
                return;
            }
            Fragment fragment = null;
            SharkModelRowScope sharkModelRowScope = modelDrawerMenuDataItems.get(argPosition);
            Class fragmentClass = sharkModelRowScope.getListenerClass();
            try {
                fragment = (Fragment) fragmentClass.newInstance();
                FragmentTransaction fragmentTransaction = activity.getFragmentManager().beginTransaction();
                fragmentTransaction.replace(frameLayResourceId, fragment);
                fragmentTransaction.commit();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            drawerLayout.closeDrawer(drawerContainerLayout);
            activity.setTitle("Changed");
        }

        private void onDisplayView(Fragment argFragment) {
        }
    }

    //|------------------------------------------------------------|
    //|------------------------------------------------------------|
    public enum FIELD_TYPE {
        TEXT_VIEW("text_view"),
        IMAGE_VIEW("image_view");
        private String fieldType;

        FIELD_TYPE(String argFieldType) {
            this.fieldType = argFieldType;
        }

        public String getFieldType() {
            return this.fieldType;
        }
    }
    //|------------------------------------------------------------|
    //|------------------------------------------------------------|
}
/*
Bundle Callback Interface
*/
