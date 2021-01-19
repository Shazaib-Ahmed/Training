package com.example.sampleproject_1;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager supportFragmentManager, int tabCount) {
        super(supportFragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                HomeFragment tab1 = new HomeFragment();
                return tab1;
            case 1:
                HistoryFragment tab2 = new HistoryFragment();
                return tab2;
            default:
                return null;
        }
    }
        @Override
        public int getCount () {
            return 2;
        }

}
