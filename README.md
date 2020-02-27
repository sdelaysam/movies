### Prerequisites

This application uses [Djinni](https://github.com/dropbox/djinni) to generate `JNI` and `Obj-C++` wrappers. Please run

```
sh update.sh
```

### Installing

Open `android` folder in Android Studio 3.6+

### Running the tests

Run tests using Android Studio or CLI

```
./gradlew testDebugUnitTest
```

### Built With

* [Djinni](https://github.com/dropbox/djinni) - A tool for generating cross-language type declarations and interface bindings
* [RxJava](https://github.com/ReactiveX/RxJava) - Reactive Extensions for the JVM
* [Dagger2](https://github.com/google/dagger) - A fast dependency injector for Android and Java
* [Conductor](https://github.com/bluelinelabs/Conductor) - A small, yet full-featured framework that allows building View-based Android applications
* [RxPM](https://github.com/dmdevgo/RxPM) - Reactive implementation of Presentation Model pattern in Android
* etc