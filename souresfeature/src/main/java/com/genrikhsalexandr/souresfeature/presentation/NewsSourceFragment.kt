package com.genrikhsalexandr.souresfeature.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.genrikhsalexandr.souresfeature.databinding.FragmentNewsSourceBinding

class NewsSourceFragment : Fragment() {

    companion object {
        fun newInstance() = NewsSourceFragment()
    }

    private var _binding: FragmentNewsSourceBinding? = null
    private val binding: FragmentNewsSourceBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsSourceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}