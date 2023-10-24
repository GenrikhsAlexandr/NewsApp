package com.genrikhsaleksandr.savefeature.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.genrikhsaleksandr.savefeature.databinding.FragmentSavedBinding
import com.genrikhsaleksandr.savefeature.domain.News
import kotlinx.coroutines.launch

class SavedFragment : Fragment() {

    companion object {

        fun newInstance(): SavedFragment {
            return SavedFragment()
        }
    }

    private val viewModel: FavoritesViewModel by viewModels()

    private var _binding: FragmentSavedBinding? = null
    private val binding: FragmentSavedBinding get() = _binding!!

    private val adapter: NewsAdapter = NewsAdapter(
        onNewsItemClickListener = { news ->
            showNews(news)
        }
    )

    private fun showNews(news: News) {
        Log.d("xxx", "showNews: ${news.url}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedBinding.inflate(inflater, container, false)
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