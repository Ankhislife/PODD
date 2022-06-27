package com.example.podd

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MultipleFeelingPage : AppCompatActivity() {
    private val feelings: Array<Array<String>> = arrayOf(
        arrayOf("Happy", "Sad", "Angry", "Neutral"),
        arrayOf("Disgusted", "Afraid", "Surprised", "In Pain"))

    private val colors: Array<Array<Int>> = arrayOf(
        arrayOf(Color.parseColor("#FFFF00"), Color.parseColor("#1e90ff"), Color.parseColor("#FF0000"), Color.parseColor("#e0e0e0")),
        arrayOf(Color.parseColor("#00a86b"), Color.parseColor("#444444"), Color.parseColor("#FF712D"), Color.parseColor("#765329")))

    private var pageIndex = 0

    private var buttons: Array<Button> = arrayOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.multiple)


        val prev = findViewById<Button>(R.id.prev)
        val next = findViewById<Button>(R.id.next)
        val tl = findViewById<Button>(R.id.topLeft)
        val tr = findViewById<Button>(R.id.topRight)
        val bl = findViewById<Button>(R.id.botLeft)
        val br = findViewById<Button>(R.id.botRight)
        buttons = buttons.plus(tl)
        buttons = buttons.plus(tr)
        buttons = buttons.plus(bl)
        buttons = buttons.plus(br)

        val butListen = ButtonClickListener()
        tl.setOnClickListener(butListen)
        tr.setOnClickListener(butListen)
        bl.setOnClickListener(butListen)
        br.setOnClickListener(butListen)
        prev.setOnClickListener{
            pageIndex--
            if(pageIndex < 0){
                pageIndex = feelings.size-1
            }
            redraw()
        }
        next.setOnClickListener{
            pageIndex++
            if(pageIndex >= feelings.size){
                pageIndex = 0
            }
            redraw()
        }

    }

    private fun redraw(){
        for(i in buttons.indices){
            buttons[i].text = feelings[pageIndex][i]
            buttons[i].tag = feelings[pageIndex][i]
            buttons[i].setBackgroundColor(colors[pageIndex][i])
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
}

