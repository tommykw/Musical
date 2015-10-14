package tokyo.tommy_kw.kotlinsample.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.view.View
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import butterknife.bindView
import com.firebase.client.Firebase
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import tokyo.tommy_kw.kotlinsample.Constant
import tokyo.tommy_kw.kotlinsample.R
import tokyo.tommy_kw.kotlinsample.api.ApiClient
import tokyo.tommy_kw.kotlinsample.entity.Weather

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val mToolbar: Toolbar by bindView(R.id.toolbar)
    val mFab: FloatingActionButton by bindView(R.id.fab)
    val mDrawer: DrawerLayout by bindView(R.id.drawer_layout)
    val mNavigationView: NavigationView by bindView(R.id.nav_view)
    val mLat: TextView by bindView(R.id.lat)
    val mLon: TextView by bindView(R.id.lon)
    val mBase: TextView by bindView(R.id.base)
    val mName: TextView by bindView(R.id.name)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(mToolbar)
        mFab.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                Toast.makeText(this@MainActivity, "Replace with your own action", Toast.LENGTH_SHORT).show()
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            }
        })

        val toggle = ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mDrawer.setDrawerListener(toggle)
        toggle.syncState()
        mNavigationView.setNavigationItemSelectedListener(this)

        writeToFirebase()
        fetchWeather()
    }

    override fun onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    @SuppressWarnings("StatementWithEmptyBody")
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId
        val intent: Intent

        if (id == R.id.nav_camara) {
            intent = SecondActivity.makeIntent(this@MainActivity, Constant.NAV_CAMERA)
            startActivity(intent)
        } else if (id == R.id.nav_gallery) {
            intent = SecondActivity.makeIntent(this@MainActivity, Constant.NAV_GALLERY)
            startActivity(intent)
        } else if (id == R.id.nav_slideshow) {
            intent = SecondActivity.makeIntent(this@MainActivity, Constant.NAV_SLIDESHOW)
            startActivity(intent)
        } else if (id == R.id.nav_manage) {
            intent = SecondActivity.makeIntent(this@MainActivity, Constant.NAV_MANAGE)
            startActivity(intent)
        } else if (id == R.id.nav_share) {
            intent = SecondActivity.makeIntent(this@MainActivity, Constant.NAV_SHARE)
            startActivity(intent)
        } else if (id == R.id.nav_send) {
            intent = SecondActivity.makeIntent(this@MainActivity, Constant.NAV_SEND)
            startActivity(intent)
        }

        mDrawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun fetchWeather() {
        ApiClient.getWeather()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: Observer<Weather> {
                    override fun onCompleted() {
                        Toast.makeText(this@MainActivity, "onCompleted", Toast.LENGTH_SHORT).show()
                    }
                    override fun onError(e: Throwable?) {
                        e?.printStackTrace()
                        Toast.makeText(this@MainActivity, "onError", Toast.LENGTH_SHORT).show()
                    }
                    override fun onNext(t: Weather?) {
                        Toast.makeText(this@MainActivity, "onNext", Toast.LENGTH_SHORT).show()
                        t?.let {
                            mBase.setText(it.base)
                            mName.setText(it.name)
                            mLon.setText(it.coord.lon.toString())
                            mLat.setText(it.coord.lat.toString())
                        }
                    }
                })
    }

    private fun writeToFirebase() {
        val firebase = Firebase(Constant.FIREBASE_SAMPLE_URL);
        firebase.child("message").setValue("hoge");
    }
}
