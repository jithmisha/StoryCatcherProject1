package com.storycatcher.storycatcher;

import android.content.Context;
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

    public EnglishPoemViewHolder(Context context, List<EnglishPoemDataClass> englishPoemsList) {
        this.context = context;
        this.englishPoemsList = englishPoemsList;
    }

    @NonNull
    @Override
    public EnglishPoemViewHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull EnglishPoemViewHolder.ViewHolder holder, int position) {
        holder.poemName.setText(englishPoemsList.get(position).getTitle());
        Glide.with(context).load(englishPoemsList.get(position).getImageUrl()).into(holder.poemImg);
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

            /*poemImg= itemView.findViewById(R.id.imgPoem);
            poemName= itemView.findViewById(R.id.txtPoemName);*/

        }
    }
}
