package com.uchump.prime._PRIME.SYS;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime._METATRON.Metatron;
import com.uchump.prime._PRIME.C_O.NIX.Rect;
import com.uchump.prime._PRIME.C_O.NIX.TimeKey;
import com.uchump.prime._PRIME.C_O.NIX.Transform;
import com.uchump.prime._PRIME.UI.aViewContext;
import com.uchump.prime._PRIME.UI._Events.Prototype.Message;
import com.uchump.prime._PRIME.UI._Scene.NIX._Stage;

public class uApp extends aViewContext implements iApplet {

	public static Metatron M;
	public static Rect Monitor; // screenSize
	public final static Transform ScreenOrigin = new Transform();
	protected static LocalDomainContext Init;
	public aViewContext CurrentView;
	public ArrayList<aViewContext> Views;

	public TimeKey Time;

	public boolean running = false;

	public uApp() {
		Init = new LocalDomainContext(this);
		CurrentView = Init;
		Monitor = new Rect(0, 0, Width, Height);
		ScreenOrigin.SetLocalScale(new Vector3(Width, Height, 1));
		M = Metatron.TheMetatron;

		Log("uApp:" + this.getName());

	}

	@Override
	public void create() {
		super.create();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.Stage = new _Stage();

		if (!M.getProcessors().contains(this.Stage, true))
			M.addProcessor(this.Stage);

	}

	@Override
	public void enter() {
		Log("" + this.getName() + ".enter()");
		super.enter();

	}

	@Override
	public void resize(int width, int height) {
		Monitor = new Rect(0, 0, Width, Height);
		this.Stage.getViewport().update(width, height);
		ScreenOrigin.SetLocalScale(new Vector3(Width, Height, 1));
	}

	@Override
	public void update(float deltaTime) {
		if (this.running)
			super.update(deltaTime);
	}

	@Override
	public void render() {
		if (this.running)
			super.render();
		this.CurrentView.render();
	}

	@Override
	public void pause() {
		this.running = false;
	}

	@Override
	public void resume() {
		this.running = true;
	}

	@Override
	public boolean handle(Message m) {

		Log("uAppRecieved:" + m.toString());
		if (this.domain().handle(m))
			return true;
		if (this.CurrentView.handle(m))
			return true;

		return false;
	}

	@Override
	public void update(int deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void terminate() {
		this.pause();
		this.dispose();
		
		// this.CurrentView.disconnect();
		// this.domain().disconnect();
		super.exit();

	}

	@Override
	public void dispose() {
		M.removeProcessor(this.Stage);
		this.Stage.dispose();
	}

	@Override
	public aViewContext domain() {
		if (Init == null) {
			Init = new LocalDomainContext(this);
		}

		return Init;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof uApp) {
			if (this.getClass().getSimpleName().equals(other.getClass().getSimpleName()))
				return true;
		}

		return false;
	}

	@Override
	public String toLog() {
		String log = this.getName() + ".toLog()\n";
		log += this.Stage.toLog();

		return log;
	}

	protected class LocalDomainContext extends aViewContext implements iApplet {
		uApp of;

		public LocalDomainContext(uApp app) {
			this.of = app;
		}

		public String toString() {
			return this.of.getClass().getSimpleName() + "." + this.getClass().getSimpleName();
		}

		@Override
		public void resize(int width, int height) {

		}

		@Override
		public void pause() {

		}

		@Override
		public void resume() {

		}

		@Override
		public void dispose() {

		}

		@Override
		public aViewContext domain() {

			return this.of.domain();
		}

		@Override
		public boolean handle(Message m) {

			return false;
		}

		@Override
		public void update(int deltaTime) {
			// TODO Auto-generated method stub

		}

		@Override
		public void terminate() {
			// TODO Auto-generated method stub

		}
	}
	//////////////////////////////////

}
