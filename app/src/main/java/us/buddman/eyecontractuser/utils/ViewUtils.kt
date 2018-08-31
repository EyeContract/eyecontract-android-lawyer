package us.buddman.eyecontractuser.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.Base64
import android.view.SurfaceView
import android.view.View
import java.io.ByteArrayOutputStream

fun View.toBitmap(): Bitmap {
    val bitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    return if (this is SurfaceView) {
        this.setZOrderOnTop(true)
        this.draw(canvas)
        this.setZOrderOnTop(false)
        bitmap
    } else {
        this.draw(canvas)
        bitmap
    }
}

fun Bitmap.toConvertableString(): String {
    val baos = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.PNG, 100, baos)
    return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT)
}

fun String.toBitmap(): Bitmap {
    val encodeByte = Base64.decode(this, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
}