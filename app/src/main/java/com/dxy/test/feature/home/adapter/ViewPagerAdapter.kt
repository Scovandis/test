package com.dxy.test.feature.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


class ViewPagerAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

  private var mFragments= ArrayList<Fragment>()
  private var mFragmentTitles = ArrayList<String>()

  override fun getCount()=mFragmentTitles.size

  fun addFragment(fragment: Fragment, title: String){
    mFragments.add(fragment)
    mFragmentTitles.add(title)
    notifyDataSetChanged()
  }

  override fun getPageTitle(position: Int)=mFragmentTitles[position]
  override fun getItem(position: Int) = mFragments[position]

}