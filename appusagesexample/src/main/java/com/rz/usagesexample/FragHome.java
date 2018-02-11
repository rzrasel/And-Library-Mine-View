package com.rz.usagesexample;

/**
 * Created by Rz Rasel on 2017-12-23.
 */

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragHome extends Fragment {
    private View rootView;
    private FragmentActivity listener;
    private OnFragmentCallbackListenerHandler onFragmentCallbackListener;
    private Button sysBtnRegi;

    @Override
    public View onCreateView(LayoutInflater argInflater, ViewGroup argContainer, Bundle argSavedInstanceState) {
        rootView = argInflater.inflate(R.layout.frag_home, argContainer, false);
        /*sysBtnRegi = (Button) rootView.findViewById(R.id.sysBtnRegi);
        sysBtnRegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View argView) {
                if (onFragmentCallbackListener != null) {
                    onFragmentCallbackListener.onButtonClicked("first");
                }
            }
        });*/
        return rootView;
    }

    @Override
    public void onAttach(Context argContext) {
        super.onAttach(argContext);
        /*if (argContext instanceof Activity) {
            this.listener = (FragmentActivity) argContext;
        }*/
        //fragmentCallback = (FragmentCallback) argContext;
        try {
            //eventListener = (EventListener) context;
            onFragmentCallbackListener = (OnFragmentCallbackListenerHandler) argContext;
        } catch (ClassCastException e) {
            //LogWriter.Log(context.toString() + " must implement EventListener");
            //throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }
        /*try {
            //eventListener = (EventListener) context;
            fragmentEventListener = (FragmentEventListener) context;
        } catch (ClassCastException e) {
            LogWriter.Log(context.toString() + " must implement EventListener");
            //throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }*/
    }

    @Override
    public void onDetach() {
        onFragmentCallbackListener = null;
        super.onDetach();
        /*super.onDetach();
        this.listener = null;*/
    }

    /*public interface Callback {
        public void onButtonClicked(View radiobtn);
    }*/
}
//https://guides.codepath.com/android/creating-and-using-fragments
//http://abhiandroid.com/ui/fragment
//DemoFragment fragmentDemo = (DemoFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentDemo);
//https://stackoverflow.com/questions/40115750/activity-to-fragment-on-button-click