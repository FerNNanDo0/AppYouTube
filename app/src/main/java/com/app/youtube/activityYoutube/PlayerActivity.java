package com.app.youtube.activityYoutube;

import static com.app.youtube.helper.YoutubeConfig.API_KEY;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.app.youtube.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
public class PlayerActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {
    YouTubePlayerView youTubePlayerView;
    String idVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        youTubePlayerView = findViewById(R.id.viewYoutube);
        youTubePlayerView.initialize( API_KEY, this);
        Bundle bundle = getIntent().getExtras();
        idVideo = bundle.getString("id");
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean estado) {
        //        Toast.makeText(this, "Sucesso ao iniciar player!", Toast.LENGTH_SHORT).show();
        //youTubePlayer.loadPlaylist("Ms4uDaAvdIY");

        Log.d("tag", "estado: "+estado);

        //youTubePlayer.setPlaybackEventListener( getPlayback() );
        youTubePlayer.setFullscreen(true);
        youTubePlayer.setShowFullscreenButton(true);
        youTubePlayer.setPlayerStateChangeListener( getPlayerStateChange() );
        if( !estado ){
//          youTubePlayer.cuePlaylist("id da playliste aqui! ");
            youTubePlayer.loadPlaylist( idVideo );
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
                Toast.makeText(PlayerActivity.this, "Video executado",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onPaused() {
                Toast.makeText(PlayerActivity.this, "Video em pause",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onStopped() {
                Toast.makeText(PlayerActivity.this, "Video em stop",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onBuffering(boolean b) {
                Toast.makeText(PlayerActivity.this, "Video pre-carregando",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onSeekTo(int i) {
                Toast.makeText(PlayerActivity.this, "Movimentando seekBar",
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
                Toast.makeText(PlayerActivity.this, "Carregando video",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onLoaded(String s) {
                Toast.makeText(PlayerActivity.this, "Video carregado",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onAdStarted() {
                Toast.makeText(PlayerActivity.this, "Video propaganda iniciou",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onVideoStarted() {
                Toast.makeText(PlayerActivity.this, "Video está começando",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onVideoEnded() {
                Toast.makeText(PlayerActivity.this, "Video Chegou ao final",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(YouTubePlayer.ErrorReason errorReason) {
                Toast.makeText(PlayerActivity.this, "Erro ao recuperar eventos de carregamento",
                        Toast.LENGTH_SHORT).show();
            }
        };
        //return playerStateChangeListener;
    }
}