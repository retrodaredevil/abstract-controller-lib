package me.retrodaredevil.controller.switching;

import me.retrodaredevil.controller.SimpleControllerPart;
import me.retrodaredevil.controller.input.InputPart;
import me.retrodaredevil.controller.input.JoystickPart;
import me.retrodaredevil.controller.input.JoystickType;
import me.retrodaredevil.controller.input.implementations.JoystickAxisFollowerPart;

import java.util.List;

public class SwitchingJoystickPart extends SimpleControllerPart implements JoystickPart {
    private final List<JoystickPart> joystickParts;
    private final SwitchingIndexSelector indexSelector;

    private final InputPart xAxis = new JoystickAxisFollowerPart(this, partUpdater, false);
    private final InputPart yAxis = new JoystickAxisFollowerPart(this, partUpdater, true);

    public SwitchingJoystickPart(List<JoystickPart> joystickParts, SwitchingIndexSelector indexSelector) {
        this.joystickParts = joystickParts;
        this.indexSelector = indexSelector;
        partUpdater.addPartsAssertNonePresent(joystickParts);
    }

    @Override
    public boolean isConnected() {
        return joystickParts.get(indexSelector.getIndex()).isConnected();
    }

    @Override
    public double getAngle() {
        return joystickParts.get(indexSelector.getIndex()).getAngle();
    }

    @Override
    public double getAngleRadians() {
        return joystickParts.get(indexSelector.getIndex()).getAngleRadians();
    }

    @Override
    public boolean isDeadzone() {
        return joystickParts.get(indexSelector.getIndex()).isDeadzone();
    }

    @Override
    public InputPart getXAxis() {
        return xAxis;
    }

    @Override
    public InputPart getYAxis() {
        return yAxis;
    }

    @Override
    public JoystickType getJoystickType() {
        return joystickParts.get(indexSelector.getIndex()).getJoystickType();
    }

    @Override
    public double getMagnitude() {
        return joystickParts.get(indexSelector.getIndex()).getMagnitude();
    }

    @Override
    public double getCorrectMagnitude() {
        return joystickParts.get(indexSelector.getIndex()).getCorrectMagnitude();
    }

    @Override
    public double getX() {
        return joystickParts.get(indexSelector.getIndex()).getX();
    }

    @Override
    public double getY() {
        return joystickParts.get(indexSelector.getIndex()).getY();
    }

    @Override
    public boolean isXDeadzone() {
        return joystickParts.get(indexSelector.getIndex()).isXDeadzone();
    }

    @Override
    public boolean isYDeadzone() {
        return joystickParts.get(indexSelector.getIndex()).isYDeadzone();
    }
}
