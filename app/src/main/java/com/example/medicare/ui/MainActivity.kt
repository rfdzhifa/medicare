package com.example.medicare.ui

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.medicare.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView.menu.getItem(1).isEnabled = false
        bottomNavigationView.background = null
        makeCurrentFragment(HomeFragment())

        supportActionBar?.hide()
        val window: Window = this@MainActivity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.blue_base)

        val homeFragment = HomeFragment()
        val profileFragment = ProfileFragment()

        val bottomnav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        scan_qr.setOnClickListener {
//            val qr_checkin = findViewById<CardView>(R.id.cv_check_in_info)
//            qr_checkin.visibility = View.VISIBLE

            barcodeLauncher.launch(ScanOptions())
        }

        replaceFragment(homeFragment)

        bottomnav.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.Nav_Home -> makeCurrentFragment(homeFragment)
                R.id.Nav_Profile -> makeCurrentFragment(profileFragment)
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

    private val barcodeLauncher   = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents != null) {
            Intent(this, WebViewActivity::class.java).run {
                putExtra("url", result.contents)
                startActivity(this)
            }
        }
    }
}