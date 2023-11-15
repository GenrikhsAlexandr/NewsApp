package com.genrikhsalexandr.headlinesfeature.presentation.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.genrikhsaleksandr.core.domain.model.Article;
import com.genrikhsalexandr.headlinesfeature.databinding.FragmentBusinessBinding;
import com.genrikhsalexandr.headlinesfeature.domain.HeadlinesInteractor;
import com.genrikhsalexandr.headlinesfeature.presentation.ArticleItemList;
import com.genrikhsalexandr.headlinesfeature.presentation.adapter.HeadlinesAdapter;
import com.genrikhsalexandr.headlinesfeature.presentation.presenter.BusinessPresenter;
import com.genrikhsalexandr.headlinesfeature.presentation.presenter.BusinessView;

import java.util.List;

import javax.inject.Inject;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;


public class BusinessFragment extends MvpAppCompatFragment implements BusinessView {

    public static BusinessFragment newInstance() {
        BusinessFragment fragment = new BusinessFragment();
        return fragment;
    }

    private FragmentBusinessBinding binding = null;

    @InjectPresenter
    BusinessPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentBusinessBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rvBusiness.addItemDecoration(
                new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        );
        HeadlinesAdapter adapter = new HeadlinesAdapter(article ->
                presenter.onNewsItemClick(article, getParentFragmentManager()));
        binding.rvBusiness.setAdapter(adapter);
        System.out.println("adapter = " + adapter.getItemCount());
    }

    @Override
    public void setLoading(Boolean isLoading) {
        System.out.println("isLoading = " + isLoading);
        // Показываем\прячем прогрессбар
        binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showNews(List<ArticleItemList> news) {
        // Показываем список новостей
        binding.rvBusiness.setVisibility(View.VISIBLE);
    }
}