package com.example.beretta.activitytransitionsimple.destination.viewpager

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.example.beretta.activitytransitionsimple.R
import com.example.beretta.activitytransitionsimple.destination.dismiss.DismissFrameLayout
import kotlinx.android.synthetic.main.activity_destination_viewpager.*

class DestinationViewPagerActivity : AppCompatActivity() {

    companion object {
        const val KEY_TRANSITION_NAME = "ext_k_transition"
        const val KEY_CURRENT_POS = "ext_k_cur_pos"
        const val KEY_URLS = "ext_k_urls"
        private var colorDrawable: ColorDrawable? = null

        private val ALPHA_MAX = 0xFF


        fun getIntent(context: Activity, transitionName: String, urls: Array<String>, currentPos: Int): Intent {
            val intent = Intent(context, DestinationViewPagerActivity::class.java)
            intent.putExtra(KEY_TRANSITION_NAME, transitionName)
            intent.putExtra(KEY_URLS, urls)
            intent.putExtra(KEY_CURRENT_POS, currentPos)
            return intent
        }
    }

    var isTransitionEnd = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_viewpager)
        supportPostponeEnterTransition()
        deserializeIntent()
        setupLayouts()
    }

    private fun setupLayouts() {

    }

    private fun deserializeIntent() {
        val urls = intent.getStringArrayExtra(KEY_URLS)
        val currentItem = intent.getIntExtra(KEY_CURRENT_POS, 0)
        destination_viewpager.adapter = SlidesViewPager(supportFragmentManager, urls, onDismissListener)
        destination_viewpager.currentItem = currentItem

        colorDrawable = ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimaryDark))
        destination_viewpager_container.setBackgroundDrawable(colorDrawable)
    }

    private val onDismissListener = object : DismissFrameLayout.OnDismissListener {
        override fun onScaleProgress(scale: Float) {
            colorDrawable?.let {
                it.alpha = Math.min(ALPHA_MAX, it.alpha - (scale * ALPHA_MAX).toInt())
            }
        }

        override fun onDismiss() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAfterTransition()
            } else {
                finish()
            }
        }

        override fun onCancel() {
            colorDrawable?.alpha = ALPHA_MAX
        }
    }

    override fun supportStartPostponedEnterTransition() {
        if (isTransitionEnd.not()) {
            super.supportStartPostponedEnterTransition()
            isTransitionEnd = true
        }
    }

    fun getActivityCurrentPosition(): Int = intent.getIntExtra(KEY_CURRENT_POS, 0)

}