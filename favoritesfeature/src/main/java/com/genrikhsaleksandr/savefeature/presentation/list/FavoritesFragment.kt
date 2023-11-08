package com.genrikhsaleksandr.savefeature.presentation.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.genrikhsaleksandr.savefeature.databinding.FragmentFavoritesBinding
import com.genrikhsaleksandr.savefeature.di.FavoritesComponentProvider
import com.genrikhsaleksandr.savefeature.di.FavoritesViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesFragment: Fragment() {

    companion object {

        fun newInstance(): FavoritesFragment {
            return FavoritesFragment()
        }
    }

    @Inject
    lateinit var viewModelFactory: FavoritesViewModelFactory

    private val viewModel: FavoritesViewModel by viewModels { viewModelFactory }


    private var _binding: FragmentFavoritesBinding? = null
    private val binding: FragmentFavoritesBinding get() = _binding!!

    private val adapter: FavoritesAdapter = FavoritesAdapter(
        onNewsItemClickListener = {
            viewModel.onNewsItemClick(it, parentFragmentManager)
        }
    )


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

        binding.rvFavorites.addItemDecoration(
            DividerItemDecoration (requireContext(), DividerItemDecoration.VERTICAL)
        )

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
}