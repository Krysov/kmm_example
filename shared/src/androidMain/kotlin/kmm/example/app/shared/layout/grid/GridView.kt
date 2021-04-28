package kmm.example.app.shared.layout.grid

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup


class GridView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    val cam = GridCameraModel.Ref({ camera ->
        measure(
            camera.projectionWidth.toInt(),
            camera.projectionWidth.toInt()
        )
    })
    private val nav = GridNavigationHandler(cam)
    private val gridViews = HashMap<View, GridRect>()
    private var touchMostRecentX: Float = 0.0f
    private var touchMostRecentY: Float = 0.0f

    fun addView(child: View, onGridAt: GridRect) {
        gridViews[child] = onGridAt
        child.layoutParams = getChildLayoutParams(onGridAt)
        super.addView(child)
    }

    override fun onViewRemoved(child: View?) {
        gridViews.remove(child)
        super.onViewRemoved(child)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 1..childCount) {
            val child = getChildAt(i - 1)
            if (child.visibility == GONE) continue
            layoutChildView(child)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        for (i in 1..childCount) {
            val child = getChildAt(i - 1)
            val gridDimen: GridRect? = gridViews[child]
            if (gridDimen != null) child.layoutParams = getChildLayoutParams(gridDimen)
        }
        measureChildren(widthMeasureSpec, heightMeasureSpec)
    }

    private fun getChildLayoutParams(dimen: GridRect): LayoutParams {
        val viewRect = transformGridToViewSpace(cam.get(), dimen)
        val lp = MarginLayoutParams(viewRect.w.toInt(), viewRect.h.toInt())
        lp.leftMargin = viewRect.x.toInt()
        lp.topMargin = viewRect.y.toInt()
        return lp
    }

    private fun layoutChildView(child: View) {
        val lp = child.layoutParams as MarginLayoutParams
        val width: Int = child.measuredWidth
        val height: Int = child.measuredHeight
        val left: Int = lp.leftMargin
        val top: Int = lp.topMargin
        child.layout(left, top, left + width, top + height)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                nav.newMotionFrom(FractionalPixels(event.x, event.y))
            }
            MotionEvent.ACTION_MOVE -> {
                nav.moveTo(FractionalPixels(event.x, event.y))
            }
        }
        return true
    }
}