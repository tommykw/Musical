package tokyo.tommy_kw.kotlinsample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by tommy on 15/10/06.
 */
class SecondActivity : AppCompatActivity() {
    companion object {
        val INTENT_ARG_MESSAGE = "INTENT_ARG_MESSAGE"
        fun makeIntent (context: Context, message: String):Intent {
            return Intent(context, SecondActivity::class.java)
                    .putExtra(INTENT_ARG_MESSAGE, message)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Toast.makeText(this@SecondActivity, getIntent().getStringExtra("INTENT_ARG_MESSAGE"), Toast.LENGTH_SHORT).show()
    }
}
