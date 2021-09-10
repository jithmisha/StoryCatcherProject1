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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SinhalaTabFragment extends Fragment {
    private View root;
    RecyclerView storyRecyclerView,songsRecyclerView,poemsRecyclerView;
    FirebaseFirestore fstore;
    private DatabaseReference myRef;
    //private Context bContext;

    LibraryViewHolder libraryViewHolder;
    private ArrayList<LibraryDataClass> bookList;

    //Song category
    SongViewHolder songViewHolder;
    List<SongsDataClass> songsList;

    //poem category
    PoemViewHolder poemViewHolder;
    List<PoemDataClass> poemsList;


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.sinhala_tab_fragment, container, false);

        storyRecyclerView=root.findViewById(R.id.storyBookRecycleView);
        songsRecyclerView=root.findViewById(R.id.songsRecyclerView);
        poemsRecyclerView=root.findViewById(R.id.PoemsRecyclerView);

        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),3);
        storyRecyclerView.setLayoutManager(layoutManager);
        storyRecyclerView.setHasFixedSize(true);

        //Firebase
        fstore=FirebaseFirestore.getInstance();
        //myRef= FirebaseDatabase.getInstance().getReference();

        //arraylist
        bookList =new ArrayList<>();

        //claear Arrray list
        clearAll();
        //Get Data method
        GetDataFromFirebase();

        //libraryViewHolder=new LibraryViewHolder(getActivity().getApplicationContext(),bookList);
        libraryViewHolder=new LibraryViewHolder(getActivity(),bookList);
        storyRecyclerView.setAdapter(libraryViewHolder);


        return root;
    }
    private void GetDataFromFirebase() {
        /*Query query=myRef.child("Bool");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clearAll();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    LibraryDataClass data= new LibraryDataClass();
                    data.setImageUrl(snapshot.child("imageUrl").getValue().toString());
                    bookList.add(data);
                }
                libraryViewHolder=new LibraryViewHolder(getActivity().getApplicationContext(),bookList);
                recyclerview.setAdapter(libraryViewHolder);
                libraryViewHolder.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });*/
        fstore.collection("Library").document("Songs").collection("ESongs")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document:task.getResult()){
                                LibraryDataClass data= document.toObject(LibraryDataClass.class);
                                bookList.add(data);
                                libraryViewHolder.notifyDataSetChanged();
                            }
                        }else {
                            Toast.makeText(getContext(),"Invalid email or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void clearAll(){
        if(bookList != null){
            bookList.clear();
            if(libraryViewHolder!=null){
                libraryViewHolder.notifyDataSetChanged();
            }
        }
        bookList=new ArrayList<>();
    }
}
