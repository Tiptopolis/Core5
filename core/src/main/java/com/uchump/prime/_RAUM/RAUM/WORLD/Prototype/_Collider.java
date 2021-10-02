package com.uchump.prime._RAUM.RAUM.WORLD.Prototype;

import com.badlogic.gdx.math.Vector2;
import com.uchump.prime._PRIME.C_O.NIX.Transform;
import com.uchump.prime._PRIME.SYS.A_I.aProperty;
import com.uchump.prime._PRIME.SYS.NMP._Component;
import com.uchump.prime._RAUM.RAUM.GFX._BoundShape;
import com.uchump.prime._RAUM.RAUM.META._Environment;
import com.uchump.prime._RAUM.RAUM.WORLD.UTIL.iSDF;

public abstract class _Collider extends _Component{

	public Transform root;
	public _Environment space;
	
	
	
	public _Collider(Transform t, _Environment e)
	{
		this.root = t;
		this.space = e;
	}
	
	
	
	public float dstFrom(Vector2 point)
	{
		return 0;
	}
	
	public float dstTo(Vector2 point)
	{
		return 0;
	}
}
