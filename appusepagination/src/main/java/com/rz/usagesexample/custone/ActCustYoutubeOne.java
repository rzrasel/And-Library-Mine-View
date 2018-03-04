package com.rz.usagesexample.custone;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rz.usagesexample.R;

public class ActCustYoutubeOne extends AppCompatActivity {
    private String GOOGLE_API_KEY = "AIzaSyAqqIRxuAOuN1fsHCoc41-Lo0-XKiB8asc";

    private static final String PLAYER_FRAG_TAG = "player_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cust_youtube_one);
        FragmentManager mgr = getFragmentManager();
        Fragment playerFrag = mgr.findFragmentByTag(PLAYER_FRAG_TAG);

        /*if(playerFrag == null) {
            final Integer styleResId = getIntent().getBooleanExtra(Constants.EXTRA_USE_CUSTOM_THEME, false) ? R.style.AppTheme_YouTubePlayer : null;
            playerFrag = UIYouTubePlayerFragment.newInstance(
                    getIntent().getStringExtra(Constants.EXTRA_VIDEO_YOUTUBE_ID),
                    GOOGLE_API_KEY, R.style.AppTheme_YouTubePlayer, true);
            mgr.beginTransaction()
                    .add(R.id.fragment_container, playerFrag, PLAYER_FRAG_TAG)
                    .commit();
        }*/
        if(playerFrag == null) {
            final Integer styleResId = getIntent().getBooleanExtra(Constants.EXTRA_USE_CUSTOM_THEME, false) ? R.style.AppTheme_YouTubePlayer : null;
            playerFrag = UIYouTubePlayerFragment.newInstance("7FIaXN9C-no", GOOGLE_API_KEY, R.style.AppTheme_YouTubePlayer, true);
            mgr.beginTransaction()
                    .add(R.id.fragment_container, playerFrag, PLAYER_FRAG_TAG)
                    .commit();
        }
    }
}
/*
https://www.numetriclabz.com/integrate-youtube-player-in-android-application-tutorial/
OnInitializedListener
PlaybackEventListener
PlayerStateChangeListener

*/