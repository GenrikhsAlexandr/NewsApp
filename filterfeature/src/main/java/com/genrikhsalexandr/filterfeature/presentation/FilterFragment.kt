package com.genrikhsalexandr.filterfeature.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.genrikhsalexandr.filterfeature.R
import com.genrikhsalexandr.filterfeature.databinding.FragmentFilterBinding
import com.google.android.material.datepicker.MaterialDatePicker


class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null

    private val binding: FragmentFilterBinding get() = _binding!!

    private val viewModel: FilterViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /* binding.toggleButton.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->
             if (!isChecked) return@addOnButtonCheckedListener
             when (checkedId) {
                 R.id.popular -> Toast.makeText(
                     context, "Popular Clicked",
                     Toast.LENGTH_SHORT
                 ).show()

                 R.id.news -> {
                     viewModel.onButtonClicked()
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
         }*/
        isButtonChecked()
        buttonCalendarClick()
    }

    companion object {
        fun newInstance(): FilterFragment {
            return FilterFragment()
        }
    }

    private fun isButtonChecked() {

        binding.toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.popular -> {
                        binding.popular.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.icon_checked, 0, 0, 0
                        )
                        binding.news.setCompoundDrawablesWithIntrinsicBounds(
                            0, 0, 0, 0
                        )
                        binding.relevant.setCompoundDrawablesWithIntrinsicBounds(
                            0, 0, 0, 0
                        )

                    }

                    R.id.news -> {
                        binding.news.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.icon_checked, 0, 0, 0
                        )
                        binding.popular.setCompoundDrawablesWithIntrinsicBounds(
                            0, 0, 0, 0
                        )
                        binding.relevant.setCompoundDrawablesWithIntrinsicBounds(
                            0, 0, 0, 0
                        )
                    }

                    R.id.relevant -> {
                        binding.relevant.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.icon_checked, 0, 0, 0
                        )
                        binding.popular.setCompoundDrawablesWithIntrinsicBounds(
                            0, 0, 0, 0
                        )
                        binding.news.setCompoundDrawablesWithIntrinsicBounds(
                            0, 0, 0, 0
                        )
                    }
                }
                // binding.toggleButton.clearChecked()
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