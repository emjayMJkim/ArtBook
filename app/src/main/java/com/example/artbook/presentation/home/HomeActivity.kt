package com.example.artbook.presentation.home

import android.os.Bundle
import com.example.artbook.R
import com.example.artbook.base.BaseActivity
import com.example.artbook.databinding.ActivityHomeBinding
import com.example.artbook.util.ArtFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        // supportFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)

        supportFragmentManager.fragmentFactory = fragmentFactory
    }
}
