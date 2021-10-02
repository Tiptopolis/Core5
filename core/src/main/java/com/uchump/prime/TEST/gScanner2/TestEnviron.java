package com.uchump.prime.TEST.gScanner2;

import static com.uchump.prime._METATRON.Metatron.CAMERA;
import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.uchump.prime.uChumpEngine;
import com.uchump.prime.TEST.gScanner.pEnt;
import com.uchump.prime._PRIME.C_O.RNG;
import com.uchump.prime._PRIME.C_O.VectorUtils;
import com.uchump.prime._PRIME.C_O.NIX.Rect;
import com.uchump.prime._PRIME.N_M.Prototype.uVector;
import com.uchump.prime._PRIME.SYS.NMP._Entity;
import com.uchump.prime._PRIME.UI._Camera.OrthoController;
import com.uchump.prime._RAUM.RAUM.GFX._BoundShape;
import com.uchump.prime._RAUM.RAUM.GFX.Prototype.Line;
import com.uchump.prime._RAUM.RAUM.GFX.Prototype.Ray;
import com.uchump.prime._RAUM.RAUM.META._Environment;
import com.uchump.prime._RAUM.RAUM.WORLD.Prototype._Collider;

public class TestEnviron extends _Environment {

	public OrthoController observer;
	public Vector3 dir;
	public Vector3 focus;

	public Array<Ray> rays;
	public Array<_Collider> colliders;
	public ArrayList<pEnt2> toDraw = new ArrayList<pEnt2>();

	public Vector3 size;

	pEnt2 cursorShape;
	pEnt2 cameraOrigin;
	pEnt2 mapOrigin;

	public TestEnviron(Vector3 size) {
		super("s-minTest");
		this.size = size;
		this.Dimension = new uVector(size.x, size.y, size.z);
		this.observer = uChumpEngine.METATRON.CAMERA;
		this.gen();
	}

	public void gen() {

		Vector3 u = this.getUnit();
		this.mapOrigin = new pEnt2(this, new Vector3(0, 0, 0));
		_BoundShape mo = _BoundShape.bindShape(mapOrigin.transform, Color.RED, 4, false);
		mo.Transform.SetLocalScale(u);
		this.mapOrigin.shape = (mo);

		this.cursorShape = new pEnt2(this, new Vector3(0, 0, 0));
		mo = _BoundShape.bindShape(mapOrigin.transform, Color.BLUE, 6, false);
		mo.Transform.SetLocalScale(u);
		mo.Transform.SetParent(this.cursorShape.transform);
		this.cursorShape.shape = (mo);

		this.cameraOrigin = new pEnt2(this, new Vector3(0, 0, 0));
		mo = _BoundShape.bindShape(mapOrigin.transform, Color.GREEN, 12, false);
		mo.Transform.SetLocalScale(u);
		mo.Transform.SetParent(this.cameraOrigin.transform);
		this.cameraOrigin.shape = (mo);

	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);

		Vector3 curAt = this.observer.Camera.camera.unproject(new Vector3(MouseX, MouseY, 0));
		Vector3 camAt = this.observer.Camera.camera.unproject(new Vector3(Width / 2, Height / 2, 0));

		this.cursorShape.setLocalPosition(curAt);
		this.cursorShape.setLocalRotation(VectorUtils.dir(camAt.cpy(), curAt.cpy()));

		this.cameraOrigin.setLocalPosition(camAt);
		this.cameraOrigin.setLocalRotation(VectorUtils.dir(camAt.cpy(), new Vector3(0, 0, 0)));

		Vector3 u = this.getUnit();
		this.focus = this.observer.Camera.camera.unproject(new Vector3(MouseX, MouseY, 0.5f));

		this.dir = VectorUtils.dir(observer.getCameraPosition(), this.focus);
		this.dir.z = -.5f;

		this.toDraw.clear();
		Rect R = CAMERA.getViewRect().cpy().extendBy(true, u.cpy().scl(2f));

		Polygon visArea = R.bind(Color.CLEAR).toPolygon();

		for (int i = 0; i < this.Members.size(); i++) {
			_Entity e = this.Members.get(i);

			if (e instanceof pEnt2) {
				pEnt2 E = (pEnt2) e;
				boolean b = R.contains(VectorUtils.downcast(E.transform.GetLocalPosition()));

				if (b) {
					this.toDraw.add(E);
				}
			}
		}


	}

	private float smin(float dA, float dB, float k) {
		float h = Math.max(k - Math.abs(dA - dB), 0) / k;

		return Math.min(dA, dB) - (h * h * h * k * (1f / 6f));
	}

	@Override
	public Vector3 getUnit() {
		return new Vector3(32, 32, 32);
	}
}
