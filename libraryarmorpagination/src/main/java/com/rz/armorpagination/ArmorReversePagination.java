package com.rz.armorpagination;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rz Rasel on 2018-03-14.
 */

public class ArmorReversePagination extends LinearLayout {
    private Activity activity;
    private Context context;
    private HorizontalScrollView horizontalScrollView;
    private LinearLayout linearLayout;
    private Button[] paginationUIButtons;
    private int pxBtnWidth = 40;
    private int pxBtnHight = 32;

    private int totalItems = 250;
    private int itemPerPage = 10;
    private int totalPages = 0;
    private int range = 3;
    private int offset = 0;
    private int currentPage = -1;
    private boolean isDebug = true;

    public ArmorReversePagination(Context argContext) {
        this(argContext, null);
    }

    public ArmorReversePagination(Context argContext, AttributeSet argAttrs) {
        this(argContext, argAttrs, 0);
    }

    public ArmorReversePagination(Context argContext, AttributeSet argAttrs, int argDefStyleAttr) {
        super(argContext, argAttrs, argDefStyleAttr);
        context = argContext;

        initView();
    }

    private void initView() {
        if (range % 2 == 0) {
            range = range + 1;
        }
        this.setOrientation(LinearLayout.VERTICAL);
        setUpHorizontalScrollView();
        //createFirstButton();
        //createPreviousButton();
        //createNextButton();
        //createLastButton();
        setUpLinearLayout();
        horizontalScrollView.addView(linearLayout);
        super.addView(horizontalScrollView);
        ////
        ////
        ////
        ////
        ////
        //currentPage = 4;
        initValues();
        getTotalPages();
        getOffset();
        onSetDefaultStarting(true);
        onReadyReversePaging();
    }

    private void onSetDefaultStarting(boolean isReverse) {
        if (isReverse) {
            currentPage = getTotalPages();
        } else {
            currentPage = 1;
        }
        onDebugLog("->>>>>>>>>>>>>>>>>> " + currentPage);
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
        onCreateUIPaginationButton();
        /*linearLayout.addView(btnNext);
        linearLayout.addView(btnLast);*/
        //linearLayout.setVisibility(LinearLayout.INVISIBLE);
        //setPagerButton();
    }

    private void onCreateUIPaginationButton() {
        paginationUIButtons = new Button[range];
        for (int uiButtonCounter = 0; uiButtonCounter < range; uiButtonCounter++) {
            Button uiBtn = new Button(getContext(), null, android.R.style.Widget_Holo_Button_Borderless);
            int dpWidth = (int) Utils.dpToPixel(getContext(), pxBtnWidth);
            int dpHeight = (int) Utils.dpToPixel(getContext(), pxBtnHight);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(dpWidth, dpHeight);
            //RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dpWidth, dpHeight);
            //RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dpWidth, LayoutParams.WRAP_CONTENT);
            //layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            //layoutParams.setMargins(left, top, right, bottom);
            //layoutParams.setMargins(120, 120, 120, 120);
            uiBtn.setGravity(Gravity.CENTER);
            uiBtn.setLayoutParams(layoutParams);
            uiBtn.setLayoutParams(layoutParams);
            uiBtn.setText("" + (uiButtonCounter + 1));
            uiBtn.setTag("" + uiButtonCounter);
            uiBtn.setTextSize(16);
            uiBtn.setBackgroundResource(R.drawable.pager_gradient_current);
            //pageBtns[forCounter].setTextColor(Color.parseColor("#006400"));
            uiBtn.setTextColor(Color.parseColor("#717171"));


            if (uiButtonCounter == 0) {
                uiBtn.setBackgroundResource(R.drawable.pager_gradient_first);
                /*if (currentPage == countStart) {
                    pageBtns[forCounter].setBackgroundResource(R.drawable.pager_gradient_current_first);
                }*/
            }
            if (uiButtonCounter == range - 1) {
                uiBtn.setBackgroundResource(R.drawable.pager_gradient_last);
                /*if (currentPage == countMax - 1) {
                    pageBtns[forCounter].setBackgroundResource(R.drawable.pager_gradient_current_last);
                }*/
            }
            LayoutParams layoutparams = (LinearLayout.LayoutParams) uiBtn.getLayoutParams();
            //RelativeLayout.LayoutParams layoutparams = (RelativeLayout.LayoutParams) uiBtn.getLayoutParams();
            //layoutparams.setMargins(25,25,25,25);
            layoutparams.setMargins(8, 0, 8, 0);
            uiBtn.setLayoutParams(layoutparams);
            //System.out.println("for_count: " + forCounter + " cmax: " + countMax);
            uiBtn.setBackgroundResource(R.drawable.pg_gd_one);
            uiBtn.setOnClickListener(new OnPagerBtnClickListener());


            paginationUIButtons[uiButtonCounter] = uiBtn;
            linearLayout.addView(paginationUIButtons[uiButtonCounter]);
            //onDebugLog("Button: " + uiButtonCounter);
        }
    }

