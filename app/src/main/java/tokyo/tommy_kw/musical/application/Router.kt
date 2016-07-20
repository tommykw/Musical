package tokyo.tommy_kw.musical.application

import android.content.Context
import tokyo.tommy_kw.musical.activity.AnkoActivity
import tokyo.tommy_kw.musical.activity.SecondActivity
import tokyo.tommy_kw.musical.constant.Constants

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