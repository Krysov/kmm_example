package kmm.example.app.shared.layout.grid


data class GridCameraModel(
    val projectionWidth: Pixel = 0,
    val projectionHeight: Pixel = 0,
    val projectionTileSize: Pixel = 0,
    val poseX: Tile = 0.0,
    val poseY: Tile = 0.0,
) {

    class Ref(
        val onUpdate: (camera: GridCameraModel) -> Unit,
        private var camera: GridCameraModel = GridCameraModel(),
    ) {

        fun get(): GridCameraModel = camera
        fun set(camera: GridCameraModel) {
            this.camera = camera
            onUpdate(camera)
        }

        fun ref(): () -> GridCameraModel = { this.get() }
    }
}
