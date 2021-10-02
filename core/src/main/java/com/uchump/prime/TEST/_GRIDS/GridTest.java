package com.uchump.prime.TEST._GRIDS;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime.uChumpEngine;
import com.uchump.prime._METATRON.Metatron;
import com.uchump.prime._PRIME.C_O.NIX.BoundShape;
import com.uchump.prime._PRIME.SYS.uApp;
import com.uchump.prime._PRIME.SYS.A_I.aProperty;
import com.uchump.prime._PRIME.SYS.NIX._Shell;
import com.uchump.prime._PRIME.UI._Events.uEvent;
import com.uchump.prime._PRIME.UI._Events.Prototype.Message;
import com.uchump.prime._RAUM.RAUM.GFX._BoundShape;
import com.uchump.prime._RAUM.RAUM.GRID.Grids;
import com.uchump.prime._RAUM.RAUM.MAP._Layer;
import com.uchump.prime._RAUM.RAUM.MAP.aMap;
import com.uchump.prime._RAUM.RAUM.META.NIX._Archetype;
import com.uchump.prime._RAUM.RAUM.META.NIX._Thing;
import com.uchump.prime._RAUM.RAUM.WORLD._World;

public class GridTest extends uApp {

	_Archetype RectCell;
	_Archetype HexCell;
	_Archetype TriCell;
	_Layer HEX;
	_Layer RECT;
	_Layer RECT2;
	_Layer TRI;

	public GridHandler R = new GridHandler();

	_World WORLD;
	aMap MAP;

	GridRenderer RENDERER;
	// CTRL+U/D to shift camera mode
	// CTRL+L/R to shift map type

	float t = 0;
	float tM = 5;

	public GridTest() {
		super();
	}

	@Override
	public void create() {
		super.create();
		RENDERER = new GridRenderer();
		WORLD = new _World();
		MAP = new aMap(WORLD, "TestMap", 100, 100);
		// buildRect();
		this.RECT = Grids.RectLayer("RECT", 100, 100);
		this.RECT2 = RECT.clone("RECT2", true);
		// this.RECT.dispose();
		this.RECT = null;
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		t += deltaTime;
		if (t >= tM) {
			t = 0;

			ArrayList<Object> sample = new ArrayList<Object>();
			sample.addAll(sampleCollection(RECT2.Cells.values().toArray(), 10));
			for (Object o : sample) {
				if (o instanceof _Thing) {
					_Thing t = (_Thing) o;
					Log(sample.indexOf(o) + ":" + t.toLog());
					Log();
					Log(t.Tags);
					Log();
					Log(uChumpEngine.METATRON.CAMERA.getViewRect());
					// Log(RECT2.Cells.get(t.get("Offset").get()) + " " + t.get("Offset").get());
				}
			}

			Log(RECT2.Cells.get(new Vector3(0, 0, 1)).Tags.Get);
			Log();
			Log(RECT2.Cells.get(new Vector3(0, 0, 1)).Connections);
			Log(RECT2.Cells.get(new Vector3(0, 0, 1)).hasConnection("Right"));
			Log(RECT2.Cells.get(new Vector3(0, 0, 1)).getConnected("Right"));
			Log();
			_Thing C = (_Thing) RECT2.Cells.get(new Vector3(0, 0, 1)).get();
			_Thing R = (_Thing) RECT2.Cells.get(new Vector3(0, 0, 1)).getConnected("Right").get();
			_Thing X = (_Thing) RECT2.Cells.get(new Vector3(0, 99, 1)).get();
			Log(C);
			Log("-----" + C.Tags.has("Corner") + ",, " + C.Tags.has("Left,Bottom"));
			Log("-----" + C.Tags.get("Corner"));
			Log("     " + C.Tags.get("Corner").isOf("Left") + " && " + C.Tags.get("Corner").isOf("Bottom") + " || "
					+ C.Tags.get("Corner").isOf("Left,Bottom"));
			Log("-----" + C.Tags.get("Left,Bottom"));
			Log(R);
			Log(C.isConnected(R) + " &|& " + R.isConnected(C) + "   " + C.isConnected(X) + " |&| " + R.isConnected(X));
			Log();
			// need to cache the geometries in handler for faster mouseOver
			sample.clear();
			sample = null;
		}
		R.update(this.RECT2);

	}

	@Override
	public void render() {
		// Log("*****************");
		RENDERER.drawLayer(this.RECT2);
	}

	@Override
	public void dispose() {
		this.R.dispose();
		this.RECT2.dispose();
	}

	@Override
	public boolean keyUp(int keycode) {
		if (!this.running)
			return false;

		if (keycode == Keys.ESCAPE) {
			Log("ESCAPE!");
			uEvent MainAppChangeMode = new uEvent();
			MainAppChangeMode.setSender(this);
			MainAppChangeMode.setTarget(Metatron.CURRENT);
			MainAppChangeMode.setContent(new Message("BOOT_RESTART"));

			return uChumpEngine.METATRON.handle(MainAppChangeMode);
		}

		if (keycode == Keys.LEFT) {
			this.RECT.active = !this.RECT.active;
		}

		return false;
	}

	@Override
	public String toLog() {

		String log = super.toString();

		return log;
	}
}
