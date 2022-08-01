package com.codepath.articlesearch

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.codepath.articlesearch.databinding.ActivityMainBinding
import com.codepath.articlesearch.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }
    companion object {
        fun newInstance() =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Set the initial state of the switch based on the dark mode preference
        binding.switchDarkMode.isChecked = sharedPreferences.getBoolean("darkMode", false)

        // Set listener for switch to update dark mode preference and UI
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            // Update dark mode preference
            sharedPreferences.edit().putBoolean("darkMode", isChecked).apply()
            // Update UI based on dark mode preference
            updateUI(isChecked)
        }

        // Update UI components based on the current dark mode preference
        updateUI(binding.switchDarkMode.isChecked)
    }



    private fun updateUI(isDarkModeEnabled: Boolean) {
        if (isDarkModeEnabled) {
            // Apply dark mode UI changes
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            // Apply light mode UI changes
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        // Recreate the activity to apply the theme changes
        requireActivity().recreate()
    }


}