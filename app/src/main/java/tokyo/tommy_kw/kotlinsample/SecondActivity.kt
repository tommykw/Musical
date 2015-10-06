package tokyo.tommy_kw.kotlinsample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by tommy on 15/10/06.
 */
public class SecondActivity : AppCompatActivity() {
    companion object {
        fun makeIntent (context: Context):Intent {
            val intent = Intent(context, SecondActivity::class.java)
            //val intent = Intent(context, SecondActivity.javaClass)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Toast.makeText(this@SecondActivity, "second activity", Toast.LENGTH_SHORT).show()
    }
}
