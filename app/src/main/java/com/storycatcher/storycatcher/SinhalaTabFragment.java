package com.storycatcher.storycatcher;

        import android.content.Intent;
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
        import java.util.List;

public class SinhalaTabFragment extends Fragment {
    private View root;
    RecyclerView booksRecyclerView,songsRecyclerView,poemsRecyclerView;
    FirebaseFirestore fstore;

    //Book category
    SinhalaBookViewHolder sinhalaBookViewHolder;
    ArrayList<SinhalaBookDataClass> sinhalaBooksList;

    //Song category
    SinhalaSongViewHolder sinhalaSongViewHolder;
    List<SinhalaSongDataClass> sinhalaSongsList;

    //poem category
    SinhalaPoemViewHolder sinhalaPoemViewHolder;
    List<SinhalaPoemDataClass> sinhalaPoemsList;


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = (ViewGroup) inflater.inflate(R.layout.sinhala_tab_fragment, container, false);

        booksRecyclerView =root.findViewById(R.id.storyBookRecycleView);
        songsRecyclerView=root.findViewById(R.id.songsRecyclerView);
        poemsRecyclerView=root.findViewById(R.id.PoemsRecyclerView);

        GridLayoutManager layoutManager1 = new GridLayoutManager(getActivity(),3);
        booksRecyclerView.setLayoutManager(layoutManager1);
        booksRecyclerView.setHasFixedSize(true);

        GridLayoutManager layoutManager2 = new GridLayoutManager(getActivity(),3);
        songsRecyclerView.setLayoutManager(layoutManager2);
        songsRecyclerView.setHasFixedSize(true);

        GridLayoutManager layoutManager3 = new GridLayoutManager(getActivity(),3);
        poemsRecyclerView.setLayoutManager(layoutManager3);
        poemsRecyclerView.setHasFixedSize(true);

        //Firebase
        fstore=FirebaseFirestore.getInstance();

        //arraylist
        sinhalaBooksList = new ArrayList<>();
        sinhalaSongsList = new ArrayList<>();
        sinhalaPoemsList = new ArrayList<>();

        /*claear Arrray list
        clearAll();*/

        //Get Data method
        GetSBDataFromFirebase();
        GetSSDataFromFirebase();
        GetSPDataFromFirebase();

        //Setting Adapter
        sinhalaBookViewHolder = new SinhalaBookViewHolder(getActivity(), sinhalaBooksList);
        booksRecyclerView.setAdapter(sinhalaBookViewHolder);

        sinhalaSongViewHolder = new SinhalaSongViewHolder(getActivity(), sinhalaSongsList);
        songsRecyclerView.setAdapter(sinhalaSongViewHolder);

        sinhalaPoemViewHolder = new SinhalaPoemViewHolder(getActivity(), sinhalaPoemsList);
        poemsRecyclerView.setAdapter(sinhalaPoemViewHolder);

        //Playing the content
        sinhalaBookViewHolder.onRecyclerViewClick(new SinhalaBookViewHolder.onRecyclerViewClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), VideoPlayer.class);
                intent.putExtra("video",sinhalaBooksList.get(position).getURL());
                intent.putExtra("ID",sinhalaBooksList.get(position).getID());
                intent.putExtra("Title",sinhalaBooksList.get(position).getTitle());
                intent.putExtra("imageUrl",sinhalaBooksList.get(position).getImageUrl());
                startActivity(intent);
            }
        });

       sinhalaSongViewHolder.onRecyclerViewClick(new SinhalaSongViewHolder.onRecyclerViewClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), VideoPlayer.class);
                intent.putExtra("video",sinhalaSongsList.get(position).getURL());
                intent.putExtra("ID",sinhalaSongsList.get(position).getID());
                intent.putExtra("Title",sinhalaSongsList.get(position).getTitle());
                intent.putExtra("imageUrl",sinhalaSongsList.get(position).getImageUrl());
                startActivity(intent);
            }
        });

        sinhalaPoemViewHolder.onRecyclerViewClick(new SinhalaPoemViewHolder.onRecyclerViewClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), VideoPlayer.class);
                intent.putExtra("video",sinhalaPoemsList.get(position).getURL());
                intent.putExtra("ID",sinhalaPoemsList.get(position).getID());
                intent.putExtra("Title",sinhalaPoemsList.get(position).getTitle());
                intent.putExtra("imageUrl",sinhalaPoemsList.get(position).getImageUrl());
                startActivity(intent);
            }
        });

        return root;
    }

    private void GetSBDataFromFirebase() {
        fstore.collection("Library").document("Books").collection("SBooks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document:task.getResult()){
                                SinhalaBookDataClass data = document.toObject(SinhalaBookDataClass.class);
                                sinhalaBooksList.add(data);
                                sinhalaBookViewHolder.notifyDataSetChanged();
                            }
                        }else {
                            Toast.makeText(getContext(),"Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void GetSSDataFromFirebase() {
        fstore.collection("Library").document("Songs").collection("SSongs")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document:task.getResult()){
                                SinhalaSongDataClass data = document.toObject(SinhalaSongDataClass.class);
                                sinhalaSongsList.add(data);
                                sinhalaSongViewHolder.notifyDataSetChanged();
                            }
                        }else {
                            Toast.makeText(getContext(),"Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void GetSPDataFromFirebase() {
        fstore.collection("Library").document("Poems").collection("SPoems")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document:task.getResult()){
                                SinhalaPoemDataClass data = document.toObject(SinhalaPoemDataClass.class);
                                sinhalaPoemsList.add(data);
                                sinhalaPoemViewHolder.notifyDataSetChanged();
                            }
                        }else {
                            Toast.makeText(getContext(),"Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

   /*private void clearAll(){
        if(sinhalaBooksList != null){
            sinhalaBooksList.clear();
            if(sinhalaBookViewHolder !=null){
                sinhalaBookViewHolder.notifyDataSetChanged();
            }
        }
        sinhalaBooksList =new ArrayList<>();
    }*/
}