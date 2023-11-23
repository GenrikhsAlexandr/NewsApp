package com.genrikhsaleksandr.core.presentation.filter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.genrikhsaleksandr.core.R
import com.genrikhsaleksandr.core.databinding.FragmentFilterBinding
import com.genrikhsaleksandr.core.di.filterdi.FilterComponentProvider
import com.genrikhsaleksandr.core.di.filterdi.FilterViewModelFactory
import com.google.android.material.datepicker.MaterialDatePicker
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
                when (checkedId) {
                    R.id.popular -> {
                        binding.popular.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.icon_checked, 0,
                            0, 0
                        )
                        binding.news.setCompoundDrawablesWithIntrinsicBounds(
                            0, 0,
                            0, 0
                        )
                        binding.relevant.setCompoundDrawablesWithIntrinsicBounds(
                            0, 0,
                            0, 0
                        )
                    }

                    R.id.news -> {
                        binding.news.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.icon_checked, 0,
                            0, 0
                        )
                        binding.relevant.setCompoundDrawablesWithIntrinsicBounds(
                            0, 0,
                            0, 0
                        )
                        binding.popular.setCompoundDrawablesWithIntrinsicBounds(
                            0, 0,
                            0, 0
                        )
                    }

                    R.id.relevant -> {
                        binding.relevant.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.icon_checked, 0,
                            0, 0
                        )
                        binding.news.setCompoundDrawablesWithIntrinsicBounds(
                            0, 0,
                            0, 0
                        )
                        binding.popular.setCompoundDrawablesWithIntrinsicBounds(
                            0, 0,
                            0, 0
                        )
                    }
                }
            }
        }
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
            dateRangePicker.selection?.let { viewModel.setDate(it) }
            println("dateRangePicker = ${dateRangePicker.selection}")
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