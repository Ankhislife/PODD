package com.example.podd

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

        //launches settings activity
        settings.setOnClickListener {
            val context = it.context
            val intent = Intent(context, SettingsActivity::class.java)
            it.context.startActivity(intent)
        }

        //launches multiple feelings activity
        start.setOnClickListener{
            val context = it.context
            val intent = Intent(context, MultipleFeelingPage::class.java)
            it.context.startActivity(intent)
            finish()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.multiplemenu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                val intent = Intent(this, MainMenu::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
            }
            R.id.gearIcon -> {
                val intent = Intent(this, SettingsActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
            }
            R.id.mfp -> {
                val intent = Intent(this, MultipleFeelingPage::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}