package com.storycatcher.storycatcher;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class EnglishSongViewHolder extends RecyclerView.Adapter<EnglishSongViewHolder.ViewHolder>{

    private Context bContext;
    private ArrayList<EnglishSongDataClass> englishSongList;

    public EnglishSongViewHolder(Context bContext, ArrayList<EnglishSongDataClass> englishSongList) {
        this.bContext = bContext;
        this.englishSongList = englishSongList;
    }

    @NonNull
    @Override
    public EnglishSongViewHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull EnglishSongViewHolder.ViewHolder holder, int position) {
        holder.name.setText(englishSongList.get(position).getTitle());
        Glide.with(bContext).load(englishSongList.get(position).getImageUrl()).into(holder.SongImg);
    }

    @Override
    public int getItemCount() {
        return englishSongList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView SongImg;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            /*SongImg= itemView.findViewById(R.id.imgSong);
            name= itemView.findViewById(R.id.txtSongName);*/

        }
    }
}
