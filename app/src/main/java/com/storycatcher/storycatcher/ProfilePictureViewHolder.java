package com.storycatcher.storycatcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfilePictureViewHolder extends RecyclerView.Adapter<ProfilePictureViewHolder.ViewHolder> {
    private Context bContext;
    private ArrayList<ProfilePictureDataClass> profilePicDataClassArrayList;
    private ProfilePictureViewHolder.onRecyclerViewClick listner;

    public interface onRecyclerViewClick {
        void onItemClick(int position);
    }

    public void onRecyclerViewClick(ProfilePictureViewHolder.onRecyclerViewClick listner) {
        this.listner = listner;
    }

    public ProfilePictureViewHolder(Context bContext, ArrayList<ProfilePictureDataClass> profilePicDataClassArrayList) {
        this.bContext = bContext;
        this.profilePicDataClassArrayList = profilePicDataClassArrayList;
    }

    @NonNull
    @Override
    public ProfilePictureViewHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_picture_item_list, parent, false);
        return new ProfilePictureViewHolder.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfilePictureViewHolder.ViewHolder holder, int position) {
        Glide.with(bContext).load(profilePicDataClassArrayList.get(position).getPicUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return profilePicDataClassArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgProfilePic);

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