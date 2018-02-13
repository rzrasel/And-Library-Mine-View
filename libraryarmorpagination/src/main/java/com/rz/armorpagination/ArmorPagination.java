package com.rz.armorpagination;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by Rz Rasel on 2018-02-13.
 */

public class ArmorPagination extends LinearLayout {
    private Context context;
    private int pageActive = 0;
    private HorizontalScrollView horizontalScrollView;
    private LinearLayout linearLayout;
    private int totalNumOfItems = 1000;
    private int numOfItemsPerPage = 10;
    private Button btnPrevious;
    private Button btnNext;
    private Button[] pageBtns;
    private int noOfBtns;

    public ArmorPagination(Context argContext) {
        this(argContext, null);
    }

    public ArmorPagination(Context argContext, AttributeSet argAttrs) {
        this(argContext, argAttrs, 0);
    }

    public ArmorPagination(Context argContext, AttributeSet argAttrs, int argDefStyleAttr) {
        super(argContext, argAttrs, argDefStyleAttr);
        context = context;
        /*TypedArray typedArray = context.obtainStyledAttributes(argAttrs, R.styleable.ArmorPagination, 0, 0);
        mFitXY = typedArray.getBoolean(R.styleable.UniversalVideoView_uvv_fitXY, false);
        mAutoRotation = typedArray.getBoolean(R.styleable.UniversalVideoView_uvv_autoRotation, false);
        typedArray.recycle();
        try {
            mShowText = typedArray.getBoolean(R.styleable.PieChart_showText, false);
            mTextPos = typedArray.getInteger(R.styleable.PieChart_labelPosition, 0);
        } finally {
            typedArray.recycle();
        }*/

        initView();
    }

    private void initView() {
        this.setOrientation(LinearLayout.VERTICAL);
        setUpHorizontalScrollView();
        setUpPreviousButton();
        setUpNextButton();
        setUpLinearLayout();
        horizontalScrollView.addView(linearLayout);
        super.addView(horizontalScrollView);
    }

    public void setTotalNumOfItems(int argTotalNumOfItems) {
        this.totalNumOfItems = argTotalNumOfItems;
    }

    public void setNumOfItemsPerPage(int argNumOfItemsPerPage) {
        this.numOfItemsPerPage = argNumOfItemsPerPage;
    }

    @Override
    public void addView(View argChild) {
        horizontalScrollView.addView(argChild);
    }

    private void setUpHorizontalScrollView() {
        horizontalScrollView = new HorizontalScrollView(getContext());
        horizontalScrollView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        horizontalScrollView.setVerticalScrollBarEnabled(false);
        horizontalScrollView.setHorizontalScrollBarEnabled(false);
        horizontalScrollView.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    private void setUpLinearLayout() {
        //linearLayout = new LinearLayout(getContext());
        linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        //linearLayout.setBackgroundColor(Color.GRAY);
        linearLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        linearLayout.addView(btnPrevious);
        bbbTen();
        linearLayout.addView(btnNext);
        //linearLayout.setVisibility(LinearLayout.INVISIBLE);
        //setPagerButton();
    }

    private void setUpPreviousButton() {
        //Button btnPrevious = new Button(getContext());
        btnPrevious = new Button(getContext());
        btnPrevious.setLayoutParams(new LayoutParams(150, LayoutParams.MATCH_PARENT));
        btnPrevious.setText("Previous");
        btnPrevious.setOnClickListener(new OnClickListener() {

            public void onClick(View argView) {
                //previous();
            }
        });
    }

    private void setUpNextButton() {
        //Button btnNext = new Button(getContext());
        btnNext = new Button(getContext());
        btnNext.setLayoutParams(new LayoutParams(150, LayoutParams.MATCH_PARENT));
        btnNext.setText("Next");
        btnNext.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                //next();
            }
        });
    }

    public void setPagerButton(int argTotalNumOfItems, int argNumOfItemsPerPage) {
        totalNumOfItems = argTotalNumOfItems;
        numOfItemsPerPage = argNumOfItemsPerPage;
        /*int val = totalNumOfItems % numOfItemsPerPage;
        val = (val == 0) ? 0 : 1;
        noOfBtns = totalNumOfItems / numOfItemsPerPage + val;
        System.out.println("Value: " + val + " -no: " + noOfBtns);*/
        System.out.println("setPagerButton:============================");
        removeAllViews();
        //removeView(linearLayout);
        //initView();
        //setUpLinearLayout();
        //linearLayout.addView(btnPrevious);
        //requestLayout();
        //invalidate();
        /*setUpHorizontalScrollView();
        setUpPreviousButton();
        setUpNextButton();
        setUpLinearLayout();
        //super.addView(linearLayout);
        //super.addView(horizontalScrollView);*/
        initView();
        requestLayout();
        invalidate();
    }

    private void bbbTen() {
        int val = totalNumOfItems % numOfItemsPerPage;
        val = (val == 0) ? 0 : 1;
        noOfBtns = totalNumOfItems / numOfItemsPerPage + val;
        System.out.println("Value: " + val + " -no: " + noOfBtns);
        pageBtns = new Button[noOfBtns];

        for (int i = 0; i < noOfBtns; i++) {
            pageBtns[i] = new Button(getContext());
            //pageBtns[i].setBackgroundColor(getResources().getColor(android.R.color.transparent));
            pageBtns[i].setLayoutParams(new LayoutParams(150, LayoutParams.MATCH_PARENT));
            pageBtns[i].setText("" + (i + 1));
            linearLayout.addView(pageBtns[i]);
        }
    }
}
//https://github.com/paulononaka/Android-PaginationLayoutSample/blob/master/src/com/paulononaka/PaginationLayout.java
//https://github.com/aritraroy/PatternLockView