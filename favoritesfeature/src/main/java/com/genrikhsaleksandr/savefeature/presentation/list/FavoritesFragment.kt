package com.genrikhsaleksandr.savefeature.presentation.list

import android.content.Context
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
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesFragment : Fragment() {

    companion object {

        fun newInstance(): FavoritesFragment {
            return FavoritesFragment()
        }
    }

    @Inject
    lateinit var viewModel: FavoritesViewModel


    private var _binding: FragmentFavoritesBinding? = null
    private val binding: FragmentFavoritesBinding get() = _binding!!

    private val adapter: FavoritesAdapter = FavoritesAdapter(
        onNewsItemClickListener = {
            showArticleFragment()
        }
    )

    private fun showArticleFragment() {
        println("ClickItemNews")
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
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