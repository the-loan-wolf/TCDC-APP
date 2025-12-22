# TCDC – Blood Cell Counter

TCDC is a modern Android application for manual blood cell differential counting.
It is designed for laboratory and microscopy workflows where fast, accurate, and distraction-free counting is required.

The app is fully rebuilt using **Jetpack Compose**, **Material 3**, and **Kotlin Flow**, replacing legacy View-based implementations with a modern, reactive architecture.

![com ris tcdc-20](https://github.com/user-attachments/assets/e10f06f9-6a66-4947-a740-4d9134c08d59)

## Features

* Manual blood cell differential counting
* Real-time total calculation
* Undo last count action
* Modern Material 3 UI
* Haptic feedback for counting actions `Not working due to the bug`
* Screen stays awake while counting
* Fully reactive state management

## Tech Stack

### UI

* Jetpack Compose
* Material 3
* Adaptive layouts

### Architecture

* MVVM (Model–View–ViewModel)
* Unidirectional data flow
* Stateless composables

### State & Async

* Kotlin Coroutines
* StateFlow / Flow
* Lifecycle-aware state collection

### Build

* Kotlin DSL
* Version Catalog (libs.versions.toml)
* Compose BOM

## Project Structure

```
app/
├── ui/
│   ├── components/
│   │   ├── BloodCellRow.kt
│   │   └── TotalCard.kt
│   ├── screens/
│   │   └── BloodCountScreen.kt
│   └── theme/
├── viewmodel/
│   └── BloodCountViewModel.kt
├── model/
│   ├── BloodCell.kt
│   └── CellType.kt
└── MainActivity.kt
```

## Architecture Overview

The app follows a strict MVVM pattern:

* **ViewModel**

  * Holds all business logic
  * Exposes immutable `StateFlow` to the UI
  * Handles increment, undo, and total calculation

* **UI (Compose)**

  * Collects state using `collectAsState()`
  * Renders purely from state
  * Emits user events back to the ViewModel

No UI logic exists outside composables.

## State Management

* Counts are stored in a `MutableStateFlow<Map<CellType, Int>>`
* Undo history is tracked using a stack-like `List<CellType>`
* Derived UI state is produced using `combine()` and `stateIn()`

This ensures:

* Predictable state updates
* Easy undo functionality
* No hidden side effects

## Haptic Feedback

* Lightweight haptic feedback on each count action
* Uses platform-safe APIs
* Respects system haptic settings
* Designed for frequent interaction without fatigue

## Screen Behavior

* The screen remains awake while the counting screen is visible
* Implemented using lifecycle-safe APIs
* Automatically released when leaving the screen

## Requirements

* Android Studio Hedgehog or newer
* Android Gradle Plugin 8+
* Kotlin 2.0+
* Minimum SDK: 24

## Build & Run

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle
4. Run on a physical device (recommended for haptics)

## Notes

* Haptic feedback will not work on emulators
* System haptic feedback must be enabled on the device
* Material icons are optional and loaded via `material-icons-extended`

## Future Improvements
* [ ] Fix Haptic
* [ ] Keep history using Room(sqlite)
* [ ] Export results
* [ ] Add reset confirmation
* [ ] Tablet-optimized layout
* [ ] Dark/light theme customization
