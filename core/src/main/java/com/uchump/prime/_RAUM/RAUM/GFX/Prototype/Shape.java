package com.uchump.prime._RAUM.RAUM.GFX.Prototype;

import java.util.HashMap;
import java.util.LinkedHashMap;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.uchump.prime._PRIME.C_O.NIX.BoundShape;
import com.uchump.prime._RAUM.RAUM.GFX._BoundShape;

public class Shape extends _BoundShape{
	BoundShape bs;
	
	
	public LinkedHashMap<Line, Boolean> outter;
	public LinkedHashMap<Line, Boolean> inner;
	public LinkedHashMap<_BoundShape, Boolean> sectors;

	public Shape()
	{
		super();
	}
	
}
