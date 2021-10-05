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

public class  UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    Context context;
    ArrayList<UserDataClass> list; //User class
    private onRecyclerViewClick listner;

    public interface onRecyclerViewClick{
        void onItemClick(int position);
    }

    public void onRecyclerViewClick(onRecyclerViewClick listner){
        this.listner = listner;
    }

    public UserAdapter(Context context, ArrayList<UserDataClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v,listner);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        UserDataClass userDataClass = list.get(position);
        holder.kidsId.setText(userDataClass.getKidID());
        holder.kidsName.setText(userDataClass.getKidsName());
        holder.kidsAge.setText(String.valueOf(userDataClass.getKidsAge()));
        Glide.with(context).load(list.get(position).getPicUrl()).into(holder.profilePic);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView kidsId,kidsName,kidsAge;
        ImageView profilePic;


        public MyViewHolder(@NonNull View itemView, onRecyclerViewClick listner) {
            super(itemView);

            kidsId = itemView.findViewById(R.id.txtviewKidsId);
            kidsName = itemView.findViewById(R.id.txtviewKidsName);
            kidsAge = itemView.findViewById(R.id.txtviewKidsAge);
            profilePic = itemView.findViewById(R.id.profilePic);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listner != null && getAdapterPosition() != RecyclerView.NO_POSITION){
                        listner.onItemClick(getAdapterPosition());
                    }
                    /*Intent i = new Intent(v.getContext(),LibraryScreen.class);
                    //i.putExtra("kidsName",list.get(getAdapterPosition()));
                    v.getContext().startActivity(i);*/
                }
            });
        }
    }

}
