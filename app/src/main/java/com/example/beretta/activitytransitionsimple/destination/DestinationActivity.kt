package com.example.beretta.activitytransitionsimple.destination

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.beretta.activitytransitionsimple.R
import kotlinx.android.synthetic.main.activity_destination.*

class DestinationActivity : AppCompatActivity() {

    companion object {
        const val KEY_TRANSITION_NAME = "ext_k_transition"
        const val KEY_URL = "ext_k_urls"

        fun getIntent(context: Activity, transitionName: String, url: String): Intent {
            val intent = Intent(context, DestinationActivity::class.java)
            intent.putExtra(KEY_TRANSITION_NAME, transitionName)
            intent.putExtra(KEY_URL, url)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination)
        postponeEnterTransition()
        deserializeIntent()
        setupLayouts()
    }

    private fun setupLayouts() {

    }

    private fun deserializeIntent() {
        val transName = intent.getStringExtra(KEY_TRANSITION_NAME)
        val url = intent.getStringExtra(KEY_URL)
        ViewCompat.setTransitionName(iv_destination_image, transName)
        Glide.with(this)
                .load(url)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }
                })

                .into(iv_destination_image)

    }

}
