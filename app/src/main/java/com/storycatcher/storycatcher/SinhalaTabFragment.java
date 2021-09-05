package com.storycatcher.storycatcher;

import android.content.Context;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SinhalaTabFragment extends Fragment {
    private View root;
    RecyclerView recyclerview;
    LibraryViewHolder libraryViewHolder;
    FirebaseFirestore fstore;
    private DatabaseReference myRef;
    //private Context bContext;
    private ArrayList<LibraryDataClass> bookList;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.sinhala_tab_fragment, container, false);

        recyclerview=root.findViewById(R.id.sinhalaRecycleView);

        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),3);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);

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
        recyclerview.setAdapter(libraryViewHolder);


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
