package com.genrikhsalexandr.headlinesfeature.presentation.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.genrikhsalexandr.headlinesfeature.databinding.FragmentEntertaimentBinding;

public class EntertaimentFragment extends Fragment {

    private FragmentEntertaimentBinding binding = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentEntertaimentBinding.inflate(inflater, container, false);
        Bundle arguments = getArguments();
        assert arguments != null;
        String message = arguments.getString("message");
        return binding.getRoot();
    }

    public static EntertaimentFragment newInstance() {
        EntertaimentFragment fragment = new EntertaimentFragment();
        Bundle args = new Bundle();
        args.putString("message", "Hello EntertatailmentFragment");
        fragment.setArguments(args);
        return fragment;
    }
}