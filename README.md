# Abstract Controller Lib
A library that makes it easy to deal with controllers in an abstract way
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
    compile 'com.github.retrodaredevil:abstract-controller-lib:<CHOOSE A RELEASE TAG>'
}
```
[jitpack.io for using with other build tools](https://jitpack.io)

### TODO
Class for LowestPositionInputPart, class to change AxisType for an InputPart