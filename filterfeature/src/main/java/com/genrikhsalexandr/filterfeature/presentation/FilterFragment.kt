package com.genrikhsalexandr.filterfeature.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.genrikhsalexandr.filterfeature.R
import com.genrikhsalexandr.filterfeature.databinding.FragmentFilterBinding

class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null

    private val binding: FragmentFilterBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toggleButton.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->
            if (!isChecked) return@addOnButtonCheckedListener
            when (checkedId) {
                R.id.popular -> Toast.makeText(
                    context, "Popular Clicked",
                    Toast.LENGTH_SHORT
                ).show()

                R.id.news -> {
                    Toast.makeText(
                        context, "New Clicked",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                R.id.relevant -> Toast.makeText(
                    context, "Relevant Clicked",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
    }

    companion object {
        fun newInstance(): FilterFragment {
            return FilterFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}