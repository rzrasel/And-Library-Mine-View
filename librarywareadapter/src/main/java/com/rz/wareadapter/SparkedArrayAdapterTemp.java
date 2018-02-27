package com.rz.wareadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

/**
 * Created by Rz Rasel on 2018-02-11.
 */
/**
 * @deprecated As of 2018-02-27
 */
@Deprecated
public class SparkedArrayAdapterTemp<T> extends ArrayAdapter<T> {
    private Context context;
    private int layoutResourceId;
    private ArrayList<T> listAdapterItems; // = new ArrayList<ModelInit>();
    private ArrayList<T> listFilterItems;
    private OnFieldListenerHandler onFieldListenerHandler = null;
    private OnEventsListenerHandler onEventsListenerHandler = null;

    public SparkedArrayAdapterTemp(Context argContext, int argLayoutResourceId, ArrayList<T> argListItems) {
        super(argContext, argLayoutResourceId, argListItems);
        this.context = argContext;
        this.layoutResourceId = argLayoutResourceId;
        this.listAdapterItems = argListItems;
        listFilterItems = new ArrayList<T>();
        this.listFilterItems.addAll(argListItems);
    }

    @Override
    public int getCount() {
        return listAdapterItems.size();
    }

    @Override
    public T getItem(int position) {
        return listAdapterItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void onSetListRowViewFields(OnFieldListenerHandler argOnFieldListenerHandler, ArrayList<ModelRowViewHolder> argListRowViewFields) {
        onFieldListenerHandler = argOnFieldListenerHandler;
        listRowViewFields = argListRowViewFields;
    }

    //Product p = getItem(position);
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
            Object item = listAdapterItems.get(argPosition);
            //if(list.get(argPosition)  instanceof A)
            if (onFieldListenerHandler != null) {
                onFieldListenerHandler.onSetFieldValue(listRowViewFields, item);
            }
        }
        //System.out.println("|----|------------|AdapterData|----|" + argPosition);
        return rowViewRoot;
    }

    /*public void filter(String argCharText) {
        argCharText = argCharText.toLowerCase(Locale.getDefault());
        listFilterItems.clear();
        if (argCharText.length() == 0) {
            listFilterItems.addAll(listAdapterItems);
        } else {
            for (Object object : listAdapterItems) {
            //
                if (wp.getCountry().toLowerCase(Locale.getDefault()).contains(argCharText)) {
                    listFilterItems.add(object);
                }
            }
        }
        notifyDataSetChanged();
    }*/

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
/*
https://stackoverflow.com/questions/36139024/arrayadapter-for-more-then-one-class-type
https://stackoverflow.com/questions/7114109/generics-with-arrayadapters
*/