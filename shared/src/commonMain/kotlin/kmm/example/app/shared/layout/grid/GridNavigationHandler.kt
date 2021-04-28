package kmm.example.app.shared.layout.grid


class GridNavigationHandler(private val cam: GridCameraModel.Ref) {
    private var latestReferencePosition = FractionalPixels()

    fun newMotionFrom(referencePosition: FractionalPixels) {
        latestReferencePosition = referencePosition
    }

    fun moveTo(referencePosition: FractionalPixels) {
        val camera = cam.get()
        val delta = transformsViewToGridDimen(
            camera,
            latestReferencePosition.minus(referencePosition)
        )
        this.cam.set(
            camera.copy(
                poseX = camera.poseX + delta.w,
                poseY = camera.poseY + delta.h,
            )
        )
        latestReferencePosition = referencePosition
    }
}