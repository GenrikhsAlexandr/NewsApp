package com.genrikhsalexandr.souresfeature.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.genrikhsalexandr.souresfeature.databinding.FragmentSourcesBinding
import com.genrikhsalexandr.souresfeature.di.SourcesComponentProvider
import com.genrikhsalexandr.souresfeature.di.SourcesViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class SourcesFragment @Inject constructor(
): Fragment() {

    companion object {

        fun newInstance(): SourcesFragment {
            return SourcesFragment()
        }
    }

    @Inject
    lateinit var viewModelFactory: SourcesViewModelFactory

    private val viewModel: SourcesViewModel by viewModels { viewModelFactory }


    private var _binding: FragmentSourcesBinding? = null
    private val binding: FragmentSourcesBinding get() = _binding!!

    private val adapter: SourcesAdapter = SourcesAdapter(
        onNewsItemClickListener = {
            viewModel.onNewsItemClick(it, parentFragmentManager)
        }
    )


    override fun onAttach(context: Context) {
        super.onAttach(context)
         (requireActivity().application as SourcesComponentProvider).provideSourcesComponent()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSourcesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFavorites.addItemDecoration(
            DividerItemDecoration (requireContext(), DividerItemDecoration.VERTICAL)
        )

        binding.rvFavorites.adapter = adapter
        lifecycleScope.launch {
            viewModel.source.collect { source ->
                adapter.submitData(source)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}