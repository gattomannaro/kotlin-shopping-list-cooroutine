package com.example.kotlin_shopping_list_cooroutine.ext

import android.widget.Button

fun Button.disable() {
    this.isEnabled = false
    this.alpha = 0.3f
}

fun Button.enable() {
    this.isEnabled = true
    this.alpha = 1f
}