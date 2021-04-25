package kmm.example.app.shared.layout.grid

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

fun transformGridToViewSpace(cam: GridCamera, rect: GridRect): ViewRect {throw NotImplementedError()}