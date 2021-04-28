package kmm.example.app.shared.layout.grid

import kotlin.math.round
import kotlin.math.sign


typealias Tile = Double
typealias Pixel = Short

data class GridRect(
    var x: Tile = .0,
    var y: Tile = .0,
    var w: Tile = .0,
    var h: Tile = .0,
)

data class GridTiles(
    var x: Tile = .0,
    var y: Tile = .0,
)

data class ViewRect(
    var x: Pixel = 0,
    var y: Pixel = 0,
    var w: Pixel = 0,
    var h: Pixel = 0,
)

data class FractionalPixels(
    var x: Float = 0f,
    var y: Float = 0f,
) {
    fun minus(pixels: FractionalPixels): FractionalPixels {
        return FractionalPixels(x - pixels.x, y - pixels.y)
    }
}

fun transformsViewToGridDimen(cam: GridCameraModel, pixels: FractionalPixels): GridTiles {
    return GridTiles(
        x = pixels.x.toDouble() / cam.projectionTileSize,
        y = pixels.y.toDouble() / cam.projectionTileSize,
    )
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