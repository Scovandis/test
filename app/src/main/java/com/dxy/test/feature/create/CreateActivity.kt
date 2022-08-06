package com.dxy.test.feature.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dxy.test.databinding.ActivityCreateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateActivity : AppCompatActivity() {
  lateinit var binding : ActivityCreateBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityCreateBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }
}