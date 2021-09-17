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

public class SinhalaBookViewHolder extends RecyclerView.Adapter<SinhalaBookViewHolder.ViewHolder> {
    public static final String Tag ="RecyclerView";
    private Context bContext;
    private ArrayList<SinhalaBookDataClass> sinhalaBookList;
    private SinhalaBookViewHolder.onRecyclerViewClick listner;

    public interface onRecyclerViewClick{
        void onItemClick(int position);
    }

    public void onRecyclerViewClick(SinhalaBookViewHolder.onRecyclerViewClick listner){
        this.listner = listner;
    }

    public SinhalaBookViewHolder(Context bContext, ArrayList<SinhalaBookDataClass> bookList) {
        this.bContext = bContext;
        this.sinhalaBookList = bookList;
    }

    @NonNull
    @Override
    public SinhalaBookViewHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bookName.setText(sinhalaBookList.get(position).getTitle());
        Glide.with(bContext).load(sinhalaBookList.get(position).getImageUrl()).into(holder.bookImage);

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
        return sinhalaBookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView bookImage;
        TextView bookName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bookImage =itemView.findViewById(R.id.imgBook);
            bookName =itemView.findViewById(R.id.txtBookName);

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
