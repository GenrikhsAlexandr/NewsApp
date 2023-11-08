package com.genrikhsalexandr.detailarticlefeature.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsalexandr.detailarticlefeature.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Locale

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

    private val viewModel: DetailViewModel by viewModels()

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val article =
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

        binding.toolbarArticle.setNavigationOnClickListener {
            fragmentManager?.popBackStack()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}