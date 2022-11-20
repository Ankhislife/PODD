package com.example.podd

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

/*
Name: Daniel Koronthaly
Reach out to me on LinkedIn or at daniel@koronthaly.net for any questions.

Specification: Main settings page

 */
class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}

