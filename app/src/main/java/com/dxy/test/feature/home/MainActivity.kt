package com.dxy.test.feature.home


import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dxy.test.R
import com.dxy.test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {
  private val TAG = MainActivity::class.java.simpleName
  lateinit var binding: ActivityMainBinding
  lateinit var actionDrawerToggle: ActionBarDrawerToggle
//  lateinit var viewPagerAdapter: ViewPagerAdapter
  private val viewModels by viewModels<MainViewModels>()
  private val mAdapter: MainAdapter by lazy{MainAdapter()}

  lateinit var def: ColorStateList


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
//    viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
    actionDrawerToggle = ActionBarDrawerToggle(this, binding.myDrawerLayout,
      R.string.nav_open,
      R.string.nav_close
    )
    onAction()
    def = binding.tab.item2.textColors
//    binding.tabLayout.setupWithViewPager(binding.viewPager)
//    setUpTabLayout()

//    binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))

//    binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//      override fun onTabSelected(tab: TabLayout.Tab) {
//        binding.viewPager.currentItem = tab.position
//      }
//      override fun onTabUnselected(tab: TabLayout.Tab) {
//
//      }
//      override fun onTabReselected(tab: TabLayout.Tab) {
//
//      }
//    })

    binding.myDrawerLayout.addDrawerListener(actionDrawerToggle)
    actionDrawerToggle.syncState()

    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    supportActionBar!!.title = getString(R.string.company_and_locatio)

    binding.tab.item1.text = "All Status"
    binding.tab.item2.text = "Active"
    binding.tab.item3.text = "Inactive"

    initRecyclerView()
    viewModels.getLiveData.observe(this){
      mAdapter.submitData(lifecycle, it)
    }
    viewModels.insertMap()
//    initViewModel()
  }

  private fun initRecyclerView() {
    binding.rvContent.apply {
      layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
      val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
      addItemDecoration(decoration)
      adapter = mAdapter
    }
  }

  private fun initViewModel() {

    lifecycleScope.launchWhenCreated {
      viewModels.getPagingSource().collectLatest {
        Log.d(TAG, "DATAA PAGING ${it}")
        mAdapter.submitData(it)
        binding.tab.item1.text = "All Status (${it})"
      }
    }
    viewModels.insertMap()
  }

  private fun onAction(){
    binding.tab.item1.setOnClickListener(this)
    binding.tab.item2.setOnClickListener(this)
    binding.tab.item3.setOnClickListener(this)
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

//    viewPagerAdapter.addFragment(AllStatusFragment(), "All Status(${allStatus})")
//    viewPagerAdapter.addFragment(ActiveFragment(), "Active(${allStatus})")
//    viewPagerAdapter.addFragment(InactiveFragment(), "Inactive(${allStatus})")
//    binding.viewPager.adapter = viewPagerAdapter
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
      if (actionDrawerToggle.onOptionsItemSelected(item)){
        return true
      }else {
        return super.onOptionsItemSelected(item)
      }
  }

  override fun onClick(view: View?) {
    if (view!!.id == R.id.item1){
      binding.tab.select.animate().x(0F).duration = 100
      binding.tab.item1.setTextColor(Color.parseColor("#FF3700B3"))
      binding.tab.item2.setTextColor(def)
      binding.tab.item3.setTextColor(def)

    }else if(view.id == R.id.item2){
      binding.tab.item1.setTextColor(def)
      binding.tab.item2.setTextColor(Color.parseColor("#FF3700B3"))
      binding.tab.item3.setTextColor(def)
      val size: Int = binding.tab.item2.width
      binding.tab.select.animate().x(size.toFloat()).duration = 100
    }else if(view.id == R.id.item3){
      binding.tab.item1.setTextColor(def)
      binding.tab.item3.setTextColor(Color.parseColor("#FF3700B3"))
      binding.tab.item2.setTextColor(def)
      val size: Int = binding.tab.item2.width * 2
      binding.tab.select.animate().x(size.toFloat()).duration = 100
    }
  }

}