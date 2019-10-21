package me.retrodaredevil.controller.gdx;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.utils.Array;

/**
 * A {@link ControllerProvider} that can be used for testing and simple setups. Not recommended for use with multiple controllers.
 */
public class IndexedControllerProvider implements ControllerProvider {
    private final int index;

    public IndexedControllerProvider(int index) {
        this.index = index;
    }

    @Override
    public Controller getController() {
        Array<Controller> controllers = Controllers.getControllers();
        if(controllers.size >= index){
            return null;
        }
        return controllers.get(index);
    }

    @Override
    public boolean isConnected() {
        return getController() != null;
    }

    @Override
    public String getName() {
        Controller controller = getController();
        if(controller == null){
            return "";
        }
        return controller.getName();
    }
}
