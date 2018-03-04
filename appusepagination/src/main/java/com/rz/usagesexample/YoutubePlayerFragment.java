package com.rz.usagesexample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
/*import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;*/

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

/**
 * Created by Rz Rasel on 2018-02-28.
 */

public class YoutubePlayerFragment extends Fragment {
    private Activity activity;
    private Context context;
    public static final String TAG = "YoutubePlayer";
    public static final String YOUTUBE_API_KEY = "AIzaSyAqqIRxuAOuN1fsHCoc41-Lo0-XKiB8asc";
    private YouTubePlayerSupportFragment mYouTubeContainer;
    private YouTubePlayer mYouTubePlayer;

    public YoutubePlayerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.youtube_player_fragment, container, false);
        //context = container.getContext();
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mDraggableView		= (DraggableView)  	getView().findViewById(R.id.draggable_view);
        initiliazeYoutubeFragment();
    }

    // инициализация плеера
    private void initiliazeYoutubeFragment() {

        mYouTubeContainer = YouTubePlayerSupportFragment.newInstance();
        mYouTubeContainer.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
                if (!wasRestored) {
                    mYouTubePlayer = youTubePlayer;
                    mYouTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    //mYouTubePlayer.cueVideo("nCgQDjiotG0");
                    System.out.println("CALLED:-----------------------");
                    mYouTubePlayer.loadVideo("7FIaXN9C-no");
                    mYouTubePlayer.play();
                    mYouTubePlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                        @Override
                        public void onLoading() {

                        }

                        @Override
                        public void onLoaded(String s) {

                        }

                        @Override
                        public void onAdStarted() {

                        }

                        @Override
                        public void onVideoStarted() {

                        }

                        @Override
                        public void onVideoEnded() {

                        }

                        @Override
                        public void onError(YouTubePlayer.ErrorReason errorReason) {
                            //Log.d(TAG,errorReason.toString());
                        }
                    });
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                //
            }
        });
        //FragmentManager fragManager = myContext.getSupportFragmentManager();
        //getChildFragmentManager().beginTransaction().replace(R.id.sysFragmentYoutubePlayer, mYouTubeContainer).commit();
        //getSupportFragmentManager().beginTransaction().replace(R.id.sysYoutubePlayerFragment, mYouTubeContainer).commit();
        //FragmentManager fragManager = context.getSupportFragmentManager();
        //FragmentManager fragManager = getActivity().getFragmentManager();
        //FragmentManager fragManager = getActivity().getSupportFragmentManager();
        /*FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.sysYoutubePlayerFragment, mYouTubeContainer).commit();*/
        /*FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.sysFragmentYoutubePlayer, mYouTubeContainer).commit();*/
        /*FragmentManager mFragmentMgr = getFragmentManager();
        FragmentTransaction mTransaction = mFragmentMgr.beginTransaction();
        Fragment childFragment = mFragmentMgr.findFragmentByTag("qa_fragment");
        mTransaction.remove(childFragment);
        mTransaction.commit();*/
        //LibYouTubePlayerView myFragment = LibYouTubePlayerView.newInstance("7FIaXN9C-no");
        //FragmentManager fragManager = getActivity().getFragmentManager();
        /*FragmentTransaction fragmentTransaction = fragManager.beginTransaction();
        fragmentTransaction.replace(R.id.sysFragmentYoutubePlayer, myFragment);
        fragmentTransaction.commit();*/
        /*Fragment fragment = fragManager.findFragmentById(R.id.sysFragmentYoutubePlayer);
        FragmentTransaction ft = fragManager.beginTransaction();
        ft.remove(fragment);
        ft.commit();*/
        //------------OK-----LibYouTubePlayerView myFragment = LibYouTubePlayerView.newInstance("S2pQ4KkC-sQ");
        //------------OK-----getChildFragmentManager().beginTransaction().replace(R.id.sysFragmentYoutubePlayer, myFragment).commit();
        getChildFragmentManager().beginTransaction().replace(R.id.sysFragmentYoutubePlayer, mYouTubeContainer).commit();
        //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.sysYoutubePlayerFragment, myFragment).commit();

    }
}