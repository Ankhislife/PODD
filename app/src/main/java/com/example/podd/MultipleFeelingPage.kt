package com.example.podd

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.preference.PreferenceManager


class MultipleFeelingPage : AppCompatActivity() {
    private val feelings: Array<String> = arrayOf(
        "Happy", "Sad", "Angry", "Neutral", "Disgusted", "Afraid", "Surprised", "In Pain")

    private var pageIndex = 0

    //0 is default, 1 is faded
    private var colorMode = 0
    private var buttons: Array<Button> = arrayOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.multiple)
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        colorMode = prefs.getString("color", "0")?.let { Integer.parseInt(it) }!!
        val prev = findViewById<Button>(R.id.prev)
        val next = findViewById<Button>(R.id.next)
        prev.setCompoundDrawablesWithIntrinsicBounds(getDrawable(R.drawable.leftarrow), null, null, null)
        next.setCompoundDrawablesWithIntrinsicBounds(null, null, getDrawable(R.drawable.rightarrow), null)
        val tl = findViewById<Button>(R.id.topLeft)
        val tr = findViewById<Button>(R.id.topRight)
        val bl = findViewById<Button>(R.id.botLeft)
        val br = findViewById<Button>(R.id.botRight)
        buttons = buttons.plus(tl)
        buttons = buttons.plus(tr)
        buttons = buttons.plus(bl)
        buttons = buttons.plus(br)
        redraw()

        val butListen = ButtonClickListener()
        tl.setOnClickListener(butListen)
        tr.setOnClickListener(butListen)
        bl.setOnClickListener(butListen)
        br.setOnClickListener(butListen)
        prev.setOnClickListener{
            pageIndex--
            if(pageIndex < 0){
                pageIndex = (feelings.size/4)-1
            }
            redraw()
        }
        next.setOnClickListener{
            pageIndex++
            if(pageIndex >= (feelings.size/4)){
                pageIndex = 0
            }
            redraw()
        }

    }

    private fun redraw(){
        for(i in buttons.indices){
            buttons[i].text = feelings[i + pageIndex * 4]
            buttons[i].tag = feelings[i + pageIndex * 4]
            val colorIdentifier = if(colorMode == 0) feelings[i + pageIndex * 4].lowercase().filter {!it.isWhitespace()} else feelings[i + pageIndex * 4].lowercase().filter {!it.isWhitespace()  } + "_faded"
            val color = ContextCompat.getColor(this, resources.getIdentifier(colorIdentifier, "color", packageName))
            buttons[i].setBackgroundColor(color)
            val prefs = PreferenceManager.getDefaultSharedPreferences(this)
            val emojiResource = resources.getIdentifier(prefs.getString(buttons[i].tag.toString(), buttons[i].tag.toString())?.lowercase()?.filter {!it.isWhitespace()}, "drawable", packageName)
            val draw = ResourcesCompat.getDrawable(resources, emojiResource, null)
            draw?.setBounds(0, 0, 200, 200)
            buttons[i].setCompoundDrawables(null, draw, null, null)
        }
    }

    class ButtonClickListener : OnClickListener {
        override fun onClick(v: View) {
            val context = v.context
            val intent = Intent(context, SingleFeelingPage::class.java)
            intent.putExtra("feeling", v.tag.toString())
            v.context.startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        redraw()
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

