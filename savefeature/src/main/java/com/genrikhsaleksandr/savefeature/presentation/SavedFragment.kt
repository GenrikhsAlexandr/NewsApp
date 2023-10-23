package com.genrikhsaleksandr.savefeature.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.genrikhsaleksandr.savefeature.databinding.FragmentSavedBinding

class SavedFragment : Fragment() {

    companion object {

        fun newInstance(): SavedFragment {
            return SavedFragment()
        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}