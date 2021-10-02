package com.uchump.prime;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;

import static com.uchump.prime._METATRON.Metatron.*;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime.TEST._GRIDS.GridTest;
import com.uchump.prime.TEST.gScanner.ScannerTest;
import com.uchump.prime.TEST.gScanner2.ScannerTest2;
import com.uchump.prime.TEST.sTest1.ShaderTest1;
import com.uchump.prime.TEST.sTest1.ShaderTest2;
import com.uchump.prime.TEST.sTest1.ShaderTest3;
import com.uchump.prime.TEST.sTest1.ShaderTest4;
import com.uchump.prime.TEST.sTest1.ShaderTest5;
import com.uchump.prime.TEST.sTest1.ShaderTest6;
import com.uchump.prime.TEST.sTest1.ShaderTest7;
import com.uchump.prime.TEST.sTest1.ShaderTest8;
import com.uchump.prime.TEST.sTest1.ShaderTest9_BS1;
import com.uchump.prime.TEST.sTest1.ShaderTest9_T3_X1;
import com.uchump.prime.TEST.sTest2.ShaderTest2_1;
import com.uchump.prime._METATRON.Boot;
import com.uchump.prime._METATRON.Metatron;
import com.uchump.prime._METATRON.oLF_Renderer;
import com.uchump.prime._PRIME.uAppUtils;
import com.uchump.prime._PRIME.uSketcher;
import com.uchump.prime._PRIME.C_O.Geom;
import com.uchump.prime._PRIME.C_O.VectorUtils;
import com.uchump.prime._PRIME.C_O.NIX.BoundShape;
import com.uchump.prime._PRIME.C_O.NIX.Transform;
import com.uchump.prime._PRIME.N_M._N.Type;
import com.uchump.prime._PRIME.N_M.UTIL.V_Operator;
import com.uchump.prime._PRIME.SYS.iApplet;
import com.uchump.prime._PRIME.SYS.uApp;
import com.uchump.prime._PRIME.SYS.A_I.aProperty;
import com.uchump.prime._PRIME.SYS.NIX._ECS;
import com.uchump.prime._PRIME.SYS.NIX._VTS;
import com.uchump.prime._PRIME.SYS.NMP._Entity;
import com.uchump.prime._PRIME.SYS.Prototype.LookUp;
import com.uchump.prime._PRIME.SYS.Prototype.Tree;
import com.uchump.prime._PRIME.UI.aViewContext;
import com.uchump.prime._PRIME.UI._Camera.OrthoController;
import com.uchump.prime._PRIME.UI._Events.Prototype.Message;
import com.uchump.prime._PRIME.UI._Scene.NIX._Actor;
import com.uchump.prime._PRIME.UI._Scene.NIX._Widget;
import com.uchump.prime._RAUM.RAUM.GFX._BoundShape;
import com.uchump.prime._RAUM.RAUM.MAP.aMap;
import com.uchump.prime._RAUM.RAUM.META.CMP._States;
import com.uchump.prime._RAUM.RAUM.META.NIX._Thing;
import com.uchump.prime._RAUM.RAUM.META.NIX._Archetype;
import com.uchump.prime._RAUM.RAUM.META.Prototype.Dex;
import com.uchump.prime._RAUM.RAUM.META.Prototype.Flags;
import com.uchump.prime._RAUM.RAUM.META.Prototype.dEnum;
import com.uchump.prime._RAUM.RAUM.WORLD._World;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */
public class uChumpEngine extends ApplicationAdapter implements iApplet {
	private SpriteBatch batch;
	private Texture image;

	public static Metatron METATRON;
	public static oLF_Renderer DEFAULT_RENDERER;

