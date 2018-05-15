package com.rz.usagesexample;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rz.strawyoutubeplayer.StrawYouTubePlayer;
import com.rz.strawyoutubeplayer.StrawYouTubePlayerFragment;

public class ActYouTubePlayerView extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private String DEVELOPER_API_KEY = "";
    private String youtubeVideoId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_you_tube_player_view);
        activity = this;
        context = this;
        //LibYouTubePlayerView sysLibFragment = (LibYouTubePlayerView) findViewById(R.id.sysLibFragment);
        //sysLibFragment.initView(activity);
        //LibYouTubePlayerView myFragment = LibYouTubePlayerView.newInstance("https://www.youtube.com/watch?v=yTVn6WcVDHY");
        //LibYouTubePlayerView myFragment = LibYouTubePlayerView.newInstance("7FIaXN9C-no");
        DEVELOPER_API_KEY = getResources().getString(R.string.app_google_youtube_key);
        youtubeVideoId = "7FIaXN9C-no";
        StrawYouTubePlayerFragment youTubePlayerFragment = StrawYouTubePlayerFragment.newInstance(context, DEVELOPER_API_KEY, youtubeVideoId);
        getSupportFragmentManager().beginTransaction().replace(R.id.sysLibFragment, youTubePlayerFragment).commit();
    }
}
