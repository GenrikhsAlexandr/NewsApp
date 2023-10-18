package com.genrikhsalexandr.newsapp.presentation.tabs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabsAdapter extends FragmentStateAdapter {
    public TabsAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 1:
                return BusinessFragment.newInstance();

            case 2:
                return TravelingFragment.newInstance();
            default:
                return GeneralFragment.newInstance();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

