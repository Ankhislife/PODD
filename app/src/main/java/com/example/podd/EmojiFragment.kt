package com.example.podd

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat


class EmojiFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.emojipref, rootKey)
        val feeling = arguments?.getString("feeling")
        for(p in preferenceScreen.sharedPreferences?.all?.keys!!) {
            val pref = findPreference<Preference>(p)

            //The only dropdown displayed is the one for our specific feeling
            if (pref != null && p != feeling && p != "color") {
                pref.isVisible = false
            }

        }
    }
}

