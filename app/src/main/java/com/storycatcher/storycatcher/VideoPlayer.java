package com.storycatcher.storycatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class VideoPlayer extends AppCompatActivity {
    Uri videoUri;
    PlayerView playerView;
    ExoPlayer exoPlayer;
    ImageView exoMute,exoFav;
    ExtractorsFactory extractorsFactory;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setFullscreen();
        setContentView(R.layout.activity_video_player);

        fstore = FirebaseFirestore.getInstance();
        playerView = findViewById(R.id.playerView);
        exoMute = findViewById(R.id.exo_mute);
        exoFav = findViewById(R.id.exo_fav);

        //Read from shared preferences
        SharedPreferences sharedPref = this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        String kidID = sharedPref.getString("kidID", "error");

        //Getting selected book data
        Intent intent = getIntent();
        String currentBookID = intent.getStringExtra("ID");
        String currentBookTitle = intent.getStringExtra("Title");
        String bookImage = intent.getStringExtra("imageUrl");
        String uri_str=intent.getStringExtra("video");

        exoFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long timestamp = System.currentTimeMillis();
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("ID", currentBookID);
                hashMap.put("Tittle", currentBookTitle);
                hashMap.put("imageUrl", bookImage);
                hashMap.put("timeStamp", timestamp);
                hashMap.put("vide", uri_str );

                fstore.collection("Favourites").document(kidID).collection(currentBookID).add(hashMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(VideoPlayer.this, "Profile created successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        if(intent!=null){
            videoUri= Uri.parse(uri_str);
        }

        exoMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float currentVolume = exoPlayer.getAudioComponent().getVolume();
                if (currentVolume == 0f) {
                    exoPlayer.getAudioComponent().setVolume(1f);
                } else {
                    exoPlayer.getAudioComponent().setVolume(0f);
                }
            }
        });

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
        exoPlayer= ExoPlayerFactory.newSimpleInstance(this,trackSelector);
        extractorsFactory = new DefaultExtractorsFactory();
        playVideo();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "Landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "Portrait", Toast.LENGTH_SHORT).show();
        }
    }


    public void setFullscreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void playVideo(){
        try{
            String playerInfo = Util.getUserAgent(this,"StoryCatcher");
            DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this,playerInfo);
            MediaSource mediaSource = new ExtractorMediaSource(videoUri,dataSourceFactory,extractorsFactory,null,null);
            playerView.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    protected void onPause() {
        super.onPause();
        exoPlayer.setPlayWhenReady(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exoPlayer.setPlayWhenReady(false);
        exoPlayer.release();
    }

}