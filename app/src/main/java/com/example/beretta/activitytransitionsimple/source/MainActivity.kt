package com.example.beretta.activitytransitionsimple.source

import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.LinearLayoutManager
import com.example.beretta.activitytransitionsimple.R
import com.example.beretta.activitytransitionsimple.destination.DestinationActivity
import com.example.beretta.activitytransitionsimple.destination.viewpager.DestinationViewPagerActivity
import com.example.beretta.activitytransitionsimple.source.adapter.ImageRecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        source_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        source_recycler_view.adapter = ImageRecyclerAdapter(listUrls, itemClickListener)

    }

    val listUrls by lazy { listOf(getString(R.string.sample_url), getString(R.string.sample_url), getString(R.string.sample_url)) }

    private val itemClickListener = object : ImageRecyclerAdapter.OnImageClicked {
        override fun onItemClicked(image: AppCompatImageView, url: String, position: Int) {
            val transitionName = ViewCompat.getTransitionName(image)
            val intent = DestinationViewPagerActivity.getIntent(this@MainActivity, transitionName, listUrls.toTypedArray(), position)

            val options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(this@MainActivity,
                            image,
                            transitionName)
            startActivity(intent, options.toBundle())
        }
    }

}
