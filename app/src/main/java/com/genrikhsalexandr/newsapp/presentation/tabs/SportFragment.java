package com.genrikhsalexandr.newsapp.presentation.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.genrikhsalexandr.newsapp.databinding.FragmentSportBinding;

public class SportFragment extends Fragment {
    private FragmentSportBinding binding = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSportBinding.inflate(inflater, container, false);
        Bundle arguments = getArguments();
        assert arguments != null;
        String message = arguments.getString("message");
        TextView textView = binding.tvSport;
        textView.setText(message);
        return binding.getRoot();
    }

    public static SportFragment newInstance() {
        SportFragment fragment = new SportFragment();
        Bundle args = new Bundle();
        args.putString("message", "Hello GeneralFragment");
        fragment.setArguments(args);
        return fragment;
    }
}