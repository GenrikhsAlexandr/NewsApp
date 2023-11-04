package com.genrikhsalexandr.headlinesfeature.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;


import com.genrikhsalexandr.headlinesfeature.databinding.FragmentHeadlinesBinding;
import com.genrikhsalexandr.headlinesfeature.presentation.tabs.TabsAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class HeadlinesFragment extends Fragment implements HeadlinesView {

    TabsAdapter tabsAdapter;
    ViewPager2 viewPager;
    TabLayout tabLayout;


    public static HeadlinesFragment newInstance() {
        return new HeadlinesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private FragmentHeadlinesBinding binding = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHeadlinesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tabLayout = binding.tabLayout;
        viewPager = binding.viewPager;
        tabsAdapter = new TabsAdapter(this);
        viewPager.setAdapter(tabsAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Objects.requireNonNull(tabLayout.getTabAt(position)).select();
            }
        });
    }
}
