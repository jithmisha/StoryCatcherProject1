package com.storycatcher.storycatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class LibraryScreen extends AppCompatActivity {
    Context context;
    TabLayout tabLayout;
    ViewPager viewPager;
    private ImageButton settingBtn;
    private  TextView kidName;
    private  String currentKidName, currentKidID;
    FirebaseFirestore fstore;
    float v=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_screen);
        context=this;
        kidName=findViewById(R.id.txtKidsName);
        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager);
        settingBtn=findViewById(R.id.SettingButton);
        fstore=FirebaseFirestore.getInstance();

        currentKidID = getIntent().getStringExtra("currentKid_ID");
        //currentKidName = getIntent().getStringExtra("currentKid_Name");
        //kidName.setText(currentKidName);

        // --> Toast message

        // --> Firebase code to get current kid name from current KidID
        tabLayout.addTab(tabLayout.newTab().setText("English"));
        tabLayout.addTab(tabLayout.newTab().setText("Sinhala"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final LibraryAdapter adapter = new LibraryAdapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setTranslationY(300);
        tabLayout.setAlpha(v);
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                Log.i("TAG", "onTabSelected: " + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.i("TAG", "onTabUnselected: " + tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.i("TAG", "onTabReselected: " + tab.getPosition());
            }
        });

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId((R.id.home));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.myLibrary:
                        startActivity(new Intent(getApplicationContext(),MyLibraryScreen.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(),SearchActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.games:
                        startActivity(new Intent(getApplicationContext(),GamesScreen.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LibraryScreen.this,Settings.class);
                intent.putExtra("currentKid_ID",currentKidID);
                startActivity(intent);
            }
        });
    }

    public void onStart() {
        super.onStart();
        fstore.collection("Kids").document(currentKidID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            User user = new User();
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document=task.getResult();
                    if(document.exists()){
                        //Toast.makeText(getApplicationContext(),"Available", Toast.LENGTH_SHORT).show();
                        String name=document.getString("kidsName");
                        kidName.setText(name);
                        Toast.makeText(LibraryScreen.this,"Hi "+name,Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"No such document", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Available"+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    /*@Override
    public void finish() {
        text= kidName.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("name", text);
        setResult(123, intent);
        super.finish();
    }*/




}