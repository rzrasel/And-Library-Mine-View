package com.sm.testui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SecondFragment extends Fragment {
    private View rootView;
    private FragmentCallback fragmentCallback;
    private Button sysBtnRegi;

    @Override
    public View onCreateView(LayoutInflater argInflater, ViewGroup argContainer, Bundle argSavedInstanceState) {
        // Inflate the layout for this fragment
        rootView = argInflater.inflate(R.layout.fragment_second, argContainer, false);
        sysBtnRegi = (Button) rootView.findViewById(R.id.sysBtnRegi);
        sysBtnRegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View argView) {
                if (fragmentCallback != null) {
                    fragmentCallback.onButtonClicked("second");
                }
            }
        });
        return rootView;
        //return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentCallback) {
            fragmentCallback = (FragmentCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentCallback = null;
    }
}
