package com.storycatcher.storycatcher;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LibraryAdapter extends FragmentPagerAdapter {

    private Context context;
    int totalTabs;

    public LibraryAdapter (FragmentManager fm, Context context, int totalTabs){
        super(fm);
        this.context=context;
        this.totalTabs=totalTabs;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    public Fragment getItem(int position){
        switch(position) {
            case 0:
                EnglishTabFragment englishTabFragment = new EnglishTabFragment();
                return englishTabFragment;
            case 1:
                SinhalaTabFragment sinhalaTabFragment = new SinhalaTabFragment();
                return sinhalaTabFragment;
            default:
                return null;

        }
    }

}
