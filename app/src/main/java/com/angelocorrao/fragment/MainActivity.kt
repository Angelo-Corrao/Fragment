package com.angelocorrao.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.angelocorrao.fragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {

            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.setCustomAnimations(
                com.google.android.material.R.anim.abc_slide_in_top,
                com.google.android.material.R.anim.abc_slide_out_bottom)

            when (it.itemId) {
                R.id.nav_home -> {
                    fragmentTransaction.replace(R.id.fragmentContainer, HomeFragment())
                }
                R.id.nav_favourites-> {
                    fragmentTransaction.replace(R.id.fragmentContainer, FavouritesFragment())
                }
                R.id.nav_search-> {
                    fragmentTransaction.replace(R.id.fragmentContainer, SearchFragment())
                }
            }

            fragmentTransaction.setReorderingAllowed(true) // Optimizes the state changes of the fragments involved in the transaction so that animations and transitions work correctly
            //fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

            return@setOnItemSelectedListener true
        }
    }

    override fun onBackPressed() {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        if(R.id.nav_home == binding.bottomNavigationView.selectedItemId)
            super.onBackPressed()
        else{
            binding.bottomNavigationView.selectedItemId = R.id.nav_home
            fragmentTransaction.replace(R.id.fragmentContainer, HomeFragment())
            fragmentTransaction.commit()
        }
    }
}