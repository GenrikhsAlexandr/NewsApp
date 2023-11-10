package com.genrikhsalexandr.headlinesfeature.presentation.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.genrikhsalexandr.headlinesfeature.databinding.FragmentGeneralBinding;

public class GeneralFragment extends Fragment {

    private FragmentGeneralBinding binding = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentGeneralBinding.inflate(inflater, container, false);

        Bundle arguments = getArguments();
        assert arguments != null;
        String message = arguments.getString("message");
        return binding.getRoot();
    }

    public static GeneralFragment newInstance() {
        GeneralFragment fragment= new GeneralFragment();
        Bundle args = new Bundle();
        args.putString("message", "Hello GeneralFragment");
        fragment.setArguments(args);
        return fragment;
    }
}