package com.movies.util.rxpm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.movies.injector
import me.dmdev.rxpm.PresentationModel
import me.dmdev.rxpm.base.PmController
import kotlin.reflect.KClass
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.*


abstract class BaseController<PM : PresentationModel>(
    private val pmClass: KClass<PM>,
    @LayoutRes private val layoutId: Int,
    bundle: Bundle? = null
) : PmController<PM>(bundle), LayoutContainer {

    override var containerView: View? = null

    override fun providePresentationModel(): PM {
        return activity!!.application.injector.pmFactory().create(pmClass.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedViewState: Bundle?): View {
        containerView = inflater.inflate(layoutId, container, false)
        onViewCreated(containerView!!)
        return containerView!!
    }

    protected open fun onViewCreated(view: View) {}

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        clearFindViewByIdCache()
        containerView = null
    }
}