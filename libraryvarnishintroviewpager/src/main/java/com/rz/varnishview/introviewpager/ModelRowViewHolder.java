package com.rz.varnishview.introviewpager;

import java.util.Objects;

/**
 * Created by Rz Rasel on 2017-12-17.
 */

public class ModelRowViewHolder {
    public Object fieldObject;
    public String fieldResourceId;
    //public int fieldResourceId;
    public String fieldExtra;

        /*public RowViewHolder() {
            //
        }*/

    public ModelRowViewHolder(Object argFieldObject, String argFieldResourceId, String argFieldExtra) {
        fieldObject = argFieldObject;
        fieldResourceId = argFieldResourceId;
        fieldExtra = argFieldExtra;
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

    public String getExtra() {
        return fieldExtra;
    }

    public void setExtra(String argFieldExtra) {
        fieldExtra = argFieldExtra;
    }

    public static ModelRowViewHolder onGetSetRow(Object argFieldObject, String argFieldResourceId, String argFieldExtra) {
        return new ModelRowViewHolder(argFieldObject, argFieldResourceId, argFieldExtra);
    }
}
