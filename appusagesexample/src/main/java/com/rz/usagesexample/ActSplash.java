package com.rz.usagesexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.rz.armorpagination.ArmorPagination;

public class ActSplash extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private ArmorPagination sysIdPagination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        activity = this;
        context = this;
        //startActivity(new Intent(context, ActPaginationImp.class));
        startActivity(new Intent(context, ActReversePagination.class));
        finish();
        /*sysIdPagination = (ArmorPagination) findViewById(R.id.sysIdPagination);
        sysIdPagination.setTotalNumOfItems(150)
                .setNumOfItemsPerPage(10)
                .setPagerClickListener(new ArmorPagination.OnPagerClickListener() {
                    @Override
                    public void onClick(int argCurrentPage, int argStarting, int argEnding) {
                        System.out.println("c: " + argCurrentPage + " s: " + argStart + " e: " + argEnd);
                    }
                })
                .setCurrentPage(1)
                .onBuildPager();*/
        /*ArmorPagination armorPagination = new ArmorPagination(this);
        armorPagination.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        setContentView(armorPagination);*/
    }

    private void onTest() {
        // creates the Pagination Layout
        ArmorPagination paginationLayout = new ArmorPagination(this);
        paginationLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));

        // creates content only for sample
        TableLayout table = new TableLayout(this);
        table.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        table.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

        TableRow row = new TableRow(this);
        row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        table.addView(row);

        TableRow row2 = new TableRow(this);
        row2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        table.addView(row2);

        for (int i = 0; i < 4; i++) {
            Button button = new Button(this);
            button.setText("Button " + i);
            if (i % 2 == 0) {
                row.addView(button);
            } else {
                row2.addView(button);
            }
        }

        // add the content in pagination
        paginationLayout.addView(table);
        // set pagination layout
        setContentView(paginationLayout);
    }
}
