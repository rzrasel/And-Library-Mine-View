package com.rz.usagesexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class ActFlyOutContainerTest extends AppCompatActivity {
    private FlyOutContainer root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.act_fly_out_container_test);
        this.root = (FlyOutContainer) this.getLayoutInflater().inflate(R.layout.act_fly_out_container_test, null);
        this.setContentView(root);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sample, menu);
        return true;
    }

    public void toggleMenu(View v) {
        this.root.toggleMenu();
    }
}
/*
https://github.com/jaylamont/AndroidFlyOutMenuDemo/tree/part1
*/