package com.rz.usagesexample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import com.rz.armorpagination.ArmorReversePagination;
import com.rz.armorpagination.NixScrollListView;

import java.util.ArrayList;

public class ActReversePagination extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private NixScrollListView sysLstViewPaging;
    private int TOTAL_LIST_ITEMS = 52;
    private int ITEMS_PER_PAGE = 5;
    private int STARTING_NUM = 0;
    private ArrayList<String> dataList = new ArrayList<String>();
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_reverse_pagination);
        activity = this;
        context = this;
        sysLstViewPaging = (NixScrollListView) findViewById(R.id.sysLstViewPaging);
        for (int i = 0; i < TOTAL_LIST_ITEMS; i++) {
            dataList.add("This is Item " + (i + 1));
        }
        loadList(0, ITEMS_PER_PAGE);
        ArmorReversePagination sysIdPagination = (ArmorReversePagination) findViewById(R.id.sysIdPagination);
        sysIdPagination.onSetTotalItem(TOTAL_LIST_ITEMS)
                .onSetItemPerPage(ITEMS_PER_PAGE)
                .setPagerClickListener(new ArmorReversePagination.OnPaginationClickListener() {
                    @Override
                    public void onClick(int argCurrentPage, int argStarting, int argEnding) {
                        loadList(argStarting, argEnding);
                    }
                })
                .onRunPagination(new ArmorReversePagination.OnPaginationInitialRunListener() {
                    @Override
                    public void onRun(int argCurrentPage, int argStarting, int argEnding) {
                        loadList(argStarting, argEnding);
                    }
                });
        /*sysIdPagination.setTotalNumOfItems(TOTAL_LIST_ITEMS)
                .setNumOfItemsPerPage(ITEMS_PER_PAGE)
                //.setCurrentPage(STARTING_NUM)
                .setPagerClickListener(new ArmorPagination.OnPagerClickListener() {
                    @Override
                    public void onClick(int argCurrentPage, int argStarting, int argEnding) {
                        loadList(argStarting, argEnding);
                    }
                })
                .initSetReverse()
                .onBuildPager();*/
    }

    private void loadList(int argStarting, int argEnding) {
        ArrayList<String> sort = new ArrayList<String>();
        System.out.println("STARTING<>: " + argStarting + " - ENDING<>: " + argEnding);
        System.out.println("PagerButton:============================");
        /*int start = argStarting * NUM_ITEMS_PAGE;
        (start) + NUM_ITEMS_PAGE;*/
        for (int i = argEnding; i >= argStarting; i--) {
            /*if (i < dataList.size()) {
                sort.add(dataList.get(i));
            } else {
                break;
            }*/
            /*if (i >= dataList.size()) {
                break;
            }*/
            System.out.println("PagerButton:============================: <" + i + ">");
            /*if (i < dataList.size() && i > -1) {
                sort.add(dataList.get(i));
            }*/
            sort.add(dataList.get(i));
        }
        arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, sort);
        sysLstViewPaging.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }
}
