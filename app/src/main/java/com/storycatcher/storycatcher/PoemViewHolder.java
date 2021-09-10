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

public class PoemViewHolder extends RecyclerView.Adapter<PoemViewHolder.ViewHolder> {
    Context context;
    List<PoemDataClass> poemsList;

    public PoemViewHolder(Context context, List<PoemDataClass> poemsList) {
        this.context = context;
        this.poemsList = poemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poem_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return poemsList.size();
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
