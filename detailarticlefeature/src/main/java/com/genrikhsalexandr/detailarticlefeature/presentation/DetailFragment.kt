package com.genrikhsalexandr.detailarticlefeature.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.genrikhsalexandr.detailarticlefeature.R
import com.genrikhsalexandr.detailarticlefeature.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    companion object {
        fun newInstance(): DetailFragment {
            return DetailFragment()
        }
    }

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}