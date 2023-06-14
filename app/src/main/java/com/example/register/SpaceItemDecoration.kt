package com.example.register

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(
    private val context: Context,
    private val verticalSpaceHeight: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        // Menambahkan jarak verticalSpaceHeight pada bagian bawah setiap item
        outRect.bottom = context.resources.getDimensionPixelSize(verticalSpaceHeight)
    }
}
