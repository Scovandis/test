package com.dxy.test.feature.home


import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.dxy.test.R
import com.dxy.test.databinding.ActivityMainBinding
import com.dxy.test.feature.home.adapter.ViewPagerAdapter
import com.dxy.test.feature.home.fragment.ActiveFragment
import com.dxy.test.feature.home.fragment.AllStatusFragment
import com.dxy.test.feature.home.fragment.InactiveFragment
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  lateinit var binding: ActivityMainBinding
  lateinit var actionDrawerToggle: ActionBarDrawerToggle
  lateinit var viewPagerAdapter: ViewPagerAdapter
  private val viewModels by viewModels<MainViewModels>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
    actionDrawerToggle = ActionBarDrawerToggle(this, binding.myDrawerLayout,
      R.string.nav_open,
      R.string.nav_close
    )
    binding.tabLayout.setupWithViewPager(binding.viewPager)
    setUpTabLayout()

    binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))

    binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
      override fun onTabSelected(tab: TabLayout.Tab) {
        binding.viewPager.currentItem = tab.position
      }
      override fun onTabUnselected(tab: TabLayout.Tab) {

      }
      override fun onTabReselected(tab: TabLayout.Tab) {

      }
    })

    binding.myDrawerLayout.addDrawerListener(actionDrawerToggle)
    actionDrawerToggle.syncState()

    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    supportActionBar!!.title = getString(R.string.company_and_locatio)

  }
  private fun setUpTabLayout(){
    var allStatus = 0
    var inactive = 0
    var active = 0

    viewModels.listLaporan.observe(this){
      repeat(it.size) { allStatus++ }
    }
    viewModels.getDataWhereStatus(true).observe(this){
      repeat(it.size){active++}
    }
    viewModels.getDataWhereStatus(false).observe(this){
      repeat(it.size){inactive++}
    }
    viewPagerAdapter.addFragment(AllStatusFragment(), "All Status(${allStatus})")
    viewPagerAdapter.addFragment(ActiveFragment(), "Active(${allStatus})")
    viewPagerAdapter.addFragment(InactiveFragment(), "Inactive(${allStatus})")
    binding.viewPager.adapter = viewPagerAdapter
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
      if (actionDrawerToggle.onOptionsItemSelected(item)){
        return true
      }else {
        return super.onOptionsItemSelected(item)
      }
  }

}