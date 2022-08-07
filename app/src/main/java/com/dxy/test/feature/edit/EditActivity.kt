package com.dxy.test.feature.edit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dxy.test.databinding.ActivityEditBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditActivity : AppCompatActivity() {
  lateinit var binding: ActivityEditBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityEditBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }
}