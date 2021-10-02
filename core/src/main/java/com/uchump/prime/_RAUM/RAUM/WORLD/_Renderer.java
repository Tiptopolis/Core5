package com.uchump.prime._RAUM.RAUM.WORLD;

import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.uchump.prime._PRIME.C_O.NIX.OroborosList;
import com.uchump.prime._PRIME.C_O.NIX.Transform;
import com.uchump.prime._PRIME.UI.aViewContext;
import com.uchump.prime._PRIME.UI._Camera.A_I.aCameraController;
import com.uchump.prime._PRIME.UI._Events.iEventProcessor;
import com.uchump.prime._PRIME.UI._Events.Prototype.Message;
import com.uchump.prime._PRIME.UI._GFX.DrawBuffer;
import com.uchump.prime._PRIME.UI._GFX.FrameBufferManager;
import com.uchump.prime._PRIME.UI._GFX.Drawable.iDrawable;

public class _Renderer implements iEventProcessor{

	//public OroborosList<iDrawable> toDraw = new OroborosList<iDrawable>(1,1280,1);
	public ArrayList<iDrawable> toDraw = new ArrayList<iDrawable>();
	
	public Batch batch;	
	
	public aViewContext view;
	public aCameraController viewer;
	public FrameBufferManager manager;
	public DrawBuffer mainBuffer;
	
	
	public _Renderer()
	{
		
	}
	
	public void setBatch(Batch batch)
	{
		this.batch = batch;
	}
	

	
	public void render(float deltaTime)
	{
		if(this.batch == null)
			this.batch = Sketcher.getBatch();
			
		for(iDrawable D : this.toDraw)
		{
			this.batch.begin();
			D.draw();
			this.batch.end();
		}
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
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handle(Message m) {
		// TODO Auto-generated method stub
		return false;
	}
}
