package com.example.storesummary;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class pageAdapter extends FragmentPagerAdapter {
    public pageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment=null;
        switch (position){
            case 0:
                fragment=new allFragment();
                break;
            case 1:
                fragment=new debitorFragment();
                break;
            case 2:
                fragment=new CreditorFragment();
                break;
        }
        return fragment;













    }

    @Override
    public int getCount() {
        return 3;
    }
}
