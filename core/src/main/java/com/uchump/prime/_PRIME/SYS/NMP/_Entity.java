package com.uchump.prime._PRIME.SYS.NMP;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;


import com.uchump.prime._PRIME.N_M._N;
import com.uchump.prime._PRIME.N_M._N.Name;
import com.uchump.prime._PRIME.N_M._N.Node;
import com.uchump.prime._PRIME.N_M._N.Type;
import com.uchump.prime._PRIME.SYS.A_I.aProperty;
import com.uchump.prime._PRIME.SYS.NMP._Object;
import com.uchump.prime._PRIME.SYS.Prototype.LookupTable;
import com.uchump.prime._RAUM.RAUM.META._Environment;

public class _Entity extends _Object implements EventListener{


	public int ID;
	public static Type Entity = new Type("Entity", _Entity.class);	
	public static Type EntityType = new Type("Entity", _Entity.class, Type.class);	
	
	
	
	
	public HashMap<String, aProperty> Components;
	
	public HashMap<_Environment, Integer> of;
	public HashMap<Integer, _Environment> in;
	
	public LookupTable<Node<EventListener>> Sensors;
	
	public _Entity() {
		super();
		this.Node = Entity;
		if(_Environment.ANY==null)
		{
			_Environment.ANY = new _Environment("Any");
		}
		this.of = new HashMap<_Environment, Integer>();
		this.in = new HashMap<Integer,_Environment>();
	}

	public _Entity(String name) {
		super(name);
		this.Node = Entity;
		this.Name= new Name(name);

	}

	public _Entity(_N n) {
		super(n.Name());
		this.Node = Entity;
		this.Name= new Name(n.toString());
	}

	//////////////////////
	@Override
	public void handleEvent(Event evt) {
		// TODO Auto-generated method stub
		
	}
	
	

	@Override
	public String toString() {
		return this.Name() + "_" + this.Node+ " @" + this.Class().getSimpleName() + "[" + this.Type() + "]";
		// REFERENCE NODE INDEX SYMBOL
	}

	@Override
	public String toLog()
	{
		return super.toLog();
	}



}
