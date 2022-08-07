package com.dxy.test.feature.create

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import com.dxy.test.R
import com.dxy.test.data.locale.MapModels
import com.dxy.test.databinding.ActivityCreateBinding
import com.dxy.test.feature.home.MainActivity
import com.google.android.material.switchmaterial.SwitchMaterial
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateActivity : AppCompatActivity() {
  lateinit var binding : ActivityCreateBinding

  private val viewModels by viewModels<CreateViewModels>()

  var name: String = ""
  var address: String = ""
  var city: String = ""
  var zip_code: String = ""
  var checkVisible: Boolean = true
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityCreateBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setAction()
  }
  private fun setAction(){

    binding.etNama.editText!!.addTextChangedListener(object: TextWatcher {
      override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
      }

      override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
      }

      override fun afterTextChanged(p0: Editable?) {
        if(p0!!.length >= 9){
          binding.etNama.helperText = getString(R.string.jumlah_tidak_boleh_lebih_dari_9)
          p0.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(9))
        }else if (p0.startsWith("-")){
          binding.etNama.error = getString(R.string.nominal_tidak_boleh_diawali_nilai_negative)
          p0.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(0))
        }else if(p0.startsWith("0")){
          binding.etNama.helperText = getString(R.string.tidak_boleh_diawali_dengan_0)
          p0.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(1))
        }else if(p0.startsWith(" ")){
          binding.etNama.error = getString(R.string.tidak_boleh_diawali_dengan_nilai_kosong)
          p0.filters = arrayOf(InputFilter.LengthFilter(0))
        }else if(p0.startsWith(".")){
          binding.etNama.error = getString(R.string.masukkan_nilai_dengan_benar)
          p0.filters = arrayOf(InputFilter.LengthFilter(0))
        }else if(p0.startsWith(",")){
          binding.etNama.error = getString(R.string.masukkan_nilai_dengan_benar)
          p0.filters = arrayOf(InputFilter.LengthFilter(0))
        } else {
          p0.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(9))
          binding.etNama.helperText = ""
        }
      }
    })

    binding.etAddress.editText!!.addTextChangedListener(object: TextWatcher {
      override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
      }

      override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
      }

      override fun afterTextChanged(p0: Editable?) {
        if(p0!!.length >= 9){
          binding.etAddress.helperText = getString(R.string.jumlah_tidak_boleh_lebih_dari_9)
          p0.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(9))
        }else if (p0.startsWith("-")){
          binding.etAddress.error = getString(R.string.nominal_tidak_boleh_diawali_nilai_negative)
          p0.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(0))
        }else if(p0.startsWith("0")){
          binding.etAddress.helperText = getString(R.string.tidak_boleh_diawali_dengan_0)
          p0.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(1))
        }else if(p0.startsWith(" ")){
          binding.etAddress.error = getString(R.string.tidak_boleh_diawali_dengan_nilai_kosong)
          p0.filters = arrayOf(InputFilter.LengthFilter(0))
        }else if(p0.startsWith(".")){
          binding.etAddress.error = getString(R.string.masukkan_nilai_dengan_benar)
          p0.filters = arrayOf(InputFilter.LengthFilter(0))
        }else if(p0.startsWith(",")){
          binding.etAddress.error = getString(R.string.masukkan_nilai_dengan_benar)
          p0.filters = arrayOf(InputFilter.LengthFilter(0))
        } else {
          p0.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(9))
          binding.etAddress.helperText = ""
        }
      }
    })
    binding.etCity.editText!!.addTextChangedListener(object: TextWatcher {
      override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
      }

      override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
      }

      override fun afterTextChanged(p0: Editable?) {
        if(p0!!.length >= 9){
          binding.etCity.helperText = getString(R.string.jumlah_tidak_boleh_lebih_dari_9)
          p0.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(9))
        }else if (p0.startsWith("-")){
          binding.etCity.error = getString(R.string.nominal_tidak_boleh_diawali_nilai_negative)
          p0.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(0))
        }else if(p0.startsWith("0")){
          binding.etCity.helperText = getString(R.string.tidak_boleh_diawali_dengan_0)
          p0.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(1))
        }else if(p0.startsWith(" ")){
          binding.etCity.error = getString(R.string.tidak_boleh_diawali_dengan_nilai_kosong)
          p0.filters = arrayOf(InputFilter.LengthFilter(0))
        }else if(p0.startsWith(".")){
          binding.etCity.error = getString(R.string.masukkan_nilai_dengan_benar)
          p0.filters = arrayOf(InputFilter.LengthFilter(0))
        }else if(p0.startsWith(",")){
          binding.etCity.error = getString(R.string.masukkan_nilai_dengan_benar)
          p0.filters = arrayOf(InputFilter.LengthFilter(0))
        } else {
          p0.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(9))
          binding.etCity.helperText = ""
        }
      }
    })
    binding.etZipCode.editText!!.addTextChangedListener(object: TextWatcher {
      override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
      }

      override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
      }

      override fun afterTextChanged(p0: Editable?) {
        if(p0!!.length >= 9){
          binding.etZipCode.helperText = getString(R.string.jumlah_tidak_boleh_lebih_dari_9)
          p0.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(9))
        }else if (p0.startsWith("-")){
          binding.etZipCode.error = getString(R.string.nominal_tidak_boleh_diawali_nilai_negative)
          p0.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(0))
        }else if(p0.startsWith("0")){
          binding.etZipCode.helperText = getString(R.string.tidak_boleh_diawali_dengan_0)
          p0.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(1))
        }else if(p0.startsWith(" ")){
          binding.etZipCode.error = getString(R.string.tidak_boleh_diawali_dengan_nilai_kosong)
          p0.filters = arrayOf(InputFilter.LengthFilter(0))
        }else if(p0.startsWith(".")){
          binding.etZipCode.error = getString(R.string.masukkan_nilai_dengan_benar)
          p0.filters = arrayOf(InputFilter.LengthFilter(0))
        }else if(p0.startsWith(",")){
          binding.etZipCode.error = getString(R.string.masukkan_nilai_dengan_benar)
          p0.filters = arrayOf(InputFilter.LengthFilter(0))
        } else {
          p0.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(9))
          binding.etZipCode.helperText = ""
        }
      }
    })
    binding.toggle.setOnCheckedChangeListener { group, checkedId ->
      val radio: RadioButton = findViewById(checkedId)
      when (radio.id) {
        R.id.rb_active -> {
          checkVisible = true
        }
        R.id.rb_inactive -> {
          checkVisible = false
        }
      }
    }
    binding.btnSimpan.setOnClickListener {
      saveData()
    }
  }
  private fun saveData()
  {
    name = binding.etNama.editText!!.text.toString()
    address = binding.etAddress.editText!!.text.toString()
    city = binding.etCity.editText!!.text.toString()
    zip_code = binding.etZipCode.editText!!.text.toString()
    var active = checkVisible

    binding.progress.visibility = View.VISIBLE
    if (name.length <= 2){
      binding.etNama.error = getString(R.string.nama_tidak_boleh_kurang_dari_2_huruf)
    }else {
      val mapData = MapModels(0, name, address, city, zip_code, active)
      viewModels.addData(mapData)
      Handler(Looper.getMainLooper()).postDelayed(
        {
          Toast.makeText(this, "data berhasil di simpan", Toast.LENGTH_SHORT).show()
          binding.progress.visibility = View.GONE
          startActivity(Intent(this, MainActivity::class.java))
        }, 1000)
    }

  }
}