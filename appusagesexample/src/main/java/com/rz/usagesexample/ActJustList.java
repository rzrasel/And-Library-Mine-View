package com.rz.usagesexample;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.rz.varnishview.spinallistdrawer.AdapterDynamicArrayAdapter;

import java.util.ArrayList;

public class ActJustList extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private ListView sysListViewList;
    private AdapterDynamicArrayAdapter adapterDynamicArrayAdapter;
    private ArrayList<ModelDataItem> modelDrawerItems = new ArrayList<ModelDataItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_just_list);
        activity = this;
        context = this;
        sysListViewList = (ListView) findViewById(R.id.sysListViewList);
        for (int count = 0; count < 40; count++) {
            String val = count + "";
            if (count < 10) {
                val = "0" + count;
            }
            modelDrawerItems.add(new ModelDataItem("List Title-" + val, "List Description-" + val));
        }
        adapterDynamicArrayAdapter = new AdapterDynamicArrayAdapter(context, R.layout.layout_navigation_drawer_row, modelDrawerItems);
        ArrayList<AdapterDynamicArrayAdapter.ModelRowViewHolder> rowFieldListItems = null;
        rowFieldListItems = new ArrayList<AdapterDynamicArrayAdapter.ModelRowViewHolder>();
        rowFieldListItems.add(adapterDynamicArrayAdapter.onGetSetModelRowViewData(new TextView(context), "sysDrawerTitle", ""));
        rowFieldListItems.add(adapterDynamicArrayAdapter.onGetSetModelRowViewData(new TextView(context), "sysDrawerDescription", ""));
        adapterDynamicArrayAdapter.onSetListRowViewFields(new AdapterDynamicArrayAdapter.OnFieldListenerHandler() {
            @Override
            public void onSetFieldValue(ArrayList<AdapterDynamicArrayAdapter.ModelRowViewHolder> argListRowViewFields, Object argObject) {
                ModelDataItem item = (ModelDataItem) argObject;
                TextView rowField = null;
                if (argListRowViewFields.size() > 0) {
                    rowField = (TextView) argListRowViewFields.get(0).getFieldObject();
                    rowField.setText(item.getTitle());
                    rowField = (TextView) argListRowViewFields.get(1).getFieldObject();
                    rowField.setText(item.getDescription());
                }
            }
        }, rowFieldListItems);
        sysListViewList.setAdapter(adapterDynamicArrayAdapter);
        sysListViewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> argAdapterView, View argView, int argPosition, long argId) {
                //System.out.println("Clicked on list item");
                //ViewParent nv = parent.getParent();
                /*ViewParent viewParent = argAdapterView.getParent();
                if (View.class.isInstance(viewParent)) {
                    View button = ((View) viewParent).findViewById(R.id.sysBtnTest);
                    if (button != null) {
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View argView) {
                                ((Button) argView).setText("Hi");
                            }
                        });
                    }
                }*/
                /*System.out.println("Clicked on list item: " + sysListViewList.getChildCount());
                System.out.println("Clicked on list item: " + argAdapterView.getChildCount());*/
                //View mayBeGroupView = sysListViewList.getChildAt(argPosition);
                View mayBeGroupView = getViewByPosition(sysListViewList, argPosition);
                if (mayBeGroupView instanceof ViewGroup) {
                    ViewGroup groupView = (ViewGroup) mayBeGroupView;
                    int secondChildCount = groupView.getChildCount();
                    for (int j = 0; j < secondChildCount; j++) {
                        View targetView = groupView.getChildAt(j);
                        if (targetView instanceof Button) {
                            System.out.println("Clicked on list item: " + j + " : " + secondChildCount);
                            ((Button) targetView).setText("Hi");
                        }
                        System.out.println("COUNTER: " + secondChildCount);
                    }
                }
                if (mayBeGroupView != null) {
                    for (int pos = 0; pos <= sysListViewList.getLastVisiblePosition() - sysListViewList.getFirstVisiblePosition(); pos++) {
                        View parentView = sysListViewList.getChildAt(pos);
                        parentView.setBackgroundColor(Color.WHITE);
                    }
                    mayBeGroupView.setBackgroundColor(Color.YELLOW);
                }
                System.out.println("POSITION: " + argPosition);
                /*int childCount = argAdapterView.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View mayBeGroupView = argAdapterView.getChildAt(i);
                    if (mayBeGroupView instanceof ViewGroup) {
                        ViewGroup groupView = (ViewGroup) mayBeGroupView;
                        int secondChildCount = groupView.getChildCount();
                        for (int j = 0; j < secondChildCount; j++) {
                            View targetView = argAdapterView.getChildAt(j);
                            if (targetView instanceof Button) {
                                ((Button) targetView).setText("Hi");
                            }
                        }
                        System.out.println("GROUP_VIEW: " + mayBeGroupView.getTag());
                        System.out.println("Clicked on list item: " + secondChildCount);
                    }
                    /-*System.out.println("TAG: " + mayBeGroupView.getTag());
                    System.out.println("TAG: " + mayBeGroupView.getId());
                    System.out.println("TAG: " + i);*//*
                }*/
                /*System.out.println("Clicked on list item");
                argAdapterView.getChildAt(argPosition).setBackgroundColor(Color.YELLOW);
                argAdapterView.setBackgroundColor(Color.parseColor("#ffffff"));*/
                /*বিধাতার এই পৃথিবী
                আমি তুমি সবাই বাস করি।
                তোমাদের রাত্তির ভেঙে ভোর ভেসে আসে*/

            }
        });
    }

    public View getViewByPosition(ListView argListView, int argPosition) {
        final int firstListItemPosition = argListView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + argListView.getChildCount() - 1;

        if (argPosition < firstListItemPosition || argPosition > lastListItemPosition) {
            return argListView.getAdapter().getView(argPosition, null, argListView);
        } else {
            final int childIndex = argPosition - firstListItemPosition;
            return argListView.getChildAt(childIndex);
        }
    }

    public class ModelDataItem {
        private String title;
        private String description;

        public ModelDataItem(String argTitle, String argDescription) {
            this.title = argTitle;
            this.description = argDescription;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String argTitle) {
            this.title = argTitle;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String argDescription) {
            this.description = argDescription;
        }

        /*public static ModelDataItem onGetSetRow(String argTitle, String argDescription) {
            return new ModelDataItem(argTitle, argDescription);
        }*/
    }
}
