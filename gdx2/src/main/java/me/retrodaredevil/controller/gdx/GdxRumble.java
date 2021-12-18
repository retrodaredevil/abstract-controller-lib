package me.retrodaredevil.controller.gdx;

import me.retrodaredevil.controller.SimpleControllerPart;
import me.retrodaredevil.controller.output.ControllerRumble;

public class GdxRumble extends SimpleControllerPart implements ControllerRumble {
    private static final int MAX_RUMBLE_TIME = 60 * 60 * 1000; // Yeah, an hour seems like enough
    private final ControllerProvider provider;

    public GdxRumble(ControllerProvider provider) {
        this.provider = provider;
    }

    private static float convert(double intensity) {
        return (float) intensity;
    }
    private static double toSingleRumble(double leftIntensity, double rightIntensity) {
        return (leftIntensity + rightIntensity) / 2.0;
    }
    private static int convert(long millis) {
        return Math.toIntExact(millis);
    }

    @Override
    public boolean isConnected() {
        return provider.isConnected() && provider.getController().canVibrate();
    }

    @Override
    public void rumbleForever(double intensity) {
        provider.getController().startVibration(MAX_RUMBLE_TIME, convert(intensity));
    }

    @Override
    public void rumbleForever(double leftIntensity, double rightIntensity) {
        rumbleForever((leftIntensity + rightIntensity) / 2.0);
    }

    @Override
    public void rumbleTimeout(long millisTimeout, double leftIntensity, double rightIntensity) {
        rumbleTimeout(millisTimeout, toSingleRumble(leftIntensity, rightIntensity));
    }

    @Override
    public void rumbleTimeout(long millisTimeout, double intensity) {
        provider.getController().startVibration(convert(millisTimeout), convert(intensity));
    }

    @Override
    public void rumbleTime(long millis, double leftIntensity, double rightIntensity) {
        rumbleTime(millis, toSingleRumble(leftIntensity, rightIntensity));
    }

    @Override
    public void rumbleTime(long millis, double intensity) {
        rumbleTimeout(millis, intensity); // do the same thing
    }

    @Override
    public boolean isAnalogRumbleSupported() {
        return true;
    }

    @Override
    public boolean isAnalogRumbleNativelySupported() {
        return true;
    }

    @Override
    public boolean isLeftAndRightSupported() {
        return false;
    }
}
