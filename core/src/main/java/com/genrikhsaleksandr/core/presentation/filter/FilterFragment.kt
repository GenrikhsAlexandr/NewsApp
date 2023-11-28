package com.genrikhsaleksandr.core.presentation.filter

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.genrikhsaleksandr.core.R
import com.genrikhsaleksandr.core.databinding.FragmentFilterBinding
import com.genrikhsaleksandr.core.di.filter.FilterComponentProvider
import com.genrikhsaleksandr.core.di.filter.FilterViewModelFactory
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
        binding.toggleButton.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (!isChecked) {
                return@addOnButtonCheckedListener
            }
            viewModel.setArticleTag(
                when (checkedId) {
                    R.id.popular -> ArticleTag.POPULAR
                    R.id.news -> ArticleTag.NEW
                    R.id.relevant -> ArticleTag.RELEVANT
                    else -> error("Unknown id")
                }
            )
        }
    }

    private fun isLanguageClick() {
        binding.chipDeutsch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onLanguageChipClicked(LocaleFilter.Deutsch(isEnabled = isChecked))
        }
        binding.chipEnglish.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onLanguageChipClicked(LocaleFilter.English(isEnabled = isChecked))
        }
        binding.chipRussian.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onLanguageChipClicked(LocaleFilter.Russian(isEnabled = isChecked))
        }
    }

    private fun subscribe() {
        lifecycleScope.launch {
            viewModel.state.observe(viewLifecycleOwner) { state ->
                applyState(state)
            }
        }
    }

    private fun applyState(state: FilterState) {
        if (state.selectedDateText != null) {
            binding.tvSelectDate.text = state.selectedDateText
            binding.tvSelectDate.setTextColor(resources.getColor(R.color.primary_text))
            binding.calendar.setIconResource(R.drawable.ic_selected_date)
            binding.calendar.setIconTintResource(R.color.light_text)
            binding.calendar.setBackgroundColor(resources.getColor(R.color.primary_text))
        }
        state.selectedLanguage.forEach {
            when (it) {
                is LocaleFilter.Deutsch -> {
                    binding.chipDeutsch.isChecked = it.isEnabled
                }

                is LocaleFilter.English -> {
                    binding.chipEnglish.isChecked = it.isEnabled
                }

                is LocaleFilter.Russian -> {
                    binding.chipRussian.isChecked = it.isEnabled
                }
            }
        }
        binding.popular.isChecked = state.selectedTag == ArticleTag.POPULAR
        binding.news.isChecked = state.selectedTag == ArticleTag.NEW
        binding.relevant.isChecked = state.selectedTag == ArticleTag.RELEVANT
        val (popularIcon, newsIcon, relevantIcon) = when (state.selectedTag) {
            ArticleTag.POPULAR -> Triple(R.drawable.icon_checked, 0, 0)
            ArticleTag.NEW -> Triple(0, R.drawable.icon_checked, 0)
            ArticleTag.RELEVANT -> Triple(0, 0, R.drawable.icon_checked)
        }
        binding.popular.setCompoundDrawablesWithIntrinsicBounds(
            popularIcon, 0,
            0, 0
        )
        binding.news.setCompoundDrawablesWithIntrinsicBounds(
            newsIcon, 0,
            0, 0
        )
        binding.relevant.setCompoundDrawablesWithIntrinsicBounds(
            relevantIcon, 0,
            0, 0
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun buttonCalendarClick() {
        binding.calendar.setOnClickListener {
            openCalendar()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun openCalendar() {
        val selection = viewModel.state.value?.selectedDateValue?.let { (first, second) ->
            Pair(first, second)
        } ?: Pair(
            MaterialDatePicker.thisMonthInUtcMilliseconds(),
            MaterialDatePicker.todayInUtcMilliseconds()
        )
        val dateRangePicker = MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText("Select dates")
            .setSelection(selection)
            .build()
        dateRangePicker.show(parentFragmentManager, dateRangePicker.toString())
        dateRangePicker.addOnPositiveButtonClickListener {
            viewModel.setDate(it.first to it.second)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}