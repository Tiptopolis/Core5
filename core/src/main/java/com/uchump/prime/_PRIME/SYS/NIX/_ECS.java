package com.uchump.prime._PRIME.SYS.NIX;

import java.util.ArrayList;

import com.uchump.prime._PRIME.SYS.iSystem;
import com.uchump.prime._PRIME.SYS.A_I.aProperty;
import com.uchump.prime._PRIME.SYS.NMP._Entity;
import com.uchump.prime._RAUM.RAUM.META.NIX._Thing;

public class _ECS extends _Shell implements iSystem {

	// public _Entity ent;

	public static ArrayList<iSystem> Running = new ArrayList<iSystem>();

	public static EntityFactory Spawner = new EntityFactory();

	public static class EntityFactory {
		// link _Archetype <?><[TYPE]> to registered properties
		public _Entity iEnt(_Thing from) {
			_Entity newEnt = new _Entity(from.Name());
			for (Node n : from.Properties) {
				newEnt.addProperty(((aProperty)n).cpy());
			}

			return newEnt;
		}

	}
	
	public void dispose()
	{
		Running.clear();
		Running = null;
		Spawner = null;
	}

}
