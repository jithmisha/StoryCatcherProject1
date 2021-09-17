package com.storycatcher.storycatcher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class EnglishTabFragment extends Fragment {
    private View root;
    //Recyceler View
    RecyclerView engBooksRecyclerView,engSongsRecyclerView,engPoemsRecyclerView;

    FirebaseFirestore fstore;

    //Book category
    EnglishBookViewHolder englishBookViewHolder;
    ArrayList<EnglishBookDataClass> englishBooksList;

    //Song Category
    EnglishSongViewHolder englishSongViewHolder;
    ArrayList<EnglishSongDataClass> englishSongsList;

    //Poem Category
    EnglishPoemViewHolder englishPoemViewHolder;
    ArrayList<EnglishPoemDataClass> englishPoemsList;


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.english_tab_fragment, container, false);

        //Jithmi do your thing
        engBooksRecyclerView = root.findViewById(R.id.EngStoryBookRecycleView);
        engSongsRecyclerView = root.findViewById(R.id.EngSongsRecyclerView);
        engPoemsRecyclerView = root.findViewById(R.id.EngPoemsRecyclerView);

        GridLayoutManager layoutManager1=new GridLayoutManager(getActivity(),3);
        engBooksRecyclerView.setLayoutManager(layoutManager1);
        engBooksRecyclerView.setHasFixedSize(true);

        GridLayoutManager layoutManager2=new GridLayoutManager(getActivity(),3);
        engSongsRecyclerView.setLayoutManager(layoutManager2);
        engSongsRecyclerView.setHasFixedSize(true);

        GridLayoutManager layoutManager3=new GridLayoutManager(getActivity(),3);
        engPoemsRecyclerView.setLayoutManager(layoutManager3);
        engPoemsRecyclerView.setHasFixedSize(true);


        //Firebase
        fstore=FirebaseFirestore.getInstance();

        //Array List
        englishBooksList = new ArrayList<>();
        englishSongsList = new ArrayList<>();
        englishPoemsList = new ArrayList<>();

        //Get data methods
        GetEBDataFromFirebase();
        GetESDataFromFirebase();
        GetEPDataFromFirebase();

        //Setting Adapter
        englishBookViewHolder = new EnglishBookViewHolder(getActivity(),englishBooksList);
        engBooksRecyclerView.setAdapter(englishBookViewHolder);

        englishSongViewHolder = new EnglishSongViewHolder(getActivity(), englishSongsList);
        engSongsRecyclerView.setAdapter(englishSongViewHolder);

        englishPoemViewHolder = new EnglishPoemViewHolder(getActivity(), englishPoemsList);
        engPoemsRecyclerView.setAdapter(englishPoemViewHolder);

        return root;
    }

    private void GetEBDataFromFirebase() {
        fstore.collection("Library").document("Books").collection("EBooks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document:task.getResult()){
                                EnglishBookDataClass data= document.toObject(EnglishBookDataClass.class);
                                englishBooksList.add(data);
                                englishBookViewHolder.notifyDataSetChanged();
                            }
                        }else {
                            Toast.makeText(getContext(),"Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void GetESDataFromFirebase() {
        fstore.collection("Library").document("Songs").collection("ESongs")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document:task.getResult()){
                                EnglishSongDataClass data= document.toObject(EnglishSongDataClass.class);
                                englishSongsList.add(data);
                                englishSongViewHolder.notifyDataSetChanged();
                            }
                        }else {
                            Toast.makeText(getContext(),"Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void GetEPDataFromFirebase() {
        fstore.collection("Library").document("Poems").collection("EPoems")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document:task.getResult()){
                                EnglishPoemDataClass data= document.toObject(EnglishPoemDataClass.class);
                                englishPoemsList.add(data);
                                englishPoemViewHolder.notifyDataSetChanged();
                            }
                        }else {
                            Toast.makeText(getContext(),"Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
