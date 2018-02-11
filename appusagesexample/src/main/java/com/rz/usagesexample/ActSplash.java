package com.rz.usagesexample;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rz.varnishview.spinallistdrawer.AdapterDynamicArrayAdapter;
import com.rz.varnishview.spinallistdrawer.ModelDrawerList;
import com.rz.varnishview.spinallistdrawer.SpinalListDrawerDrawTemp01;
import com.rz.varnishview.spinallistdrawer.SpinalRowDrawerDraw;

import java.util.ArrayList;

public class ActSplash extends AppCompatActivity {
    private Activity activity;
    private Context context;
    //|------------------------------------------------------------|
    private Toolbar sysToolBar;
    private DrawerLayout sysDrawerLayout;
    private RelativeLayout sysIdDrawerContainer;
    private ListView sysDrawerList;

    private SpinalListDrawerDrawTemp01 spinalListDrawerDraw;
    private SpinalListDrawerDrawTemp01.OnSetupSpinalToolBar onSetupSpinalToolBar;
    private SpinalListDrawerDrawTemp01.OnSetupSpinalNavDrawer onSetupSpinalNavDrawer;
    //|------------------------------------------------------------|
    private AdapterDynamicArrayAdapter adapterDynamicArrayAdapter;
    private ArrayList<ModelDrawerList> modelDrawerItems = new ArrayList<ModelDrawerList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_drawer_two);
        //|------------------------------------------------------------|
        activity = this;
        context = this;
        //|------------------------------------------------------------|
        boolean shouldChangeStatusBarTintToDark = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            if (shouldChangeStatusBarTintToDark) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                // We want to change tint color to white again.
                // You can also record the flags in advance so that you can turn UI back completely if
                // you have set other flags before, such as translucent or full screen.
                decor.setSystemUiVisibility(0);
            }
        }
        //|------------------------------------------------------------|
        sysToolBar = (Toolbar) findViewById(R.id.sysToolBar);
        sysDrawerLayout = (DrawerLayout) findViewById(R.id.sysDrawerLayout);
        sysIdDrawerContainer = (RelativeLayout) findViewById(R.id.sysIdDrawerContainer);
        //|------------------------------------------------------------|
        LinearLayout idLinLayMainContainerView = (LinearLayout) findViewById(R.id.idLinLayMainContainerView);
        new DrawerListViewSetUp().onSetModelItems().onSetAdapter();
        //|------------------------------------------------------------|
        //|------------------------------------------------------------|
        spinalListDrawerDraw = new SpinalListDrawerDrawTemp01(activity, context);
        onSetupSpinalNavDrawer = spinalListDrawerDraw.new OnSetupSpinalNavDrawer();
        onSetupSpinalToolBar = spinalListDrawerDraw.new OnSetupSpinalToolBar();
        spinalListDrawerDraw.setToolBar(sysToolBar)
                .setDrawerLayout(sysDrawerLayout)
                .setDrawerContainerLayout(sysIdDrawerContainer)
                .setDrawerListView(sysDrawerList)
                .setMainContLinearLayout(idLinLayMainContainerView);
        onSetupSpinalToolBar.onHideActionBar()
                .onSetActionBar()
                .onShowHomeButton()
                .onSetTitleText("Spinal Drawer")
                .onSetTitleTextColor("#ffffff")
                .onSetBackgroundColor("#80000000")
                .onSetElevation(0);
        onSetupSpinalNavDrawer.onConfigureDrawer();
        //|------------------------------------------------------------|
        sysDrawerList.setOnItemClickListener(new OnNavigationDrawerClickListener());
        //|------------------------------------------------------------|
        //|------------------------------------------------------------|
        if (savedInstanceState == null) {
            // on first time display view for first nav item
            DisplayView(0);
        }
        //adapterLstDrawer.setSelectedPosition(0, true, colorPrimaryDark);
        //|------------------------------------------------------------|
        Intent intentRedirectWindow = new Intent(context, ActSpinalRowDrawerDraw.class);
        startActivity(intentRedirectWindow);
        startActivity(new Intent(context, ActJustList.class));
        //|------------------------------------------------------------|
    }

    //|------------------------------------------------------------|
    private class OnNavigationDrawerClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> argParent, View argView, int argPosition, long argId) {
            Toast.makeText(context, "NAVIGATION_DRAWER_POSITION: " + argPosition, Toast.LENGTH_LONG).show();
            sysDrawerList.setItemChecked(argPosition, true);
            sysDrawerLayout.closeDrawer(sysIdDrawerContainer);
            //for (int pos = 0; pos < adapterDynamicArray.getCount(); pos++) {
            for (int pos = 0; pos <= sysDrawerList.getLastVisiblePosition() - sysDrawerList.getFirstVisiblePosition(); pos++) {
                //sysDrawerList.getChildAt(i).setBackgroundColor(Color.BLUE);
                View parentView = null;
                //View parentView = adapterDynamicArray.getView(pos, null, sysDrawerList);
                //sysDrawerList.getItemAtPosition(pos);
                parentView = sysDrawerList.getChildAt(pos);
                parentView.setBackgroundColor(Color.BLUE);
                //parentView.getCh
                //adapterDynamicArray.notifyDataSetChanged();
            }
            argParent.getChildAt(argPosition).setBackgroundColor(Color.YELLOW);
            /*DisplayView(position);
            adapterLstDrawer.setSelectedPosition(position - 0, true, colorPrimaryDark);*/
            adapterDynamicArrayAdapter.notifyDataSetChanged();
        }
    }

    public class DrawerListViewSetUp {
        private ArrayList<AdapterDynamicArrayAdapter.ModelRowViewHolder> rowFieldListItems = null;

        private void onClassTest(Class<?> argClass) {
            Class cls = argClass;
            try {
                Object object = cls.newInstance();
                /*Constructor<?> ctor = argClass.getConstructor(cls);
                Object object = ctor.newInstance();*/
                //TestClassReflection testClass = (TestClassReflection) cls.newInstance();
                TestClassReflection testClass = (TestClassReflection) object;
                testClass.print();
            } catch (InstantiationException e) {
                //e.printStackTrace();
                System.out.println("ERROR_InstantiationException: " + e.getMessage());
            } catch (IllegalAccessException e) {
                //e.printStackTrace();
                System.out.println("ERROR_IllegalAccessException: " + e.getMessage());
            }
        }

        public DrawerListViewSetUp() {
            sysDrawerList = (ListView) findViewById(R.id.sysDrawerList);
            //|------------------------------------------------------------|
            onClassTest(TestClassReflection.class);
            /*Class<?> clss = DrawerListViewSetUp.class;
            Class cls = DrawerListViewSetUp.class;
            //Object object = (Object) DrawerListViewSetUp.class.newInstance();
            //Object object = (Object) DrawerListViewSetUp.class.getConstructors();
            Object object = cls.getConstructors();
            DrawerListViewSetUp drawerListViewSetUp;
            drawerListViewSetUp = (DrawerListViewSetUp) object;
            drawerListViewSetUp.onSetModelItems().onSetAdapter();
            Class<?> clazz = Class.forName(DrawerListViewSetUp.class);
            Constructor<?> ctor = clazz.getConstructor(DrawerListViewSetUp.class);
            Object object = ctor.newInstance(new Object[] { ctorArgument });*/
            //|------------------------------------------------------------|
        }

        public DrawerListViewSetUp onSetModelItems() {
            for (int count = 0; count < 40; count++) {
                String val = count + "";
                if (count < 10) {
                    val = "0" + count;
                }
                modelDrawerItems.add(ModelDrawerList.onGetSetRow("Title-" + val, "Description-" + val));
            }
            return this;
        }

        public DrawerListViewSetUp onSetAdapter() {
            adapterDynamicArrayAdapter = new AdapterDynamicArrayAdapter(context, R.layout.layout_navigation_drawer_row, modelDrawerItems);
            setListAdapterRow();
            setFieldListenerHandler();
            sysDrawerList.setAdapter(adapterDynamicArrayAdapter);
            return this;
        }

        private void setListAdapterRow() {
            //ArrayList<AdapterDynamicArrayAdapter.ModelRowViewHolder> listRowViewFields = null;
            rowFieldListItems = new ArrayList<AdapterDynamicArrayAdapter.ModelRowViewHolder>();
            rowFieldListItems.add(adapterDynamicArrayAdapter.onGetSetModelRowViewData(new TextView(context), "sysDrawerTitle", ""));
            rowFieldListItems.add(adapterDynamicArrayAdapter.onGetSetModelRowViewData(new TextView(context), "sysDrawerDescription", ""));
        }

        private void setFieldListenerHandler() {
            adapterDynamicArrayAdapter.onSetListRowViewFields(new AdapterDynamicArrayAdapter.OnFieldListenerHandler() {
                @Override
                public void onSetFieldValue(ArrayList<AdapterDynamicArrayAdapter.ModelRowViewHolder> argListRowViewFields, Object argObject) {
                    ModelDrawerList item = (ModelDrawerList) argObject;
                    TextView rowField = null;
                    if (argListRowViewFields.size() > 0) {
                        rowField = (TextView) argListRowViewFields.get(0).getFieldObject();
                        rowField.setText(item.getTitle());
                        rowField = (TextView) argListRowViewFields.get(1).getFieldObject();
                        rowField.setText(item.getDescription());
                    }
                }
            }, rowFieldListItems);
        }
    }

    //|------------------------------------------------------------|
    @Override
    public boolean onOptionsItemSelected(MenuItem argMenuItem) {
        switch (argMenuItem.getItemId()) {
            case android.R.id.home:
                onSetupSpinalNavDrawer.onOptionsItemSelected(argMenuItem);
                return true;
            default:
                return super.onOptionsItemSelected(argMenuItem);
        }
        //System.out.println("----------------------");
        //return super.onOptionsItemSelected(argMenuItem);
    }

    //|------------------------------------------------------------|
    @Override
    public void onConfigurationChanged(Configuration argNewConfig) {
        super.onConfigurationChanged(argNewConfig);
        onSetupSpinalNavDrawer.onConfigurationChanged(argNewConfig);
    }

    //|------------------------------------------------------------|
    private void DisplayView(int position) {
        //FragmentManager fragmentManager = getFragmentManager();
        //fragmentEventListener;
        String toolbarTitle = "CMDSS";
        Fragment fragment = null;
        fragment = new FragHome();
        Bundle bundle = null;
        //fragment = new FragTest();
        if (fragment != null) {
            //FragmentManager fragmentManager = getFragmentManager();
            //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //FragmentManager fragmentManager = getFragmentManager();
            //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            //fragmentTransaction.replace(R.id.frame_container, Fragment.instantiate(ActSplash.this, fragment));
            FragmentTransaction fragmentTransaction = activity.getFragmentManager().beginTransaction();
            if (bundle == null) {
                bundle = new Bundle();
            }
            //bundle.putSerializable(APPConstants.SESSION.KEY, userSession);
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.sysFrameContainer, fragment);
            fragmentTransaction.commit();

            // update selected item and title, then close the drawer
            sysDrawerList.setItemChecked(position, true);
            sysDrawerList.setSelection(position);
            setTitle(toolbarTitle);
        } else {
            // error in creating fragment
            Log.e("Dashboard", "Error in creating fragment");
        }
        sysDrawerList.setItemChecked(position, true);
        sysDrawerLayout.closeDrawer(sysIdDrawerContainer);
    }

    //|------------------------------------------------------------|
    //|------------------------------------------------------------|
}
