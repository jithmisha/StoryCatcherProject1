package com.storycatcher.storycatcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SongViewHolder extends RecyclerView.Adapter<SongViewHolder.ViewHolder> {

    Context context;
    List<SongsDataClass> songsList;

    public SongViewHolder(Context context, List<SongsDataClass> songsList) {
        this.context = context;
        this.songsList = songsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView SongImg;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            SongImg= itemView.findViewById(R.id.imgSong);
            name= itemView.findViewById(R.id.txtSongName);

        }
    }


}
