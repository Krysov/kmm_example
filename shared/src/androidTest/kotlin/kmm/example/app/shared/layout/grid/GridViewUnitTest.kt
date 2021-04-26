package kmm.example.app.shared.layout.grid

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.test.core.app.ApplicationProvider
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.test.assertEquals


@RunWith(RobolectricTestRunner::class)
class GridViewUnitTest {

    @Test
    fun testAttachLayoutDetachItemViews() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val grid = GridView(context)
        val cam: GridCamera = grid.camera

        val tile1 = GridRect()
        tile1.x = 1.0
        tile1.y = 1.0
        tile1.w = 1.0
        tile1.h = 1.0
        val itemView1 = View(context)
        itemView1.setBackgroundColor(Color.RED)

        val tile2 = GridRect()
        tile2.x = 2.0
        tile2.y = 3.0
        tile2.w = 2.0
        tile2.h = 1.0
        val itemView2 = View(context)
        itemView1.setBackgroundColor(Color.BLUE)

        var lpItem: ViewGroup.MarginLayoutParams
        var vsItem: ViewRect

        grid.addView(itemView1, tile1)
        grid.addView(itemView2, tile2)
        assertEquals(2, grid.childCount)

        vsItem = transformGridToViewSpace(cam, tile1)
        lpItem = grid.getChildAt(0).layoutParams as ViewGroup.MarginLayoutParams
        assertEquals(vsItem.y.toInt(), lpItem.topMargin)
        assertEquals(vsItem.x.toInt(), lpItem.leftMargin)
        assertEquals(vsItem.w.toInt(), lpItem.width)
        assertEquals(vsItem.h.toInt(), lpItem.height)

        vsItem = transformGridToViewSpace(cam, tile2)
        lpItem = grid.getChildAt(1).layoutParams as ViewGroup.MarginLayoutParams
        assertEquals(vsItem.y.toInt(), lpItem.topMargin)
        assertEquals(vsItem.x.toInt(), lpItem.leftMargin)
        assertEquals(vsItem.w.toInt(), lpItem.width)
        assertEquals(vsItem.h.toInt(), lpItem.height)

        grid.removeView(itemView1)
        grid.removeView(itemView2)
        assertEquals(0, grid.childCount)
    }
}