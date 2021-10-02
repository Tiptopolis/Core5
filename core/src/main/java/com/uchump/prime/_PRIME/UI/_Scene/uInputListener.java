package com.uchump.prime._PRIME.UI._Scene;

import java.util.EventListener;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;

import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.uchump.prime._PRIME.C_O.NIX.Transform;
import com.uchump.prime._PRIME.SYS.A_I.iAgent;
import com.uchump.prime._PRIME.UI._Events.iEventReciever;


public class uInputListener extends ClickListener implements iEventReciever, iAgent{

	

	@Override
	public void addEventListener(String type, org.w3c.dom.events.EventListener listener, boolean useCapture) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEventListener(String type, org.w3c.dom.events.EventListener listener, boolean useCapture) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean dispatchEvent(Event evt) throws EventException {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public Transform getTransform() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void transformUpdated() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toLog() {
		// TODO Auto-generated method stub
		return null;
	}

}
