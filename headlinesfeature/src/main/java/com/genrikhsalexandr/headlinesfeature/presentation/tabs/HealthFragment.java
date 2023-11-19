package com.genrikhsalexandr.headlinesfeature.presentation.tabs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.genrikhsaleksandr.core.presentation.ArticleItemList;
import com.genrikhsaleksandr.core.presentation.CoreAdapter;
import com.genrikhsalexandr.headlinesfeature.databinding.FragmentHealthBinding;
import com.genrikhsalexandr.headlinesfeature.di.HeadlinesComponentProvider;
import com.genrikhsalexandr.headlinesfeature.presentation.presenter.HeadlinesView;
import com.genrikhsalexandr.headlinesfeature.presentation.presenter.HealthPresenter;

import java.util.List;

import javax.inject.Inject;

import kotlin.Unit;
import moxy.MvpAppCompatFragment;

public class HealthFragment extends MvpAppCompatFragment implements HeadlinesView {

    public HealthFragment() {
    }

    public static HealthFragment newInstance() {
        return new HealthFragment();
    }

    private FragmentHealthBinding _binding;

    private FragmentHealthBinding getBinding() {
        return _binding;
    }

    @Inject
    HealthPresenter presenter;

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
        _binding = FragmentHealthBinding.inflate(inflater, container, false);
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
        getBinding().rvHealth.setAdapter(adapter);
        getBinding().rvHealth.addItemDecoration(
                new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        );
        SwipeRefreshLayout swipeRefresh = getBinding().swipeRefresh;
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefresh();
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void setLoading(Boolean isLoading) {
        System.out.println("isLoading = " + isLoading);
        getBinding().progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showArticles(List<ArticleItemList> news) {
        getBinding().rvHealth.setVisibility(View.VISIBLE);
        adapter.submitList(news);
        System.out.println("showArticles = " + news);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}