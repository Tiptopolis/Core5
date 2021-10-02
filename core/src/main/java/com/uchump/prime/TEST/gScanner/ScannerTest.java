package com.uchump.prime.TEST.gScanner;

import static com.uchump.prime._METATRON.Metatron.CAMERA;
import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.google.common.graph.ElementOrder;
import com.uchump.prime._PRIME.C_O.Geom;
import com.uchump.prime._PRIME.C_O.Maths;
import com.uchump.prime._PRIME.C_O.VectorUtils;
import com.uchump.prime._PRIME.C_O.NIX.BoundShape;
import com.uchump.prime._PRIME.SYS.uApp;
import com.uchump.prime._PRIME.SYS.NMP._Entity;
import com.uchump.prime._RAUM.RAUM.GFX._BoundShape;
import com.uchump.prime._RAUM.RAUM.GFX.Prototype.Line;
import com.uchump.prime._RAUM.RAUM.GFX.Prototype.Ray;

public class ScannerTest extends uApp {

	public TstEnv environment;
	// pBoxEnt ent;

	public ScannerTest() {
		super();
	}

	public void create() {
		super.create();
		this.environment = new TstEnv(new Vector3(100, 100, 1));

	}

	public void update(float deltaTime) {
		super.update(deltaTime);
		this.environment.update(deltaTime);
		// Log("@#%^: " + deltaTime);
	}

	@Override
	public void render() {
		super.render();

		Vector3 from = CAMERA.getCameraPosition().cpy();
		Vector3 to = new Vector3(32, 32, 32);
		Vector3 dst = VectorUtils.dst(from, to);
		Vector3 dir = VectorUtils.dir(from, to);

		to = new Vector3(MouseX, MouseY, 1);
		CAMERA.Camera.camera.unproject(to);
		Vector3 mDst = VectorUtils.dst(from, to);
		Vector3 mDir = VectorUtils.dir(from, to);

		Line LN = new Line(from, to);
		LN.vertexSize = 1 + (1 - CAMERA.Camera.zoom);
		LN.lineWidth = CAMERA.Camera.zoom;
		LN.fillColor = Color.BLACK;

		_BoundShape RN = new Ray(from, mDir);
		RN.vertexSize = (1 + (1 - CAMERA.Camera.zoom)) / 2;
		RN.lineWidth = CAMERA.Camera.zoom / 2;
		RN.fillColor = (new Color(0f, 0.5f, 0f, 0.5f));
		Sketcher.setProjectionMatrix(CAMERA.getProjection());
		Sketcher.begin();
		// LN.draw();
		// RN.draw();
		this.renderEnv();
		Sketcher.end();
	}

	public void renderEnv() {
		Vector3 pos = this.environment.observer.getCameraPosition();
		Vector3 dir = this.environment.dir;
		Vector3 focus = this.environment.focus;
		Line toFocus = new Line(pos, focus);
		toFocus.fillColor = new Color(0.6f, 0.2f, 0.2f, 0.5f);
		toFocus.vertexSize = 1 - CAMERA.Camera.zoom;
		toFocus.vertexSize = 1 + (1 - CAMERA.Camera.zoom);
		Ray toDir = new Ray(pos, dir);
		toDir.fill = false;
		toDir.vertexSize = 1 + CAMERA.Camera.zoom;
		toDir.lineColor = new Color(0.75f, 0.75f, 0.75f, 0.25f);
		toDir.fillColor = new Color(0.25f, 0.25f, 0.25f, 0.25f);
		toDir.vertexSize = 1 + (1 - CAMERA.Camera.zoom);

		toFocus.draw();
		toDir.draw();

		for (_Entity e : this.environment.toDraw) {

			if (e instanceof pEnt) {
				pEnt E = (pEnt) e;
				Vector3 s = E.transform.GetLocalScale();
				Color c = new Color(0.25f, 0.25f, 0.25f, 0.25f);
				Sketcher.setColor(c);
				Sketcher.Drawer.filledPolygon(E.shape.toPolygon());
				E.draw();

				Vector3 hit = toDir.hit(E.shape);

				if (hit != null) {

					Log("HIT-> " + hit);

					Sketcher.setColor(Color.RED);
					Sketcher.Drawer.circle(hit.x, hit.y, 2);
				}

				hit = toDir.hit(E.shape, false);
				if (hit != null) {

					Log("HIT-> " + hit);

					Sketcher.setColor(Color.BLUE);
					Sketcher.Drawer.circle(hit.x, hit.y, 2);
				}

				Array<Vector3> hits = toDir.hits(E.shape);
				if (hits != null && !hits.isEmpty()) {
					for (Vector3 v : hits) {

						Log(E.shape.umDst(v));
						float f = E.shape.umDst(v);
						float sF = (s.x + s.y) / 2;
						Color C = Color.CLEAR;

						float k = Maths.map(f, 0f, sF, -1f, 1f);

						if (E.shape.epsContains(VectorUtils.downcast(v), 1 - CAMERA.Camera.zoom)) {
							C = (Color.TEAL);

							// C.a=1-(Math.abs(sF-Math.abs(f)));
							C.a = k * 0.5f;
						}

						else {
							C = (Color.MAGENTA);
							// dst from raycaster to shape ctr
							float dO = VectorUtils.dst(pos, E.transform.GetLocalPosition()).len();
							// dst from raycaster to hit
							float dV = VectorUtils.dst(pos, v).len();

							float mO = Maths.map(dV, 0, dO, 0, 1);
							if (mO < 1)
								C.a = 1 - mO;
							else
								C.a = mO;
						}

						Sketcher.setColor(C);
						Sketcher.Drawer.circle(v.x, v.y, 1);
					}
				}
			}
		}

	}

	public void tst() {

	}

}
