package com.rz.usagesexample;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActYouTubePlayerView extends AppCompatActivity {
    private Activity activity;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_you_tube_player_view);
        activity = this;
        context = this;
        //LibYouTubePlayerView sysLibFragment = (LibYouTubePlayerView) findViewById(R.id.sysLibFragment);
        //sysLibFragment.initView(activity);
        //LibYouTubePlayerView myFragment = LibYouTubePlayerView.newInstance("https://www.youtube.com/watch?v=yTVn6WcVDHY");
        LibYouTubePlayerView myFragment = LibYouTubePlayerView.newInstance("7FIaXN9C-no");
        getSupportFragmentManager().beginTransaction().replace(R.id.sysLibFragment, myFragment).commit();
    }
}
