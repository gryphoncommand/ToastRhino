# ToastRhino
[![Build Status](https://travis-ci.org/LN-STEMpunks/ToastRhino.svg?branch=master)](https://travis-ci.org/LN-STEMpunks/ToastRhino)

Rhino robot code, now using Toast and Gradle!

Although the system becomes much more complex, the Toast API simplifies our code!

To set up this module in your development environment, follow these steps:

1) Clone the repository  
2) Run `gradlew eclipse` for eclipse, or `gradlew idea` for IntelliJ (use `gradle` instead of `gradlew` if installed system-wide)  
3) Edit the `build.gradle` file to reflect your desired configuration (e.g. changing the Team Number)  

To build this module, simply run `gradlew build`.
To deploy this module to your Robot, simply run `gradlew deploy`.
If you haven't already, you can deploy [Toast](https://github.com/Open-RIO/ToastAPI) to your Robot by running `gradlew toastDeploy`.
