package com.rz.usagesexample;

/**
 * Created by developer on 2/7/18.
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;

/**
 * Created by Azim Ansari on 11/6/2015.
 * <p/>
 * An {@link AutoCompleteTextView} customized to work similar to a {@link android.widget.Spinner} also
 * called dropdown box. It allows user to search the dropdown items and select any one valid value.
 * It takes care of valid selection in the box. It performs validation whenever it loses focus or on getting value {@link #getValue()}.
 *
 * @attr ref android.R.styleable#SearchableSpinner_defaultText
 * @attr ref android.R.styleable#SearchableSpinner_invalidTextColor
 */
public class SearchableSpinnerOne extends android.support.v7.widget.AppCompatAutoCompleteTextView implements View.OnClickListener, View.OnFocusChangeListener, AutoCompleteTextView.Validator, AdapterView.OnItemClickListener {

    /**
     * Data set for the adapter
     * Always using string array as data set
     * If user passed {@link ArrayList<String>} then it is converted automatically to {@link String[]}
     */
    private String[] data;

    /**
     * Current selected valid value from the dropdown
     * as displaying in the dropdown.
     * It will be null if there is no valid value in dropdown or user is filtering something
     */
    private String value;

    /**
     * Listener for item selection from the dropdown
     * {@link OnSelectionListener}
     */
    private OnSelectionListener selectionListener;

    public SearchableSpinnerOne(Context context) {
        super(context);
        init();
    }

    public SearchableSpinnerOne(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyAttributes(attrs, 0, 0);
        init();
    }

    public SearchableSpinnerOne(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyAttributes(attrs, defStyleAttr, 0);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SearchableSpinnerOne(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        //super(context, attrs, defStyleAttr, defStyleRes);
        super(context, attrs, defStyleAttr);
        applyAttributes(attrs, defStyleAttr, defStyleRes);
        init();
    }

    /**
     * Initializing the spinner
     */
    private void init() {
        this.setBackgroundResource(R.drawable.background);
        this.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_drop_down, 0);
        float scale = getResources().getDisplayMetrics().density;
        int padding = (int) (12 * scale + 0.5f);
        this.setSingleLine();
        this.setPadding(padding, padding, padding, padding);
        this.setThreshold(1);
        this.setOnClickListener(this);
        this.setOnFocusChangeListener(this);
        this.setValidator(this);
        this.setOnItemClickListener(this);
        this.setText(null);
    }

    /**
     * Applying attributes
     */
    private void applyAttributes(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SearchableSpinner, defStyleAttr, defStyleRes);
        try {
            setDefaultText(getText());
            setDefaultText(a.getString(R.styleable.SearchableSpinner_defaultText));
            setInvalidTextColor(a.getColor(R.styleable.SearchableSpinner_invalidTextColor, getCurrentHintTextColor()));
        } finally {
            a.recycle();
        }
    }

    /**
     * Set the array data for the dropdown.
     *
     * @param data {@link String[]} to use as data set.
     */
    public void setData(String[] data) {
        this.data = data;
        setAdapter(this.data);

    }

    /**
     * Set the list of data for the dropdown.
     *
     * @param data {@link ArrayList<String>} to set
     */
    public void setData(ArrayList<String> data) {
        this.data = data.toArray(new String[data.size()]);
        setAdapter(this.data);
    }

    /**
     * Like {@link #setText(CharSequence)}, except that it can disable filtering.
     *
     * @param text   text to set
     * @param filter If <code>false</code>, no filtering will be performed.
     *               <p/>
     *               Note: We are blocking filter
     */
    @Override
    public void setText(CharSequence text, boolean filter) {
        setDefaultText(text);
    }

    /**
     * Sets the adapter that provides the data and the views to represent the data
     * in this widget.
     *
     * @param data data set passed to the adapter.
     */
    private void setAdapter(String[] data) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, data);
        setAdapter(adapter);
    }

    /**
     * Sets the default text when there is nothing selected.
     *
     * @param defaultText default text to set.
     */
    public void setDefaultText(CharSequence defaultText) {
        if (defaultText != null)
            setHint(defaultText);
    }

    /**
     * Sets the default text when there is nothing selected.
     *
     * @param defaultText default text resource id to set.
     */
    public void setDefaultText(@StringRes int defaultText) {
        setHint(defaultText);
    }

    /**
     * Set default text color when the user input is not valid.
     *
     * @param invalidTextColor color to use when the input is not valid
     */
    public void setInvalidTextColor(@ColorInt int invalidTextColor) {
        setHintTextColor(invalidTextColor);
    }

    /**
     * Set selection listener.
     * This listener will be notified whenever user selects an item from the dropdown.
     * See {@link OnSelectionListener}
     *
     * @param selectionListener listener to notify for item selection
     */
    public void setSelectionListener(OnSelectionListener selectionListener) {
        this.selectionListener = selectionListener;
    }

    /**
     * Get the currently selected valid text selected by the user.
     * <p/>
     * Note: May return null if the value inside the spinner is not valid
     *
     * @return currently selected value or null if invalid input or nothing selected
     */
    public String getValue() {
        performValidation();
        return value;
    }

    /**
     * Called when dropdown box has been clicked.
     *
     * @param v The {@link SearchableSpinnerOne} as {@link View}
     */
    @Override
    public void onClick(View v) {
        this.showDropDown();
    }

    /**
     * Called when the focus state of a view has changed.
     *
     * @param v        The {@link SearchableSpinnerOne} as {@link View}.
     * @param hasFocus The new focus state of {@link SearchableSpinnerOne}.
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus)
            showDropDown();
        else
            performValidation();
    }

    /**
     * Validates the specified text.
     *
     * @param text text to validate
     * @return true If the text currently in the text editor is valid.
     * @see #fixText(CharSequence)
     */
    @Override
    public boolean isValid(CharSequence text) {
        for (String s : data) {
            if (s.equalsIgnoreCase(text.toString())) {
                value = s;
                return true;
            }
        }
        value = null;
        return false;
    }

    /**
     * Corrects the specified text to make it valid.
     *
     * @param invalidText A string that doesn't pass validation: isValid(invalidText)
     *                    returns false
     * @return null so that the invalid text in the box is removed and default text with invalid text color is displayed
     * @see #isValid(CharSequence)
     */
    @Override
    public CharSequence fixText(CharSequence invalidText) {
        return null;
    }

    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p/>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectionListener.onSelect(getId(), position, (String) parent.getItemAtPosition(position));
    }

    /**
     * Listener for item selection from the dropdown
     *
     * @see #setSelectionListener(OnSelectionListener)
     */
    public interface OnSelectionListener {

        /**
         * Callback method to be invoked when an item in the dropdown has
         * been selected.
         *
         * @param spinnerId Id of the spinner to which the listener is attached.
         * @param position  The position of the view in the dropdown.
         * @param value     A string value corresponding to the position.
         */
        void onSelect(int spinnerId, int position, String value);
    }
}