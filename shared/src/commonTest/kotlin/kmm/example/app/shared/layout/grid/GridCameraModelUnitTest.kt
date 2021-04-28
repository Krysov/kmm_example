package kmm.example.app.shared.layout.grid


import kotlin.math.roundToInt
import kotlin.test.Test
import kotlin.test.assertEquals

class GridCameraModelUnitTest {

    private class CameraContainer(onUpdate: (camera: GridCameraModel) -> Unit) {
        val cam = GridCameraModel.Ref({ cam -> onUpdate(cam) })
    }

    @Test
    fun testCameraUpdate() {
        var numCallsOnUpdate = 0
        val con = CameraContainer(onUpdate = { numCallsOnUpdate++ })
        var tmpCam = con.cam.get()
        assertEquals(0, tmpCam.poseX.roundToInt())
        assertEquals(0, tmpCam.poseY.roundToInt())
        assertEquals(0, numCallsOnUpdate)

        con.cam.set(GridCameraModel(poseX = 5.0))
        assertEquals(1, numCallsOnUpdate)

        val refCam = con.cam.ref()
        tmpCam = con.cam.get()
        tmpCam = tmpCam.copy(poseY = 7.0)
        con.cam.set(tmpCam)
        assertEquals(2, numCallsOnUpdate)
        assertEquals(5, refCam().poseX.roundToInt())
        assertEquals(7, refCam().poseY.roundToInt())
    }
}