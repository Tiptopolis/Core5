package com.uchump.prime._RAUM.RAUM.MAP;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.uchump.prime._PRIME.C_O.NIX.Rect;
import com.uchump.prime._PRIME.C_O.NIX.Transform;
import com.uchump.prime._PRIME.N_M._N.Node;
import com.uchump.prime._PRIME.N_M._N.Type;
import com.uchump.prime._PRIME.SYS.NMP._Entity;
import com.uchump.prime._PRIME.SYS.Prototype.iGraph;
import com.uchump.prime._PRIME.UI.iSpace;
import com.uchump.prime._PRIME.UI._GFX.Drawable.iDrawable;
import com.uchump.prime._PRIME.UI._Scene.NIX._Stage;
import com.uchump.prime._RAUM.RAUM.META.NIX._Thing;
import com.uchump.prime._RAUM.RAUM.WORLD._World;
import com.uchump.prime._RAUM.RAUM.META.NIX._Archetype;

public class _Map extends _Layer implements iSpace, iDrawable {

	_World Of;
	public _Stage Stage;

	
	
	// protected _Layer base; build out some layer templates

	public _Map() {
		this("Map", 1, 1);
	}

	public _Map(int width, int height) {
		this("Map", width, height);

	}

	public _Map(String name, int width, int height) {
		super(name, width, height);

		this.Node = new Type("Map", _Map.class);

	}

	public void prep() {
		// override me
	}

	public _Map gen() {
		// override me
		return this;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public Vector3 getUnit() {
		return this.CellSize;
	}

	@Override
	public Vector3 getSize() {

		return this.Size;
	}



	@Override
	public boolean isEmpty() {

		return this.Cells.isEmpty();
	}

	@Override
	public String toString() {
		return this.Name() + "_" + this.Node + " @" + this.Class().getSimpleName() + "[" + this.Type() + "]";
		// REFERENCE NODE INDEX SYMBOL
	}



}
