package kmm.example.app.shared.layout.grid

import kotlin.math.round
import kotlin.math.sign


typealias Tiles = Double
typealias Pixels = Short

class GridRect {
    var x: Tiles = .0
    var y: Tiles = .0
    var w: Tiles = .0
    var h: Tiles = .0
}

class ViewRect {
    var x: Pixels = 0
    var y: Pixels = 0
    var w: Pixels = 0
    var h: Pixels = 0
}

fun transformGridToViewSpace(cam: GridCameraModel, rect: GridRect): ViewRect {
    val result = ViewRect()
    result.x = biasedRoundToShort((rect.x - cam.poseX) * cam.projectionTileSize)
    result.y = biasedRoundToShort((rect.y - cam.poseY) * cam.projectionTileSize)
    result.w = biasedRoundToShort(rect.w * cam.projectionTileSize)
    result.h = biasedRoundToShort(rect.h * cam.projectionTileSize)
    return result
}

private fun biasedRoundToShort(n: Double): Short {
    val roundingBias = 0.000001 // this aims to alleviate the inconsistency with round()
    return round(n + n.sign * roundingBias).toInt().toShort()
}