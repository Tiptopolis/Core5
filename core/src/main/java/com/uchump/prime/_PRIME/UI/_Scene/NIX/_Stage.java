package com.uchump.prime._PRIME.UI._Scene.NIX;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.uchump.prime._PRIME.C_O.NIX.Transform;
import com.uchump.prime._PRIME.N_M._N.Node;
import com.uchump.prime._PRIME.SYS.A_I.iMonad;
import com.uchump.prime._PRIME.SYS.Prototype.Tree;
import com.uchump.prime._PRIME.UI.iSpace;
import com.uchump.prime._PRIME.UI._Camera.uCameraController;
import com.uchump.prime._PRIME.UI._Events.iEventProcessor;
import com.uchump.prime._PRIME.UI._Events.Prototype.Message;

public class _Stage extends Stage implements iSpace, iEventProcessor, iMonad{

	public OrthographicCamera StageCamera;
	public uCameraController StageCam;
	public Tree<Node<Actor>> SceneGraph;
	
	@Override
	public void addEventListener(String type, EventListener listener, boolean useCapture) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEventListener(String type, EventListener listener, boolean useCapture) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean dispatchEvent(Event evt) throws EventException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void broadcast() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void broadcast(Object at) {
		// TODO Auto-generated method stub
		
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

	@Override
	public boolean handle(Message m) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vector3 getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector3 getUnit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

}
