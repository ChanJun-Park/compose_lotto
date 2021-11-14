package com.jingom.composelotto.support.util

import android.content.res.Resources

object DisplayUtils {
	val screenWidth = Resources.getSystem().displayMetrics.widthPixels / Resources.getSystem().displayMetrics.density

	val screenWidthInPx = Resources.getSystem().displayMetrics.widthPixels

	val screenHeight = Resources.getSystem().displayMetrics.heightPixels / Resources.getSystem().displayMetrics.density

	val screenHeightInPx = Resources.getSystem().displayMetrics.heightPixels
}