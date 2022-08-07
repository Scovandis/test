package com.dxy.test.feature.edit

import androidx.lifecycle.ViewModel
import com.dxy.test.data.MapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditViewModels @Inject constructor(private val repository: MapRepository): ViewModel(){

}