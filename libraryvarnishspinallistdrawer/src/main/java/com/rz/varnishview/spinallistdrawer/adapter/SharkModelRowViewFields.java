package com.rz.varnishview.spinallistdrawer.adapter;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Rz Rasel on 2017-12-26.
 */

public class SharkModelRowViewFields {
    private Object fieldObject;
    private String fieldResourceId;
    public static ArrayList<SharkModelRowViewFields> rowFieldListItems = new ArrayList<SharkModelRowViewFields>();

    public SharkModelRowViewFields() {
    }

    public SharkModelRowViewFields(Object argFieldObject, String argFieldResourceId) {
        //new TextView(context)
        fieldObject = argFieldObject;
        fieldResourceId = argFieldResourceId;
    }

    public Object getFieldObject() {
        return fieldObject;
    }

    public void setFieldObject(Objects argFieldObject) {
        fieldObject = argFieldObject;
    }

    public String getFieldResourceId() {
        return fieldResourceId;
    }

    public void setFieldResourceId(String argFieldResourceId) {
        fieldResourceId = argFieldResourceId;
    }

    public static SharkModelRowViewFields onGetSetModelRow(Object argFieldObject, String argFieldResourceId) {
        return new SharkModelRowViewFields(argFieldObject, argFieldResourceId);
    }
}
