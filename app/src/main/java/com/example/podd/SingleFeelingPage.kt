package com.example.podd

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager

class SingleFeelingPage : AppCompatActivity(){
    //0 is default, 1 is faded
    private var colorMode = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.single)
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        colorMode = prefs.getString("color", "0")?.let { Integer.parseInt(it) }!!
        val extras = intent.extras
        val emoji = findViewById<ImageView>(R.id.emoji)


        val feelingName = findViewById<TextView>(R.id.feelingName)
        val prev = findViewById<Button>(R.id.prev)
        prev.setCompoundDrawablesWithIntrinsicBounds(getDrawable(R.drawable.leftarrow), null, null, null)
        prev.setOnClickListener {
            finish()
        }
        val audio = findViewById<Button>(R.id.audio)
        val text = findViewById<Button>(R.id.text)
        audio.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.drawable.notesmall), null, null)
        text.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.drawable.speaksmall), null, null)

        if(extras != null){
            val feeling = extras.getString("feeling")
            emoji.setOnClickListener {
                val context = it.context
                val intent = Intent(context, EmojiSelection::class.java)
                intent.putExtra("feeling", feeling)
                getResult.launch(intent)
            }
            feelingName.text = feeling
            val background = findViewById<ConstraintLayout>(R.id.single)
            val colorIdentifier = if(colorMode == 0) feeling?.lowercase()?.filter {!it.isWhitespace()} else feeling?.lowercase()?.filter {!it.isWhitespace()  } + "_faded"
            val color = ContextCompat.getColor(this, resources.getIdentifier(colorIdentifier, "color", packageName))
            background.setBackgroundColor(color)
            val emojiResource = resources.getIdentifier(prefs.getString(feeling, feeling)!!.lowercase().filter {!it.isWhitespace()}, "drawable", packageName)
            emoji.setImageResource(emojiResource)
            //TO-DO: set up audio samples for two buttons
            //Dynamic content description of emoji
        }

    }

    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK){
            redraw()
        }
    }

    private fun redraw() {
        val extras = intent.extras
        val feeling = extras?.getString("feeling")
        val emoji = findViewById<ImageView>(R.id.emoji)
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val emojiResource = resources.getIdentifier(prefs.getString(feeling, feeling)!!.lowercase().filter {!it.isWhitespace()}, "drawable", packageName)
        emoji.setImageResource(emojiResource)
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