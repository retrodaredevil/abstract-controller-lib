package me.retrodaredevil.controller.gdx;

import com.badlogic.gdx.controllers.Controller;

public interface ControllerProvider {
    Controller getController();
    boolean isConnected();
    String getName();

}
