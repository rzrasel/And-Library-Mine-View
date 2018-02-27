package com.rz.wareadapter;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Rz Rasel on 2018-02-27.
 */

public class SparkedModelRowViewFields {
    private Object fieldObject;
    private String fieldResourceId;
    public static ArrayList<SparkedModelRowViewFields> rowFieldListItems = new ArrayList<SparkedModelRowViewFields>();

    public SparkedModelRowViewFields() {
    }

    public SparkedModelRowViewFields(Object argFieldObject, String argFieldResourceId) {
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

    public static SparkedModelRowViewFields onGetSetModelRow(Object argFieldObject, String argFieldResourceId) {
        return new SparkedModelRowViewFields(argFieldObject, argFieldResourceId);
    }
}
