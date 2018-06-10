package com.rz.usagesexample;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.rz.wareadapter.SparkedArrayAdapter;
import com.rz.wareadapter.SparkedModelRowScope;
import com.rz.wareadapter.SparkedModelRowViewFields;

import java.util.ArrayList;
import java.util.HashMap;

public class ActSplash extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private ListView sysLstView;
    private SparkedArrayAdapter adapterListAdapter;
    private ArrayList<SparkedModelRowViewFields> rowViewFieldListItems = new ArrayList<SparkedModelRowViewFields>();
    private ArrayList<SparkedModelRowScope> modelDrawerMenuDataItems = new ArrayList<SparkedModelRowScope>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        activity = this;
        context = this;
        System.out.println("OPENED");
        sysLstView = (ListView) findViewById(R.id.sysLstView);
        //onSetRowViewField(SparkedArrayAdapter.FIELD_TYPE.TEXT_VIEW, "sysDrawerTitle");
        //onSetRowViewField(SparkedArrayAdapter.FIELD_TYPE.TEXT_VIEW, "sysDrawerDescription");
        //modelDrawerMenuDataItems.add(SparkedModelRowScope.onGetSetRow(argRowDataItems, (String) null, SparkedModelRowScope.LISTENER_TYPE.NONE));
        onSetDrawerMenuItems();
        adapterListAdapter = new SparkedArrayAdapter(context, R.layout.layout_list_row, modelDrawerMenuDataItems);
        rowViewFieldListItems.add(adapterListAdapter.onSetRowViewField(SparkedArrayAdapter.FIELD_TYPE.TEXT_VIEW, "sysDrawerTitle"));
        rowViewFieldListItems.add(adapterListAdapter.onSetRowViewField(SparkedArrayAdapter.FIELD_TYPE.TEXT_VIEW, "sysDrawerDescription"));
        adapterListAdapter.onSetRowViewFieldList(rowViewFieldListItems);
        sysLstView.setAdapter(adapterListAdapter);
        /*adapterListAdapter.onSetExternalListenerHandler(new SparkedArrayAdapter.OnExternalListenerHandler() {
            @Override
            public void onFileManage(View argView, String argValue) {
                System.out.println("EXTERNAL: " + argValue);
            }
        });*/
    }

    public void onSetDrawerMenuItems() {
        HashMap<String, String> eachRowDataItems = null;
        eachRowDataItems = new HashMap();
        eachRowDataItems.put("sysDrawerTitle", "Title-01");
        eachRowDataItems.put("sysDrawerDescription", "Description-01");
        //spinalRowDrawerDraw.spinalDrawerMenu.onSetItemData(eachRowDataItems, FirstFragment.class);
        modelDrawerMenuDataItems.add(SparkedModelRowScope.onGetSetRow(eachRowDataItems, (String) null, SparkedModelRowScope.LISTENER_TYPE.NONE));
        eachRowDataItems = new HashMap();
        eachRowDataItems.put("sysDrawerTitle", "Title-02");
        eachRowDataItems.put("sysDrawerDescription", "Description-02");
        //spinalRowDrawerDraw.spinalDrawerMenu.onSetItemData(eachRowDataItems, SecondFragment.class);
        modelDrawerMenuDataItems.add(SparkedModelRowScope.onGetSetRow(eachRowDataItems, (String) null, SparkedModelRowScope.LISTENER_TYPE.URL));
        //modelDrawerListItems = spinalRowDrawerDraw.spinalDrawerMenu.onGetDataList();
    }

    private void onSetAdapter() {
        adapterListAdapter.onSetRowViewFieldList(rowViewFieldListItems)
                .onSetRowViewFieldListenerHandler(new SparkedArrayAdapter.OnFieldListenerHandler() {
                    @Override
                    public void onSetFieldValue(ArrayList<SparkedModelRowViewFields> argRowViewFieldList, Object argObject) {
                        SparkedModelRowScope itemScope = (SparkedModelRowScope) argObject;
                        HashMap<String, String> hashMapRowIdValueItem = itemScope.getHashMapRowIdValueItems();
                        for (SparkedModelRowViewFields itemField : argRowViewFieldList) {
                            Object object = itemField.getFieldObject();
                            String fieldResourceId = itemField.getFieldResourceId();
                            if (object instanceof TextView) {
                                TextView rowField = null;
                                rowField = (TextView) itemField.getFieldObject();
                                if (hashMapRowIdValueItem.containsKey(fieldResourceId)) {
                                    rowField.setText(hashMapRowIdValueItem.get(fieldResourceId));
                                }
                                System.out.println(itemField.getFieldResourceId());
                            }
                            System.out.println("------" + fieldResourceId);
                        }
                    }
                });
    }

    private void onSetRowViewField(SparkedArrayAdapter.FIELD_TYPE argFieldType, String argFieldResourceId) {
        if (argFieldType == SparkedArrayAdapter.FIELD_TYPE.TEXT_VIEW) {
            TextView textView = new TextView(context);
            rowViewFieldListItems.add(SparkedModelRowViewFields.onGetSetModelRow(textView, argFieldResourceId));
        } else if (argFieldType == SparkedArrayAdapter.FIELD_TYPE.IMAGE_VIEW) {
            ImageView imageView = new ImageView(context);
            rowViewFieldListItems.add(SparkedModelRowViewFields.onGetSetModelRow(imageView, argFieldResourceId));
        }
    }
}
