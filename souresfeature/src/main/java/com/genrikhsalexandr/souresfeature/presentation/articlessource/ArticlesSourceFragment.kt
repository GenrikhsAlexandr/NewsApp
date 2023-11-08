package com.genrikhsalexandr.souresfeature.presentation.articlessource

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.genrikhsaleksandr.core.domain.model.Source
import com.genrikhsalexandr.sourcesfeature.presentation.ArticlesSource.ArticlesSourceAdapter
import com.genrikhsalexandr.souresfeature.databinding.FragmentArticlesSourceBinding
import com.genrikhsalexandr.souresfeature.di.SourcesComponentProvider
import kotlinx.coroutines.launch

class ArticlesSourceFragment : Fragment() {

    companion object {

        private const val BUNDLE_KEY_ARTICLES_SOURCE = "articlesSource"

        fun newInstance(articlesSource: Source): ArticlesSourceFragment {
            return ArticlesSourceFragment().apply {
                arguments = Bundle().apply {
                    putString(BUNDLE_KEY_ARTICLES_SOURCE, articlesSource.id)
                }
            }
        }
    }

    private val viewModel: ArticlesSourceViewModel by viewModels()

    /*  @Inject
      lateinit var viewModelFactory: SourcesViewModelFactory

      private val viewModel: ArticlesSourceViewModel by viewModels { viewModelFactory }
  */
    private var _binding: FragmentArticlesSourceBinding? = null
    private val binding: FragmentArticlesSourceBinding get() = _binding!!

    private val adapter: ArticlesSourceAdapter = ArticlesSourceAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as SourcesComponentProvider).provideSourcesComponent()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticlesSourceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFavorites.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )

        binding.rvFavorites.adapter = adapter
        lifecycleScope.launch {
            viewModel.source.collect {
                adapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}