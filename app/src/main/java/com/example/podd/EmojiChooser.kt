package com.example.podd

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

/*
Name: Daniel Koronthaly
Reach out to me on LinkedIn or at daniel@koronthaly.net for any questions.

Specification: THIS FILE IS NOT CURRENTLY IN USE

EmojiSelection is what is currently used. This is an alternative that shows a dropdown menu to choose the emoji.

This file was deprecated because it doesn't allow the user to actually see which emoji is being selected.

To add different emojis, add the file to the drawables folder, and then in values/arrays.xml put the filename in $feeling_filenames. There must be four emojis exactly.

 */

class EmojiChooser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)
        val extras = intent.extras
        val feeling = extras?.getString("feeling")
        val bundle = Bundle()
        bundle.putString("feeling", feeling)
        val fragment = EmojiFragment()
        fragment.arguments = bundle

        this.supportFragmentManager.beginTransaction().replace(R.id.settings, fragment).commit()


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