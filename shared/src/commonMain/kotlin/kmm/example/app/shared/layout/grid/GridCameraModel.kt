package kmm.example.app.shared.layout.grid


data class GridCameraModel(
    val projection: Projection = Projection(),
    val orientation: Orientation = Orientation(),
) {

    data class Projection(
        val tileSize: Pixels = 0,
        val viewWidth: Pixels = 0,
        val viewHeight: Pixels = 0,
    )

    data class Orientation(
        val offsetX: Tiles = 0.0,
        val offsetY: Tiles = 0.0,
    )

    class Ref(val onUpdate: (camera: GridCameraModel) -> Unit) {
        private var camera = GridCameraModel()
        fun get(): GridCameraModel = camera
        fun set(camera: GridCameraModel) {
            this.camera = camera
            onUpdate(camera)
        }
    }
}
