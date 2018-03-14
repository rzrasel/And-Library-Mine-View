package com.rz.armorpagination;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rz Rasel on 2018-03-14.
 */

public class ArmorReversePagination extends LinearLayout {
    private Activity activity;
    private Context context;

    private int totalItems = 54;
    private int itemPerPage = 5;
    private int totalPages = 0;
    private int range = 5;
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
        currentPage = 7;
        initValues();
        getTotalPages();
        getOffset();
        onReversePaging();
    }

    private void initValues() {
        currentPage = (currentPage > 0) ? currentPage : 1;
        onDebugLog("Current Page: " + currentPage);
    }

    private int getTotalPages() {
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

    private void onReversePaging() {
        int startPoint = 0;
        int endPoint = 0;
        endPoint = getStartingPage();
        startPoint = getEndingPage();
        //startPoint = currentPage - 1;
        //for (int startGen = $pagination -> total_pages(); startGen >= 1; startGen--)
        onDebugLog("CURRENT: " + (currentPage - 1) + " STARTING: " + startPoint + " ENDING: " + endPoint);
        String debugPage = "";
        for (int pageCounter = startPoint; pageCounter >= endPoint; pageCounter--) {
            int pageValue = pageCounter;
            pageValue++;
            if (pageValue == currentPage) {
                debugPage += " [";
            }
            debugPage += " " + pageValue + " ";
            if (pageValue == currentPage) {
                debugPage += "] ";
            }
        }
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