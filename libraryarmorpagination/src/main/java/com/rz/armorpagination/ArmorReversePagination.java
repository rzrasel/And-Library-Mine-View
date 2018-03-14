package com.rz.armorpagination;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.lang.reflect.Method;

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
        initValues();
        getTotalPages();
        getOffset();
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

    private void onDebugLog(String argMessage) {
        System.out.println(this.getClass().getSimpleName() + "----------------------------");
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : elements) {
            String methodName = element.getMethodName();
            int lineNum = element.getLineNumber();
            isLogMethodExists(methodName);
            System.out.println(this.getClass().getSimpleName() + " - ELEMENT NAME: " + methodName + " Line: " + lineNum);
        }
        System.out.println(this.getClass().getSimpleName() + "----------------------------");
        System.out.println(this.getClass().getSimpleName() + " - DEBUG_LOG_PRINT: " + argMessage);
    }

    private boolean isLogMethodExists(String argMethodName) {
        Class cls = this.getClass();
        System.out.println(this.getClass().getSimpleName() + " - EXISTS: " + cls);
        //Method method = null;
        for (Method method : cls.getDeclaredMethods()) {
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