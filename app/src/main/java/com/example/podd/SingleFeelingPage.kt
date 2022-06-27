package com.example.podd

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class SingleFeelingPage : AppCompatActivity(){
    private val colors = mapOf("Happy" to Color.parseColor("#FFFF00"), "Sad" to Color.parseColor("#1e90ff"),
                            "Angry" to Color.parseColor("#FF0000"), "Neutral" to Color.parseColor("#e0e0e0"),
                            "Disgusted" to Color.parseColor("#00a86b"), "Afraid" to Color.parseColor("#444444"),
                            "Surprised" to Color.parseColor("#FF712D"), "In Pain" to Color.parseColor("#765329"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.single)

        val extras = intent.extras
        var emoji = findViewById<ImageView>(R.id.emoji)
        var feelingName = findViewById<TextView>(R.id.feelingName)
        var prev = findViewById<Button>(R.id.prev)
        prev.setOnClickListener {
            finish()
        }
        var audio = findViewById<Button>(R.id.audio)
        var text = findViewById<Button>(R.id.text)


        if(extras != null){
            val feeling = extras.getString("feeling")
            feelingName.text = feeling
            var background = findViewById<ConstraintLayout>(R.id.background)
            background.setBackgroundColor(colors[feeling] as Int)
            //TO-DO: set up audio samples for two buttons
            //Dynamic content description of emoji
        }

    }
}