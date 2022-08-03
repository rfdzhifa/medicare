package com.example.medicare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(2).isEnabled = false
        replaceFragment(HomeFragment())

        supportActionBar?.hide()
        val window: Window = this@MainActivity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.blue_base)

        val homeFragment = HomeFragment()

        val bottomnav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        scan_qr.setOnClickListener {
            val qr_checkin = findViewById<CardView>(R.id.cv_check_in_info)
            qr_checkin.visibility = View.VISIBLE
        }



        makeCurrentFragment(homeFragment)

        bottomnav.setOnNavigationItemReselectedListener {
            when(it.itemId) {
                R.id.Nav_Home -> makeCurrentFragment(homeFragment)
            }
            true
        }

    }

    private fun replaceFragment(homeFragment: HomeFragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, homeFragment)
        fragmentTransaction.commit()
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            commit()
        }
    }
}