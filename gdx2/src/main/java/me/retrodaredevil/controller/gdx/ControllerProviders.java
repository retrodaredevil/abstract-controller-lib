package me.retrodaredevil.controller.gdx;

import com.badlogic.gdx.controllers.Controller;

public final class ControllerProviders {
    private ControllerProviders(){ throw new UnsupportedOperationException(); }

    public static ControllerProvider wrap(final Controller controller){
        return new ControllerProvider() {
            @Override
            public Controller getController() {
                if(isConnected()){
                    return controller;
                }
                return null;
            }

            @Override
            public boolean isConnected() {
                return controller.isConnected();
            }

            @Override
            public String getName() {
                Controller controller = getController();
                if(controller == null){
                    return "";
                }
                return controller.getName();
            }
        };
    }
}
