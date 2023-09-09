package com.example.artbook.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.artbook.R
import com.example.artbook.base.BaseActivity
import com.example.artbook.databinding.ActivityHomeBinding
import com.example.artbook.util.FragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: FragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        // supportFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)

        supportFragmentManager.fragmentFactory = fragmentFactory
        setContentView(R.layout.activity_home)
    }
}
