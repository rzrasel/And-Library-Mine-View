package com.rz.usagesexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.rz.armorpagination.ArmorPagination;
import com.rz.armorpagination.NoneScrollListView;
import com.rz.usagesexample.custone.ActCustYoutubeOne;

import java.util.ArrayList;

public class ActSplash extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private NoneScrollListView sysLstViewPaging;
    private int TOTAL_LIST_ITEMS = 1030;
    private int ITEMS_PER_PAGE = 100;
    private int STARTING_NUM = 0;
    private ArrayList<String> dataList = new ArrayList<String>();
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        activity = this;
        context = this;
        //startActivity(new Intent(context, ActCustYoutubeOne.class));
        startActivity(new Intent(context, ActYouTubePlayerView.class));
        finish();
        //SparkedArrayAdapter
        sysLstViewPaging = (NoneScrollListView) findViewById(R.id.sysLstViewPaging);
        for (int i = 0; i < TOTAL_LIST_ITEMS; i++) {
            dataList.add("This is Item " + (i + 1));
        }
        loadList(0, ITEMS_PER_PAGE);
        ArmorPagination sysIdPagination = (ArmorPagination) findViewById(R.id.sysIdPagination);
        sysIdPagination.setTotalNumOfItems(TOTAL_LIST_ITEMS)
                .setNumOfItemsPerPage(ITEMS_PER_PAGE)
                .setCurrentPage(STARTING_NUM)
                .setPagerClickListener(new ArmorPagination.OnPagerClickListener() {
                    @Override
                    public void onClick(int argCurrentPage, int argStarting, int argEnding) {
                        loadList(argStarting, argEnding);
                    }
                })
                .onBuildPager();
    }

    private void loadList(int argStarting, int argEnding) {
        ArrayList<String> sort = new ArrayList<String>();
        System.out.println("STARTING: " + argStarting + " - ENDING: " + argEnding);
        System.out.println("PagerButton:============================");
        for (int i = argStarting; i < argEnding; i++) {
            sort.add(dataList.get(i));
        }
        arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, sort);
        sysLstViewPaging.setAdapter(arrayAdapter);
    }
}
