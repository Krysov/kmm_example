package kmm.example.app.shared.layout.grid

import kmm.example.app.shared.layout.grid.GridCameraModel.Orientation
import kotlin.math.roundToInt
import kotlin.test.Test
import kotlin.test.assertEquals

class GridCameraModelUnitTest {

    private class CameraContainer(onUpdate: (camera: GridCameraModel) -> Unit) {
        val cam = GridCameraModel::Ref{ cam -> onUpdate(cam) }
    }

    @Test
    fun testCameraUpdate() {
        var numCallsOnUpdate = 0
        val con = CameraContainer(onUpdate = { numCallsOnUpdate++ })
        var tmpCam = con.cam.get()
        assertEquals(0, tmpCam.orientation.offsetX.roundToInt())
        assertEquals(0, tmpCam.orientation.offsetY.roundToInt())
        assertEquals(0, numCallsOnUpdate)

        con.cam.set(GridCameraModel(orientation = Orientation(offsetX = 5.0)))
        assertEquals(1, numCallsOnUpdate)

        tmpCam = tmpCam.copy(orientation = tmpCam.orientation.copy(offsetY = 7.0))
        con.cam.set(tmpCam)
        assertEquals(2, numCallsOnUpdate)

        tmpCam = con.cam.get()
        assertEquals(5, tmpCam.orientation.offsetX.roundToInt())
        assertEquals(7, tmpCam.orientation.offsetY.roundToInt())
    }
}