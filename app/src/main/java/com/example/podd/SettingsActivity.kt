package com.example.podd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)
        this.supportFragmentManager.beginTransaction().replace(R.id.settings, SettingsFragment()).commit()

    }
}