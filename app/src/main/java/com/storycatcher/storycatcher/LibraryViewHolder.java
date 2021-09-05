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

public class LibraryViewHolder extends RecyclerView.Adapter<LibraryViewHolder.ViewHolder> {
    public static final String Tag ="RecyclerView";
    private Context bContext;
    private ArrayList<LibraryDataClass> bookList;

    public LibraryViewHolder(Context bContext, ArrayList<LibraryDataClass> bookList) {
        this.bContext = bContext;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public LibraryViewHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bookName.setText(bookList.get(position).getTitle());
        Glide.with(bContext).load(bookList.get(position).getImageUrl()).into(holder.bookImage);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView bookImage;
        TextView bookName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bookImage =itemView.findViewById(R.id.imgBook);
            bookName =itemView.findViewById(R.id.txtBookName);
        }
    }

}
