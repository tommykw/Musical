package com.github.tommykw.musical.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.firebase.client.DataSnapshot
import com.firebase.client.Firebase
import com.firebase.client.FirebaseError
import com.firebase.client.ValueEventListener
import com.github.tommykw.musical.R
import com.github.tommykw.musical.constant.Constants

/**
 * Created by tommy on 15/10/06.
 */
class SecondActivity : AppCompatActivity() {
    companion object {
        const val INTENT_ARG_MESSAGE = "INTENT_ARG_MESSAGE"
        fun makeIntent(context: Context, message: String): Intent {
            return Intent(context, SecondActivity::class.java)
                    .putExtra(INTENT_ARG_MESSAGE, message)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        if (intent.getStringExtra("INTENT_ARG_MESSAGE") != null) {
            Toast.makeText(this@SecondActivity, intent.getStringExtra("INTENT_ARG_MESSAGE"), Toast.LENGTH_SHORT).show()

            val firebase = Firebase(Constants.FIREBASE_SAMPLE_URL);
            firebase.child("message").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Toast.makeText(this@SecondActivity, snapshot.value.toString(), Toast.LENGTH_SHORT).show()
                }

                override fun onCancelled(error: FirebaseError) {
                }
            });
        }
    }
}
