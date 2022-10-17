package com.example.podd

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity



class MainMenu : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)
        val settings = findViewById<Button>(R.id.settings)
        val start = findViewById<Button>(R.id.start)



        settings.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.drawable.gearsmall), null, null)
        start.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.drawable.wavingsmall), null, null)

        settings.setOnClickListener {
            val context = it.context
            val intent = Intent(context, SettingsActivity::class.java)
            it.context.startActivity(intent)
        }
        start.setOnClickListener{
            val context = it.context
            val intent = Intent(context, MultipleFeelingPage::class.java)
            it.context.startActivity(intent)
            finish()
        }

    }
}