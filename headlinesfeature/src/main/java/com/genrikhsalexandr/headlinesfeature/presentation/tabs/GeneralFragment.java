package com.genrikhsalexandr.headlinesfeature.presentation.tabs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.genrikhsalexandr.headlinesfeature.databinding.FragmentGeneralBinding;
import com.genrikhsalexandr.headlinesfeature.di.HeadlinesComponentProvider;
import com.genrikhsalexandr.headlinesfeature.presentation.adapter.HeadlinesAdapter;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class GeneralFragment extends Fragment {

    public static GeneralFragment newInstance() {
        GeneralFragment fragment = new GeneralFragment();
        return fragment;
    }

    private final CompositeDisposable disposables = new CompositeDisposable();


    private FragmentGeneralBinding _binding;

    private FragmentGeneralBinding getBinding() {
        return _binding;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        _binding = FragmentGeneralBinding.inflate(inflater, container, false);
        return getBinding().getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getBinding().rvGeneral.addItemDecoration(
                new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        );
      //  getBinding().rvGeneral.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}