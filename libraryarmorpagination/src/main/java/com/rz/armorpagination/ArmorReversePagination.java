package com.rz.armorpagination;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Rz Rasel on 2018-03-06.
 */

public class ArmorReversePagination extends LinearLayout {
    private Context context;
    private ArmorPagination.OnPagerClickListener onPagerClickListener;
    private int pageActive = 0;
    private int currentPage = -1;
    private HorizontalScrollView horizontalScrollView;
    private LinearLayout linearLayout;
    private int totalNumOfItems = 1000;
    private int numOfItemsPerPage = 10;
    private Button btnPrevious;
    private Button btnNext;
    private Button btnFirst;
    private Button btnLast;
    private Button[] pageBtns;
    private int numOfPages;
    private int pxBtnWidth = 40;
    private int pxBtnHight = 32;

    public ArmorReversePagination(Context argContext) {
        this(argContext, null);
    }

    public ArmorReversePagination(Context argContext, AttributeSet argAttrs) {
        this(argContext, argAttrs, 0);
    }

    public ArmorReversePagination(Context argContext, AttributeSet argAttrs, int argDefStyleAttr) {
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
        createFirstButton();
        createPreviousButton();
        createNextButton();
        createLastButton();
        setUpLinearLayout();
        horizontalScrollView.addView(linearLayout);
        super.addView(horizontalScrollView);
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
        /*linearLayout.addView(btnFirst);
        linearLayout.addView(btnPrevious);*/
        createPaginationButton();
        /*linearLayout.addView(btnNext);
        linearLayout.addView(btnLast);*/
        //linearLayout.setVisibility(LinearLayout.INVISIBLE);
        //setPagerButton();
    }

    private void createFirstButton() {
        //Button btnPrevious = new Button(getContext());
        btnFirst = new Button(getContext());
        //btnPrevious.setLayoutParams(new LayoutParams(150, LayoutParams.MATCH_PARENT));
        int dpWidth = (int) Utils.dpToPixel(getContext(), pxBtnWidth);
        int dpHeight = (int) Utils.dpToPixel(getContext(), pxBtnHight);
        btnFirst.setLayoutParams(new LayoutParams(dpWidth, dpHeight));
        //btnPrevious.setText("Previous");
        onSetDrawable(btnFirst, R.drawable.pagination_arrow_first);
        btnFirst.setOnClickListener(new OnClickListener() {

            public void onClick(View argView) {
                //previous();
            }
        });
    }

    private void createLastButton() {
        //Button btnPrevious = new Button(getContext());
        btnLast = new Button(getContext());
        //btnPrevious.setLayoutParams(new LayoutParams(150, LayoutParams.MATCH_PARENT));
        int dpWidth = (int) Utils.dpToPixel(getContext(), pxBtnWidth);
        int dpHeight = (int) Utils.dpToPixel(getContext(), pxBtnHight);
        btnLast.setLayoutParams(new LayoutParams(dpWidth, dpHeight));
        //btnPrevious.setText("Previous");
        onSetDrawable(btnLast, R.drawable.pagination_arrow_last);
        btnLast.setOnClickListener(new OnClickListener() {

            public void onClick(View argView) {
                //previous();
            }
        });
    }

    private void createPreviousButton() {
        //Button btnPrevious = new Button(getContext());
        btnPrevious = new Button(getContext());
        //btnPrevious.setLayoutParams(new LayoutParams(150, LayoutParams.MATCH_PARENT));
        int dpWidth = (int) Utils.dpToPixel(getContext(), pxBtnWidth);
        int dpHeight = (int) Utils.dpToPixel(getContext(), pxBtnHight);
        btnPrevious.setLayoutParams(new LayoutParams(dpWidth, dpHeight));
        //btnPrevious.setText("Previous");
        onSetDrawable(btnPrevious, R.drawable.pagination_arrow_left);
        btnPrevious.setOnClickListener(new OnClickListener() {

            public void onClick(View argView) {
                //previous();
            }
        });
    }

