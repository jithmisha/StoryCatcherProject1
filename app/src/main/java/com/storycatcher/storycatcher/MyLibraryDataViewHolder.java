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

public class MyLibraryDataViewHolder extends RecyclerView.Adapter<MyLibraryDataViewHolder.ViewHolder> {
    private Context bContext;
    private ArrayList<MyLibraryDataClass> myLibraryDataClassArrayList;
    private  MyLibraryDataViewHolder.onRecyclerViewClick listner;

    public interface onRecyclerViewClick {
        void onItemClick(int position);
    }

    public void onRecyclerViewClick(MyLibraryDataViewHolder.onRecyclerViewClick listner) {
        this.listner = listner;
    }

    public MyLibraryDataViewHolder(Context bContext, ArrayList<MyLibraryDataClass> myLibraryDataClassArrayList) {
        this.bContext = bContext;
        this.myLibraryDataClassArrayList = myLibraryDataClassArrayList;
    }

    @NonNull
    @Override
    public MyLibraryDataViewHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mylibrary_item_list, parent, false);
        return new MyLibraryDataViewHolder.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyLibraryDataViewHolder.ViewHolder holder, int position) {
        holder.title.setText(myLibraryDataClassArrayList.get(position).getTitle());
        Glide.with(bContext).load(myLibraryDataClassArrayList.get(position).getImageUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return myLibraryDataClassArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgMyLibraryItem);
            title = itemView.findViewById(R.id.txtMyLibraryName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listner != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listner.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
