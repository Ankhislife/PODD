package com.example.podd

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity


class MainMenu : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)
        val settings = findViewById<Button>(R.id.settings)
        val start = findViewById<Button>(R.id.start)

        settings.setOnClickListener {
            this.supportFragmentManager.beginTransaction().replace(R.id.main, SettingsFragment()).commit()
        }
        start.setOnClickListener{
            val context = it.context
            val intent = Intent(context, MultipleFeelingPage::class.java)
            it.context.startActivity(intent)
            finish()
        }

    }
}