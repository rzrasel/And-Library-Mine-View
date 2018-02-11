package com.sm.viewpagercircularindicator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class ActSplash extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    protected View view;
    private ImageButton btnNext, btnFinish;
    private ViewPager intro_images;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    private ViewPagerAdapter mAdapter;

    private String[] mImageResources = {
            "Line 1",
            "Line 2",
            "Line 3",
            "Line 4",
            "Line 5",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        Intent intentRedirectWindow = new Intent(this, ActWebView.class);
        startActivity(intentRedirectWindow);
        finish();
        setReference();
    }

    public void setReference() {
        view = LayoutInflater.from(this).inflate(R.layout.act_splash, null);

        /*intro_images = (ViewPager) view.findViewById(R.id.pager_introduction);
        btnNext = (ImageButton) view.findViewById(R.id.btn_next);
        btnFinish = (ImageButton) view.findViewById(R.id.btn_finish);

        pager_indicator = (LinearLayout) view.findViewById(R.id.viewPagerCountDots);*/

        intro_images = (ViewPager) findViewById(R.id.pager_introduction);
        btnNext = (ImageButton) findViewById(R.id.btn_next);
        btnFinish = (ImageButton) findViewById(R.id.btn_finish);

        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);

        btnNext.setOnClickListener(this);
        btnFinish.setOnClickListener(this);

        mAdapter = new ViewPagerAdapter(this, mImageResources);
        intro_images.setAdapter(mAdapter);
        intro_images.setCurrentItem(0);
        intro_images.addOnPageChangeListener(this);
        setUiPageViewController();
        //https://github.com/vivchar/ViewPagerIndicator/blob/master/example/src/main/java/com/example/vivchar/MainActivity.java
        ViewPagerIndicator mViewPagerIndicator = (ViewPagerIndicator) findViewById(R.id.view_pager_indicator);
        mViewPagerIndicator.setupWithViewPager(intro_images);
        mViewPagerIndicator.addOnPageChangeListener(this);
    }

    private void setUiPageViewController() {

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResDrawable(this, R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            pager_indicator.addView(dots[i], params);
        }
        dots[0].setImageDrawable(getResDrawable(this, R.drawable.selecteditem_dot));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                intro_images.setCurrentItem((intro_images.getCurrentItem() < dotsCount) ? intro_images.getCurrentItem() + 1 : 0);
                break;

            case R.id.btn_finish:
                //finish();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResDrawable(this, R.drawable.nonselecteditem_dot));
        }

        dots[position].setImageDrawable(getResDrawable(this, R.drawable.selecteditem_dot));

        if (position + 1 == dotsCount) {
            btnNext.setVisibility(View.GONE);
            btnFinish.setVisibility(View.VISIBLE);
        } else {
            btnNext.setVisibility(View.VISIBLE);
            btnFinish.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    //
    public Drawable getResDrawable(Activity argActivity, int argId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return ContextCompat.getDrawable(argActivity, argId);
        } else {
            return getResources().getDrawable(argId);
        }
    }
}
