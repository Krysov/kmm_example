package kmm.example.app.shared.layout.grid

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.test.core.app.ApplicationProvider
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals


@RunWith(RobolectricTestRunner::class)
class GridViewUnitTest {

    @Test
    fun testAttachLayoutDetachItemViews() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val grid = GridView(context)
        val cam = grid.camera.copy(
            projection = grid.camera.projection.copy(
                tileSize = 32
            )
        )
        grid.camera = cam

        val tile1 = GridRect()
        tile1.x = 1.0
        tile1.y = 1.0
        tile1.w = 1.0
        tile1.h = 1.0
        val itemView1 = View(context)

        val tile2 = GridRect()
        tile2.x = 2.0
        tile2.y = 3.0
        tile2.w = 2.0
        tile2.h = 1.0
        val itemView2 = View(context)

        var lpItem: ViewGroup.MarginLayoutParams
        var vsItem: ViewRect
        var iv: View

        grid.addView(itemView1, tile1)
        grid.addView(itemView2, tile2)
        assertEquals(2, grid.childCount)

        grid.measure(0, 0)

        vsItem = transformGridToViewSpace(cam, tile1)
        iv = grid.getChildAt(0)
        lpItem = iv.layoutParams as ViewGroup.MarginLayoutParams
        assertNotEquals(0, iv.measuredWidth)
        assertNotEquals(0, iv.measuredHeight)
        assertEquals(vsItem.y.toInt(), lpItem.topMargin)
        assertEquals(vsItem.x.toInt(), lpItem.leftMargin)
        assertEquals(vsItem.w.toInt(), lpItem.width)
        assertEquals(vsItem.h.toInt(), lpItem.height)

        vsItem = transformGridToViewSpace(cam, tile2)
        iv = grid.getChildAt(1)
        lpItem = iv.layoutParams as ViewGroup.MarginLayoutParams
        assertNotEquals(0, iv.measuredWidth)
        assertNotEquals(0, iv.measuredHeight)
        assertEquals(vsItem.y.toInt(), lpItem.topMargin)
        assertEquals(vsItem.x.toInt(), lpItem.leftMargin)
        assertEquals(vsItem.w.toInt(), lpItem.width)
        assertEquals(vsItem.h.toInt(), lpItem.height)

        grid.removeView(itemView1)
        grid.removeView(itemView2)
        assertEquals(0, grid.childCount)

        val nonItemView = View(context)
        grid.addView(nonItemView)
        grid.removeView(nonItemView)
    }
}