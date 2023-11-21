package com.genrikhsalexandr.headlinesfeature.presentation.tabs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.genrikhsaleksandr.core.presentation.ItemList;
import com.genrikhsaleksandr.core.presentation.adapter.CoreAdapter;
import com.genrikhsalexandr.headlinesfeature.databinding.FragmentTechnologyBinding;
import com.genrikhsalexandr.headlinesfeature.di.HeadlinesComponentProvider;
import com.genrikhsalexandr.headlinesfeature.presentation.presenter.HeadlinesView;
import com.genrikhsalexandr.headlinesfeature.presentation.presenter.TechnologyPresenter;

import java.util.List;

import javax.inject.Inject;

import kotlin.Unit;
import moxy.MvpAppCompatFragment;

public class TechnologyFragment extends MvpAppCompatFragment implements HeadlinesView {

    public TechnologyFragment() {
    }

    public static TechnologyFragment newInstance() {
        return new TechnologyFragment();
    }

    private FragmentTechnologyBinding _binding;

    private FragmentTechnologyBinding getBinding() {
        return _binding;
    }

    @Inject
    TechnologyPresenter presenter;

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
        _binding = FragmentTechnologyBinding.inflate(inflater, container, false);
        return getBinding().getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new CoreAdapter(
                false,
                article ->
                {
                    presenter.onArticleItemClick(article, requireParentFragment().getParentFragmentManager());
                    return Unit.INSTANCE;
                });
        getBinding().rvTechnology.setAdapter(adapter);
        getBinding().rvTechnology.addItemDecoration(
                new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        );
        SwipeRefreshLayout swipeRefresh = getBinding().swipeRefresh;
        swipeRefresh.setOnRefreshListener(() -> {
            presenter.onRefresh();
            swipeRefresh.setRefreshing(false);
        });

        getBinding().rvTechnology.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    presenter.loadNextPage();
                }
            }
        });
    }

    @Override
    public void setLoading(Boolean isLoading) {
        System.out.println("isLoading = " + isLoading);
        getBinding().progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showArticles(List<ItemList> article) {
        getBinding().rvTechnology.setVisibility(View.VISIBLE);
        adapter.submitList(article);
        System.out.println("showArticles = " + article);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}