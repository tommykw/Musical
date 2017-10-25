package com.github.tommykw.musical.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

/**
 * Created by tommy on 2016/07/20.
 */
class AnkoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            textView {
                text = "Hello, Anko!!!"
            }
        }
    }

    companion object {
        fun makeIntent(context: Context) = Intent(context, AnkoActivity::class.java)
    }
}