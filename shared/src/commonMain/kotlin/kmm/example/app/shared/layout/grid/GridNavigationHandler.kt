package kmm.example.app.shared.layout.grid


class GridNavigationHandler(val cam: GridCameraModel.Ref) {
    private var mostRecentReferencePosition = FractionalPixels()
    fun newMotionFrom(referencePosition: FractionalPixels) {
        mostRecentReferencePosition = referencePosition
    }

    fun moveTo(referencePosition: FractionalPixels) {

        mostRecentReferencePosition = referencePosition
    }
}