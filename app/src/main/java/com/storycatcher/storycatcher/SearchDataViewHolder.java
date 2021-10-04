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

import java.util.ArrayList;

public class SearchDataViewHolder extends RecyclerView.Adapter<SearchDataViewHolder.ViewHolder> {
    private Context bContext;
    private ArrayList<SearchDataClass> searchDataClassArrayList;
    private SearchDataViewHolder.onRecyclerViewClick listner;

    public interface onRecyclerViewClick{
        void onItemClick(int position);
    }

    public void onRecyclerViewClick(SearchDataViewHolder.onRecyclerViewClick listner){
        this.listner = listner;
    }

    public SearchDataViewHolder(Context bContext, ArrayList<SearchDataClass> searchDataClassArrayList) {
        this.bContext = bContext;
        this.searchDataClassArrayList = searchDataClassArrayList;
    }

    @NonNull
    @Override
    public SearchDataViewHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchDataViewHolder.ViewHolder holder, int position) {
        holder.title.setText(searchDataClassArrayList.get(position).getTitle());
        Glide.with(bContext).load(searchDataClassArrayList.get(position).getImageUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return searchDataClassArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgSearchItem);
            title = itemView.findViewById(R.id.txtSearchName);

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
