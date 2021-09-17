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

public class SinhalaPoemViewHolder extends RecyclerView.Adapter<SinhalaPoemViewHolder.ViewHolder> {
    Context context;
    List<SinhalaPoemDataClass> sinhalaPoemsList;
    private SinhalaPoemViewHolder.onRecyclerViewClick listner;

    public interface onRecyclerViewClick{
        void onItemClick(int position);
    }

    public void onRecyclerViewClick(SinhalaPoemViewHolder.onRecyclerViewClick listner){
        this.listner = listner;
    }

    public SinhalaPoemViewHolder(Context context, List<SinhalaPoemDataClass> poemsList) {
        this.context = context;
        this.sinhalaPoemsList = poemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poem_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.poemName.setText(sinhalaPoemsList.get(position).getTitle());
        Glide.with(context).load(sinhalaPoemsList.get(position).getImageUrl()).into(holder.poemImg);

        /*holder.itemView.setOnClickListener(new View.OnClickListener(){
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
        return sinhalaPoemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poemImg;
        TextView poemName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poemImg= itemView.findViewById(R.id.imgPoem);
            poemName= itemView.findViewById(R.id.txtPoemName);

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
