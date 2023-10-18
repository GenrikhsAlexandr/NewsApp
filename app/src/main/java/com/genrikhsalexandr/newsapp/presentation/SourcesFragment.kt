package com.genrikhsalexandr.newsapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.genrikhsalexandr.newsapp.databinding.FragmentSourcesBinding

class SourcesFragment : Fragment() {

    companion object {
        fun newInstance() = SourcesFragment()
    }

    private var _binding: FragmentSourcesBinding? = null
    private val binding: FragmentSourcesBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSourcesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}