    private void createNextButton() {
        //Button btnNext = new Button(getContext());
        btnNext = new Button(getContext());
        /*btnNext.setLayoutParams(new LayoutParams(150, LayoutParams.MATCH_PARENT));
        btnNext.setText("Next");*/
        int dpWidth = (int) Utils.dpToPixel(getContext(), pxBtnWidth);
        int dpHeight = (int) Utils.dpToPixel(getContext(), pxBtnHight);
        btnNext.setLayoutParams(new LayoutParams(dpWidth, dpHeight));
        onSetDrawable(btnNext, R.drawable.pagination_arrow_right);
        //btnNext.setBackgroundResource(R.drawable.half_gradient);
        btnNext.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                //next();
            }
        });
    }

    private void createPaginationButton() {
        int val = totalNumOfItems % numOfItemsPerPage;
        val = (val == 0) ? 0 : 1;
        numOfPages = totalNumOfItems / numOfItemsPerPage + val;
        System.out.println("Value: " + val + " -no: " + numOfPages);
        System.out.println("total items: " + totalNumOfItems + " -no per page: " + numOfItemsPerPage);
        int totalButtonShown = 5;
        int midPoint = totalButtonShown / 2;
        int countMax = totalButtonShown;
        int countStart = 0;
        if (currentPage <= 0) {
            currentPage = 0;
        } else if (currentPage >= numOfPages) {
            currentPage = numOfPages - 1;
        }
        if (numOfPages < totalButtonShown) {
            countMax = numOfPages;
        } else {
            countMax = currentPage + midPoint + 1;
            if (countMax <= totalButtonShown) {
                countMax = totalButtonShown;
            }
            if (countMax >= numOfPages) {
                countMax = numOfPages - 1;
            }
            //System.out.println("COUNT_MAX: " + countMax);
        }
        countStart = countMax - totalButtonShown;
        System.out.println("COUNT_MAX: " + countMax);
        System.out.println("TOTAL_BUTTON_SHOW: " + totalButtonShown);
        System.out.println("COUNT_START: " + countStart);
        System.out.println("CURRENT_PAGES: " + currentPage);
        if (countStart <= 0) {
            countStart = 0;
        }
        /*int midPoint = totalButtonShown / 2;
        if (currentPage <= 0) {
            countStart = 0;
        } else {
            countStart = currentPage - midPoint;
            if (countStart <= 0 || countStart <= totalButtonShown) {
                countStart = 0;
            } else {
                //
            }
            System.out.println("MID: " + midPoint);
        }
        System.out.println("MIDOO: " + midPoint * 2);*/
        //System.out.println("COUNT_MAX: " + countMax + " CURR: " + currentPage);
        pageBtns = new Button[totalButtonShown];
        int forCounter = 0;
        //for (int i = countStart; i < countMax; i++) {
        for (int i = countMax; i > countStart; i--) {
            //pageBtns[i] = new Button(getContext());
            //pageBtns[i] = new Button(getContext(), null, android.R.style.Widget_Material_ButtonBar);
            //Widget.AppCompat.Button.Borderless
            pageBtns[forCounter] = new Button(getContext(), null, android.R.style.Widget_Holo_Button_Borderless);
            //pageBtns[i].setBackgroundColor(getResources().getColor(android.R.color.transparent));
            //pageBtns[i].setLayoutParams(new LayoutParams(150, LayoutParams.MATCH_PARENT));
            int dpWidth = (int) Utils.dpToPixel(getContext(), pxBtnWidth);
            int dpHeight = (int) Utils.dpToPixel(getContext(), pxBtnHight);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dpWidth, dpHeight);
            //RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dpWidth, LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            //layoutParams.setMargins(left, top, right, bottom);
            //layoutParams.setMargins(120, 120, 120, 120);
            pageBtns[forCounter].setGravity(Gravity.CENTER);
            pageBtns[forCounter].setLayoutParams(layoutParams);
            pageBtns[forCounter].setText("" + (i + 1));
            pageBtns[forCounter].setTag("" + i);
            pageBtns[forCounter].setTextSize(16);
            pageBtns[forCounter].setOnClickListener(new OnPagerBtnClickListener());
            pageBtns[forCounter].setBackgroundResource(R.drawable.pager_gradient_current);
            //pageBtns[forCounter].setTextColor(Color.parseColor("#006400"));
            pageBtns[forCounter].setTextColor(Color.parseColor("#717171"));
            linearLayout.addView(pageBtns[forCounter]);
            /*if (i == midPoint) {
                pageBtns[i].setTypeface(pageBtns[i].getTypeface(), Typeface.BOLD);
            }*/
            if (currentPage == i) {
                pageBtns[forCounter].setTypeface(pageBtns[forCounter].getTypeface(), Typeface.BOLD);
                pageBtns[forCounter].setTextColor(Color.parseColor("#feffff"));
                pageBtns[forCounter].setBackgroundResource(R.drawable.pager_gradient_current);
            }
            if (forCounter == 0) {
                pageBtns[forCounter].setBackgroundResource(R.drawable.pager_gradient_first);
                if (currentPage == countStart) {
                    pageBtns[forCounter].setBackgroundResource(R.drawable.pager_gradient_current_first);
                }
            }
            if (i == countMax - 1) {
                pageBtns[forCounter].setBackgroundResource(R.drawable.pager_gradient_last);
                if (currentPage == countMax - 1) {
                    pageBtns[forCounter].setBackgroundResource(R.drawable.pager_gradient_current_last);
                }
            }
            LayoutParams layoutparams = (LinearLayout.LayoutParams) pageBtns[forCounter].getLayoutParams();
            //layoutparams.setMargins(25,25,25,25);
            layoutparams.setMargins(8, 0, 8, 0);
            pageBtns[forCounter].setLayoutParams(layoutparams);
            //System.out.println("for_count: " + forCounter + " cmax: " + countMax);
            pageBtns[forCounter].setBackgroundResource(R.drawable.pg_gd_one);
            if (currentPage == i) {
                pageBtns[forCounter].setBackgroundResource(R.drawable.pg_gd_one_current);
            }
            forCounter++;
        }
    }

    public class OnPagerBtnClickListener implements OnClickListener {
        @Override
        public void onClick(View argView) {
            int tagValue = Integer.parseInt(argView.getTag().toString());
            if (tagValue == currentPage) {
                //System.out.println("TAG_CURRENT: " + tagValue);
                return;
            }
            currentPage = tagValue + 1;
            int startingNumber = tagValue * numOfItemsPerPage;
            int endingNumber = startingNumber + numOfItemsPerPage;
            //System.out.println("TAG: " + tagValue);
            if (onPagerClickListener != null) {
                if (endingNumber > totalNumOfItems) {
                    endingNumber = (totalNumOfItems - startingNumber) + startingNumber;
                }
                onPagerClickListener.onClick(currentPage, startingNumber, endingNumber);
            }
            setCurrentPage(currentPage);
            onBuildPager();
        }
    }

    public interface OnPagerClickListener {
        public void onClick(int argCurrentPage, int argStarting, int argEnding);
    }

    public void onSetDrawable(View argView, int argResId) {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            //layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.ready));
            argView.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), argResId));
        } else {
            argView.setBackground(ContextCompat.getDrawable(getContext(), argResId));
        }
    }

    public ArmorReversePagination setCurrentPage(int argCurrentPage) {
        this.currentPage = argCurrentPage - 1;
        return this;
    }

    public ArmorReversePagination setTotalNumOfItems(int argTotalNumOfItems) {
        this.totalNumOfItems = argTotalNumOfItems;
        return this;
    }

    public ArmorReversePagination setPagerClickListener(ArmorPagination.OnPagerClickListener argOnPagerClickListener) {
        this.onPagerClickListener = argOnPagerClickListener;
        return this;
    }

    public ArmorReversePagination setNumOfItemsPerPage(int argNumOfItemsPerPage) {
        this.numOfItemsPerPage = argNumOfItemsPerPage;
        return this;
    }

    public ArmorReversePagination initSetReverse() {
        int val = totalNumOfItems % numOfItemsPerPage;
        val = (val == 0) ? 0 : 1;
        numOfPages = totalNumOfItems / numOfItemsPerPage + val;
        if (currentPage <= 0) {
            setCurrentPage(numOfPages);
            //System.out.println("IF:....");
        } else if (currentPage >= numOfPages) {
            setCurrentPage(numOfPages);
            //System.out.println("ELSE:....");
        }
        //System.out.println("CURRENT_PAGES:.... " + currentPage);
        //System.out.println("Value:..... " + val + " -no: " + numOfPages);
        return this;
    }

    public void onBuildPager() {
        /*totalNumOfItems = argTotalNumOfItems;
        numOfItemsPerPage = argNumOfItemsPerPage;*/
        /*int val = totalNumOfItems % numOfItemsPerPage;
        val = (val == 0) ? 0 : 1;
        noOfBtns = totalNumOfItems / numOfItemsPerPage + val;
        System.out.println("Value: " + val + " -no: " + noOfBtns);*/
        //System.out.println("setPagerButton:============================");
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
}
//https://github.com/paulononaka/Android-PaginationLayoutSample/blob/master/src/com/paulononaka/PaginationLayout.java
//https://github.com/aritraroy/PatternLockView
//http://www.devexchanges.info/2015/05/showhide-layouts-when-scroll-listview.html
//https://colinyeoh.wordpress.com/2012/10/07/simple-android-animation-on-view-gonevisible/
/*
 <?php
function paginate($pag, $count, $link, $step)
{
    $str = '';
    if ($step < $count) {
        $mesdisp = $step;
        $max     = $count;
        $pag     = ($pag > $count) ? $count : $pag;
        $pag     = (floor($pag / $mesdisp)) * $mesdisp;

        if ((6 % 2) == 1)
            $pc = (int) ((6 - 1) / 2);
        else
            $pc = (int) (6 / 2);
        if ($pag > $mesdisp * $pc)
            $str .= '<a href="/' . $link . 'pag=0">1</a>';

        if ($pag > $mesdisp * ($pc + 1))
            $str .= "...";

        for ($nCont = $pc; $nCont >= 1; $nCont--)
            if ($pag >= $mesdisp * $nCont) {
                $tmpStart = $pag - $mesdisp * $nCont;
                $tmpPage  = $tmpStart / $mesdisp + 1;
                $str .= '<a href="/' . $link . 'pag=' . $tmpStart . '">' . $tmpPage . '</a>';
            }

        $tmpPage = $pag / $mesdisp + 1;
        $str .= "<span class=\"current\">" . $tmpPage . "</span>";
        $tmpMaxPages = (int) (($max - 1) / $mesdisp) * $mesdisp;
        for ($nCont = 1; $nCont <= $pc; $nCont++)
            if ($pag + $mesdisp * $nCont <= $tmpMaxPages) {
                $tmpStart = $pag + $mesdisp * $nCont;
                $tmpPage  = $tmpStart / $mesdisp + 1;

                $str .= '<a href="/' . $link . 'pag=' . $tmpStart . '">' . $tmpPage . '</a>';
            }

        if (($pag + $mesdisp * ($pc + 1)) < $tmpMaxPages)
            $str .= "...";
        if (($pag + $mesdisp * $pc) < $tmpMaxPages) {
            $tmpPage = $tmpMaxPages / $mesdisp + 1;
            $str .= '<a href="/' . $link . 'pag=' . $tmpMaxPages . '">' . $tmpPage . '</a>';
        }
    }
    return $str;
}
?>
https://github.com/loveorigami/yii2-reversed-pagination/blob/master/ReversePagination.php
https://github.com/alexandresalome/pagination/blob/master/Pager.php
https://github.com/jasongrimes/php-paginator/blob/master/src/JasonGrimes/Paginator.php
https://github.com/sayopaul/paginationclass/blob/master/Pagination.class.php
https://github.com/lifankohome/Pagination/blob/master/Pagination.php
https://github.com/sethbaur/pagify/blob/master/Pagify.php
*/