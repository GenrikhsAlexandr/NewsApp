package com.genrikhsaleksandr.savefeature.presentation.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.genrikhsaleksandr.savefeature.databinding.FragmentFavoritesBinding
import com.genrikhsaleksandr.savefeature.di.FavoritesComponentProvider
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
            viewModel.onNewsItemClick(it,parentFragmentManager)
        }
    )

    private fun showArticleFragment() {
        println("ClickItemNews")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as FavoritesComponentProvider).provideFavoritesComponent()
            .inject(this)
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


        binding.rvFavorites.adapter = adapter
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

    override fun onStop() {
        super.onStop()
        println("onStop favoritesFragment")
    }

    override fun onPause() {
        super.onPause()
        println("onPause favoritesFragment")
    }

    override fun onResume() {
        super.onResume()
        println("onResume favoritesFragment")
    }

    override fun onStart() {
        super.onStart()
        println("onStart favoritesFragment")
    }
}