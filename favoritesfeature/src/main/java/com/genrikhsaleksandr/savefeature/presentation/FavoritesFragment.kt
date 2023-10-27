package com.genrikhsaleksandr.savefeature.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.savefeature.databinding.FragmentFavoritesBinding
import com.genrikhsaleksandr.savefeature.domain.News
import kotlinx.coroutines.launch

class FavoritesFragment : Fragment() {

    companion object {

        fun newInstance(): FavoritesFragment {
            return FavoritesFragment()
        }
    }

    private val viewModel: FavoritesViewModel by viewModels()

    private var _binding: FragmentFavoritesBinding? = null
    private val binding: FragmentFavoritesBinding get() = _binding!!

    private val adapter: FavoritesAdapter = FavoritesAdapter(
        onNewsItemClickListener = {
            showNews(it)
        }
    )

    private fun showNews(news: Article) {
        Log.d("xxx", "showNews: $news")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvSearch.adapter = adapter
        lifecycleScope.launch {
            viewModel.news.collect { news ->

                adapter.submitData(news)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}