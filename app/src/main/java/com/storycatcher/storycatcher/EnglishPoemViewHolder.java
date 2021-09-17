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

public class EnglishPoemViewHolder extends RecyclerView.Adapter<EnglishPoemViewHolder.ViewHolder>{
    Context context;
    List<EnglishPoemDataClass> englishPoemsList;
    private EnglishPoemViewHolder.onRecyclerViewClick listner;

    public interface onRecyclerViewClick{
        void onItemClick(int position);
    }

    public void onRecyclerViewClick(EnglishPoemViewHolder.onRecyclerViewClick listner){
        this.listner = listner;
    }
    public EnglishPoemViewHolder(Context context, List<EnglishPoemDataClass> englishPoemsList) {
        this.context = context;
        this.englishPoemsList = englishPoemsList;
    }

    @NonNull
    @Override
    public EnglishPoemViewHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poem_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EnglishPoemViewHolder.ViewHolder holder, int position) {
        holder.poemName.setText(englishPoemsList.get(position).getTitle());
        Glide.with(context).load(englishPoemsList.get(position).getImageUrl()).into(holder.poemImg);

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
        return englishPoemsList.size();
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
