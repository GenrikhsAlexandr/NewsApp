package com.genrikhsalexandr.newsapp.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.genrikhsalexandr.newsapp.R
import com.genrikhsalexandr.newsapp.databinding.FragmentMainBinding
import com.genrikhsalexandr.newsapp.di.MainComponentProvider
import com.genrikhsalexandr.newsapp.di.MainViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private val viewModel: MainFragmentViewModel by viewModels { viewModelFactory }


    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MainComponentProvider).provideMainComponent().inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        mainToolBar()
        bottomNavigation()
        clickedBackSearchView()
        clickedBackFilterToolBar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewModelScope.launch {
            viewModel.isAppBarVisible.collect {
                binding.appBar.isVisible = it
            }
        }
    }

    private fun clickedBackSearchView() {
        binding.ivSearchView.setOnClickListener {
            childFragmentManager.popBackStack()
        }
    }

    private fun clickedBackFilterToolBar() {
        binding.toolbarFilter.setNavigationOnClickListener {
            childFragmentManager.popBackStack()
        }

        binding.toolbarFilter.setOnMenuItemClickListener { item ->
            return@setOnMenuItemClickListener when (item.itemId) {
                com.genrikhsalexandr.filterfeature.R.id.checked -> {
                    Toast.makeText(context, "Filter saved", Toast.LENGTH_LONG).show()
                    childFragmentManager.popBackStack()
                    binding.appBar.isVisible = true
                    binding.appBarFilter.isVisible = false
                    true
                }

                else -> false
            }
        }
    }

    private fun mainToolBar() {
        binding.toolbar.setOnMenuItemClickListener { item ->
            return@setOnMenuItemClickListener when (item.itemId) {
                R.id.search -> {
                    viewModel.onSearchClick(parentFragmentManager)
                    binding.searchView.requestFocus()
                    binding.layoutSearchView.isVisible = true

                    true
                }

                R.id.filter -> {
                    viewModel.onFilterClick(parentFragmentManager)
                    binding.appBarFilter.isVisible = true
                    true
                }

                else -> false
            }
        }
    }

    private fun bottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            return@setOnItemSelectedListener when (menuItem.itemId) {
                R.id.headlines -> {
                    viewModel.onHeadlinesClick(parentFragmentManager)
                    binding.toolbar.title = getString(R.string.app_name)
                    binding.appBarFilter.isVisible = false
                    binding.layoutSearchView.isVisible = false
                    true
                }

                R.id.favorite -> {
                    viewModel.onFavoritesClick(parentFragmentManager)
                    binding.toolbar.title = getString(R.string.saved)
                    binding.appBarFilter.isVisible = false
                    binding.layoutSearchView.isVisible = false
                    true
                }

                R.id.sources -> {
                    viewModel.onSourcesClick(parentFragmentManager)
                    binding.layoutSearchView.isVisible = false
                    binding.toolbar.title = getString(R.string.sources)
                    binding.appBarFilter.isVisible = false
                    binding.layoutSearchView.isVisible = false
                    true
                }

                else -> false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}