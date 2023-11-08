package com.genrikhsalexandr.detailarticlefeature.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsalexandr.detailarticlefeature.R
import com.genrikhsalexandr.detailarticlefeature.databinding.FragmentDetailBinding
import com.genrikhsalexandr.detailarticlefeature.di.DetailComponentProvider
import com.genrikhsalexandr.detailarticlefeature.di.DetailViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class DetailFragment : Fragment() {

    companion object {

        private const val BUNDLE_KEY_ARTICLE = "article"

        fun newInstance(article: Article): DetailFragment {
            return DetailFragment().apply {
                arguments = bundleOf(
                    BUNDLE_KEY_ARTICLE to article
                )
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: DetailViewModelFactory

    private val viewModel: DetailViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as DetailComponentProvider).provideDetailComponent()
            .inject(this)
    }

    private var article: Article? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        article =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getSerializable(BUNDLE_KEY_ARTICLE, Article::class.java)
            } else
                arguments?.getSerializable(BUNDLE_KEY_ARTICLE) as Article

        article?.let {
            binding.contentDetail.text = it.content

            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
            val outputFormat = SimpleDateFormat("MMM dd, yyyy | hh:mm a", Locale.US)
            val date = inputFormat.parse(it.publishedAt)
            val formattedDate = outputFormat.format(date)

            binding.dateDetail.text = formattedDate
            binding.titleDetail.text = it.title
            binding.collapsingToolbarArticle.title = it.title
            if (it.urlToImage != null) {
                Picasso.get()
                    .load(it.urlToImage.toString())
                    .into(binding.imageArticle)
            }
        }
       // article?.let { viewModel.setArticle(it) }

        binding.toolbarArticle.setNavigationOnClickListener {
            fragmentManager?.popBackStack()
        }

        binding.toolbarArticle.setOnMenuItemClickListener { item ->
            return@setOnMenuItemClickListener when (item.itemId) {
                R.id.saved -> {
                    viewModel.onFavoriteButtonClicked()
                    true
                }

                else -> false
            }
        }
        subscribe()
        setClickListeners()
        return binding.root
    }

    private fun setClickListeners(){
        val articleUrl = article?.url
        binding.contentDetail.setOnClickListener {
            val uri = Uri.parse(articleUrl)
            val intent = Intent(Intent.ACTION_VIEW,uri)
            startActivity(intent)
        }
    }

    private fun subscribe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isIconClick.collect { isIconClick ->
                val menuItem = binding.toolbarArticle.menu.findItem(R.id.saved)
                if (isIconClick) {
                    menuItem.setIcon(R.drawable.ic_favoriteschoose)
                } else {
                    menuItem.setIcon(R.drawable.ic_favorites)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}