package com.movies.ui.transitions

import android.os.Bundle
import android.transition.*
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import com.bluelinelabs.conductor.changehandler.SharedElementTransitionChangeHandler

class MovieDetailTransition(private var elements: Array<String>? = null) : SharedElementTransitionChangeHandler() {

    companion object {
        private const val KEY_ELEMENTS = "KEY_ELEMENTS"
    }

    override fun getSharedElementTransition(container: ViewGroup, from: View?, to: View?, isPush: Boolean): Transition? {
        return TransitionSet().addTransition(ChangeBounds())
            .addTransition(ChangeClipBounds())
            .addTransition(ChangeTransform())
            .setInterpolator(DecelerateInterpolator())
    }

    override fun getExitTransition(container: ViewGroup, from: View?, to: View?, isPush: Boolean): Transition? {
        return if (isPush) {
            Explode().setInterpolator(DecelerateInterpolator())
        } else {
            AutoTransition().setInterpolator(DecelerateInterpolator())
        }
    }

    override fun getEnterTransition(container: ViewGroup, from: View?, to: View?, isPush: Boolean): Transition? {
        return if (isPush) {
            Slide(Gravity.BOTTOM).setInterpolator(DecelerateInterpolator())
        } else {
            Explode().setInterpolator(DecelerateInterpolator())
        }
    }

    override fun configureSharedElements(container: ViewGroup, from: View?, to: View?, isPush: Boolean) {
        elements?.forEach {
            addSharedElement(it)
            waitOnSharedElementNamed(it)
        }
    }

    override fun saveToBundle(bundle: Bundle) {
        super.saveToBundle(bundle)
        elements?.let { bundle.putStringArray(KEY_ELEMENTS, it) }
    }

    override fun restoreFromBundle(bundle: Bundle) {
        super.restoreFromBundle(bundle)
        elements = bundle.getStringArray(KEY_ELEMENTS)
    }
}