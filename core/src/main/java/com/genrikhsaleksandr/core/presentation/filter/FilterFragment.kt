package com.genrikhsaleksandr.core.presentation.filter

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.genrikhsaleksandr.core.R
import com.genrikhsaleksandr.core.databinding.FragmentFilterBinding
import com.genrikhsaleksandr.core.di.filterdi.FilterComponentProvider
import com.genrikhsaleksandr.core.di.filterdi.FilterViewModelFactory
import com.genrikhsaleksandr.core.domain.model.ArticleTag
import com.genrikhsaleksandr.core.domain.model.LocaleFilter
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isArticleTagClick()
        buttonCalendarClick()
        setOnMenuItemClickListener()
        isLanguageClick()
        subscribe()
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

    private fun isArticleTagClick() {
        binding.toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.popular -> {
                        viewModel.setArticleTag(ArticleTag.POPULAR)
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
                        viewModel.setArticleTag(ArticleTag.NEW)
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
                        viewModel.setArticleTag(ArticleTag.RELEVANT)

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


    private fun isLanguageClick() {
        binding.chipDeutsch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onLanguageChipClicked(LocaleFilter.Deutsch(isEnable = isChecked))
        }
        binding.chipEnglish.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onLanguageChipClicked(LocaleFilter.English(isEnable = isChecked))
        }
        binding.chipRussian.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onLanguageChipClicked(LocaleFilter.Russian(isEnable = isChecked))
        }
    }

    private fun subscribe() {
        lifecycleScope.launch {
            viewModel.state.observe(viewLifecycleOwner) { state ->
                if (state.selectedDate != null) {
                    binding.tvSelectDate.text = state.selectedDate
                    binding.tvSelectDate.setTextColor(resources.getColor(R.color.primary_text))
                    binding.calendar.setIconResource(R.drawable.ic_selected_date)
                    binding.calendar.setIconTintResource(R.color.light_text)
                    binding.calendar.setBackgroundColor(resources.getColor(R.color.primary_text))
                }
                state.selectedLanguage.forEach {
                    when (it) {
                        is LocaleFilter.Deutsch -> {
                            binding.chipDeutsch.isChecked = it.isEnable
                        }

                        is LocaleFilter.English -> {
                            binding.chipEnglish.isChecked = it.isEnable
                        }

                        is LocaleFilter.Russian -> {
                            binding.chipRussian.isChecked = it.isEnable
                        }
                    }
                }
                state.selectedTag
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun buttonCalendarClick() {
        binding.calendar.setOnClickListener {
            openCalendar()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
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
            viewModel.setDate(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}