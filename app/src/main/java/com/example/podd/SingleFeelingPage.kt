package com.example.podd

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.preference.PreferenceManager

class SingleFeelingPage : AppCompatActivity(){
    private val colors = arrayOf(
        //default
                                mapOf("Happy" to Color.parseColor("#FFFF00"), "Sad" to Color.parseColor("#1e90ff"),
                                    "Angry" to Color.parseColor("#FF0000"), "Neutral" to Color.parseColor("#e0e0e0"),
                                    "Disgusted" to Color.parseColor("#00a86b"), "Afraid" to Color.parseColor("#444444"),
                                    "Surprised" to Color.parseColor("#FF712D"), "In Pain" to Color.parseColor("#765329")),
        //faded
                                mapOf("Happy" to Color.parseColor("#FFFFBF"), "Sad" to Color.parseColor("#A9D1F7"),
                                    "Angry" to Color.parseColor("#FFB1B0"), "Neutral" to Color.parseColor("#e0e0e0"),
                                    "Disgusted" to Color.parseColor("#B4F0A7"), "Afraid" to Color.parseColor("#555555"),
                                    "Surprised" to Color.parseColor("#FFDFBE"), "In Pain" to Color.parseColor("#BD9A76")))

    private val emojis = mapOf("Happy" to R.drawable.happy, "Sad" to R.drawable.sad,
        "Angry" to R.drawable.angry, "Neutral" to R.drawable.neutral,
        "Disgusted" to R.drawable.disgusted, "Afraid" to R.drawable.afraid,
        "Surprised" to R.drawable.surprised, "In Pain" to R.drawable.inpain)

    private var colorMode = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.single)
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        colorMode = prefs.getString("color", "0")?.let { Integer.parseInt(it) }!!
        val extras = intent.extras
        var emoji = findViewById<ImageView>(R.id.emoji)


        var feelingName = findViewById<TextView>(R.id.feelingName)
        var prev = findViewById<Button>(R.id.prev)
        prev.setCompoundDrawablesWithIntrinsicBounds(getDrawable(R.drawable.leftarrow), null, null, null)
        prev.setOnClickListener {
            finish()
        }
        var audio = findViewById<Button>(R.id.audio)
        var text = findViewById<Button>(R.id.text)
        audio.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.drawable.notesmall), null, null)
        text.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.drawable.speaksmall), null, null)


        if(extras != null){
            val feeling = extras.getString("feeling")
            emoji.setOnClickListener {
                val context = it.context
                val intent = Intent(context, EmojiChooser::class.java)
                intent.putExtra("feeling", feeling)
                it.context.startActivity(intent)
            }
            feelingName.text = feeling
            var background = findViewById<ConstraintLayout>(R.id.single)
            val color = colors[colorMode][feeling] as Int
            background.setBackgroundColor(color)
            emoji.setImageResource(emojis[feeling] as Int)
            //TO-DO: set up audio samples for two buttons
            //Dynamic content description of emoji
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