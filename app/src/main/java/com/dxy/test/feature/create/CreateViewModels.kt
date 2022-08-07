package com.dxy.test.feature.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dxy.test.data.MapRepository
import com.dxy.test.data.locale.MapModels
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateViewModels @Inject constructor(private val repository: MapRepository): ViewModel(){
  fun addData(data : MapModels)= viewModelScope.launch (Dispatchers.IO){
    repository.addToModels(data)
  }
}