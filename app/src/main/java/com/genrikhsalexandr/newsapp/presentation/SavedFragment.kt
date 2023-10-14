package com.genrikhsalexandr.newsapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.genrikhsalexandr.newsapp.databinding.FragmentSavedBinding

class SavedFragment : Fragment() {

    companion object {
        fun newInstance() = SavedFragment()
    }

    private var _binding: FragmentSavedBinding? = null
    private val binding: FragmentSavedBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root
    }
}