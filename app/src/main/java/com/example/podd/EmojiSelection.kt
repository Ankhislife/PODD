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

        for(i in emojis.indices){
            val emojiResource = resources.getIdentifier(emojifilenames[i], "drawable", packageName)
            emojis[i].setImageResource(emojiResource)
            emojis[i].tag = emojifilenames[i]
        }

        var selected: ImageView? = null
        val listener = OnClickListener {
            val highlight = getDrawable(R.drawable.highlight)
            for (i in emojis.indices) {
                if (emojis[i].background != null) {
                    emojis[i].background = null
                }
            }
            if(selected == it){
                it.background = null
                selected = null
            }
            else{
                it.background = highlight
                selected = it as ImageView
            }
        }

        tl.setOnClickListener(listener)
        tr.setOnClickListener(listener)
        bl.setOnClickListener(listener)
        br.setOnClickListener(listener)

        val selectButton = findViewById<Button>(R.id.button)

        selectButton.setOnClickListener {
            if(selected != null){
                editor.putString(feeling, selected!!.tag as String?).apply()
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