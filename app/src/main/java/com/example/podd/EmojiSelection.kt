package com.example.podd

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager

/*
Name: Daniel Koronthaly
Reach out to me on LinkedIn or at daniel@koronthaly.net for any questions.

Specification: This file is to choose an emoji as per emojiselect.xml

To add different emojis, add the file to the drawables folder, and then in values/arrays.xml put the filename in $feeling_filenames. There must be four emojis exactly.
 */
class EmojiSelection : AppCompatActivity() {
    private var emojis: Array<ImageView> = arrayOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.emojiselect)

        val extras = intent.extras
        val feeling = extras?.getString("feeling")

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = prefs.edit()

        val prev = findViewById<Button>(R.id.prev)
        prev.setCompoundDrawablesWithIntrinsicBounds(getDrawable(R.drawable.leftarrow), null, null, null)
        prev.setOnClickListener {
            finish()
        }

        val tl = findViewById<ImageView>(R.id.tl)
        val tr = findViewById<ImageView>(R.id.tr)
        val bl = findViewById<ImageView>(R.id.bl)
        val br = findViewById<ImageView>(R.id.br)



        emojis = emojis.plus(tl)
        emojis = emojis.plus(tr)
        emojis = emojis.plus(bl)
        emojis = emojis.plus(br)

        val arrayid = this.resources.getIdentifier(feeling?.lowercase()?.filter {!it.isWhitespace()} + "_filenames", "array", this.packageName)
        val emojifilenames = resources.getStringArray(arrayid)

        //The current emoji being used gets a green border
        val highlightgreen = getDrawable(R.drawable.highlightgreen)
        val currentEmojiFile = resources.getIdentifier(prefs.getString(feeling, feeling)!!.lowercase().filter {!it.isWhitespace()}, "drawable", packageName)

        var currentEmoji: ImageView? = null

        for(i in emojis.indices){
            val emojiResource = resources.getIdentifier(emojifilenames[i], "drawable", packageName)
            emojis[i].setImageResource(emojiResource)
            emojis[i].tag = emojifilenames[i]
            if(currentEmojiFile == emojiResource){
                currentEmoji = emojis[i]
                currentEmoji.background = highlightgreen
            }
        }
        val selectButton = findViewById<Button>(R.id.button)
        var selected: ImageView? = null
        selectButton.background.alpha = 50


        //If you click on an emoji that isn't the one currently used, it gets a yellow background
        //Click it again to deselect
        //Click the "Select" button to make it the currently chosen emoji
        val listener = OnClickListener {
            val highlight = getDrawable(R.drawable.highlight)
            for (i in emojis.indices) {
                if (emojis[i].background != null && emojis[i] != currentEmoji) {
                    emojis[i].background = null
                }
            }
            if(selected == it && it != currentEmoji){
                it.background = null
                selected = null
                selectButton.background.alpha = 50
            }
            else if(it != currentEmoji){
                it.background = highlight
                selected = it as ImageView
                selectButton.background.alpha = 255
            }
            else {
                selected = null
                selectButton.background.alpha = 50
            }
        }

        tl.setOnClickListener(listener)
        tr.setOnClickListener(listener)
        bl.setOnClickListener(listener)
        br.setOnClickListener(listener)


        selectButton.setOnClickListener {
            if(selected != null){
                editor.putString(feeling, selected!!.tag as String?).apply()
                currentEmoji = selected
                for (i in emojis.indices) {
                    if (emojis[i].background != null && emojis[i] != currentEmoji) {
                        emojis[i].background = null
                    }
                }
                currentEmoji?.background = highlightgreen
                selectButton.background.alpha = 0
                setResult(RESULT_OK)
            }
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