package com.rz.wareadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rz Rasel on 2018-02-27.
 */

public class SparkedArrayAdapter<T> extends ArrayAdapter<T> {
    private Context context;
    //private static Context staticContext;
    private int layoutResourceId;
    public ArrayList<SparkedModelRowViewFields> rowViewFieldListItems = new ArrayList<SparkedModelRowViewFields>();
    private ArrayList<T> adapterListItems; // = new ArrayList<ModelInit>();
    private OnFieldListenerHandler onFieldListenerHandler = null;
    //private AdapterDynamicArrayAdapter.OnEventsListenerHandler onEventsListenerHandler = null;
    private boolean isClicked = false;
    private int selectedPosition;
    private int checkedColor;

    public SparkedArrayAdapter(Context argContext, int argLayoutResourceId, ArrayList<T> argAdapterListItems) {
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

    public SparkedArrayAdapter onSetRowViewFieldList(ArrayList<SparkedModelRowViewFields> argRowViewFieldListItems) {
        rowViewFieldListItems = argRowViewFieldListItems;
        return this;
    }

    public SparkedArrayAdapter onSetRowViewFieldListenerHandler(OnFieldListenerHandler argOnFieldListenerHandler) {
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
            SparkedInitializedRowFields.onSetRowFields(context, rowViewRoot, rowViewFieldListItems);
            //Object item = getItem(argPosition);
            Object items = adapterListItems.get(argPosition);
            //if(list.get(argPosition)  instanceof A)
            if (onFieldListenerHandler != null) {
                onFieldListenerHandler.onSetFieldValue(rowViewFieldListItems, items);
            }
            onSetRowViewFieldsValues(rowViewFieldListItems, items);
        }
        //System.out.println("|----|------------|AdapterData|----|" + argPosition);
        return rowViewRoot;
    }

    private void onSetRowViewFieldsValues(ArrayList<SparkedModelRowViewFields> argRowViewFieldList, Object argObject) {
        SparkedModelRowScope itemScope = (SparkedModelRowScope) argObject;
        HashMap<String, String> hashMapRowIdValueItem = itemScope.getHashMapRowIdValueItems();
        for (SparkedModelRowViewFields itemField : argRowViewFieldList) {
            Object object = itemField.getFieldObject();
            String fieldResourceId = itemField.getFieldResourceId();
            if (object instanceof TextView) {
                TextView rowField = null;
                rowField = (TextView) itemField.getFieldObject();
                if (hashMapRowIdValueItem.containsKey(fieldResourceId)) {
                    rowField.setText(hashMapRowIdValueItem.get(fieldResourceId));
                }
                //System.out.println(itemField.getFieldResourceId());
            } else if (object instanceof ImageView) {
                ImageView rowField = null;
                rowField = (ImageView) itemField.getFieldObject();
                if (hashMapRowIdValueItem.containsKey(fieldResourceId)) {
                    int resourceId = Integer.valueOf(hashMapRowIdValueItem.get(fieldResourceId));
                    rowField.setImageDrawable(context.getResources().getDrawable(resourceId));
                }
                System.out.println(itemField.getFieldResourceId());
            }
            //System.out.println("------" + fieldResourceId);
        }
    }

    public SparkedModelRowViewFields onSetRowViewField(FIELD_TYPE argFieldType, String argFieldResourceId) {
        if (argFieldType == FIELD_TYPE.TEXT_VIEW) {
            TextView textView = new TextView(context);
            return SparkedModelRowViewFields.onGetSetModelRow(textView, argFieldResourceId);
        } else if (argFieldType == FIELD_TYPE.IMAGE_VIEW) {
            ImageView imageView = new ImageView(context);
            return SparkedModelRowViewFields.onGetSetModelRow(imageView, argFieldResourceId);
        }
        return null;
    }

    public void setSelectedPosition(int argPosition, boolean argIsClicked, int argCheckedColor) {

        this.selectedPosition = argPosition;
        this.isClicked = argIsClicked;
        this.checkedColor = argCheckedColor;

    }

    public interface OnFieldListenerHandler {
        public void onSetFieldValue(ArrayList<SparkedModelRowViewFields> argRowViewFieldList, Object argObject);
        //public void onSetResourceLayout(int argLayoutResourceId);
        //Customer cust = (Customer) pObject;
    }

    public enum FIELD_TYPE {
        TEXT_VIEW("text_view"),
        IMAGE_VIEW("image_view");
        private String fieldType;

        FIELD_TYPE(String argFieldType) {
            this.fieldType = argFieldType;
        }

        public String getFieldType() {
            return this.fieldType;
        }
    }
}