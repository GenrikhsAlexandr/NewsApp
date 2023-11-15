package com.genrikhsalexandr.searchfeature.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.genrikhsalexandr.searchfeature.databinding.FragmentSearchBinding
import com.genrikhsalexandr.searchfeature.di.SearchComponentProvider
import com.genrikhsalexandr.searchfeature.di.SearchViewModelFactory
import com.genrikhsalexandr.searchfeature.domain.SearchInteractor
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


    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!

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