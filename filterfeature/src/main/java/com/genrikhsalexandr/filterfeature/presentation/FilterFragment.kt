package com.genrikhsalexandr.filterfeature.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.genrikhsalexandr.filterfeature.R
import com.genrikhsalexandr.filterfeature.databinding.FragmentFilterBinding
import com.genrikhsalexandr.filterfeature.di.FilterComponentProvider
import com.genrikhsalexandr.filterfeature.di.FilterViewModelFactory
import com.genrikhsalexandr.filterfeature.domain.FilterButton
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.launch
import javax.inject.Inject

class FilterFragment : Fragment() {
    companion object {
        fun newInstance(): FilterFragment {
            return FilterFragment()
        }
    }

    private var _binding: FragmentFilterBinding? = null

    private val binding: FragmentFilterBinding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: FilterViewModelFactory

    private val viewModel: FilterViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as FilterComponentProvider).provideFilterComponent()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isButtonChecked()
        buttonCalendarClick()
        subscribe()
        setOnMenuItemClickListener()

        binding.toolbarFilter.setNavigationOnClickListener {
            viewModel.onNavigationBackFilter(parentFragmentManager)
        }

    }

    private fun setOnMenuItemClickListener() {
        binding.toolbarFilter.setOnMenuItemClickListener { item ->
            return@setOnMenuItemClickListener when (item.itemId) {
                R.id.checked -> {
                    viewModel.onNavigationSavedFilter(parentFragmentManager)
                    Toast.makeText(context, "Filter saved", Toast.LENGTH_LONG).show()
                    true
                }

                else -> false
            }
        }
    }

    private fun isButtonChecked() {
        binding.toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                val button = when (checkedId) {
                    R.id.popular -> FilterButton.Popular
                    R.id.news -> FilterButton.News
                    R.id.relevant -> FilterButton.Relevant
                    else -> FilterButton.None
                }

                lifecycleScope.launch {
                    viewModel.onButtonClicked(button)
                }
            }
        }
    }

    private fun subscribe() {
        lifecycleScope.launch {
            viewModel.selectedButton.collect { selectedButton ->
                updateButtonState(selectedButton)
            }
        }
    }

    private fun updateButtonState(selectedButton: FilterButton) {
        binding.popular.setCompoundDrawablesWithIntrinsicBounds(
            if (selectedButton == FilterButton.Popular) R.drawable.icon_checked else 0,
            0, 0, 0
        )

        binding.news.setCompoundDrawablesWithIntrinsicBounds(
            if (selectedButton == FilterButton.News) R.drawable.icon_checked else 0,
            0, 0, 0
        )

        binding.relevant.setCompoundDrawablesWithIntrinsicBounds(
            if (selectedButton == FilterButton.Relevant) R.drawable.icon_checked else 0,
            0, 0, 0
        )
    }

    private fun buttonCalendarClick() {
        binding.calendar.setOnClickListener {
            openCalendar()
        }
    }

    private fun openCalendar() {
        val dateRangePicker =
            MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Select dates")
                .setSelection(
                    androidx.core.util.Pair(
                        MaterialDatePicker.thisMonthInUtcMilliseconds(),
                        MaterialDatePicker.todayInUtcMilliseconds()
                    )
                )
                .build()
        dateRangePicker.show(parentFragmentManager, dateRangePicker.toString())

        dateRangePicker.addOnPositiveButtonClickListener {
            binding.tvSelectDate.text = dateRangePicker.headerText
            binding.tvSelectDate.setTextColor(resources.getColor(R.color.primary_text))
            binding.calendar.setIconResource(R.drawable.ic_selected_date)
            binding.calendar.setIconTintResource(R.color.light_text)
            binding.calendar.setBackgroundColor(resources.getColor(R.color.primary_text))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}