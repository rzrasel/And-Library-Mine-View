package com.rz.varnishview.spinallistdrawer;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * Created by Rasel on 2017-12-21.
 */

public class SpinalListDrawerDrawTemp01 {
    //|------------------------------------------------------------|
    private Activity activity;
    private Context context;
    //|------------------------------------------------------------|
    private Toolbar spinalToolBar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout spinalDrawerLayout;
    private RelativeLayout spinalDrawerContainerLayout;
    private ListView spinalDrawerContainerView;
    private ListView spinalDrawerList;
    private LinearLayout linearLayoutMainContainerView;

    //|------------------------------------------------------------|
    public SpinalListDrawerDrawTemp01(Activity argActivity, Context argContext) {
        this.activity = argActivity;
        this.context = argContext;
    }

    //|------------------------------------------------------------|
    public SpinalListDrawerDrawTemp01 setToolBar(Toolbar argSpinalToolBar) {
        spinalToolBar = argSpinalToolBar;
        return this;
    }

    public SpinalListDrawerDrawTemp01 setDrawerLayout(DrawerLayout argSpinalDrawerLayout) {
        spinalDrawerLayout = argSpinalDrawerLayout;
        return this;
    }

    public SpinalListDrawerDrawTemp01 setDrawerContainerLayout(RelativeLayout argSpinalDrawerContainerLayout) {
        spinalDrawerContainerLayout = argSpinalDrawerContainerLayout;
        return this;
    }

    public SpinalListDrawerDrawTemp01 setDrawerContainerLayout(ListView argSpinalDrawerContainerView) {
        spinalDrawerContainerView = argSpinalDrawerContainerView;
        return this;
    }

    public SpinalListDrawerDrawTemp01 setDrawerListView(ListView argSpinalDrawerList) {
        spinalDrawerList = argSpinalDrawerList;
        return this;
    }

    public SpinalListDrawerDrawTemp01 setMainContLinearLayout(LinearLayout argLinearLayoutMainContainerView) {
        this.linearLayoutMainContainerView = argLinearLayoutMainContainerView;
        return this;
    }

    //|------------------------------------------------------------|
    public class OnSetupSpinalToolBar {
        public OnSetupSpinalToolBar() {
            //|------------------------------------------------------------ |
            /*ActionBar actionBar = getSupportActionBar();
            if (actionBar != null)
                actionBar.hide();
            if (argSysToolBar != null) {
                setSupportActionBar(argSysToolBar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }*/
            //|------------------------------------------------------------ |
            /*ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
            if (actionBar != null)
                actionBar.hide();
            sysToolBar = argSysToolBar;
            //sysToolBar = (Toolbar) findViewById(R.id.sysToolBar);
            //sysToolBar.setTitleTextColor(Color.parseColor("#ffffff"));
            setSupportActionBar(sysToolBar);*/
            /*((AppCompatActivity) activity).getSupportActionBar().setHomeButtonEnabled(true);
            ((AppCompatActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) activity).getSupportActionBar().setElevation(0);
            ((AppCompatActivity) activity).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#80000000")));*/
            //|------------------------------------------------------------|
        }

        public OnSetupSpinalToolBar onHideActionBar() {
            ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
            if (actionBar != null)
                actionBar.hide();
            return this;
        }

        public OnSetupSpinalToolBar onSetActionBar() {
            ((AppCompatActivity) activity).setSupportActionBar(spinalToolBar);
            return this;
        }

        public OnSetupSpinalToolBar onSetTitleTextColor(String argHtmlColorCode) {
            //"#ffffff"
            spinalToolBar.setTitleTextColor(Color.parseColor(argHtmlColorCode));
            return this;
        }

        public OnSetupSpinalToolBar onSetTitleText(String argTitle) {
            if (spinalToolBar != null) {
                spinalToolBar.setTitle(argTitle);
            } else {
                ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setTitle(argTitle);
                }
            }
            return this;
        }

