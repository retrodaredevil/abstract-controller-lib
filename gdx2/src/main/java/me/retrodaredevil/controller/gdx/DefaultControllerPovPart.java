package me.retrodaredevil.controller.gdx;

import com.badlogic.gdx.controllers.Controller;
import me.retrodaredevil.controller.input.InputPart;
import me.retrodaredevil.controller.input.JoystickType;
import me.retrodaredevil.controller.input.implementations.JoystickAxisFollowerPart;
import me.retrodaredevil.controller.input.implementations.SimpleJoystickPart;

public class DefaultControllerPovPart extends SimpleJoystickPart {
    private static final double SQRT2 = Math.sqrt(2.0);

    private final InputPart xAxis = new JoystickAxisFollowerPart(this, partUpdater, false);
    private final InputPart yAxis = new JoystickAxisFollowerPart(this, partUpdater, true);

    private final ControllerProvider provider;

    private double x, y;

    public DefaultControllerPovPart(ControllerProvider provider){
        super(new JoystickType(false, false, false, true), false,false);
        this.provider = provider;
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
    protected void onUpdate() {
        super.onUpdate();
        Controller controller = provider.getController();
        x = 0;
        y = 0;
        if(controller != null) {
            if (controller.getMapping().buttonDpadLeft > 0) {
                x -= 1;
            }
            if (controller.getMapping().buttonDpadRight > 0) {
                x += 1;
            }
            if (controller.getMapping().buttonDpadDown > 0) {
                y -= 1;
            }
            if (controller.getMapping().buttonDpadUp > 0) {
                y += 1;
            }
            if (x != 0 && y != 0) {
                x /= SQRT2;
                y /= SQRT2;
            }
        }

    }

    @Override
    protected double calculateMagnitude(double x, double y) {
        return isDeadzone() ? 0 : 1;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getCorrectX() {
        return x;
    }

    @Override
    public double getCorrectY() {
        return y;
    }

    @Override
    public boolean isXDeadzone() {
        return x == 0;
    }

    @Override
    public boolean isYDeadzone() {
        return y == 0;
    }

    @Override
    public boolean isConnected() {
        return provider.isConnected();
    }
}
