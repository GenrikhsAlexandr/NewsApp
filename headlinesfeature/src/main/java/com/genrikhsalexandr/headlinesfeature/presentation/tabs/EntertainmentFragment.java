package com.genrikhsalexandr.headlinesfeature.presentation.tabs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.genrikhsaleksandr.core.presentation.ArticleItemList;
import com.genrikhsaleksandr.core.presentation.CoreAdapter;
import com.genrikhsalexandr.headlinesfeature.databinding.FragmentEntertaimentBinding;
import com.genrikhsalexandr.headlinesfeature.di.HeadlinesComponentProvider;
import com.genrikhsalexandr.headlinesfeature.presentation.presenter.EntertainmentPresenter;
import com.genrikhsalexandr.headlinesfeature.presentation.presenter.HeadlinesView;

import java.util.List;

import javax.inject.Inject;

import kotlin.Unit;
import moxy.MvpAppCompatFragment;


public class EntertainmentFragment extends MvpAppCompatFragment implements HeadlinesView {

    public EntertainmentFragment() {
    }

    public static EntertainmentFragment newInstance() {
        return new EntertainmentFragment();
    }

    private FragmentEntertaimentBinding _binding;

    private FragmentEntertaimentBinding getBinding() {
        return _binding;
    }

    @Inject
    EntertainmentPresenter presenter;

    CoreAdapter adapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((HeadlinesComponentProvider) requireActivity().getApplication())
                .provideHeadlinesComponent()
                .inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        _binding = FragmentEntertaimentBinding.inflate(inflater, container, false);
        return getBinding().getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new CoreAdapter(article ->
        {
            presenter.onNewsItemClick(article, requireParentFragment().getParentFragmentManager());
            return Unit.INSTANCE;
        });
        getBinding().rvEntertainment.setAdapter(adapter);
        getBinding().rvEntertainment.addItemDecoration(
                new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        );
    }

    @Override
    public void setLoading(Boolean isLoading) {
        System.out.println("isLoading = " + isLoading);
        getBinding().progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showArticles(List<ArticleItemList> news) {
        getBinding().rvEntertainment.setVisibility(View.VISIBLE);
        adapter.submitData(news);
        System.out.println("showArticles = " + news);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}