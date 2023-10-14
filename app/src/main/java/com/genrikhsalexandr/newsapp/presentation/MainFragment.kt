package com.genrikhsalexandr.newsapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.genrikhsalexandr.newsapp.R
import com.genrikhsalexandr.newsapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            return@setOnItemSelectedListener when (menuItem.itemId) {
                R.id.headlines -> {
                    headlinesFragment()
                    true
                }

                R.id.saved -> {
                    savedFragment()
                    true
                }

                R.id.sources -> {
                    sourcesFragment()
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
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}