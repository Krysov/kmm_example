package kmm.example.app.shared.layout.grid


data class GridCameraModel(
    val projectionWidth: Pixels = 0,
    val projectionHeight: Pixels = 0,
    val projectionTileSize: Pixels = 0,
    val poseX: Tiles = 0.0,
    val poseY: Tiles = 0.0,
) {

    class Ref(val onUpdate: (camera: GridCameraModel) -> Unit) {
        private var camera = GridCameraModel()
        fun get(): GridCameraModel = camera
        fun set(camera: GridCameraModel) {
            this.camera = camera
            onUpdate(camera)
        }
    }
}
