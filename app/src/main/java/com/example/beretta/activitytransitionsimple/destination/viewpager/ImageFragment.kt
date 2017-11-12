package com.example.beretta.activitytransitionsimple.destination.viewpager

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.beretta.activitytransitionsimple.R
import com.example.beretta.activitytransitionsimple.destination.dismiss.DismissFrameLayout
import kotlinx.android.synthetic.main.fragment_image.*

/**
 * Created by beretta on 12.11.2017.
 */
class ImageFragment : Fragment() {
    companion object {
        const val KEY_SOURCE_URL = "ext_k_source"
        const val KEY_CURRENT_POS = "ext_k_current"
        fun newInstance(sourceUrl: String, currentPos: Int, onDismissListener: DismissFrameLayout.OnDismissListener): ImageFragment {
            val fragment = ImageFragment()
            val args = Bundle()
            args.putInt(KEY_CURRENT_POS, currentPos)
            args.putString(KEY_SOURCE_URL, sourceUrl)
            fragment.setOnDismissListener(onDismissListener)
            fragment.arguments = args
            return fragment
        }
    }

    private var onDismissListener: DismissFrameLayout.OnDismissListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_image, container, false)
        val layout = DismissFrameLayout(activity)
        layout.setDismissListener(onDismissListener)
        layout.addView(view)
        return layout
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        deserializeArgs()
    }

    private fun setOnDismissListener(onDismissListener: DismissFrameLayout.OnDismissListener) {
        this.onDismissListener = onDismissListener
    }

    private fun deserializeArgs() {
        val url = arguments.getString(KEY_SOURCE_URL)
        val currentPos = arguments.getInt(KEY_CURRENT_POS)
        fragment_image.transitionName = getString(R.string.recycler_transition, currentPos)

        Glide.with(activity)
                .load(url)
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        (activity as DestinationViewPagerActivity).supportStartPostponedEnterTransition()
                        return false
                    }

                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        (activity as DestinationViewPagerActivity).supportStartPostponedEnterTransition()
                        return false
                    }

                })
                .into(fragment_image)

    }
}