	@Override
	public void create() {
		batch = new SpriteBatch();
		image = new Texture("badlogic.png");

		uSketcher s = new uSketcher();
		METATRON = Metatron.TheMetatron;
		CAMERA = new OrthoController(METATRON);
		CAMERA.init();
		// Metatron.launch(new Boot());
		// Metatron.launch(new GridTest());
		//Metatron.launch(new ScannerTest());
		//Metatron.launch(new ScannerTest2());
		//Metatron.launch(new ShaderTest1());
		//Metatron.launch(new ShaderTest2());
		//Metatron.launch(new ShaderTest3());
		//Metatron.launch(new ShaderTest8());
		//Metatron.launch(new ShaderTest9_T3_X1()); //Y3
		Metatron.launch(new ShaderTest9_BS1());
		Metatron.launch(new ShaderTest2_1());
		
		_BoundShape bs = new _BoundShape();

		mpTst();
		Log();
		Log();
		tst2();
		Log();
		Log();
		Log();
		// Log(_VTS.Global.values().toArray());
		Log();
		Log(Metatron.CURRENT);
		Log();

		Log(Metatron.CURRENT.getClass().getSimpleName());
		Log("______");
		Log(bs.toLog());
		Log(" -- " + bs.isOf("Drawable"));// now get & execute
		Log("------");
		// tst3();
		Log();
		Log();
		Vector2[] V2 = V_Operator.fromStreamVect2(new float[] { 1, 2, 3, 4, 5, 6, 7, 8 });
		Vector3[] V3 = V_Operator.fromStreamVect3(new float[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
		Log(V2);
		Log(V3);
		Log();
		Log(V_Operator.toStream(V2));
		Log();
		Log(V_Operator.toStream(V3));

		Log();
		Log();
		Log(_VTS.Global.values().toArray());

	}

	public void tst3() {
		Dex conT = new Dex("ConnectionTypes");
		conT.def("Neighbor", "Left");
		conT.def("Neighbor", "Right");
		conT.def("Neighbor", "Up");
		conT.def("Neighbor", "Down");
		Log();
		Log();
		Log();
		Log();
		Log(conT.toLog());
		Log();
		Log();
		Log(conT.get("Left"));
	}

	public void tst2() {
		_World World = new _World();
		Log(World.toLog());
		Log();
		Log();
		Log();
		aMap M = new aMap(World, "TstMap", 200, 200);
		Log(M.toLog());
	}

	public void tst() {
		Log();
		Log();
		Log();
		Log(_World.CoreMeta.toArray());
		Log(_World.getFrom("EntityType", "Doodad"));
	}

	public void mpTst() {
		// aMap M = new aMap();
		// Log(M.toLog());
		dEnum D = new dEnum("EntityType", "Entity") {
			@Override
			public dEnum def() {
				this.base("Entity", new aProperty("Exists", true), new aProperty("Speed", 0f),
						new aProperty("Locomotor", new Type(false, "?", new Type(false, "Locomotor"))), new Transform(),
						new aProperty("Actor", new _Actor()));

				this.def("Unit");
				this.def("Doodad");
				this.def("Destructable");
				this.def("Decoration");
				this.def("Region");
				return this;
			}
		};

		Log(D.toLog());
		Log();
		aProperty doesSomething = new aProperty("TaskDoer", new Type(false, "?", new Type(false, "Task")));
		Log(D.get("Entity"));
		Log(D.get("Doodad"));
		Log();
		Log(doesSomething.toLog());
		Log();
		_Thing aDoodad = new _Thing("Doodad", D.getTemplate()); // instantiates a blank Doodad_Entity with <?>components
		aDoodad.addProperty(doesSomething.cpy());
		Log(aDoodad.toLog());
		// Log();
		// Log();
		// Log(_VTS.ALL.values().toArray());
		Log();
		Log(D.getTemplate().toLog());
		Log();
		Log();
		// _Thing in = D.getTemplate().inst(0);
		_Thing in = D.getTemplate().getA(1);
		Log(in.toLog());

		_Entity ent = _ECS.Spawner.iEnt(in);
		Log(ent.toLog());
		/*
		 * ((Flags) in.get("Flags")).def("Alive"); Log(in.get("Flags").toLog());
		 * Log(in.toLog()); Log("______"); Log(in.Flags.get("Alive"));
		 * Log(in.Flags.get("Alive").set(true)); Log(in.Flags.has("Alive"));
		 * Log(in.Flags.is("Alive")); Log(); Log(in.get("Flags").toLog());
		 * Log(in.Flags.get("Alive")); Log(in.Flags.has("Alive"));
		 * Log(in.Flags.is("Alive"));
		 */
		// tst();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		uAppUtils.update(0);
		float deltaTime = Gdx.graphics.getDeltaTime();
		batch.begin();
		batch.draw(image, 165, 180);
		batch.end();

		METATRON.update(deltaTime);
		CAMERA.update(deltaTime);

		Sketcher.setProjectionMatrix(CAMERA.getProjection());
		Sketcher.begin();
		Sketcher.setColor(Color.RED);
		Sketcher.Drawer.rectangle(0, 0, 32, 32);
		// Sketcher.getBatch().draw(image, 165, 180);
		Sketcher.end();

	}

	@Override
	public void resize(int width, int height) {
		uAppUtils.resize();
		CAMERA.resize(Width, Height);
		CAMERA.update(0);

	}

	@Override
	public void dispose() {
		batch.dispose();
		image.dispose();

		Sketcher.dispose();
		if (CURRENT != null)
			CURRENT.dispose();

		METATRON.dispose();
	}

	///////
	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(int deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public aViewContext domain() {
		// TODO Auto-generated method stub
		return Metatron.CURRENT;
	}

	@Override
	public void terminate() {
		this.dispose();

	}

	@Override
	public boolean handle(Message m) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Transform getTransform() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void transformUpdated() {
		// TODO Auto-generated method stub

	}

	@Override
	public String toLog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addEventListener(String type, EventListener listener, boolean useCapture) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeEventListener(String type, EventListener listener, boolean useCapture) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean dispatchEvent(Event evt) throws EventException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void broadcast() {
		// TODO Auto-generated method stub

	}

	@Override
	public void broadcast(Object at) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}
}