package kmm.example.app.shared.layout.grid

import kmm.example.app.shared.layout.grid.GridCameraModel.Orientation
import kmm.example.app.shared.layout.grid.GridCameraModel.Projection
import kotlin.test.Test
import kotlin.test.assertEquals

class GridTransformsUnitTest {

    @Test
    fun testTransformGridToViewSpaceCoordinatesAndDimensions() {

        /* Test Grid Data:
        .|0123
        -+----
        0|....
        1|.1..
        2|....
        3|..22
        */

        var cam = GridCameraModel(
            projection = Projection(
                tileSize = 75,
                viewWidth = 200,
                viewHeight = 300,
            )
        )

        val tile1 = GridRect()
        tile1.x = 1.0
        tile1.y = 1.0
        tile1.w = 1.0
        tile1.h = 1.0
        var output1: ViewRect

        val tile2 = GridRect()
        tile2.x = 2.0
        tile2.y = 3.0
        tile2.w = 2.0
        tile2.h = 1.0
        var output2: ViewRect

        output1 = transformGridToViewSpace(cam, tile1)
        assertEquals(75, output1.x)
        assertEquals(75, output1.y)
        assertEquals(75, output1.w)
        assertEquals(75, output1.h)
        output2 = transformGridToViewSpace(cam, tile2)
        assertEquals(150, output2.x)
        assertEquals(225, output2.y)
        assertEquals(150, output2.w)
        assertEquals(75, output2.h)

        cam = cam.copy(
            orientation = Orientation(
                offsetX = 1.0,
                offsetY = 1.0,
            )
        )
        output1 = transformGridToViewSpace(cam, tile1)
        assertEquals(0, output1.x)
        assertEquals(0, output1.y)
        assertEquals(75, output1.w)
        assertEquals(75, output1.h)
        output2 = transformGridToViewSpace(cam, tile2)
        assertEquals(75, output2.x)
        assertEquals(150, output2.y)
        assertEquals(150, output2.w)
        assertEquals(75, output2.h)

        cam = cam.copy(
            orientation = Orientation(
                offsetX = 1.5,
                offsetY = 2.5,
            )
        )
        output1 = transformGridToViewSpace(cam, tile1)
        assertEquals(-38, output1.x) // rounded from -37.5 to -38
        assertEquals(-113, output1.y) // rounded from -112.5 to -113
        assertEquals(75, output1.w)
        assertEquals(75, output1.h)
        output2 = transformGridToViewSpace(cam, tile2)
        assertEquals(38, output2.x)
        assertEquals(38, output2.y)
        assertEquals(150, output2.w)
        assertEquals(75, output2.h)
    }
}