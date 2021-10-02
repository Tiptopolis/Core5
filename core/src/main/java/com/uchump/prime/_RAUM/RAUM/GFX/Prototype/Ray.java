package com.uchump.prime._RAUM.RAUM.GFX.Prototype;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.uchump.prime._PRIME.C_O.Geom;
import com.uchump.prime._PRIME.C_O.VectorUtils;
import com.uchump.prime._PRIME.SYS.A_I.aProperty;
import com.uchump.prime._RAUM.RAUM.GFX._BoundShape;

public class Ray extends _BoundShape {

	Vector3 end = new Vector3(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
	boolean _2D = true;
	public Array<Vector3> collisions = new Array<Vector3>();

	public Ray(Vector3 origin, Vector3 direction) {
		super();
		this.Transform.SetLocalPosition(origin);
		this.Transform.SetLocalRotation(VectorUtils.upcast(direction));
		this.shape = Geom.generatePolygon(origin, new Vector3(Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE),
				direction, 1);
	}

	@Override
	public void draw() {

		Vector3 o = this.Transform.GetLocalPosition().cpy();
		Vector3 d = VectorUtils.downcast(this.Transform.GetLocalRotation());
		Vector3 s = new Vector3(Width * 2, Width * 2, Width * 2);

		if (_2D) {
			o.z = 0;
			d.z = 0;
			s.z = 0;
		}

		this.shape = Geom.generatePolygon(o, s, d, 1);
		super.draw();

	}

	public Vector3 getOrigin() {
		// return this.shape.get(0);
		return this.Transform.GetLocalPosition();
	}

	public Vector3 getEnd() {
		return this.shape.get(this.shape.size() - 1);
	}

	
	public Vector3 hit(_BoundShape b)
	{
		return this.hit(b,true);
	}
	public Vector3 hit(_BoundShape b, boolean toFrom) {
		// Intersector i;
		// can either convert this into a polygon by adding either [midpoint] or
		// {endpoint normals}({normals of endpoint})lol
		this.end = new Vector3(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);

		Line L = new Line(this.getOrigin(), this.getEnd());
		Vector3 hit = b.intersectLine(L, toFrom);

		if (hit != null) {
			this.end = hit;
			return hit;
		}
		return null;
	}

	public Array<Vector3> hits(_BoundShape b) {
		this.end = new Vector3(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);

		Line L = new Line(this.getOrigin(), this.getEnd());

		return b.intersectsLine(L);
	}
	
	

	@Override
	public String toString() {
		return super.toString() + " " + this.getOrigin() + "->" + this.getEnd();
	}
}