    private void onShowUIButton(int argUIPosition, int argButtonValue) {
        paginationUIButtons[argUIPosition].setText("" + argButtonValue);
        paginationUIButtons[argUIPosition].setTag("" + argButtonValue);
        paginationUIButtons[argUIPosition].setTextColor(Color.parseColor("#717171"));
        paginationUIButtons[argUIPosition].setBackgroundResource(R.drawable.pg_gd_one);
        if (argButtonValue == currentPage) {
            paginationUIButtons[argUIPosition].setTextColor(Color.parseColor("#feffff"));
            paginationUIButtons[argUIPosition].setBackgroundResource(R.drawable.pg_gd_one_current);
        }
    }

    private void onHideUIButton(int argLastPosition) {
        if (argLastPosition < paginationUIButtons.length) {
            for (int i = argLastPosition; i < paginationUIButtons.length; i++) {
                paginationUIButtons[i].setVisibility(View.GONE);
            }
        }
        //onDebugLog("LAST POSITION: " + argLastPosition + " LENGTH: " + paginationUIButtons.length);
    }

    public class OnPagerBtnClickListener implements OnClickListener {
        @Override
        public void onClick(View argView) {
            int tagValue = Integer.parseInt(argView.getTag().toString());
            if (tagValue == currentPage) {
                //System.out.println("TAG_CURRENT: " + tagValue);
                return;
            }
            currentPage = tagValue;
            onReadyReversePaging();
        }
    }

    @Override
    public void addView(View argChild) {
        horizontalScrollView.addView(argChild);
    }

    private void initValues() {
        currentPage = (currentPage > 0) ? currentPage : 1;
        onDebugLog("Current Page: " + currentPage);
    }

    private int getTotalPages() {
        if (totalItems <= 0 || itemPerPage <= 0) {
            return 0;
        }
        int val = totalItems % itemPerPage;
        val = (val == 0) ? 0 : 1;
        int total = totalItems / itemPerPage + val;
        onDebugLog("Total: " + total);
        return total;
    }

    private int getOffset() {
        offset = (currentPage - 1) * itemPerPage;
        onDebugLog("Offset: " + offset);
        return offset;
    }

    //int argTotalPages, int argCurrentPage
    private int getStartingPage() {
        int currentPoint = currentPage - 1;
        int starting = 0;
        int leftOffset = range / 2;
        starting = currentPoint - leftOffset;
        int getLast = currentPoint + leftOffset * 2;
        //getLast = getEndingPage();
        int getTotalNode = getLast - starting;
        onDebugLog("TOTAL_NODE: " + getTotalNode + " LAST: " + getLast);
        if (getLast > getTotalPages()) {
            starting = (getTotalPages() - 1) - leftOffset * 2;
        }
        //onDebugLog("STARTING_NODE: " + getTotalNode + " LAST: " + getLast);
        if (starting <= 0) {
            starting = 0;
        }
        return starting;
    }

    private int getEndingPage() {
        int currentPoint = currentPage - 1;
        int ending = 0;
        int rightOffset = range / 2;
        //onDebugLog("RIGHT OFFSET: " + rightOffset);
        //ending = argStartingPage + rightOffset * 2;
        ending = currentPoint + rightOffset;
        int getStart = currentPage - rightOffset * 2;
        onDebugLog("TOTAL_NODE: " + 0 + " FIRST: " + getStart);
        if (getStart < 0) {
            //starting = (getTotalPages() - 1) - leftOffset * 2;
            ending = rightOffset * 2;
        }
        if (ending >= getTotalPages()) {
            ending = getTotalPages() - 1;
        }
        onDebugLog("RIGHT OFFSET: " + rightOffset + " ENDING: " + ending);
        return ending;
    }

