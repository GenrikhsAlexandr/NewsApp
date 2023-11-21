package com.genrikhsaleksandr.core.presentation.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.genrikhsaleksandr.core.databinding.FragmentSearchBinding
import com.genrikhsaleksandr.core.di.serachdi.SearchComponentProvider
import com.genrikhsaleksandr.core.di.serachdi.SearchViewModelFactory
import com.genrikhsaleksandr.core.presentation.CoreAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragment : Fragment() {

    companion object {

        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    @Inject
    lateinit var viewModelFactory: SearchViewModelFactory

    private val viewModel: SearchViewModel by viewModels { viewModelFactory }

    private val adapter: CoreAdapter = CoreAdapter(
        isPrimaryBackground = true,
        onNewsItemClickListener = {
            viewModel.onNewsItemClick(it, parentFragmentManager)
        }
    )


    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!

    lateinit var searchView: SearchView


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as SearchComponentProvider).provideSearchComponent()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        divider()
        onBackIconClick()
        binding.rvSearch.adapter = adapter
        lifecycleScope.launch {
            viewModel.news.collect {
                adapter.submitList(it)
            }
        }

        getQuery()
    }

    private fun getQuery() {
        searchView = binding.searchView
       binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.onSearchQuery(newText)
                }
                return true
            }

        })
    }

    private fun onBackIconClick() {
        binding.ivSearchView.setOnClickListener {
            viewModel.onNavigationBackSearch(parentFragmentManager)
        }
    }

    private fun divider() {
        binding.rvSearch.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}