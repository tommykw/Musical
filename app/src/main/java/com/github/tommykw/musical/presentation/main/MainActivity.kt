package com.github.tommykw.musical.presentation.main

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import com.firebase.client.Firebase
import com.github.tommykw.musical.R
import com.github.tommykw.musical.api.ApiClient
import com.github.tommykw.musical.application.Router
import com.github.tommykw.musical.constant.Constants
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.github.tommykw.musical.data.entity.Event
import com.github.tommykw.musical.databinding.MainActivityBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            }
        })

        val toggle = ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawerLayout.setDrawerListener(toggle)
        toggle.syncState()

        writeToFirebase()
        //fetchWeather()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
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

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun fetchWeather() {
        ApiClient.getEvent()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Event> {
                    override fun onError(e: Throwable) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onNext(t: Event) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onSubscribe(d: Disposable) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onComplete() {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }
                })
    }

    private fun writeToFirebase() {
        val firebase = Firebase(Constants.FIREBASE_SAMPLE_URL);
        firebase.child("message").setValue("hoge");
    }
}
