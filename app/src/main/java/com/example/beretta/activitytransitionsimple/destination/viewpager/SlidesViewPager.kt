package com.example.beretta.activitytransitionsimple.destination.viewpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.beretta.activitytransitionsimple.destination.dismiss.DismissFrameLayout

/**
 * Created by beretta on 12.11.2017.
 */
class SlidesViewPager(fm: FragmentManager, val urls: Array<String>, val onDismissListener: DismissFrameLayout.OnDismissListener) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return ImageFragment.newInstance(urls[position], position, onDismissListener)
    }

    override fun getCount(): Int = urls.count()


}