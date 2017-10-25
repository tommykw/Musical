package com.github.tommykw.musical.activity

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.firebase.client.Firebase
import kotterknife.bindView
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import com.github.tommykw.musical.R
import com.github.tommykw.musical.api.ApiClient
import com.github.tommykw.musical.application.Router
import com.github.tommykw.musical.constant.Constants
import com.github.tommykw.musical.entity.Weather

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            }
        })

        val toggle = ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mDrawer.setDrawerListener(toggle)
        toggle.syncState()
        mNavigationView.setNavigationItemSelectedListener(this)

        writeToFirebase()
        //fetchWeather()
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_camara -> Router.routeToCamera(this)
            R.id.nav_gallery -> Router.routeToGallery(this)
            R.id.nav_slideshow -> Router.routeToSlideShow(this)
            R.id.nav_manage -> Router.routeToManage(this)
            R.id.nav_share -> Router.routeToShare(this)
            R.id.nav_send -> Router.routeToSend(this)
        }

        mDrawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun fetchWeather() {
        ApiClient.getWeather()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Weather> {
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
                            mBase.text = it.base
                            mName.text = it.name
                            mLon.text = it.coord.lon.toString()
                            mLat.text = it.coord.lat.toString()
                        }
                    }
                })
    }

    private fun writeToFirebase() {
        val firebase = Firebase(Constants.FIREBASE_SAMPLE_URL);
        firebase.child("message").setValue("hoge");
    }
}
