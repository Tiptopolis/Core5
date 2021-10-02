package com.uchump.prime._PRIME.SYS;

import com.badlogic.gdx.Files;

import com.uchump.prime._PRIME.N_M._N;
import com.uchump.prime._PRIME.N_M.UTIL.iStatique;
import com.uchump.prime._PRIME.UI._Events.iEventProcessor;

public interface iSystem extends iStatique, iEventProcessor {
	//Stores mapped references to VirtualFiles, VirtualTypes, and ObjectiveValues (JSON_Value)
	// public static Node UNDEFINED = new Node(new Lookup(), "[?]", Object.class);

	public void update(float deltaTime);

	public void update(int deltaTime);

	public void terminate();
	
}
