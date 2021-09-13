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

import java.util.ArrayList;

public class EnglishBookViewHolder extends RecyclerView.Adapter<EnglishBookViewHolder.ViewHolder> {

    private Context bContext;
    private ArrayList<EnglishBookDataClass> englishBookList;

    public EnglishBookViewHolder(Context bContext, ArrayList<EnglishBookDataClass> englishBookList) {
        this.bContext = bContext;
        this.englishBookList = englishBookList;
    }

    @NonNull
    @Override
    public EnglishBookViewHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EnglishBookViewHolder.ViewHolder holder, int position) {
        holder.bookName.setText(englishBookList.get(position).getTitle());
        Glide.with(bContext).load(englishBookList.get(position).getImageUrl()).into(holder.bookImage);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),VideoPlayer.class);
                //intent.putExtra("videoId",item.getId());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return englishBookList.size();
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
