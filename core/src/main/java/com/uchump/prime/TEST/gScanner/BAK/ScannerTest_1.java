package com.uchump.prime.TEST.gScanner.BAK;

import static com.uchump.prime._METATRON.Metatron.CAMERA;
import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime._PRIME.C_O.Geom;
import com.uchump.prime._PRIME.C_O.VectorUtils;
import com.uchump.prime._PRIME.C_O.NIX.BoundShape;
import com.uchump.prime._PRIME.SYS.uApp;
import com.uchump.prime._PRIME.SYS.NMP._Entity;
import com.uchump.prime._RAUM.RAUM.GFX._BoundShape;
import com.uchump.prime._RAUM.RAUM.GFX.Prototype.Line;
import com.uchump.prime._RAUM.RAUM.GFX.Prototype.Ray;

public class ScannerTest_1 extends uApp {

	public TstEnv environment;
	// pBoxEnt ent;

	public ScannerTest_1() {
		super();
	}

	public void create() {
		super.create();
		this.environment = new TstEnv(new Vector3(100, 100, 1));

	}

	public void update(float deltaTime) {
		super.update(deltaTime);
		this.environment.update(deltaTime);
		
	}

	@Override
	public void render() {
		super.render();

		Vector3 from = CAMERA.getCameraPosition().cpy();
		Vector3 to = new Vector3(32, 32, 32);
		Vector3 dst = VectorUtils.dst(from, to);
		Vector3 dir = VectorUtils.dir(from, to);

		// from = new Vector3(8,0,8);
		to = new Vector3(MouseX, MouseY, 1);
		CAMERA.Camera.camera.unproject(to);
		Vector3 mDst = VectorUtils.dst(from, to);
		Vector3 mDir = VectorUtils.dir(from, to);
		// Log(" >> "+mDst + " " + mDst.len() + " " + mDst.len()/3);
		BoundShape L = BoundShape.bindLine(from, to);
		L.shape.clear();
		// RAY = bindRadius() -> bindLine()
		L.shape = Geom.lineTo(from, to, (int) mDst.len() / 3);
		L.shape = Geom.lineTo(from, to, 3); // < from,to,mid
		L.vertexSize = 1 + (1 - CAMERA.Camera.zoom);
		L.lineWidth = CAMERA.Camera.zoom;
		L.fillColor = Color.ORANGE;

		BoundShape R = BoundShape.bindRadius(to, new Vector3(100, 100, 100), VectorUtils.dir(from, to).scl(-1),	Color.MAGENTA);
		BoundShape F = BoundShape.bindRadius(from, new Vector3(100, 100, 100), VectorUtils.dir(from, to), Color.RED);
		BoundShape W = BoundShape.bindRadius(from, new Vector3(100, 100, 100), VectorUtils.dir(from, to).scl(-1),Color.GREEN);

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
		Sketcher.setColor(Color.RED);
		Sketcher.Drawer.rectangle(0, 0, 32, 32);
		// L.drawShape();
		// R.drawShape();
		// F.drawShape();

		LN.draw();
		RN.draw();
		this.renderEnv();
		Sketcher.end();
	}

	public void renderEnv() {
		for (_Entity e : this.environment.Members) {
			
			if (e instanceof pEnt) {
				pEnt E = (pEnt) e;
				
				E.draw();
				
				
			}
		}

	}

	
	
	public void tst()
	{
		
	}
	
}
