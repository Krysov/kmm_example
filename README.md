# Kotlin Multiplatform Testground

### About
I'm increasingly looking into platform agnostic development strategies after having worked on native app implementations for a good while.
Some of the current Kotlin Multiplatform capabilities caught my attention and I attempted a somewhat start to finish example app to
familiarize myself with contemporary and upcoming work flows.

### As Of Now
![Current State of the Android UI](/info/android_2021-04-30_14-14-09.mp4)

Running a test suit between the Android and shared Kotlin codebase was mostly pain free as compared to React Native.
Putting together data, math, logic and even some ui components in a test driven fashion went ahead smoothly although
I still have to see how well the Android Studio centric process translates to iOS.

The custom grid view I set up is handled mostly through shared code thus only a minimal portion will have to be reimplemented for iOS.
I was interested in using it for potential future projects so I'll put some more work into it to add scrolling constraints,
animated scrolling to position and view recycler capabilities but for now an example crossword app has to suffice.

The UI is currently quick and dirty and needs a (shared code) view model to handle an actual user driven game session but so far at least
for Android development the KMM seems to handle itself well and aside from the more complex project structure and partial library restrictions,
really doesn't take much from a purely native approach.

I'll likely polish this project a bit more for the sake of trying out work flows and approaches outside a more critical application.