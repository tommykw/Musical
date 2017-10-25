package com.github.tommykw.musical.application

import android.content.Context
import com.github.tommykw.musical.activity.AnkoActivity
import com.github.tommykw.musical.activity.SecondActivity
import com.github.tommykw.musical.constant.Constants

object Router {
    fun routeToCamera(context: Context) =
            context.startActivity(SecondActivity.makeIntent(context, Constants.NAV_CAMERA))

    fun routeToGallery(context: Context) =
            context.startActivity(SecondActivity.makeIntent(context, Constants.NAV_GALLERY))

    fun routeToSlideShow(context: Context) =
            context.startActivity(SecondActivity.makeIntent(context, Constants.NAV_SLIDESHOW))

    fun routeToManage(context: Context) =
            context.startActivity(SecondActivity.makeIntent(context, Constants.NAV_MANAGE))

    fun routeToShare(context: Context) =
            context.startActivity(SecondActivity.makeIntent(context, Constants.NAV_SHARE))

    fun routeToSend(context: Context) =
            context.startActivity(SecondActivity.makeIntent(context, Constants.NAV_SEND))

    fun routeToAnko(context: Context) =
            context.startActivity(AnkoActivity.makeIntent(context))
}