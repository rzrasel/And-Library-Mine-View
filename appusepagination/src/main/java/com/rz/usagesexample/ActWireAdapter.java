package com.rz.usagesexample;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.rz.armorpagination.NoneScrollListView;
import com.rz.wareadapter.SparkedArrayAdapter;
import com.rz.wareadapter.SparkedModelRowScope;
import com.rz.wareadapter.SparkedModelRowViewFields;

import java.util.ArrayList;
import java.util.HashMap;

public class ActWireAdapter extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private NoneScrollListView sysLstViewPaging;
    private SparkedArrayAdapter adapterListAdapter;
    private ArrayList<SparkedModelRowViewFields> rowViewFieldListItems = new ArrayList<SparkedModelRowViewFields>();
    private ArrayList<SparkedModelRowScope> modelDrawerMenuDataItems = new ArrayList<SparkedModelRowScope>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_wire_adapter);
        activity = this;
        context = this;
        System.out.println("OPENED");
        sysLstViewPaging = (NoneScrollListView) findViewById(R.id.sysLstViewPaging);
        onSetDrawerMenuItems();
        adapterListAdapter = new SparkedArrayAdapter(context, R.layout.layout_list_row, modelDrawerMenuDataItems);
        rowViewFieldListItems.add(adapterListAdapter.onSetRowViewField(SparkedArrayAdapter.FIELD_TYPE.TEXT_VIEW, "sysDrawerTitle"));
        rowViewFieldListItems.add(adapterListAdapter.onSetRowViewField(SparkedArrayAdapter.FIELD_TYPE.TEXT_VIEW, "sysDrawerDescription"));
        adapterListAdapter.onSetRowViewFieldList(rowViewFieldListItems);
        sysLstViewPaging.setAdapter(adapterListAdapter);
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
        modelDrawerMenuDataItems.add(SparkedModelRowScope.onGetSetRow(eachRowDataItems, (String) null, SparkedModelRowScope.LISTENER_TYPE.NONE));
        //modelDrawerListItems = spinalRowDrawerDraw.spinalDrawerMenu.onGetDataList();
    }
}
