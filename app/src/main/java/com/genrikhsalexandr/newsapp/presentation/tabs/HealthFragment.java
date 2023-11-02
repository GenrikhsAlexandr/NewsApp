package com.genrikhsalexandr.newsapp.presentation.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.genrikhsalexandr.newsapp.databinding.FragmentHealthBinding;

public class HealthFragment extends Fragment {

    private FragmentHealthBinding binding = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHealthBinding.inflate(inflater, container, false);
        Bundle arguments = getArguments();
        assert arguments != null;
        String message = arguments.getString("message");
        TextView textView = binding.tvHealth;
        textView.setText(message);
        return binding.getRoot();
    }

    public static HealthFragment newInstance() {
        HealthFragment fragment = new HealthFragment();
        Bundle args = new Bundle();
        args.putString("message", "Hello GeneralFragment");
        fragment.setArguments(args);
        return fragment;
    }
}