package com.vivek.myapplication.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.vivek.myapplication.R
import com.vivek.myapplication.fragments.*


class Passgen_screen : AppCompatActivity() {

    lateinit var drawerlayout : DrawerLayout
    lateinit var navigation : NavigationView
    lateinit var toggle : ActionBarDrawerToggle
    lateinit var frameLayout: FrameLayout


    @SuppressLint("SetTextI18n", "RestrictedApi", "WrongConstant")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_passgen_screen)
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.frame,
                Generate_fragment()
            )
            .commit()

        //frame layout for the frames to load
        frameLayout = findViewById(R.id.frame)


        //Hamburger icon setting
        drawerlayout = findViewById(R.id.Drawer)
        navigation = findViewById(R.id.navigation)
         toggle= ActionBarDrawerToggle(this,drawerlayout,
             R.string.navigation_open,
             R.string.navigation_close
         )
        drawerlayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        navigation.setCheckedItem(R.id.generate)

        navigation.setNavigationItemSelectedListener {


            when(it.itemId){
                R.id.generate -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frame,
                            Generate_fragment()
                        )
                        .commit()
                    Toast.makeText(applicationContext,"Generate clicked",Toast.LENGTH_SHORT).show()
                }
                R.id.save -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frame,
                            save_fragment()
                        )
                        .commit()
                    Toast.makeText(this,"Save clicked",Toast.LENGTH_SHORT).show()
                }
                R.id.history -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frame,
                            history_fragment()
                        )
                        .commit()
                    Toast.makeText(this,"history clicked",Toast.LENGTH_SHORT).show()
                }
                R.id.encryption ->{
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frame,
                            encrypt_fragment()
                        )
                        .commit()
                    Toast.makeText(this,"encryption clicked",Toast.LENGTH_SHORT).show()
                }
                R.id.decrypt ->{
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frame,
                            decrypt_fragment()
                        )
                        .commit()
                    Toast.makeText(this,"Decryption clicked",Toast.LENGTH_SHORT).show()
                }
                R.id.logout ->{
                    val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)

                    val myEdit = sharedPreferences.edit()
                    myEdit.putBoolean("loggedin", false).commit()
                    Toast.makeText(this,"Logout clicked",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,MainActivity::class.java))
                    finishAffinity()

                }
            }
            drawerlayout.closeDrawers()
            true

        }

    }

    //for hamburgericon clicking
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == android.R.id.home){
            drawerlayout.openDrawer(GravityCompat.START)
        }

        return super.onOptionsItemSelected(item)
    }


}