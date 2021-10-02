package com.uchump.prime.TEST.sTest1.tstEnv;

import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.uchump.prime._PRIME.C_O.VectorUtils;
import com.uchump.prime._PRIME.C_O.NIX.Transform;
import com.uchump.prime._PRIME.SYS.NMP._Entity;
import com.uchump.prime._RAUM.RAUM.GFX._BoundShape;
import com.uchump.prime._RAUM.RAUM.META._Environment;

public class pEnt2 extends _Entity {

	public Transform transform;	
	public _BoundShape shape;

	public pEnt2(_Environment e, Vector3 at) {
		this.transform = new Transform(this);
		this.transform.SetLocalPosition(at);
		Vector3 u = VectorUtils.div(e.getUnit(), new Vector3(2, 2, 2));
		this.transform.SetLocalScale(u);
		
		e.Members.add(this);
	}

	public void draw() {
			this.shape.draw();
	}
	
	
	public Vector3 getLocalPosition()
	{
		return this.transform.GetLocalPosition();
	}
	
	public void setLocalPosition(Vector3 position)
	{
		this.transform.SetLocalPosition(position);
					
			this.shape.rebind(this.transform);
		
	}
	
	public Quaternion getLocalRotation()
	{
		return this.transform.GetLocalRotation();
	}
	
	public void setLocalRotation(Vector3 rotation)
	{
		this.transform.SetLocalRotation(VectorUtils.upcast(rotation));
	}
	
	
	public Vector3 getLocalScale()
	{
		return this.transform.GetLocalScale();
	}
	
	public void setLocalScale(Vector3 scale)
	{
		this.transform.SetLocalScale(scale);
	}
	
	

}
