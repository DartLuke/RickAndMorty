package com.danielpasser.rickandmorty.ui.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemDecorator(private val height:Int): RecyclerView.ItemDecoration()
{

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top=height
        outRect.bottom=height
    }
}