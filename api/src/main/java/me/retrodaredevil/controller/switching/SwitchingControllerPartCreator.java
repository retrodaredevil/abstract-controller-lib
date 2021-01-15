package me.retrodaredevil.controller.switching;

import me.retrodaredevil.controller.implementations.ControllerPartCreator;
import me.retrodaredevil.controller.input.AxisType;
import me.retrodaredevil.controller.input.InputPart;
import me.retrodaredevil.controller.input.JoystickPart;
import me.retrodaredevil.controller.output.ControllerRumble;
import me.retrodaredevil.controller.output.DisconnectedRumble;

import java.util.ArrayList;
import java.util.List;

public class SwitchingControllerPartCreator implements ControllerPartCreator {
    private final List<ControllerPartCreator> creators;
    private final SwitchingIndexSelector indexSelector;

    public SwitchingControllerPartCreator(List<ControllerPartCreator> creators, SwitchingIndexSelector indexSelector) {
        this.creators = creators;
        this.indexSelector = indexSelector;
    }

    @Override
    public InputPart createDigital(int code) {
        List<InputPart> parts = new ArrayList<>(creators.size());
        for (ControllerPartCreator creator : creators) {
            parts.add(creator.createDigital(code));
        }
        return new SwitchingInputPart(AxisType.DIGITAL, parts, indexSelector);
    }

    @Override
    public JoystickPart createPov(int povNumber, int xAxis, int yAxis) {
        List<JoystickPart> parts = new ArrayList<>(creators.size());
        for (ControllerPartCreator creator : creators) {
            parts.add(creator.createPov(povNumber, xAxis, yAxis));
        }
        return new SwitchingJoystickPart(parts, indexSelector);
    }

    @Override
    public JoystickPart createPov(int povNumber) {
        List<JoystickPart> parts = new ArrayList<>(creators.size());
        for (ControllerPartCreator creator : creators) {
            parts.add(creator.createPov(povNumber));
        }
        return new SwitchingJoystickPart(parts, indexSelector);
    }

    @Override
    public JoystickPart createPov(int xAxis, int yAxis) {
        List<JoystickPart> parts = new ArrayList<>(creators.size());
        for (ControllerPartCreator creator : creators) {
            parts.add(creator.createPov(xAxis, yAxis));
        }
        return new SwitchingJoystickPart(parts, indexSelector);
    }

    @Override
    public JoystickPart createJoystick(int xAxis, int yAxis) {
        List<JoystickPart> parts = new ArrayList<>(creators.size());
        for (ControllerPartCreator creator : creators) {
            parts.add(creator.createJoystick(xAxis, yAxis));
        }
        return new SwitchingJoystickPart(parts, indexSelector);
    }

    @Override
    public InputPart createFullAnalog(int axisCode) {
        List<InputPart> parts = new ArrayList<>(creators.size());
        for (ControllerPartCreator creator : creators) {
            parts.add(creator.createFullAnalog(axisCode));
        }
        return new SwitchingInputPart(AxisType.FULL_ANALOG, parts, indexSelector);
    }

    @Override
    public InputPart createAnalog(int axisCode) {
        List<InputPart> parts = new ArrayList<>(creators.size());
        for (ControllerPartCreator creator : creators) {
            parts.add(creator.createAnalog(axisCode));
        }
        return new SwitchingInputPart(AxisType.ANALOG, parts, indexSelector);
    }

    @Override
    public InputPart createFullAnalog(int axisCode, boolean isVertical) {
        List<InputPart> parts = new ArrayList<>(creators.size());
        for (ControllerPartCreator creator : creators) {
            parts.add(creator.createFullAnalog(axisCode, isVertical));
        }
        return new SwitchingInputPart(AxisType.FULL_ANALOG, parts, indexSelector);
    }

    @Override
    public InputPart createAnalog(int axisCode, boolean isVertical) {
        List<InputPart> parts = new ArrayList<>(creators.size());
        for (ControllerPartCreator creator : creators) {
            parts.add(creator.createAnalog(axisCode, isVertical));
        }
        return new SwitchingInputPart(AxisType.ANALOG, parts, indexSelector);
    }

    @Override
    public InputPart createAnalogTrigger(int axisCode) {
        List<InputPart> parts = new ArrayList<>(creators.size());
        for (ControllerPartCreator creator : creators) {
            parts.add(creator.createAnalogTrigger(axisCode));
        }
        return new SwitchingInputPart(AxisType.ANALOG, parts, indexSelector);
    }

    @Override
    public InputPart createTrigger(int digitalCode, int analogCode) {
        List<InputPart> parts = new ArrayList<>(creators.size());
        for (ControllerPartCreator creator : creators) {
            parts.add(creator.createTrigger(digitalCode, analogCode));
        }
        return new SwitchingInputPart(parts.get(0).getAxisType(), parts, indexSelector) {
            @Override
            public AxisType getAxisType() {
                return parts.get(indexSelector.getIndex()).getAxisType();
            }
        };
    }

    @Override
    public ControllerRumble createRumble() {
        return DisconnectedRumble.getInstance(); // TODO not a priority
    }

    @Override
    public boolean isConnected() {
        return creators.get(indexSelector.getIndex()).isConnected();
    }

    @Override
    public String getName() {
        return creators.get(indexSelector.getIndex()).getName();
    }
}
