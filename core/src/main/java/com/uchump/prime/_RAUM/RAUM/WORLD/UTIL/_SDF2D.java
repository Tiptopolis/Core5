package com.uchump.prime._RAUM.RAUM.WORLD.UTIL;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime._PRIME.C_O.Geom;
import com.uchump.prime._PRIME.C_O.VectorUtils;
import com.uchump.prime._PRIME.C_O.NIX.Transform;
import com.uchump.prime._PRIME.N_M.UTIL.V_Operator;
import com.uchump.prime._RAUM.RAUM.GFX._BoundShape;

public class _SDF2D extends _BoundShape implements iSDF {

	// float distance(Number...values);
	// _SDF2D_2 will use aVector...

	public static float min(Vector2 of) {
		return Math.min(of.x, of.y);
	}

	public static float max(Vector2 of) {
		return Math.max(of.x, of.y);
	}

	public static Vector2 min(Vector2 a, Vector2 b) {
		float x = Math.min(a.x, b.x);
		float y = Math.min(a.y, b.y);
		Vector2 r = new Vector2(x, y);
		return r;
	}

	public static Vector3 min(Vector3 a, Vector3 b) {
		float x = Math.min(a.x, b.x);
		float y = Math.min(a.y, b.y);
		float z = Math.min(a.z, b.z);
		Vector3 r = new Vector3(x, y, z);
		return r;
	}

	public static Vector2 max(Vector2 a, Vector2 b) {
		float x = Math.max(a.x, b.x);
		float y = Math.max(a.y, b.y);
		Vector2 r = new Vector2(x, y);
		return r;
	}

	public static Vector3 max(Vector3 a, Vector3 b) {
		float x = Math.max(a.x, b.x);
		float y = Math.max(a.y, b.y);
		float z = Math.max(a.z, b.z);
		Vector3 r = new Vector3(x, y, z);
		return r;
		// return V_Operator.max(a, b);
	}

	public static float length(Vector2 v) {
		return v.len();
	}

	public static float length(Vector3 v) {
		return v.len();
	}

	public static Vector3 abs(Vector3 v) {
		float x = Math.abs(v.x);
		float y = Math.abs(v.y);
		float z = Math.abs(v.z);
		return new Vector3(x, y, z);
	}
	
	public static Vector2 abs(Vector2 v) {
		float x = Math.abs(v.x);
		float y = Math.abs(v.y);
		
		return new Vector2(x, y);
	}

	public static class _Circle extends _SDF2D {

		public _Circle(Vector2 at, Vector2 toPoint, float radius) {
			super();
			Vector3 pos = new Vector3(at.x, at.y, 0);
			Vector3 s = new Vector3(radius, radius, radius);
			this.Transform = new Transform();
			this.Transform.SetLocalPosition(pos);
			this.Transform.SetLocalScale(s);

		}

		/////////////////////////

		public static float distance(Vector2 point, Vector2 position, float r) {

			// Vector3 myPos = VectorUtils.upcast(position.cpy());
			// Vector3 oPos = VectorUtils.upcast(point.cpy());
			// return VectorUtils.dst(myPos, oPos).len() - r;
			return length(position.sub(point)) - r;

		}

		public static float distance(Vector3 point, Vector3 position, float r) {

			return VectorUtils.dst(position, point).len() - r;
		}

	}

	public static class _Box extends _SDF2D {
		public _Box(Vector2 at, Vector2 toPoint, float size) {
			super();
			Vector3 pos = new Vector3(at.x, at.y, 0);
			Vector3 s = new Vector3(size, size, size);
			this.Transform = new Transform();
			this.Transform.SetLocalPosition(pos);
			this.Transform.SetLocalScale(s);

		}

		public _Box(Vector2 at, Vector2 toPoint, Vector2 size) {
			super();
			Vector3 pos = new Vector3(at.x, at.y, 0);
			Vector3 s = new Vector3(size.x, size.y, 1);
			this.Transform = new Transform();
			this.Transform.SetLocalPosition(pos);
			this.Transform.SetLocalScale(s);

		}

		public static float distance(Vector2 point, Vector2 a, Vector2 b, Vector2 size) {

			Vector2 dst = b.cpy().sub(a.cpy());

			float l = length(b.cpy().sub(a.cpy()));
			Vector2 d = VectorUtils.div(dst, new Vector2(l, l));

			Log("** " + d + " :: " + l);

			return 0;
		}

		public static float distanceX(Vector2 point, Vector2 position, Vector2 size) {
			float offX = Math.abs(point.x - position.x) - size.x;
			float offY = Math.abs(point.y - position.y) - size.y;
			Vector2 offset = new Vector2(offX, offY);
			float uDst = max(offset, new Vector2(0, 0)).len(); // unsigned dst
			float iDst = max(min(offset, new Vector2(0, 0)));// inside dst
			return uDst + iDst;

		}

	}

}
