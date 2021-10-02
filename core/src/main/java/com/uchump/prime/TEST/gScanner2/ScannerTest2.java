package com.uchump.prime.TEST.gScanner2;

import static com.uchump.prime._METATRON.Metatron.CAMERA;
import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime.TEST.gScanner.TstEnv;
import com.uchump.prime.TEST.gScanner.pEnt;
import com.uchump.prime._PRIME.C_O.VectorUtils;
import com.uchump.prime._PRIME.SYS.uApp;
import com.uchump.prime._PRIME.SYS.NMP._Entity;
import com.uchump.prime._RAUM.RAUM.GFX._BoundShape;

public class ScannerTest2 extends uApp{

	TestEnviron environment;
	
	
	
	
	public ScannerTest2() {
		super();
		this.environment = new TestEnviron(new Vector3(100,100,1));
		this.environment.observer = CAMERA;
	}

	public void create() {
		super.create();

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
		Vector3 to = new Vector3(MouseX, MouseY, 1);
		CAMERA.Camera.camera.unproject(to);
		Vector3 dst = VectorUtils.dst(from, to);
		Vector3 dir = VectorUtils.dir(from, to);
		Log(dst);
		Sketcher.begin();
		Sketcher.Drawer.setColor(new Color(0.5f,0.5f,0.5f,0.5f));
		Sketcher.Drawer.line(new Vector2(from.x, from.y), new Vector2(to.x, to.y));
		
		
		this.renderEnv();
		
		Sketcher.end();
	}
	
	private void renderEnv()
	{
		for (_Entity e : this.environment.toDraw) {
			if (e instanceof pEnt2) {
				pEnt2 E = (pEnt2) e;
				_BoundShape s = E.shape;
					s.draw();
				
			}
		}
	}
	
}