        public OnSetupSpinalToolBar onSetTitleText(String argTitle, String argSubTitle) {
            if (spinalToolBar != null) {
                spinalToolBar.setTitle(argTitle);
                spinalToolBar.setSubtitle(argSubTitle);
            } else {
                ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setTitle(argTitle);
                    actionBar.setSubtitle(argSubTitle);
                }
            }
            return this;
        }

        public OnSetupSpinalToolBar onShowHomeButton() {
            ((AppCompatActivity) activity).getSupportActionBar().setHomeButtonEnabled(true);
            ((AppCompatActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            return this;
        }

        public OnSetupSpinalToolBar onSetBackgroundColor(String argHtmlColorCode) {
            //"#80000000"
            ((AppCompatActivity) activity).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(argHtmlColorCode)));
            return this;
        }

        public OnSetupSpinalToolBar onSetElevation(int argElevation) {
            //0
            ((AppCompatActivity) activity).getSupportActionBar().setElevation(argElevation);
            return this;
        }
    }
    //|------------------------------------------------------------|

    public class OnSetupSpinalNavDrawer {

        //|------------------------------------------------------------|
        /*public OnSetupSpinalNavDrawer onConfigureToolBar() {
            //|------------------------------------------------------------ |
            return this;
        }*/

        public OnSetupSpinalNavDrawer onConfigureDrawer() {
            //|------------------------------------------------------------|
            int drawerOpenStringId;
            int drawerCloseStringId;
            drawerOpenStringId = R.string.spinal_list_nav_drawer_open;
            drawerCloseStringId = R.string.spinal_list_nav_drawer_close;
            actionBarDrawerToggle = new ActionBarDrawerToggle(activity, spinalDrawerLayout, drawerOpenStringId, drawerCloseStringId) {
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
                    linearLayoutMainContainerView.setTranslationX(argSlideOffset * argDrawerView.getWidth());
                    spinalDrawerContainerLayout.bringChildToFront(argDrawerView);
                    spinalDrawerContainerLayout.requestLayout();
                }
            };
            /*sysToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sysDrawerLayout.openDrawer(GravityCompat.START);
                }
            });*/
            actionBarDrawerToggle.syncState();
            spinalDrawerLayout.addDrawerListener(actionBarDrawerToggle);
            //|------------------------------------------------------------|
            return this;
        }

        public void onPostCreate() {
            actionBarDrawerToggle.syncState();
        }

        public boolean onOptionsItemSelected(MenuItem argMenuItem) {
            if (actionBarDrawerToggle.onOptionsItemSelected(argMenuItem)) {
                return true;
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
        /*public void onCreate(int argLayoutResourceId) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rootParentLayout = layoutInflater.inflate(argLayoutResourceId, null, false);
            sysTvRowTitle = (TextView) rootParentLayout.findViewById(R.id.sysTvRowTitle);
            sysTvRowTitle.setText("Test_TEXT");
        }*/
    }

    public class OnSetupSpinalDrawerItems {
        /*public class OnNavigationDrawerClickListener implements ListView.OnItemClickListener {
            @Override
            public void onItemClick(AdapterView<?> argParent, View argView, int argPosition, long argId) {
                //Toast.makeText(context, "NAVIGATION_DRAWER_POSITION: " + argPosition, Toast.LENGTH_LONG).show();
                sysDrawerList.setItemChecked(argPosition, true);
                sysDrawerLayout.closeDrawer(sysIdDrawerContainer);
                for (int i = 0; i < sysDrawerList.getAdapter().getCount(); i++) {
                    sysDrawerList.getChildAt(i).setBackgroundColor(Color.BLUE);
                }
                argParent.getChildAt(argPosition).setBackgroundColor(Color.YELLOW);
                *//*DisplayView(position);
                adapterLstDrawer.setSelectedPosition(position - 0, true, colorPrimaryDark);*//*
                adapterDynamicArray.notifyDataSetChanged();
            }
        }*/
    }

    public class OnSlidingPushActivity {
    }
}
/*
Legend
SpinalRowDrawerDraw - Main
Model Drawer Items
- Abstract Class
-- Class Name (Class.class Variable)
-- Class Name Getter Setter
* ToolBar
    *) ToolBar
* Drawer Menu
    *) Navigation Drawer
    *) Drawer Menu Layout - Row Resource Id, Row View Model
        * Row Items
    *) Drawer Menu Items Click Handler -
* Sliding Push Activity
    *) Sliding Push Activity
slide activity push activity
https://stackoverflow.com/questions/19577630/push-activity-on-the-right-when-open-drawer
https://medium.com/@janishar.ali/navigation-drawer-android-example-8dfe38c66f59
https://www.journaldev.com/9958/android-navigation-drawer-example-tutorial
*/
