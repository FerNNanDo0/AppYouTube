package com.app.youtube;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class TestActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {

    private static final String API_KEY = "AIzaSyDv2KcvdmdHlz0btGPR1xnQHFoR9xBju9A";
    YouTubePlayerView youTubePlayerView;
    //YouTubePlayer.PlaybackEventListener playbackEventListener;
    //YouTubePlayer.PlayerStateChangeListener playerStateChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.view_player);

        youTubePlayerView = findViewById(R.id.viewYoutube);
        youTubePlayerView.initialize(API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer youTubePlayer, boolean estado) {
//        Toast.makeText(this, "Sucesso ao iniciar player!", Toast.LENGTH_SHORT).show();
        //youTubePlayer.loadPlaylist("Ms4uDaAvdIY");

        Log.d("tag", "estado: "+estado);

        //youTubePlayer.setPlaybackEventListener( getPlayback() );
        youTubePlayer.setPlayerStateChangeListener( getPlayerStateChange() );
        if( !estado ){
//          youTubePlayer.cuePlaylist("id da playliste aqui! ");
            youTubePlayer.cueVideo("vXAPWgKP9Ds");
        }
    }
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Erro ao iniciar player!"+youTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();
    }

    // playbackEventListener
    private YouTubePlayer.PlaybackEventListener getPlayback(){
        // playbackEventListener
        //playbackEventListener =
            return new YouTubePlayer.PlaybackEventListener() {
            @Override
            public void onPlaying() {
                Toast.makeText(TestActivity.this, "Video executado",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onPaused() {
                Toast.makeText(TestActivity.this, "Video em pause",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onStopped() {
                Toast.makeText(TestActivity.this, "Video em stop",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onBuffering(boolean b) {
                Toast.makeText(TestActivity.this, "Video pre-carregando",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onSeekTo(int i) {
                Toast.makeText(TestActivity.this, "Movimentando seekBar",
                        Toast.LENGTH_SHORT).show();
            }
        };
        //return playbackEventListener;
    }

    //playerStateChangeListener
    private YouTubePlayer.PlayerStateChangeListener getPlayerStateChange(){
        // playerStateChangeListener
        //playerStateChangeListener =
            return new YouTubePlayer.PlayerStateChangeListener() {
            @Override
            public void onLoading() {
                Toast.makeText(TestActivity.this, "Carregando video",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onLoaded(String s) {
                Toast.makeText(TestActivity.this, "Video carregado",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onAdStarted() {
                Toast.makeText(TestActivity.this, "Video propaganda iniciou",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onVideoStarted() {
                Toast.makeText(TestActivity.this, "Video está começando",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onVideoEnded() {
                Toast.makeText(TestActivity.this, "Video Chegou ao final",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(YouTubePlayer.ErrorReason errorReason) {
                Toast.makeText(TestActivity.this, "Erro ao recuperar eventos de carregamento",
                        Toast.LENGTH_SHORT).show();
            }
        };
        //return playerStateChangeListener;
    }

}
