package com.storycatcher.storycatcher;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class SinhalaSongViewHolder extends RecyclerView.Adapter<SinhalaSongViewHolder.ViewHolder> {

    Context context;
    List<SinhalaSongDataClass> sinhalaSongsList;
    private SinhalaSongViewHolder.onRecyclerViewClick listner;

    public interface onRecyclerViewClick{
        void onItemClick(int position);
    }

    public void onRecyclerViewClick(SinhalaSongViewHolder.onRecyclerViewClick listner){
        this.listner = listner;
    }

    public SinhalaSongViewHolder(Context context, List<SinhalaSongDataClass> songsList) {
        this.context = context;
        this.sinhalaSongsList = songsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(sinhalaSongsList.get(position).getTitle());
        Glide.with(context).load(sinhalaSongsList.get(position).getImageUrl()).into(holder.SongImg);

       /* holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),VideoPlayer.class);
                //intent.putExtra("videoId",item.getId());
                v.getContext().startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return sinhalaSongsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView SongImg;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            SongImg= itemView.findViewById(R.id.imgSong);
            name= itemView.findViewById(R.id.txtSongName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listner != null && getAdapterPosition() != RecyclerView.NO_POSITION){
                        listner.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }


}
