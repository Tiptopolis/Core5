package com.uchump.prime.TEST.gScanner;

import com.badlogic.gdx.math.Vector3;
import com.uchump.prime._PRIME.C_O.VectorUtils;
import com.uchump.prime._PRIME.C_O.NIX.Transform;
import com.uchump.prime._PRIME.SYS.NMP._Entity;
import com.uchump.prime._RAUM.RAUM.GFX._BoundShape;
import com.uchump.prime._RAUM.RAUM.GFX.Prototype.Shape;
import com.uchump.prime._RAUM.RAUM.META._Environment;
import com.uchump.prime._RAUM.RAUM.WORLD.Prototype._Collider;

public class pEnt extends _Entity{

	public Transform transform;
	public _BoundShape shape;
	
	public pEnt(_Environment e, Vector3 at)
	{
		this.transform = new Transform(this);
		this.transform.SetLocalPosition(at);
		Vector3 u = VectorUtils.div(e.getUnit(), new Vector3(2,2,2));
		this.transform.SetLocalScale(u);
		e.Members.add(this);
	}
	
	public void draw()
	{
		this.shape.draw();
	}
}
