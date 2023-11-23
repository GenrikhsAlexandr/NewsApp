package com.genrikhsalexandr.newsapp.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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
        (requireActivity().application as MainComponentProvider).provideMainComponent()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.enableEdgeToEdge()
        super.onViewCreated(view, savedInstanceState)
        mainToolBar()
        bottomNavigation()
        subscribe()
        onClickNavigationIcon()
    }

    private fun mainToolBar() {
        binding.toolbar.setOnMenuItemClickListener { item ->
            return@setOnMenuItemClickListener when (item.itemId) {
                R.id.search -> {
                    viewModel.onSearchClick(parentFragmentManager)
                    true
                }

                R.id.filter -> {
                    viewModel.onFilterClick(parentFragmentManager)
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
                    true
                }

                R.id.favorite -> {
                    viewModel.onFavoritesClick(parentFragmentManager)
                    binding.toolbar.title = getString(R.string.saved)
                    true
                }

                R.id.sources -> {
                    viewModel.onSourcesClick(parentFragmentManager)
                    binding.toolbar.title = getString(R.string.sources)
                    true
                }

                else -> false
            }
        }
    }

    private fun subscribe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isNavigationIconVisible.collect {
                if (it) {
                    binding.toolbar.setNavigationIcon(
                        com.genrikhsalexandr.detailarticlefeature.R.drawable.ic_back
                    )
                } else {
                    binding.toolbar.navigationIcon = null
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.toolBarTitle.collect {
                binding.toolbar.title = it
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isAppBarVisible.collect {
                binding.appBar.isVisible = it
            }
        }
    }

    private fun onClickNavigationIcon() {
        binding.toolbar.setNavigationOnClickListener {
            viewModel.onNavigationIconArticlesSourceClick(parentFragmentManager)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}