package com.sm.testui;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class ActFragment extends AppCompatActivity implements FragmentCallback {
    private Activity activity;
    private Context context;
    private FrameLayout sysFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_fragment);
        activity = this;
        context = this;
        sysFrameLayout = (FrameLayout) findViewById(R.id.sysFrameLayout);
        loadFragment(new FirstFragment());
    }

    private void loadFragment(Fragment argFragment) {
        /*FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();*/
        ObjectAnimator objectAnimator = new ObjectAnimator();
        objectAnimator.setPropertyName("animX");
        objectAnimator.setFloatValues(0, 1);
        //objectAnimator.setTarget(logoView);//call this when your ready to set target
        objectAnimator.setDuration(1000);
        //setCustomAnimations(R.anim.enter_from_left, R.anim.enter_from_left, R.anim.exit_to_left, R.anim.exit_to_left);
        FragmentTransaction fragmentTransaction = activity.getFragmentManager().beginTransaction();
        //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.slide_in, R.animator.slide_out);
        /*fragmentTransaction.setCustomAnimations(
                R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                R.animator.card_flip_left_in, R.animator.card_flip_left_out);*/
        fragmentTransaction.setCustomAnimations(
                R.animator.in_from_left, R.animator.in_from_right);
        //fragmentTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
        //fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_right, R.anim.exit_to_left);
        fragmentTransaction.replace(R.id.sysFrameLayout, argFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


        /*// create a FragmentManager
        FragmentManager fm = getFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.sysFrameLayout, argFragment);
        fragmentTransaction.commit(); // save sysFrameLayout changes*/
    }

    @Override
    public void onButtonClicked(String argValue) {
        if (argValue.equalsIgnoreCase("second")) {
            loadFragment(new FirstFragment());
        } else if (argValue.equalsIgnoreCase("first")) {
            loadFragment(new SecondFragment());
        }
        //Toast.makeText(context, "Clicked Button", Toast.LENGTH_LONG).show();
    }
}
/*
http://trickyandroid.com/fragments-translate-animation/
*/