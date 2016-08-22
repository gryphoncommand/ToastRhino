# ToastRhino
[![Build Status](https://travis-ci.org/LN-STEMpunks/ToastRhino.svg?branch=master)](https://travis-ci.org/LN-STEMpunks/ToastRhino)

Rhino robot code, now using [Toast](https://github.com/Open-RIO/ToastAPI) and Gradle!

Although the system becomes much more complex, the Toast API simplifies our code!

To set up this module in your development environment, follow these steps:

1) Clone the repository  
2) Run `gradlew eclipse` for eclipse, or `gradlew idea` for IntelliJ (use `gradle` instead of `gradlew` if installed system-wide)  
3) Edit the `build.gradle` file to reflect your desired configuration (e.g. changing the Team Number)  

To build this module, simply run `gradlew build`.
To deploy this module to your Robot, simply run `gradlew deploy`.
If you haven't already, you can deploy [Toast](https://github.com/Open-RIO/ToastAPI) to your Robot by running `gradlew toastDeploy`.


## Using with NetBeans
1. Clone the repo
2. Install Gradle plugin for Netbeans (Go to `Tools -> Plugins -> Available Plugins` and search `Gradle JavaEE`. Install that plugin, restart netbeans).
3. Install FRC Netbeans plugin (Go to `Tools -> Plugins -> Settings` and click `Add` for name, use something like `FRC Plugins`, and for the url, use `http://first.wpi.edu/FRC/java/netbeans/update/Release/updates.xml` Restart netbeans).
4. Go to `Tools -> Options -> Miscellaneous -> FRC Configuration` and set `Team Number` to `3966`.
5. Now you can click the large green button in Netbeans, and it runs `gradlew clean build`
