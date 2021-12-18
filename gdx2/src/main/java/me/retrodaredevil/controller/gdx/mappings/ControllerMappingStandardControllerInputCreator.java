package me.retrodaredevil.controller.gdx.mappings;

import com.badlogic.gdx.controllers.ControllerMapping;
import me.retrodaredevil.controller.implementations.ControllerPartCreator;
import me.retrodaredevil.controller.implementations.StandardControllerInputCreator;
import me.retrodaredevil.controller.input.InputPart;
import me.retrodaredevil.controller.input.JoystickPart;

public class ControllerMappingStandardControllerInputCreator implements StandardControllerInputCreator {
    private final ControllerMapping controllerMapping;

    public ControllerMappingStandardControllerInputCreator(ControllerMapping controllerMapping) {
        this.controllerMapping = controllerMapping;
    }


    @Override public InputPart createStart(ControllerPartCreator controller) {
        return controller.createDigital(controllerMapping.buttonStart);
    }
    @Override public InputPart createSelect(ControllerPartCreator controller) {
        return controller.createDigital(controllerMapping.buttonBack);
    }

    @Override public InputPart createYButton(ControllerPartCreator controller) {
        return controller.createDigital(controllerMapping.buttonY);
    }
    @Override public InputPart createAButton(ControllerPartCreator controller) {
        return controller.createDigital(controllerMapping.buttonA);
    }
    @Override public InputPart createXButton(ControllerPartCreator controller) {
        return controller.createDigital(controllerMapping.buttonX);
    }
    @Override public InputPart createBButton(ControllerPartCreator controller) {
        return controller.createDigital(controllerMapping.buttonB);
    }

    @Override public InputPart createLeftBumper(ControllerPartCreator controller) {
        return controller.createDigital(controllerMapping.buttonL1);
    }
    @Override public InputPart createRightBumper(ControllerPartCreator controller) {
        return controller.createDigital(controllerMapping.buttonR1);
    }
    @Override public InputPart createLeftTrigger(ControllerPartCreator controller) { // varies
        return controller.createTrigger(controllerMapping.buttonL2, 4);
    }
    @Override public InputPart createRightTrigger(ControllerPartCreator controller) { // varies
        return controller.createTrigger(controllerMapping.buttonR2, 5);
    }

    @Override public InputPart createLeftStick(ControllerPartCreator controller) { //
        return controller.createDigital(controllerMapping.buttonLeftStick);
    }
    @Override public InputPart createRightStick(ControllerPartCreator controller) { //
        return controller.createDigital(controllerMapping.buttonRightStick);
    }

    @Override public JoystickPart createDPad(ControllerPartCreator controller) {
        return controller.createPov(0);
    }
    @Override public JoystickPart createLeftJoy(ControllerPartCreator controller) {
        return controller.createJoystick(controllerMapping.axisLeftX, controllerMapping.axisLeftY);
    }
    @Override public JoystickPart createRightJoy(ControllerPartCreator controller) {
        return controller.createJoystick(controllerMapping.axisRightX, controllerMapping.axisRightY);
    }

    @Override public Boolean getPhysicalLocationsSwapped() {
        return null;
    }
    @Override public Boolean getButtonNamesSwapped() {
        return null;
    }
}
