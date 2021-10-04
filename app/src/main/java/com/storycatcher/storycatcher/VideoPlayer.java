package com.storycatcher.storycatcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;

public class VideoPlayer extends AppCompatActivity {
    Uri videoUri;
    PlayerView playerView;
    ExoPlayer exoPlayer;
    ImageView exoMute,exoFav;
    ExtractorsFactory extractorsFactory;
    FirebaseFirestore fstore;
    String kidID, currentBookID, currentBookTitle, bookImage, uri_str;
    boolean isInFavourites = false;

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
        kidID = sharedPref.getString("kidID", "error");

        //Getting selected book data
        Intent intent = getIntent();
        currentBookID = intent.getStringExtra("ID");
        currentBookTitle = intent.getStringExtra("Title");
        bookImage = intent.getStringExtra("imageUrl");
        uri_str = intent.getStringExtra("video");

        checkFavourites();

        exoFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isInFavourites){
                    removeFromFavourites();
                }else{
                    addToFavourites();
                }
            }
        });

        if(intent != null){
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

    public void checkFavourites(){
        fstore.collection("Kids").document(kidID).collection("Favourites").document(currentBookID).
                addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        isInFavourites = value.exists();
                        if(isInFavourites){
                            exoFav.setBackgroundResource(R.drawable.ic_fav);
                        }else{
                            exoFav.setBackgroundResource(R.drawable.ic_fav_border);
                        }
                    }
                });
    }

    public void addToFavourites(){
        Long timestamp = System.currentTimeMillis();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("ID", currentBookID);
        hashMap.put("Title", currentBookTitle);
        hashMap.put("imageUrl", bookImage);
        hashMap.put("timeStamp", timestamp);
        hashMap.put("URL", uri_str );

        fstore.collection("Kids").document(kidID).collection("Favourites").document(currentBookID)
                .set(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(VideoPlayer.this, "Added To My Library", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(VideoPlayer.this, "Could Not Add to My Library", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void removeFromFavourites(){
        fstore.collection("Kids").document(kidID).collection("Favourites").document(currentBookID)
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(VideoPlayer.this, "Removed From My Library", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(VideoPlayer.this, "Could Not Remove From My Library", Toast.LENGTH_SHORT).show();
            }
        });

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