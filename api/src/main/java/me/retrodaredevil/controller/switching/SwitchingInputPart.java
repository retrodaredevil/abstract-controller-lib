package me.retrodaredevil.controller.switching;

import me.retrodaredevil.controller.input.AxisType;
import me.retrodaredevil.controller.input.InputPart;
import me.retrodaredevil.controller.input.implementations.SimpleInputPart;

import java.util.List;

public class SwitchingInputPart extends SimpleInputPart {
    private final List<InputPart> inputParts;
    private final SwitchingIndexSelector indexSelector;

    public SwitchingInputPart(AxisType axisType, List<InputPart> inputParts, SwitchingIndexSelector indexSelector) {
        super(axisType);
        this.inputParts = inputParts;
        this.indexSelector = indexSelector;
        partUpdater.addPartsAssertNonePresent(inputParts);
    }

    @Override
    public boolean isConnected() {
        return inputParts.get(indexSelector.getIndex()).isConnected();
    }

    @Override
    public double getPosition() {
        return inputParts.get(indexSelector.getIndex()).getPosition();
    }

    @Override
    public boolean isDown() {
        return inputParts.get(indexSelector.getIndex()).isDown();
    }

    @Override
    public boolean isJustPressed() {
        return inputParts.get(indexSelector.getIndex()).isJustPressed();
    }

    @Override
    public boolean isJustReleased() {
        return inputParts.get(indexSelector.getIndex()).isJustReleased();
    }
}
