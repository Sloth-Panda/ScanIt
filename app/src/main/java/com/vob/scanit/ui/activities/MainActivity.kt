package com.vob.scanit.ui.activities

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import com.rbddevs.splashy.Splashy
import com.vob.scanit.R
import com.vob.scanit.ui.fragments.HomeFragment
import com.vob.scanit.ui.fragments.OCRFragment
import com.vob.scanit.ui.fragments.QRFragment

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 7
    private var imageView: ImageView? = null
    private lateinit var bottomNavigationView: BottomNavigationView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setSplashScreen()

        FirebaseApp.initializeApp(applicationContext)

        bottomNavigationView = findViewById(R.id.bottom_nav_view)
        loadFragment(HomeFragment())
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.ocr -> {
                    loadFragment(OCRFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.qr -> {
                    loadFragment(QRFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else ->
                {
                    loadFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }

        setupToolbar()
    }

    private fun setSplashScreen() {
        Splashy(this)
                .setLogo(R.mipmap.scanit_icon_round)
                .setTitle("ScanIt")
                .setTitleColor("#FFFFFF")
                .setSubTitle("Your All-In-One Scanner")
                .setSubTitleColor("#FFFFFF")
                .setProgressColor(R.color.white)
                .setBackgroundColor(R.color.colorPrimaryDark)
                .setFullScreen(true)
                .setDuration(5000)
                .show()
    }

    private fun setupToolbar() {
        var toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        toolbar.setTitle("ScanIt")
        toolbar.setTitleTextAppearance(applicationContext, R.style.TextAppearance_AppCompat_Title)
        toolbar.setTitleTextColor(-0x1)

        setSupportActionBar(toolbar)


    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}