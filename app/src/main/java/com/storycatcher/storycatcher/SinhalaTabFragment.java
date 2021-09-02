package com.storycatcher.storycatcher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SinhalaTabFragment extends Fragment {
    private View root;
    RecyclerView recyclerview;
    private ArrayList<LibraryData> bookList;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = (ViewGroup) inflater.inflate(R.layout.sinhala_tab_fragment, container, false);

        recyclerview=root.findViewById(R.id.sinhalaRecycleView);

        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),3);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);

        //arraylist
        bookList =new ArrayList<>();

        //cleararratLisy



        return root;


    }
}
