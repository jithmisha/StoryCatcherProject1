package com.storycatcher.storycatcher;

import android.content.Context;
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

        }
    }
}
