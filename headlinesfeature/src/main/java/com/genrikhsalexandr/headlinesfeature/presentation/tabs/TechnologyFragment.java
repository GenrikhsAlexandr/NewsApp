package com.genrikhsalexandr.headlinesfeature.presentation.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.genrikhsalexandr.headlinesfeature.databinding.FragmentTechnologyBinding;

public class TechnologyFragment extends Fragment {
    private FragmentTechnologyBinding binding = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentTechnologyBinding.inflate(inflater, container, false);
        Bundle arguments = getArguments();
        assert arguments != null;
        String message = arguments.getString("message");
        TextView textView = binding.tvTechnology;
        textView.setText(message);
        return binding.getRoot();
    }

    public static TechnologyFragment newInstance() {
        TechnologyFragment fragment = new TechnologyFragment();
        Bundle args = new Bundle();
        args.putString("message", "Hello TechnjlogyFragment");
        fragment.setArguments(args);
        return fragment;
    }
}