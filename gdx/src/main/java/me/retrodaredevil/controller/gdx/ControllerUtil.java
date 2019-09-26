package me.retrodaredevil.controller.gdx;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;

public final class ControllerUtil {
	private ControllerUtil(){ throw new UnsupportedOperationException(); }
	
	public static boolean isControllerConnected(Controller controller){
		return Controllers.getControllers().contains(controller, true);
	}
}
