package kmm.example.app.shared.layout.grid

import kotlin.math.round
import kotlin.math.sign

typealias Tiles = Double
typealias Pixels = Short

class GridCamera {
    val projection: Projection = Projection()
    val orientation: Orientation = Orientation()

    inner class Projection {
        var tileSize: Pixels = 0
        var viewWidth: Pixels = 0
        var viewHeight: Pixels = 0
    }

    inner class Orientation {
        var offsetX: Tiles = 0.0
        var offsetY: Tiles = 0.0
    }
}

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

fun transformGridToViewSpace(cam: GridCamera, rect: GridRect): ViewRect {
    val result = ViewRect()
    result.x = biasedRoundToShort((rect.x - cam.orientation.offsetX) * cam.projection.tileSize)
    result.y = biasedRoundToShort((rect.y - cam.orientation.offsetY) * cam.projection.tileSize)
    result.w = biasedRoundToShort(rect.w * cam.projection.tileSize)
    result.h = biasedRoundToShort(rect.h * cam.projection.tileSize)
    return result
}

private fun biasedRoundToShort(n: Double): Short {
    val roundingBias = 0.000001 // this aims to alleviate the inconsistency with round()
    return round(n + n.sign * roundingBias).toInt().toShort()
}