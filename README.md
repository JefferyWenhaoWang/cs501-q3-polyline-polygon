# Q3 Polyline and Polygon

This Android app demonstrates how to use polyline and polygon overlays on Google Maps.

## Features

1. Displays a Google Map with a polyline representing a hiking trail
2. Displays a polygon highlighting an area of interest
3. Allows the user to customize the color and width of the polyline
4. Allows the user to customize the color and border width of the polygon
5. Adds click listeners to display information when the trail or area is tapped

## Technologies Used

- Kotlin
- Jetpack Compose
- Google Maps Compose

## How It Works

- The app opens a Google Map centered on a fixed area
- A polyline is drawn on the map to represent a hiking trail
- A polygon is drawn on the map to represent a park or highlighted region
- Buttons at the bottom let the user:
  - change the trail color
  - change the trail width
  - change the park color
  - change the park border width
- Tapping the polyline or polygon updates the information text shown in the bottom card

## Customization Options

### Polyline
- Red
- Blue
- Purple
- Width increase
- Width decrease

### Polygon
- Green
- Yellow
- Blue
- Border increase
- Border decrease

## Notes

- The app requires Google Maps API key configuration
- The app is tested on Android Emulator
- The polyline and polygon are drawn using fixed coordinates

## Project Structure

- `MainActivity.kt` contains the map UI, polyline, polygon, button controls, and click listeners
- `AndroidManifest.xml` contains Google Maps API key metadata
- `build.gradle.kts` includes required Google Maps Compose dependencies
