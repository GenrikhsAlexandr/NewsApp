package com.genrikhsalexandr.newsapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.genrikhsaleksandr.savefeature.presentation.FavoritesFragment
import com.genrikhsalexandr.newsapp.R
import com.genrikhsalexandr.newsapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!

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

    private fun clickedBackSearchView() {
        binding.ivSearchView.setOnClickListener {
            childFragmentManager.popBackStack()
            binding.appBar.isVisible = true
            binding.layoutSearchView.isVisible = false
        }
    }

    private fun clickedBackFilterToolBar(){
        binding.toolbarFilter.setNavigationOnClickListener {
            childFragmentManager.popBackStack()
            binding.appBar.isVisible = true
            binding.appBarFilter.isVisible = false
        }

        binding.toolbarFilter.setOnMenuItemClickListener { item ->
            return@setOnMenuItemClickListener when (item.itemId) {
                R.id.checked -> {
                    Toast.makeText(context,"Filter saved",Toast.LENGTH_LONG).show()
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
                    binding.appBar.isVisible = false
                    binding.layoutSearchView.isVisible = true
                    binding.searchView.requestFocus()
                    searchFragmentInstance()
                    true
                }

                R.id.filter -> {
                    binding.appBar.isVisible = false
                    binding.appBarFilter.isVisible = true
                    filterFragmentInstance()
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
                    headlinesFragmentInstance()
                    binding.appBar.isVisible = true
                    binding.layoutSearchView.isVisible = false
                    binding.toolbar.title = getString(R.string.app_name)

                    true
                }

                R.id.saved -> {
                    savedFragmentInstance()
                    binding.appBar.isVisible = true
                    binding.layoutSearchView.isVisible = false
                    binding.toolbar.title = getString(R.string.saved)

                    true
                }

                R.id.sources -> {
                    sourcesFragmentInstance()
                    binding.appBar.isVisible = true
                    binding.layoutSearchView.isVisible = false
                    binding.toolbar.title = getString(R.string.sources)

                    true
                }

                else -> false
            }
        }
    }

    private fun savedFragmentInstance() {
        childFragmentManager.commit {
            replace(R.id.fragment_container, FavoritesFragment.newInstance())
            addToBackStack(null)
        }
    }

    private fun sourcesFragmentInstance() {
        childFragmentManager.commit {
            replace(R.id.fragment_container, SourcesFragment.newInstance())
            addToBackStack(null)
        }
    }

    private fun headlinesFragmentInstance() {
        val headlinesFragment = HeadlinesFragment.newInstance()
        childFragmentManager.commit {
            replace(R.id.fragment_container, headlinesFragment)
            addToBackStack(null)
        }
    }

    private fun searchFragmentInstance() {
        val searchFragment = SearchFragment.newInstance()
        childFragmentManager.commit {
            replace(R.id.fragment_container, searchFragment)
            addToBackStack(null)
        }
    }

    private fun filterFragmentInstance() {
        val filterFragment = FilterFragment.newInstance()
        childFragmentManager.commit {
            replace(R.id.fragment_container, filterFragment)
            addToBackStack(null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}