    private void onReadyReversePaging() {
        int startPoint = 0;
        int endPoint = 0;
        endPoint = getStartingPage();
        startPoint = getEndingPage();
        onDebugLog("===================>" + paginationUIButtons.length);
        //startPoint = currentPage - 1;
        //for (int startGen = $pagination -> total_pages(); startGen >= 1; startGen--)
        onDebugLog("CURRENT: " + (currentPage - 1) + " STARTING: " + startPoint + " ENDING: " + endPoint);
        String debugPage = "";
        int uiButtonCounter = 0;
        for (int pageCounter = startPoint; pageCounter >= endPoint; pageCounter--) {
            int pageValue = pageCounter;
            pageValue++;
            /*paginationUIButtons[uiButtonCounter].setText("" + pageValue);
            paginationUIButtons[uiButtonCounter].setTag("" + pageValue);*/
            onShowUIButton(uiButtonCounter, pageValue);
            /*if (pageValue == currentPage) {
                debugPage += "[" + pageValue + "] ";
            } else {
                debugPage += pageValue + " ";
            }*/
            uiButtonCounter++;
        }
        onHideUIButton(uiButtonCounter);
        onDebugLog("Page: " + debugPage);
    }

    private void onDebugLog(String argMessage) {
        if (!isDebug) {
            return;
        }
        //System.out.println(this.getClass().getSimpleName() + "----------------------------");
        String msgPartInfo = "";
        Map<String, Integer> tracingMap = new HashMap<String, Integer>();
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : elements) {
            String methodName = element.getMethodName();
            int lineNum = element.getLineNumber();
            boolean isExists = isLogMethodExists(methodName);
            if (isExists) {
                /*if (msgPartInfo.isEmpty()) {
                    msgPartInfo += methodName + " - (" + lineNum + ")";
                }*/
                //System.out.println(methodName + " - (" + lineNum + ")");
                msgPartInfo += methodName + "(" + lineNum + ") ";
                //tracingMap.put(methodName, lineNum);
            }
            //System.out.println(this.getClass().getSimpleName() + " - ELEMENT NAME: " + methodName + " Line: " + lineNum);
        }
        //System.out.println(this.getClass().getSimpleName() + "----------------------------");
        if (tracingMap.size() > 0) {
            //msgPartInfo = "";
            List<String> list = new ArrayList<String>();
            /*for (Map.Entry<String, Integer> entry : tracingMap.entrySet()) {
                String mapValue = entry.getKey() + " - (" + entry.getValue() + ") ";
                list.add(mapValue);
            }*/
            for (String result : tracingMap.keySet()) {
                //System.out.println(result + " => " + tracingMap.get(result));
                msgPartInfo += result + " - (" + tracingMap.get(result) + ") ";
            }
            //Collections.reverse(list);
            //msgPartInfo = list.toString();
        }
        msgPartInfo = msgPartInfo.trim();
        msgPartInfo = msgPartInfo.replaceAll("\\s+", ">");
        System.out.println(this.getClass().getSimpleName() + " - DEBUG_LOG_PRINT: " + argMessage + " - " + msgPartInfo);
    }

    private boolean isLogMethodExists(String argMethodName) {
        Class cls = this.getClass();
        //System.out.println(this.getClass().getSimpleName() + " - EXISTS: " + cls);
        //Method method = null;
        for (Method method : cls.getDeclaredMethods()) {
            if (method.getName().equals("onDebugLog")) {
                return false;
            }
            if (method.getName().equals(argMethodName)) {
                //System.out.println("Method " + argMethodName + " exists.");
                return true;
            }
        }
        return false;
        /*try {
            //method = this.getClass().getMethod(argMethodName);
            method = this.getClass().getDeclaredMethod(argMethodName);
            System.out.println(this.getClass().getSimpleName() + " - METHOD EXISTS: " + method);
            return true;
        } catch (Exception e) {
            System.out.println(this.getClass().getSimpleName() + " - METHOD EXCEPTION: " + e);
            return false;
        }*/
    }

    public static int getLineNumber() {
        return Thread.currentThread().getStackTrace()[2].getLineNumber();
    }
}
//IMPORTANT: https://stackoverflow.com/questions/42292731/java-check-if-a-class-exists-and-call-a-specific-method-if-it-exists?noredirect=1&lq=1