package com.storycatcher.storycatcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    Context context;

    ArrayList<User> list;

    public UserAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user =list.get(position);
        holder.kidsId.setText(user.getKidsId());
        holder.kidsName.setText(user.getKidsName());
        holder.kidsAge.setText(user.getKidsAge());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView kidsId,kidsName,kidsAge;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            kidsId=itemView.findViewById(R.id.txtviewKidsId);
            kidsName=itemView.findViewById(R.id.txtviewKidsName);
            kidsAge=itemView.findViewById(R.id.txtviewKidsAge);
        }
    }

}
