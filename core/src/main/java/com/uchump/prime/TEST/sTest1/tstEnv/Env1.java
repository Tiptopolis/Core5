package com.uchump.prime.TEST.sTest1.tstEnv;

import static com.uchump.prime._METATRON.Metatron.CAMERA;
import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime.uChumpEngine;
import com.uchump.prime.TEST.gScanner.pEnt;
import com.uchump.prime.TEST.gScanner2.pEnt2;
import com.uchump.prime._PRIME.C_O.RNG;
import com.uchump.prime._PRIME.C_O.VectorUtils;
import com.uchump.prime._PRIME.C_O.NIX.Rect;
import com.uchump.prime._PRIME.N_M.Prototype.uVector;
import com.uchump.prime._PRIME.N_M._N.Node;
import com.uchump.prime._PRIME.SYS.NMP._Entity;
import com.uchump.prime._PRIME.UI._Camera.OrthoController;
import com.uchump.prime._RAUM.RAUM.GFX._BoundShape;
import com.uchump.prime._RAUM.RAUM.META._Environment;

public class Env1 extends _Environment{

	
	public OrthoController observer;
	public Vector3 size;
	public Vector3 dir;
	public Vector3 focus;
	
	public ArrayList<pEnt2> toDraw = new ArrayList<pEnt2>();

	
	public Env1(Vector3 size) {
		super("SDF_Env");
		this.size = size;
		this.Dimension = new uVector(size.x, size.y, size.z);
		this.observer = uChumpEngine.METATRON.CAMERA;
		
	}
	
	@Override
	public void update(float deltaTime) {

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
	
	@Override
	public Vector3 getUnit() {
		return new Vector3(32, 32, 32);
	}
	
	@Override
	public String toLog() {
		String log = "";
		log += this.toString() + "\n";
		log += "Obs: " + this.observer.getClass().getSimpleName() + "\n";
		log += " =@:" + this.observer.getCameraPosition() + "\n";
		log += " =>:" + this.dir + "\n";
		log += " =#:" + this.toDraw.size() + "\n";
		if (!this.Properties.isEmpty()) {
			log += "Properties: " + this.properties().size;
			log += "\n";
			for (Node N : this.Properties) {
				log += ("*" + N + "\n");
			}
		}
		return log;
	}
	
	
//////////
protected void gen(int num) {
	Vector3 u = this.getUnit();
	for (int i = 0; i < num; i++) {
		int op = RNG.rndInt(0, 4);// non-inclusive, so really 0-(n-1)
		float x = (float) RNG.rndDouble(0, this.size.x * u.x);
		float y = (float) RNG.rndDouble(0, this.size.y * u.y);
		Vector3 n = new Vector3(x, y, 0.5f);
		this.genNew(op, n);
		//////
	}
}

public void genNew(int op, Vector3 at) {
	Log(op);
	Vector3 nDir = new Vector3().setToRandomDirection();

	nDir.z = 0; // keeps everything 2D. 3D works, but not all points of the shape are coplanar
				// with the ViewOrtho
	pEnt n = new pEnt(this, at);
	n.transform.SetLocalRotation(VectorUtils.upcast(nDir));
	if (op == 0) {

		n.shape = _BoundShape.bindShape(n.transform, Color.GREEN, 20, false);
	}
	if (op == 1) {

		n.shape = _BoundShape.bindShape(n.transform, Color.GREEN, 3, false);
	}
	if (op == 2) {

		n.shape = _BoundShape.bindShape(n.transform, Color.GREEN, 4, false);
	}
	if (op == 3) {

		n.shape = _BoundShape.bindShape(n.transform, Color.GREEN, 6, false);
	}
	nDir = null;
}


////////////////

public void bind(ShaderProgram s)
{
	//Log(s.getUniformLocation("u_mouse"));
	Log("BINDING SHAPES TO SHADER:::");
	//Transform, VtexNum for Active
	//Transform, ShapeType for Static
	
}


}
