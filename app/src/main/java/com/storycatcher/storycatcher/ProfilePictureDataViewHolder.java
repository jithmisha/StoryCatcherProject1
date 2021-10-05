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
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfilePictureDataViewHolder extends RecyclerView.Adapter<ProfilePictureDataViewHolder.ViewHolder> {
    private Context bContext;
    private ArrayList<ProfilePictureDataClass> profilePicDataClassArrayList;
    private ProfilePictureDataViewHolder.onRecyclerViewClick listner;

    public interface onRecyclerViewClick {
        void onItemClick(int position);
    }

    public void onRecyclerViewClick(ProfilePictureDataViewHolder.onRecyclerViewClick listner) {
        this.listner = listner;
    }

    public ProfilePictureDataViewHolder(Context bContext, ArrayList<ProfilePictureDataClass> profilePicDataClassArrayList) {
        this.bContext = bContext;
        this.profilePicDataClassArrayList = profilePicDataClassArrayList;
    }

    @NonNull
    @Override
    public ProfilePictureDataViewHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mylibrary_item_list, parent, false);
        return new ProfilePictureDataViewHolder.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfilePictureDataViewHolder.ViewHolder holder, int position) {
        Glide.with(bContext).load(profilePicDataClassArrayList.get(position).getProfilePicUrl()).into(holder.image);
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