package kmm.example.app.shared.layout.grid

import kotlin.math.roundToInt
import kotlin.test.Test
import kotlin.test.assertEquals


class GridNavigationHandlerUnitTest {

    @Test
    fun testPanning() {
        var numCallsOnUpdate = 0
        val cam = GridCameraModel.Ref(
            { numCallsOnUpdate++ },
            GridCameraModel(projectionTileSize = 50, poseX = 1.0, poseY = -2.0)
        )
        val nav = GridNavigationHandler(cam)
        assertEquals(0, numCallsOnUpdate)
        assertEquals(1, cam.get().poseX.roundToInt())
        assertEquals(-2, cam.get().poseY.roundToInt())

        nav.newMotionFrom(FractionalPixels(50f, 100f))
        nav.moveTo(FractionalPixels(50f, 150f))
        assertEquals(1, numCallsOnUpdate)
        assertEquals(1, cam.get().poseX.roundToInt())
        assertEquals(-3, cam.get().poseY.roundToInt())

        nav.moveTo(FractionalPixels(100f, -50f))
        assertEquals(2, numCallsOnUpdate)
        assertEquals(0, cam.get().poseX.roundToInt())
        assertEquals(1, cam.get().poseY.roundToInt())
    }
}