package tokyo.tommy_kw.kotlinsample

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
import android.widget.Toast
import butterknife.bindView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val toolbar:Toolbar by bindView(R.id.toolbar)
    val fab:FloatingActionButton by bindView(R.id.fab)
    val drawer:DrawerLayout by bindView(R.id.drawer_layout)
    val navigationView:NavigationView by bindView(R.id.nav_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fab.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                Toast.makeText(this@MainActivity, "Replace with your own action", Toast.LENGTH_SHORT).show()
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            }
        })

        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
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

        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
