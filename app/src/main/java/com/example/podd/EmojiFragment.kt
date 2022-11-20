package com.example.podd

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

/*
Name: Daniel Koronthaly
Reach out to me on LinkedIn or at daniel@koronthaly.net for any questions.

Specification: THIS FILE IS NOT CURRENTLY IN USE

EmojiSelection is what is currently used. This is an alternative that shows a dropdown menu to choose the emoji.

This file was deprecated because it doesn't allow the user to actually see which emoji is being selected.

To add different emojis, add the file to the drawables folder, and then in values/arrays.xml put the filename in $feeling_filenames. There must be four emojis exactly.
 */
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

