package com.storycatcher.storycatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
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

public class LibraryScreen extends AppCompatActivity {
    Context context;
    TabLayout tabLayout;
    ViewPager viewPager;
    private ImageButton settingBtn;
    private  TextView kidName;
    private  String currentKidID;
    FirebaseFirestore fstore;
    float v=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_screen);
        context = this;
        kidName = findViewById(R.id.txtKidsName);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        settingBtn = findViewById(R.id.SettingButton);
        fstore = FirebaseFirestore.getInstance();

        currentKidID = getIntent().getStringExtra("currentKid_ID");

        //Write to shared preference
        SharedPreferences sharedPref = this.getSharedPreferences("pref",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("kidID",currentKidID);
        editor.apply();

        tabLayout.addTab(tabLayout.newTab().setText("English"));
        tabLayout.addTab(tabLayout.newTab().setText("සිංහල"));
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
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        break;

                    case R.id.myLibrary:
                        Intent intent1=new Intent(LibraryScreen.this,MyLibraryScreen.class);
                        intent1.putExtra("currentKid_ID",currentKidID);
                        startActivity(intent1);
                        finish();
                        overridePendingTransition(0,0);
                        break;

                    case R.id.search:
                        Intent intent2=new Intent(LibraryScreen.this,SearchActivity.class);
                        intent2.putExtra("currentKid_ID",currentKidID);
                        startActivity(intent2);
                        finish();
                        overridePendingTransition(0,0);
                        break;

                    case R.id.games:
                        Intent intent3=new Intent(LibraryScreen.this,GamesScreen.class);
                        intent3.putExtra("currentKid_ID",currentKidID);
                        startActivity(intent3);
                        finish();
                        overridePendingTransition(0,0);
                        break;
                }

                return false;
            }
        });


        settingBtn.setOnTouchListener(new View.OnTouchListener() {
            View v;
            private GestureDetector gestureDetector = new GestureDetector(LibraryScreen.this, new GestureDetector.OnGestureListener() {
                @Override
                public boolean onDown(MotionEvent e) {
                    return false;
                }

                @Override
                public void onShowPress(MotionEvent e) {

                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    Toast.makeText(LibraryScreen.this,"Long Press to enter Settings",Toast.LENGTH_SHORT).show();
                    return false;
                }
                @Override
                public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                    return false;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    Intent intent = new Intent(LibraryScreen.this,Settings.class);
                    startActivity(intent);
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    return false;
                }
            });
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return false;
            }
        });
        /*settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LibraryScreen.this,Settings.class);
                startActivity(intent);
            }
        });*/

    }

    public void onStart() {
        super.onStart();
        fstore.collection("Kids").document(currentKidID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            UserDataClass userDataClass = new UserDataClass();
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        String name = document.getString("kidsName");
                        kidName.setText(name);
                        //Toast.makeText(LibraryScreen.this,"Hi "+name,Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"No such document", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Available"+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LibraryScreen.this,UserList.class);
        startActivity(intent);
    }
}