package com.uchump.prime._METATRON.SYS;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;
import java.util.Collection;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;

import com.badlogic.gdx.InputProcessor;
import com.uchump.prime._PRIME.C_O.NIX.Transform;
import com.uchump.prime._PRIME.UI._Events.iEventProcessor;
import com.uchump.prime._PRIME.UI._Events.Prototype.Message;


public class mConsoleLogger implements iEventProcessor{

	public boolean active = true;
	public static mConsoleLogger DefaultLogger;
	private static Collection<String> pending = new ArrayList<String>();
	private static Collection<String> toLog = new ArrayList<String>();
	
	InputProcessor owner;
	public mConsoleLogger(InputProcessor owner)
	{
		DefaultLogger = this;
		this.owner = owner;
		
	}
	
	public static void logOut(String log)
	{
		
		Log(log);
	}
	
	public static void logOut()
	{
		for(String p : pending)
		{
			toLog.add(p);
		}
		for(String s : toLog)
		{
			Log(s);			
		}
		toLog.clear();
	}
	
	public static void toLog(String to)
	{
		
		
		pending.add(to);
			//scan toLog list for Commands
		
	}
	
	public static void toLog(Collection<String> to)
	{
		for(String s : to)
		{
			pending.add(s);
		}
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


	
}
