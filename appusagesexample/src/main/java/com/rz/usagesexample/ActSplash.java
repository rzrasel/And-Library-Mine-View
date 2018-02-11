package com.rz.usagesexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rz.varnishview.introviewpager.AdapterViewPager;
import com.rz.varnishview.introviewpager.ModelRowViewHolder;
import com.rz.varnishview.introviewpager.OnIndicatorEventListenerHandler;
import com.rz.varnishview.introviewpager.PagerSkipNextButton;
import com.rz.varnishview.introviewpager.ViewPagerIndicator;

import java.util.ArrayList;

public class ActSplash extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private ViewPager sysViewPagerIntroduction;
    private LinearLayout layPagerIndicator;
    private AdapterViewPager adapterViewPager;
    private ArrayList<ModelPagerContent> adapterListItems = new ArrayList<ModelPagerContent>();
    private ArrayList<ModelRowViewHolder> fieldsRowViewListItems = new ArrayList<ModelRowViewHolder>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        activity = this;
        context = this;
        Intent intentRedirectWindow = new Intent(context, ActWebView.class);
        startActivity(intentRedirectWindow);
        adapterListItems.add(new ModelPagerContent("Page 1: Jul 31, 2015 - The images above are taken from the official Google Drive app and show how their intro screen is interactive, easy to use and entertaining. ... The background color fades as you swipe between pages and objects on the screen move in layers at different speeds. ... Most Android developers"));
        adapterListItems.add(new ModelPagerContent("Page 2: android material-design onboarding android-library. ... I decided to rewrite completely almost all features in order to make Android intro screen easy to use for everyone and extensible as possible. ... dependencies { compile 'agency.tango.android:material-intro-screen:{latest_release}' }"));
        adapterListItems.add(new ModelPagerContent("Page 3: May 10, 2016 - Check for the fist time app launch using prefManager.isFirstTimeLaunch() method. If it returns true, MainActivity will be launched skipping the intro activity. > Made the notification bar transparent, so that the view background color can be seen through. > Created a PagerAdapter for the ViewPager and ..."));
        adapterListItems.add(new ModelPagerContent("Page 4: Jul 13, 2017 - A lot of apps have a landing screen with images and logos where you can slide through various screens. There are various third party libraries in the open source world that makes this easy, but none of them are highly customizable. In this post, I'll go over how to build one of these slideshows from scratch."));
        fieldsRowViewListItems.add(ModelRowViewHolder.onGetSetRow(new TextView(context), "sysInstructions", ""));
        sysViewPagerIntroduction = (ViewPager) findViewById(R.id.sysViewPagerIntroduction);
        adapterViewPager = new AdapterViewPager(context, R.layout.lay_pager_item_textview, adapterListItems);
        sysViewPagerIntroduction.setAdapter(adapterViewPager);
        adapterViewPager.onSetEventListenerHandler(new OnIndicatorEventListenerHandler() {
            @Override
            public boolean onIsViewFromObject(View argView, Object argObject) {
                return argView == ((LinearLayout) argObject);
            }

            @Override
            public void onDestroyItem(ViewGroup argContainer, int argPosition, Object argObject) {
                argContainer.removeView((LinearLayout) argObject);
            }

            @Override
            public void onSetFieldsValue(ArrayList<ModelRowViewHolder> argListRowViewFields, Object argObject) {
                TextView rowField = null;
                ModelPagerContent items = (ModelPagerContent) argObject;
                if (argListRowViewFields.size() > 0) {
                    rowField = (TextView) argListRowViewFields.get(0).getFieldObject();
                    rowField.setText(items.getInstructions());
                }
            }
        }, fieldsRowViewListItems);
        sysViewPagerIntroduction.setCurrentItem(0);
        ViewPagerIndicator sysViewPagerIndicator = (ViewPagerIndicator) findViewById(R.id.sysViewPagerIndicator);
        sysViewPagerIndicator.setupWithViewPager(sysViewPagerIntroduction);
        sysViewPagerIndicator.addOnPageChangeListener(onPageChangeListener);
        PagerSkipNextButton sysViewPagerSkipNextButton = (PagerSkipNextButton) findViewById(R.id.sysViewPagerSkipNextButton);
        sysViewPagerSkipNextButton.setupWithViewPager(sysViewPagerIntroduction);
        sysViewPagerSkipNextButton.addOnPageChangeListener(onPageChangeListener);
        sysViewPagerSkipNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View argView) {
                int btnId = argView.getId();
                //Toast.makeText(context, "Clicked: " + argView.getId(), Toast.LENGTH_LONG).show();
                if (btnId == com.rz.varnishview.introviewpager.R.id.sysBtnPagerViewSkip) {
                } else if (btnId == com.rz.varnishview.introviewpager.R.id.sysBtnPagerViewNext) {
                    int currentIndex = sysViewPagerIntroduction.getCurrentItem();
                    currentIndex++;
                    sysViewPagerIntroduction.setCurrentItem(currentIndex);
                }
            }
        });
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(final int position) {
            //Toast.makeText(context, "Page selected " + position, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPageScrollStateChanged(final int state) {

        }
    };

    public class ModelPagerContent {
        private String instructions;

        public ModelPagerContent(String argInstructions) {
            this.instructions = argInstructions;
        }

        public String getInstructions() {
            return this.instructions;
        }
    }
}
