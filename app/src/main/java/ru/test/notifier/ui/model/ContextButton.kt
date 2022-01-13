package ru.test.notifier.ui.model

import android.graphics.Rect

data class ContextButton (
    val code: String,
    val label: String,
    val position: Int,
    val res: Int,
    val bounds: Rect,
)