package com.rz.varnishview.spinallistdrawer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rz Rasel on 2017-12-26.
 */

public class SharkArrayAdapter<T> extends ArrayAdapter<T> {
    private Context context;
    private int layoutResourceId;
    public ArrayList<SharkModelRowViewFields> rowViewFieldListItems = new ArrayList<SharkModelRowViewFields>();
    private ArrayList<T> adapterListItems; // = new ArrayList<ModelInit>();
    private OnFieldListenerHandler onFieldListenerHandler = null;
    //private AdapterDynamicArrayAdapter.OnEventsListenerHandler onEventsListenerHandler = null;
    private boolean isClicked = false;
    private int selectedPosition;
    private int checkedColor;

    public SharkArrayAdapter(Context argContext, int argLayoutResourceId, ArrayList<T> argAdapterListItems) {
        super(argContext, argLayoutResourceId, argAdapterListItems);
        this.context = argContext;
        this.layoutResourceId = argLayoutResourceId;
        this.adapterListItems = argAdapterListItems;
    }

    @Override
    public int getCount() {
        return adapterListItems.size();
    }

    @Override
    public T getItem(int argPosition) {
        return adapterListItems.get(argPosition);
    }

    @Override
    public long getItemId(int argPosition) {
        return argPosition;
    }

    public SharkArrayAdapter onSetRowViewFieldList(ArrayList<SharkModelRowViewFields> argRowViewFieldListItems) {
        rowViewFieldListItems = argRowViewFieldListItems;
        return this;
    }

    public SharkArrayAdapter onSetRowViewFieldListenerHandler(OnFieldListenerHandler argOnFieldListenerHandler) {
        onFieldListenerHandler = argOnFieldListenerHandler;
        return this;
    }

    @Override
    public View getView(int argPosition, View argConvertView, ViewGroup argParent) {
        View rowViewRoot = argConvertView;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (argConvertView == null) {
            rowViewRoot = layoutInflater.inflate(layoutResourceId, argParent, false);
            //rowViewRoot.setTag(listRowViewFields);
        } else {
        }
        if (rowViewRoot != null) {
            //onInitializedLayoutFields(context, rowViewRoot);
            SharkInitializedRowLayout.onSetRowFields(context, rowViewRoot, rowViewFieldListItems);
            //Object item = getItem(argPosition);
            Object items = adapterListItems.get(argPosition);
            //if(list.get(argPosition)  instanceof A)
            if (onFieldListenerHandler != null) {
                onFieldListenerHandler.onSetFieldValue(rowViewFieldListItems, items);
            }
        }
        //System.out.println("|----|------------|AdapterData|----|" + argPosition);
        return rowViewRoot;
    }

    public void setSelectedPosition(int argPosition, boolean argIsClicked, int argCheckedColor) {

        this.selectedPosition = argPosition;
        this.isClicked = argIsClicked;
        this.checkedColor = argCheckedColor;

    }

    public interface OnFieldListenerHandler {
        public void onSetFieldValue(ArrayList<SharkModelRowViewFields> argRowViewFieldList, Object argObject);
        //public void onSetResourceLayout(int argLayoutResourceId);
        //Customer cust = (Customer) pObject;
    }
}