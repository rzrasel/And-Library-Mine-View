package com.rz.usagesexample;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ActSplash extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private Spinner sysSpinner;
    private NoDefaultSpinner sysSpinnerTwo;
    private SearchableSpinnerOne spinner1;
    private TextView first, second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        activity = this;
        context = this;
        sysSpinner = (Spinner) findViewById(R.id.sysSpinner);
        sysSpinnerTwo = (NoDefaultSpinner) findViewById(R.id.sysSpinnerTwo);
        List<String> listTwo = new ArrayList<String>();
        listTwo.add("list 1-1");
        listTwo.add("list 2-1");
        listTwo.add("list 3-1");
        ArrayAdapter<String> dataAdapterTwo = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, listTwo);
        dataAdapterTwo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sysSpinnerTwo.setAdapter(dataAdapterTwo);
        sysSpinnerTwo.setPrompt("Select your favorite Planet!");
        sysSpinnerTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItem = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //
            }
        });
        List<String> list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sysSpinner.setAdapter(dataAdapter);

        String[] data = new String[]{"a", "b", "c"};
        first = (TextView) findViewById(R.id.first);
        spinner1 = (SearchableSpinnerOne) findViewById(R.id.spinner1);
        spinner1.setData(data);
        spinner1.setDefaultText("Select country");
        spinner1.setInvalidTextColor(getResources().getColor(R.color.colorAccent));
        spinner1.setSelectionListener(new SearchableSpinnerOne.OnSelectionListener() {
            @Override
            public void onSelect(int spinnerId, int position, String value) {
                //Log.i("Select1", "Position : " + position + " : Value : " + value + " : " + spinnerId);
                setText(first, value);

            }
        });
    }
    public void getFirstValue(View view) {
        //Log.i("First", spinner1.getValue() + "");
        setText(first, spinner1.getValue());
    }

    public void getSecondValue(View view) {
        //Log.i("Second", spinner2.getValue() + "");
        //setText(second, spinner2.getValue());
    }

    private void setText(TextView textView, String text) {
        if (text != null)
            textView.setText(text);
        else
            textView.setText("Nothing selected...");
    }
}
