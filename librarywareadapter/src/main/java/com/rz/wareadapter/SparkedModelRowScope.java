package com.rz.wareadapter;

import java.util.HashMap;

/**
 * Created by Rz Rasel on 2018-02-27.
 */

public class SparkedModelRowScope {

    //<String, String> 1) String is for "field id" or "field resource id", 2) String is for field value
    private HashMap<String, String> hashMapRowIdValueItems = new HashMap<String, String>();
    private String listenerValue = null;
    private Class listenerClass = null;
    private LISTENER_TYPE listenerType;

    public SparkedModelRowScope() {
    }

    /*public SharkModelRowScope(HashMap<String, String> argHashMapRowIdValueItems) {
        this.hashMapRowIdValueItems = argHashMapRowIdValueItems;
        this.listenerClass = null;
    }*/

    public SparkedModelRowScope(HashMap<String, String> argHashMapRowIdValueItems, Class<?> argListenerClass, LISTENER_TYPE argListenerType) {
        this.hashMapRowIdValueItems = argHashMapRowIdValueItems;
        this.listenerClass = argListenerClass;
        this.listenerType = argListenerType;
    }

    public SparkedModelRowScope(HashMap<String, String> argHashMapRowIdValueItems, String argListenerValue, LISTENER_TYPE argListenerType) {
        this.hashMapRowIdValueItems = argHashMapRowIdValueItems;
        this.listenerValue = argListenerValue;
        this.listenerType = argListenerType;
    }

    public HashMap<String, String> getHashMapRowIdValueItems() {
        return this.hashMapRowIdValueItems;
    }

    public void setHashMapRowIdValueItems(HashMap<String, String> argHashMapRowIdValueItems) {
        this.hashMapRowIdValueItems = argHashMapRowIdValueItems;
    }

    public Class getListenerClass() {
        return this.listenerClass;
    }

    public void setListenerClass(Class<?> argListenerClass) {
        this.listenerClass = argListenerClass;
    }

    public String getListenerValue() {
        return this.listenerValue;
    }

    public void setListenerValue(String argListenerValue) {
        this.listenerValue = argListenerValue;
    }

    public LISTENER_TYPE getListenerType() {
        return this.listenerType;
    }

    public void setListenerType(LISTENER_TYPE argListenerType) {
        this.listenerType = argListenerType;
    }

    public enum LISTENER_TYPE {
        CLASS("class_reference"),
        NONE("none"),
        RESOURCE_ID("resource_id"),
        STRING("string_data"),
        URL("url_address");
        private String fieldType;

        LISTENER_TYPE(String argFieldType) {
            this.fieldType = argFieldType;
        }

        public String getFieldType() {
            return this.fieldType;
        }
    }

    /*public static SharkModelRowScope onGetSetRowTest(HashMap<String, String> argHashMapRowIdValueItems) {
        return new SharkModelRowScope(argHashMapRowIdValueItems);
    }*/

    public static SparkedModelRowScope onGetSetRow(HashMap<String, String> argHashMapRowIdValueItems, Class<?> argListenerClass, LISTENER_TYPE argListenerType) {
        return new SparkedModelRowScope(argHashMapRowIdValueItems, argListenerClass, argListenerType);
    }

    public static SparkedModelRowScope onGetSetRow(HashMap<String, String> argHashMapRowIdValueItems, String argListenerValue, LISTENER_TYPE argListenerType) {
        return new SparkedModelRowScope(argHashMapRowIdValueItems, argListenerValue, argListenerType);
    }
}