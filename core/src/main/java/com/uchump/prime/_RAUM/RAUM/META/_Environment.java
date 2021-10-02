package com.uchump.prime._RAUM.RAUM.META;

import java.util.ArrayList;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;

import com.badlogic.gdx.math.Vector3;
import com.uchump.prime._PRIME.C_O.NIX.Transform;
import com.uchump.prime._PRIME.N_M.A_I.aVector;
import com.uchump.prime._PRIME.SYS.A_I.iMonad;
import com.uchump.prime._PRIME.SYS.NIX._Shell;
import com.uchump.prime._PRIME.SYS.NMP._Entity;
import com.uchump.prime._PRIME.UI.iSpace;
import com.uchump.prime._PRIME.UI._Events.iEventProcessor;
import com.uchump.prime._PRIME.UI._Events.Prototype.Message;
import com.uchump.prime._PRIME.UI._Scene.NIX._Stage;

public class _Environment extends _Shell implements iSpace {

	// an 'interaction layer', as it were...
	public static _Environment ANY;
	public aVector Dimension;
	public ArrayList<_Entity> Members;

	public _Environment(String name) {
		super(name);
		this.Members = new ArrayList<_Entity>();

	}

	@Override
	public Vector3 getSize() {

		return new Vector3(1, 1, 1);
	}

	@Override
	public Vector3 getUnit() {

		return new Vector3(1, 1, 1);
	}

	@Override
	public boolean isEmpty() {
		return this.Members.isEmpty();
	}

}
