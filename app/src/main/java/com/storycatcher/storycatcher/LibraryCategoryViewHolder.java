package com.storycatcher.storycatcher;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LibraryCategoryViewHolder extends RecyclerView.Adapter<LibraryCategoryViewHolder.ViewHolder> {

    private Activity activity;
    ArrayList<LibraryCatTitleClass> parentItemArrayList;
    ArrayList<LibraryDataClass> childItemArrayList;

    public LibraryCategoryViewHolder(Activity activity, ArrayList<LibraryCatTitleClass> parentItemArrayList, ArrayList<LibraryDataClass> childItemArrayList) {
        this.activity = activity;
        this.parentItemArrayList = parentItemArrayList;
        this.childItemArrayList = childItemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorytitle_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LibraryCatTitleClass libraryCatTitleClass = parentItemArrayList.get(position);
        holder. categoryName.setText(libraryCatTitleClass.CategoryTitle);


    }

    @Override
    public int getItemCount() {
        return parentItemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView categoryName;
        RecyclerView nested_rv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName= itemView.findViewById(R.id.categoryTitle);
            nested_rv= itemView.findViewById(R.id.nested_rv);


        }
    }


}
