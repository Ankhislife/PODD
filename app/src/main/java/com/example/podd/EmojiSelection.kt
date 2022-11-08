package com.example.podd

import android.content.Intent
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.LightingColorFilter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager


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
        }
        return super.onOptionsItemSelected(item)
    }
}