package com.genrikhsalexandr.searchfeature.presentation

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
import com.genrikhsalexandr.searchfeature.databinding.FragmentSearchBinding
import com.genrikhsalexandr.searchfeature.di.SearchComponentProvider
import com.genrikhsalexandr.searchfeature.di.SearchViewModelFactory
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

    private val adapter: SearchAdapter = SearchAdapter(
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
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        divider()
        onBackIconClick()
        binding.rvSearch.adapter = adapter
        lifecycleScope.launch {
            viewModel.query.collect {
                adapter.submitData(it)
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
                    viewModel.setQuery(newText)
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