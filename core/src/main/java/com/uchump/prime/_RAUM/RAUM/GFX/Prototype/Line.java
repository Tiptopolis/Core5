package com.uchump.prime._RAUM.RAUM.GFX.Prototype;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.uchump.prime._PRIME.C_O.Geom;
import com.uchump.prime._PRIME.C_O.Maths;
import com.uchump.prime._PRIME.C_O.VectorUtils;
import com.uchump.prime._PRIME.SYS.A_I.aProperty;
import com.uchump.prime._RAUM.RAUM.GFX._BoundShape;

public class Line extends _BoundShape {

	// add in a mid-factor for line to poly transmutation

	public Line(Vector3 origin, Vector3 to) {
		super();
		Vector3 dir = VectorUtils.dir(origin.cpy(), to.cpy());
		Vector3 dst = to.cpy().sub(origin.cpy());
		this.Transform.SetLocalPosition(origin);
		this.Transform.SetLocalScale(dst);
		this.Transform.SetLocalRotation(VectorUtils.upcast(dir));
		this.vertexNum = 2;

		this.shape = Geom.lineTo(origin, to, (int) ((dst.len() / 3)*2));

	}

	public Line(Vector3 origin, Vector3 to, int div) {
		super();
		Vector3 dir = VectorUtils.dir(origin.cpy(), to.cpy());
		Vector3 dst = to.cpy().sub(origin.cpy());
		this.Transform.SetLocalPosition(origin);
		this.Transform.SetLocalScale(dst);
		this.Transform.SetLocalRotation(VectorUtils.upcast(dir));
		this.vertexNum = 2;

		this.shape = Geom.lineTo(origin, to, (int) ((dst.len() / 3)*2));
	}

	@Override
	public void draw() {
		super.draw();

		if (this.debug) {
			int s = this.shape.size() - 1;
			Vector3 mid = this.getMidpoint();
			Vector3 origin = this.shape.get(0);
			Vector3 end = this.shape.get(s);
			Vector3 dir = VectorUtils.dir(origin.cpy(), end.cpy());
			Vector3 dst = end.cpy().sub(origin.cpy());

			Vector2 from = VectorUtils.downcast(origin);
			Vector2 to = VectorUtils.downcast(end);

			Vector3 rN = mid.cpy().add(dir.cpy().crs(0, 0, 1).nor().scl(1 + this.vertexSize));
			Vector3 lN = mid.cpy().add(dir.cpy().crs(0, 0, -1).nor().scl(1 + this.vertexSize));

			Sketcher.setColor(0.75f, 0.75f, 0.75f, 0.15f);
			Sketcher.Drawer.line(from, to);
			Sketcher.setColor(0.75f, 0.75f, 0.75f, 0.25f);
			Sketcher.Drawer.circle(mid.x, mid.y, this.vertexSize);
			Sketcher.Drawer.circle(from.x, from.y, this.vertexSize);
			Sketcher.Drawer.circle(to.x, to.y, this.vertexSize);

			Sketcher.setColor(0.75f, 0.25f, 0.25f, 0.25f);
			Sketcher.Drawer.circle(rN.x, rN.y, this.vertexSize);
			Sketcher.setColor(0.25f, 0.75f, 0.25f, 0.25f);
			Sketcher.Drawer.circle(lN.x, lN.y, this.vertexSize);

			Sketcher.setColor(0.75f, 0.55f, 0.55f, 0.15f);
			Sketcher.Drawer.circle(from.x, to.y, this.vertexSize);
			Sketcher.Drawer.circle(to.x, from.y, this.vertexSize);
		}
	}

	@Override
	public Polygon toPolygon() {
		Vector3 e = this.getEnd();
		Vector3 o = this.getOrigin();
		Vector3 dir = VectorUtils.dir(o.cpy(), e.cpy());
		Vector3 dst = e.cpy().sub(o.cpy());

		Vector3 rN = e.cpy().add(dir.cpy().crs(0, 0, 1).nor().scl(1 + this.vertexSize));
		Vector3 lN = e.cpy().add(dir.cpy().crs(0, 0, -1).nor().scl(1 + this.vertexSize));

		ArrayList<Vector2> tmpVerts = new ArrayList<Vector2>();
		tmpVerts.add(VectorUtils.downcast(o.cpy()));
		tmpVerts.add(VectorUtils.downcast(rN));
		tmpVerts.add(VectorUtils.downcast(lN));

		Polygon result = new Polygon(VectorUtils.disassembleVects(tmpVerts.toArray()));
		if (this.Transform != null) {
			Vector3 scl = this.Transform.GetLocalScale();
			result.setOrigin(this.Transform.GetLocalPosition().x, this.Transform.GetLocalPosition().y);
			Quaternion rot = this.Transform.GetLocalRotation();
			result.setRotation((float) Math.atan2(rot.x, rot.y));
		}

		return result;

	}

	public Vector3 getOrigin() {
		return this.shape.get(0);
	}

	public Vector3 getEnd() {
		return this.shape.get(this.shape.size() - 1);
	}

	public Vector3 getMidpoint() {
		int ind = this.shape.size();
		int mid = 1;
		if (Maths.isEven(ind)) {
			mid = ind / 2;
			return this.shape.get(mid);
		} else {
			mid = ((ind - 1) / 2);			
			return this.shape.get(mid);
		}
	}

	public float len() {
		return VectorUtils.dst(this.getOrigin(), this.getEnd()).len();
	}

	@Override
	public String toString() {
		return super.toString() + " " + this.getOrigin() + "->" + this.getEnd();
	}

	public Vector3 intersectAt(Line other) {
		return intersectAt(other, true);
	}

	public Vector3 intersectAt(Line other, boolean toFrom) {

		for (int i = 0; i < this.shape.size(); i++) {
			Vector3 v = this.shape.get(i);
			for (Vector3 k : other.shape) {

				if (v.epsilonEquals(k, other.vertexSize + 1f)) {
					if (toFrom)
						return k;
					else
						return v;
				}
			}
		}
		return null;
	}

	public Array<Vector3> intersectsAt(Line other) {
		return intersectsAt(other, true);
	}

	public Array<Vector3> intersectsAt(Line other, boolean toFrom) {
		Array<Vector3> res = new Array<Vector3>(true, 0, Vector3.class);

		for (int i = 0; i < this.shape.size(); i++) {
			Vector3 v = this.shape.get(i);
			for (Vector3 k : other.shape) {

				if (v.epsilonEquals(k, other.vertexSize + 1f)) {

					res.add(k);
					res.add(v);
				}
			}
		}
		if (res.isEmpty())
			return null;
		else
			return res;
	}

	//////////

}
