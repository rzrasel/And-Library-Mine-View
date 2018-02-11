package com.rz.wareadapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Rz Rasel on 2018-02-11.
 */

public class SharkFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    //|------------------------------------------------------------|
    private String userSession = new String();
    private int mNumOfTabs;
    //|------------------------------------------------------------|

    public SharkFragmentStatePagerAdapter(FragmentManager argFragmentManager, int argNumOfTabs, String argUserSession) {
        super(argFragmentManager);
        this.mNumOfTabs = argNumOfTabs;
        this.userSession = argUserSession;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        /*bundle.putSerializable(APPConstants.SESSION.KEY, userSession);
        switch (position) {
            case 0:
                fragment = new FragReceiveMessage();
                break;
            case 1:
                fragment = new FragSendBoxMessage();
                break;
            case 2:
                fragment = new FragDraftsMessage();
                break;
            default:
                fragment = null;
        }
        if (fragment != null) {
            fragment.setArguments(bundle);
        }*/
        return fragment;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}