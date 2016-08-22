# ToastRhino
[![Build Status](https://travis-ci.org/LN-STEMpunks/ToastRhino.svg?branch=master)](https://travis-ci.org/LN-STEMpunks/ToastRhino)

Rhino robot code, now using [Toast](https://github.com/Open-RIO/ToastAPI) and Gradle!

Although the system becomes much more complex, the Toast API simplifies our code!

To set up this module in your development environment, follow these steps:

## Using ToastRhino

Clone the repository
If you haven't already, you can deploy [Toast](https://github.com/Open-RIO/ToastAPI) to your Robot by running `gradlew toastDeploy`.


### Eclipse or IntelliJ IDEA
1. Run `gradlew eclipse` for eclipse, or `gradlew idea` for IntelliJ  
2. Edit the `build.gradle` file to reflect your desired configuration (e.g. changing the Team Number)  
3. Open the project using `File -> Open Project`
4. To build, go to the directory in a shell and run `gradlew deploy`
If you haven't already, you can deploy [Toast](https://github.com/Open-RIO/ToastAPI) to your Robot by running `gradlew toastDeploy`.


### NetBeans
1. Install Gradle plugin for Netbeans (Go to `Tools -> Plugins -> Available Plugins` and search `Gradle JavaEE`. Install that plugin, restart netbeans).
2. Install FRC Netbeans plugin (Go to `Tools -> Plugins -> Settings` and click `Add` for name, use something like `FRC Plugins`, and for the url, use `http://first.wpi.edu/FRC/java/netbeans/update/Release/updates.xml` Restart netbeans).
3. Go to `Tools -> Options -> Miscellaneous -> FRC Configuration` and set `Team Number` to `3966`.
4. Open the repo using `File -> Open Project`
5. Now you can click the large green button in Netbeans, and it runs `gradlew clean build`
