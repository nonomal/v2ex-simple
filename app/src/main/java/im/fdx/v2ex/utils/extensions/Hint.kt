package im.fdx.v2ex.utils.extensions

import android.app.Activity
import android.content.Context
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.elvishew.xlog.XLog
import com.google.android.material.snackbar.Snackbar
import im.fdx.v2ex.R
import im.fdx.v2ex.ui.LoginActivity
import im.fdx.v2ex.utils.extensions.startActivity
import im.fdx.v2ex.utils.extensions.toast

/**
 * Created by fdx on 2017/6/14.
 * fdx will maintain it
 */

fun Context.dealError(errorCode: Int = -1, swipe: androidx.swiperefreshlayout.widget.SwipeRefreshLayout? = null) {

    when {
        this is Activity -> runOnUiThread {
            swipe?.isRefreshing = false
            when (errorCode) {
                -1 -> toast(getString(R.string.error_network))
                302 -> toast(getString(R.string.error_auth_failure))
                else -> toast(getString(R.string.error_network))
            }
        }
    }
}

@Deprecated(replaceWith = ReplaceWith("showHint"), message = "sdk 31 problem", level = DeprecationLevel.WARNING)
fun Fragment.toast(message: CharSequence): Toast? {
    return activity?.let {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT)
            .apply {
                show()
            }
    }
}


fun Any.logv(msg: Any?) {
    XLog.tag("v+" + this::class.java.simpleName).v(msg)
}

fun Any.logd(msg: Any?) {
    XLog.tag("v+" + this::class.java.simpleName).d(msg)
}

fun Any.logi(msg: Any?) {
    XLog.tag("v+" + this::class.java.simpleName).i(msg)
}

fun Any.logw(msg: Any?) {
    XLog.tag("v+" + this::class.java.simpleName).w(msg)
}

fun Any.loge(msg: Any?) {
    XLog.tag("v+" + this::class.java.simpleName).e(msg)
}


fun Activity.showLoginHint(view: View, message: String = getString(R.string.not_login_tips)) {

    val colorAttr: Int = R.attr.toolbar_background
    val typedValue = TypedValue()
    this.theme.resolveAttribute(colorAttr, typedValue, true)
    val color = typedValue.data
    Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        .setBackgroundTint(color)
        .setActionTextColor(ContextCompat.getColor(this, R.color.white))
        .setAction(getString(R.string.login)) {
            startActivity<LoginActivity>()
        }.show()
}

/**
 *  用来代替 toast方法。
 */
fun Activity.showHint(view: View, message: String, length: Int = Snackbar.LENGTH_LONG) {
    if (this.isFinishing || this.isDestroyed ) return

    val colorAttr: Int = R.attr.toolbar_background
    val typedValue = TypedValue()
    this.theme.resolveAttribute(colorAttr, typedValue, true)
    val color = typedValue.data
    Snackbar.make(view, message, length)
        .setBackgroundTint(color)
        .setActionTextColor(ContextCompat.getColor(this, R.color.white))
        .setAction(R.string.ok){}
        .show()
}