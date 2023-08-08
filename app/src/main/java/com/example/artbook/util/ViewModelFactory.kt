package com.example.artbook.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            else -> {
                throw java.lang.IllegalArgumentException("Unknown ViewModel")
            }
        }
    }
}
