package com.example.kotlin_shopping_list_cooroutine.ext

import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

fun Fragment.showDialog(
    title: String,
    message: String,
    okText: String,
    okCallBack: DialogInterface.OnClickListener,
    koText: String,
    koCallback: DialogInterface.OnClickListener
) {
    val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(okText, okCallBack)
        .setNegativeButton(koText, koCallback)

    builder.show()
}