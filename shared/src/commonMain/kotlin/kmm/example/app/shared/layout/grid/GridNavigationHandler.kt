package kmm.example.app.shared.layout.grid

data class FractionalPixels(
    var x: Float = 0f,
    var y: Float = 0f,
)

class GridNavigationHandler(val cam: GridCameraModel.Ref) {
    private var mostRecentReferencePosition = FractionalPixels()
    fun newMotionFrom(referencePosition: FractionalPixels) {
        mostRecentReferencePosition = referencePosition
    }

    fun moveTo(referencePosition: FractionalPixels) {

        mostRecentReferencePosition = referencePosition
    }
}