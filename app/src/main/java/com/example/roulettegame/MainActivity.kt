package com.example.roulettegame

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.util.Random

class MainActivity : AppCompatActivity() {
    private lateinit var result: TextView
    private lateinit var roulette: ImageView
    private lateinit var random: Random

    private var oldDegree: Int = 0
    private var degree: Int = 0

    private var factor: Float = 4.736842f

    private val numbers = arrayOf("32 BLACK", "15 RED", "19 BLACK", "4 RED", "21 BLACK", "2 RED",
        "25 BLACK", "17 RED", "34 BLACK", "37 RED", "6 BLACK", "27 RED", "13 BLACK", "36 RED",
        "11 BLACK", "30 RED", "8 BLACK", "23 RED", "10 BLACK", "5 RED", "24 BLACK", "16 RED",
        "33 BLACK", "1 RED", "20 BLACK", "14 RED", "31 BLACK", "9 RED", "22 BLACK", "18 RED",
        "29 BLACK", "7 RED", "28 BLACK", "12 RED", "35 BLACK", "3 RED", "26 BLACK", "0 GREEN")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        init()
    }

    fun onClickStart(view: View) {
        oldDegree = degree % 360
        degree = random.nextInt(3600) + 720
        val rotate = RotateAnimation(oldDegree.toFloat(), degree.toFloat(),
            RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f)

        rotate.duration = 3600
        rotate.fillAfter = true
        rotate.interpolator = DecelerateInterpolator()

        rotate.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                result.text = ""
            }

            override fun onAnimationEnd(animation: Animation?) {
                result.text = getResult(360 - (degree % 360))
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

        roulette.startAnimation(rotate)

    }

    private fun init() {
        result = findViewById(R.id.result)
        roulette = findViewById(R.id.roulette)
        random = Random()
    }

    private fun getResult(degree: Int): String {
        var text = ""
        var factorX = 1
        var factorY = 3

        for(i in 0 until 38) {
            if(degree >= (factor * factorX) && degree <= (factor * factorY)) {
                text = numbers[i]
            }
            factorX += 2
            factorY += 2
        }

        if(degree >= (factor * 73) && degree < 360 || degree >= 0 && degree < (factor * 1)) {
            text = numbers[numbers.size - 1]
        }

        return text
    }

}