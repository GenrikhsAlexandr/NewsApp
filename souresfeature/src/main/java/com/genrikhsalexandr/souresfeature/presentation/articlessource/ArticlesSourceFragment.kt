package com.genrikhsalexandr.souresfeature.presentation.articlessource

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.genrikhsalexandr.souresfeature.databinding.FragmentArticlesSourceBinding

class ArticlesSourceFragment : Fragment() {

    companion object {
        fun newInstance() = ArticlesSourceFragment()
    }

    private var _binding: FragmentArticlesSourceBinding? = null
    private val binding: FragmentArticlesSourceBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticlesSourceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}