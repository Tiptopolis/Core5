package com.uchump.prime._PRIME.SYS.NIX;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;
import static com.uchump.prime._PRIME.N_M._N.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

import com.badlogic.gdx.files.FileHandle;
import com.uchump.prime._PRIME.C_O.NIX.Transform;
import com.uchump.prime._PRIME.N_M._N;
import com.uchump.prime._PRIME.N_M._N.Name;
import com.uchump.prime._PRIME.N_M._N.Node;
import com.uchump.prime._PRIME.N_M._N.Type;
import com.uchump.prime._PRIME.SYS.iApplet;
import com.uchump.prime._PRIME.SYS.iSystem;
import com.uchump.prime._PRIME.SYS.NMP._Object;
import com.uchump.prime._PRIME.UI._Events.aEventManager;
import com.uchump.prime._PRIME.UI._Events.Prototype.Message;
import com.uchump.prime._RAUM.RAUM.WORLD._World;

public class _Shell extends _Object implements iSystem {

	// DIAL M FOR METATRON lolol

	public aEventManager EventManager;

	public static _VTS VTS;
	public static _VFS VFS;
	public static _ECS ECS;

	public ArrayList<_Shell> Other = new ArrayList<_Shell>();	
	public ArrayList<iSystem> Systems = new ArrayList<iSystem>();
	protected HashMap<UpdateType, iSystem> Running = new HashMap<UpdateType, iSystem>();
	
	public static Type shell = new Type("Shell");
	public static Type type = new Type("Shell", _Shell.class, Type.Type);
	// VFS, VTS, & VFS all doubly link their entries
	// protected HashMap<_VFS, Lookup> Files; // everything in here will be
	// accessible as a File
	// protected HashMap<_ECS, Lookup> Entities; // everything in here will be
	// accessible as an Entity
	// protected HashMap<_VTS, Lookup> Types; // everything in here will be
	// accessible as a Type/Class

	protected enum UpdateType
	{
		Fixed,
		Dynamic,
		Reactive;
	}
	
	public _Shell() {
		super();
		this.Name = new Name("Shell");
		this.Node = shell;
		this.Class = shell;		
		this.Of=type;		
		this.Environment = this;
		this.Other.add(this);
		this.Connections = new HashMap<String, Node>();
		
	}

	public _Shell(String name) {
		this.Name = new Name(name.toUpperCase());
		this.Node = shell;
		this.Class = shell;		
		this.Of=type;
		this.Environment = this;
		this.Other.add(this);
		this.Connections = new HashMap<String, Node>();
	}

	public _Shell(String name, iSystem env) {
		this(name);
		this.Node = shell;		
		this.Class = shell;
		this.Of=type;
		this.Environment = env;
		this.Connections = new HashMap<String, Node>();
		// if(env.)
	}

	public static _Shell DomainShell() {
		_Shell n = new _Shell("Domain");
		return n;
	}

	public static _Shell DomainShell(String name) {
		_Shell n = new _Shell("Domain");
		n.Name = new Name(name);
		return n;
	}
	
	

	@Override
	public void update(int deltaTime) {
		//Log(this.Name + ":I" + deltaTime);
		// check for pending changes & then do them
		
		
		for(Entry<UpdateType, iSystem> S : this.Running.entrySet())
		{
			S.getValue().update(deltaTime);
		}
	}

	@Override
	public void update(float deltaTime) {
		//Log(this.Name + ":F" + deltaTime);
		for(Entry<UpdateType, iSystem> S : this.Running.entrySet())
		{
			S.getValue().update(deltaTime);
		}
	}

	public void launch(iApplet app)
	{
		
	}

	public void terminate()
	{
		this.Connections.clear();
		
	
		
	}
	
	////////////////
	///////////////

	@Override
	public boolean handle(Message m) {
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
	public void broadcast(java.lang.Object at) {
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

	

}
