package com.example.rickandmorty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.ui.characters.CharactersListFragment
import com.example.rickandmorty.ui.episodes.EpisodesListFragment
import com.example.rickandmorty.ui.locations.LocationsListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1000)
        setTheme(R.style.Theme_RickAndMorty2)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(CharactersListFragment())
        binding.navView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_characters -> replaceFragment(CharactersListFragment())
                R.id.navigation_episodes -> replaceFragment(EpisodesListFragment())
                R.id.navigation_locations -> replaceFragment(LocationsListFragment())
                else -> {

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}