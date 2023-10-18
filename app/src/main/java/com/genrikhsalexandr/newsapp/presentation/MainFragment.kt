package com.genrikhsalexandr.newsapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
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

        binding.toolbar.setOnMenuItemClickListener { item ->
            return@setOnMenuItemClickListener when (item.itemId) {
                R.id.search -> {
                    binding.appBar.isVisible = false
                    binding.appBarSearch.isVisible = true
                    searchFragment()
                    true
                }
                R.id.filter -> {
                    filterFragment()
                    true
                }
                else -> false
            }
        }

        binding.toolbarSearch.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
            Log.d("xxx","back")
            binding.appBar.isVisible = true
            binding.appBarSearch.isVisible = false
        }

        binding.toolbarSearch.setOnMenuItemClickListener { item ->
            return@setOnMenuItemClickListener when (item.itemId) {
                R.id.clear_text -> {
                    binding.tvSearch.text?.clear()
                    binding.appBar.isVisible = false
                    binding.appBarSearch.isVisible = true
                    true
                }
                else -> false
            }
        }

        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            return@setOnItemSelectedListener when (menuItem.itemId) {
                R.id.headlines -> {
                    headlinesFragment()
                    binding.appBar.isVisible = true
                    binding.appBarSearch.isVisible = false

                    true
                }

                R.id.saved -> {
                    savedFragment()
                    binding.appBar.isVisible = true
                    binding.appBarSearch.isVisible = false

                    true
                }

                R.id.sources -> {
                    sourcesFragment()
                    binding.appBar.isVisible = true
                    binding.appBarSearch.isVisible = false

                    true
                }

                else -> false
            }
        }

        return binding.root
    }


    private fun savedFragment() {
        childFragmentManager.commit {
            replace(R.id.fragment_container, SavedFragment.newInstance())
            addToBackStack(null)
        }
    }

    private fun sourcesFragment() {
        childFragmentManager.commit {
            replace(R.id.fragment_container, SourcesFragment.newInstance())
            addToBackStack(null)
        }
    }

    private fun headlinesFragment() {
        val headlinesFragment = HeadlinesFragment.newInstance()
        childFragmentManager.commit {
            replace(R.id.fragment_container, headlinesFragment)
            addToBackStack(null)
        }
    }

    private fun searchFragment() {
        val searchFragment = SearchFragment.newInstance()
        childFragmentManager.commit {
            replace(R.id.fragment_container, searchFragment)
            addToBackStack(null)
        }
    }

    private fun filterFragment() {
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