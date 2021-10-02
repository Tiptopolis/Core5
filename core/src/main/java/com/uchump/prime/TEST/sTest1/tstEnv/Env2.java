package com.uchump.prime.TEST.sTest1.tstEnv;

import static com.uchump.prime._METATRON.Metatron.CAMERA;
import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.uchump.prime.uChumpEngine;
import com.uchump.prime.TEST.gScanner.pEnt;
import com.uchump.prime.TEST.gScanner2.pEnt2;
import com.uchump.prime._PRIME.C_O.RNG;
import com.uchump.prime._PRIME.C_O.VectorUtils;
import com.uchump.prime._PRIME.C_O.NIX.Rect;
import com.uchump.prime._PRIME.C_O.NIX.Transform;
import com.uchump.prime._PRIME.N_M.Prototype.uVector;
import com.uchump.prime._PRIME.N_M._N.Node;
import com.uchump.prime._PRIME.SYS.NMP._Entity;
import com.uchump.prime._PRIME.UI._Camera.OrthoController;
import com.uchump.prime._RAUM.RAUM.GFX._BoundShape;
import com.uchump.prime._RAUM.RAUM.META._Environment;

public class Env2 extends _Environment{

	
	public OrthoController observer;
	public Vector3 size;
	public Vector3 dir;
	public Vector3 focus;
	
	public ArrayList<pEnt2> toDraw = new ArrayList<pEnt2>();
	
	
	pEnt2 cursorShape;
	pEnt2 cameraOrigin;
	pEnt2 mapOrigin;

	
	public Env2(Vector3 size) {
		super("SDF_Env");
		this.size = size;
		this.Dimension = new uVector(size.x, size.y, size.z);
		this.observer = uChumpEngine.METATRON.CAMERA;
		this.gen();
	}
	
	@Override
	public void update(float deltaTime) {

		Vector3 u = this.getUnit();

		
		Vector3 curAt = this.observer.Camera.camera.unproject(new Vector3(MouseX, MouseY, 0));
		Vector3 camAt = this.observer.Camera.camera.unproject(new Vector3(Width / 2, Height / 2, 0));
		Vector3 mapAt = this.observer.Camera.camera.project(new Vector3(Width, Height, 0));
		
		this.cursorShape.setLocalPosition(curAt);
		this.cursorShape.setLocalRotation(VectorUtils.dir(camAt.cpy(), curAt.cpy()));

		this.cameraOrigin.setLocalPosition(camAt);
		this.cameraOrigin.setLocalRotation(VectorUtils.dir(camAt.cpy(), new Vector3(0, 0, 0)));
		
		
		
		
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


////////////////

public void bind(ShaderProgram s)
{
	//Log(s.getUniformLocation("u_mouse"));
	Log("BINDING SHAPES TO SHADER:::");
	//Transform, VtexNum for Active
	//Transform, ShapeType for Static
	Transform t1 = cursorShape.transform;
	Transform t2 = cameraOrigin.transform;
	Transform t3 = mapOrigin.transform;
	
	//Vector3 v1 = CAMERA.Camera.camera.project(t1.GetLocalPosition());
	Vector3 v1 = t1.GetLocalPosition();
	Vector3 v2 = t2.GetLocalPosition();
	Vector3 v3 = t3.GetLocalPosition();
	
	Array<Vector3> vs = new Array<Vector3>(true, 0, Vector3.class);
	vs.addAll(new Vector3[] {v1,v2,v3});
	
	
	int shpCnt = 3;

	s.setUniformi("shpCnt", shpCnt);
	//s.setUniform3fv("posAr[0]", VectorUtils.disassembleVect3(v1,v2,v3), 0, shpCnt*3);	
	//s.setUniform3fv("posAr[0]", VectorUtils.disassembleVect3(v1,v2,v3), 0, shpCnt*3);	
	//s.setUniform3fv("posAr[0]", VectorUtils.disassembleVect3(v1,v2,v3), 0, shpCnt*3);	
	//s.setUniform3fv("posAr["+shpCnt+"]", VectorUtils.disassembleVect3(v1,v2,v3), 0, shpCnt*3);
	//s.setUniformf("posAr[0]", v1);
	//s.setUniformf("posAr[1]", v2);
	//s.setUniformf("posAr[2]", v3);
	s.setUniform3fv("posAr[0]", new float[] {v1.x,v1.y,v1.z}, 0, 3);
	s.setUniform3fv("posAr[1]", new float[] {v2.x,v2.y,v2.z}, 0, 3);
	s.setUniform3fv("posAr[2]", new float[] {v3.x,v3.y,v3.z}, 0, 3);
	
	for(int i = 0; i < shpCnt; i++){
		int loc = s.getUniformLocation("posAr[" + i + "]");
		//setuniform here
		
		Log(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  " + loc);
		//Log(s.getUniformLocation("posAr[1]"));
		}
}


}
