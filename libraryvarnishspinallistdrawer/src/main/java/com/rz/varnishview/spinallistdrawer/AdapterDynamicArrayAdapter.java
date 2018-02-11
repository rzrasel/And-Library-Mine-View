package com.rz.varnishview.spinallistdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Rz Rasel on 2017-12-21.
 */

public class AdapterDynamicArrayAdapter<T> extends ArrayAdapter<T> {
    private Context context;
    private int layoutResourceId;
    private ArrayList<T> adapterListItems; // = new ArrayList<ModelInit>();
    private OnFieldListenerHandler onFieldListenerHandler = null;
    private OnEventsListenerHandler onEventsListenerHandler = null;
    private boolean isClicked = false;
    private int selectedPosition;
    private int checkedColor;

    public AdapterDynamicArrayAdapter(Context argContext, int argLayoutResourceId, ArrayList<T> argAdapterListItems) {
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

    public void onSetListRowViewFields(OnFieldListenerHandler argOnFieldListenerHandler, ArrayList<ModelRowViewHolder> argListRowViewFields) {
        onFieldListenerHandler = argOnFieldListenerHandler;
        listRowViewFields = argListRowViewFields;
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
            onInitializedLayoutFields(context, rowViewRoot);
            //Object item = getItem(argPosition);
            Object items = adapterListItems.get(argPosition);
            //if(list.get(argPosition)  instanceof A)
            if (onFieldListenerHandler != null) {
                onFieldListenerHandler.onSetFieldValue(listRowViewFields, items);
            }
        }
        //System.out.println("|----|------------|AdapterData|----|" + argPosition);
        return rowViewRoot;
    }

    /*if (isClicked) {
        if (position == selectedPosition) {
            //layout.setBackgroundColor(mContext.getResources().getColor(R.color.app_bg));
            layout.setBackgroundColor(checkedColor);
            holderNormal.sysTextViewIcon.setTextColor(Color.parseColor("#ffffff"));
            holderNormal.sysTextViewTitle.setTextColor(Color.parseColor("#ffffff"));
        } else {
            //layout.setBackgroundColor(mContext.getResources().getColor(R.color.normal_bg));
            layout.setBackgroundColor(Color.parseColor("#ffffff"));
            holderNormal.sysTextViewIcon.setTextColor(checkedColor);
            holderNormal.sysTextViewTitle.setTextColor(Color.parseColor("#000000"));
        }
    } else {
        if (position == 0) {
            //layout.setBackgroundColor(checkedColor);
        }
    }*/
    public void setSelectedPosition(int argPosition, boolean argIsClicked, int argCheckedColor) {

        this.selectedPosition = argPosition;
        this.isClicked = argIsClicked;
        this.checkedColor = argCheckedColor;

    }

    private void onInitializedLayoutFields(Context argContext, View argRootView) {
        for (int i = 0; i < listRowViewFields.size(); i++) {
            Object objField = listRowViewFields.get(i).getFieldObject();
            String textResourceID = listRowViewFields.get(i).getFieldResourceId();
            String textExtra = listRowViewFields.get(i).getExtra();
            int resourceID = context.getResources().getIdentifier(textResourceID, "id", argContext.getPackageName());
            if (objField instanceof TextView) {
                TextView fieldTextView = (TextView) argRootView.findViewById(resourceID);
                listRowViewFields.set(i, onGetSetModelRowViewData(fieldTextView, textResourceID, textExtra));
                //System.out.println("‒‒‒‒|‑‑‑‑|――――――――――――|TEXT_VIEW|");
            } else if (objField instanceof ImageView) {
                ImageView fieldImageView = (ImageView) argRootView.findViewById(resourceID);
                listRowViewFields.set(i, onGetSetModelRowViewData(fieldImageView, textResourceID, textExtra));
            }
        }
    }

    public interface OnFieldListenerHandler {
        public void onSetFieldValue(ArrayList<ModelRowViewHolder> argListRowViewFields, Object argObject);
        //Customer cust = (Customer) pObject;
    }

    public interface OnEventsListenerHandler {
    }

    public enum APP_FIELDS {
        TEXT_VIEW("text_view"),
        IMAGE_VIEW("image_view");
        private String fieldType;

        APP_FIELDS(String argFieldType) {
            this.fieldType = argFieldType;
        }

        public String getFieldType() {
            return this.fieldType;
        }
    }

    //private Map<String, Object> mapAppFields = new HashMap<String, Object>();
    public ArrayList<ModelRowViewHolder> listRowViewFields = new ArrayList<ModelRowViewHolder>();

    public static class ModelRowViewHolder {
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
    }

    public ModelRowViewHolder onGetSetModelRowViewData(Object argFieldObject, String argFieldResourceId, String argFieldExtra) {
        return new ModelRowViewHolder(argFieldObject, argFieldResourceId, argFieldExtra);
    }
}
