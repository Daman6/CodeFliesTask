package com.example.codefliestask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.codefliestask.databinding.ActivityMainBinding
import com.example.codefliestask.repo.TherapyRepo
import com.example.codefliestask.utils.Resource
import com.example.codefliestask.viewModel.MainViewModel
import com.example.codefliestask.viewModel.TherapiesViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    lateinit var viewmodel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        val repo = TherapyRepo()
        val viewModelProviderFactory = TherapiesViewModelProviderFactory(repo)

        viewmodel  = ViewModelProvider(this,viewModelProviderFactory).get(MainViewModel::class.java)
        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.loginFragment || destination.id ==  R.id.signUpFragment) {

                binding.bottomNavigationView.visibility = View.GONE
            } else {

                binding.bottomNavigationView.visibility = View.VISIBLE
            }
        }

//        viewmodel.pushPost("dsd","wdw","djnd@gmail.com","fffdddddd",7392739237)
//        viewmodel.myPushUser.observe(this, Observer {
//            when (it) {
//                is Resource.Loading -> {
//
//                }
//                is Resource.Success -> {
//                    if (it.data?.message == "You'r register successfully!") {
//                        Toast.makeText(
//                            this,
//                            "Registation Success !! Please Login",
//                            Toast.LENGTH_LONG
//                        ).show()
//
//                    } else {
//                        Toast.makeText(this, "" + it.data?.message, Toast.LENGTH_LONG).show()
//                    }
//                }
//
//                is Resource.Error -> {
//                    Toast.makeText(this, "" + it.data!!.message, Toast.LENGTH_LONG).show()
//                }
//            }
//        })
    }
}