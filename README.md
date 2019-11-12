# Abstract Controller Lib
A library that makes it easy to deal with controllers in an abstract way

[![](https://jitpack.io/v/retrodaredevil/abstract-controller-lib.svg)](https://jitpack.io/#retrodaredevil/abstract-controller-lib)

## How is this used
This library provides no concrete implementation for actual physical controllers. This provides
many useful interfaces. InputPart for buttons and axi/triggers, JoystickPart for joysticks
(consists of 2 InputParts).
## More useful features
Provides an options package that allows to you to change settings of a controller during runtime 
(You can use the concrete SensitiveInputPart for this).

There are also some concrete implementations such as TwoWayInput(positive and negative axi),
TwoAxisJoystickPart(A joystick that consists of two axi), HighestPositionInputPart(used to link
two InputParts to a single InputPart)
## Using in your project
```
// gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
dependencies {
    implementation 'com.github.retrodaredevil:abstract-controller-lib:<CHOOSE A RELEASE TAG>'
    // or for older gradle versions:
    compile 'com.github.retrodaredevil.abstract-controller-lib:api:<CHOOSE A RELEASE TAG>'

    // also add either of these depending on if you are using libGDX or WPILib.
    compile 'com.github.retrodaredevil.abstract-controller-lib:gdx:<CHOOSE A RELEASE TAG>'
    compile 'com.github.retrodaredevil.abstract-controller-lib:wpi:<CHOOSE A RELEASE TAG>'
}
```
[jitpack.io for using with other build tools](https://jitpack.io)

## Version used
This project uses Java 8 features but does not use new Java 8 APIs to allow compatibility with
android SDK level 19.

Because of this, you may see interfaces with a single method. This is to allow lambda functions
without importing classes only in Java 8 such as Supplier and Consumer.

## Deadzones
This project has an interface called DeadzoneablePart. There may be many times when you want a joystick to have a large
deadzone (maybe .3). This isDeadzone() method is not meant to be easily extended to make callers ignore a magnitude of .3
just because you say so. If you want to have a deadzone this large, you should scale the x and y values as well. There
is currently no InputPart or JoystickPart implementation that can be used for this.

### TODO
class to change AxisType for an InputPart
