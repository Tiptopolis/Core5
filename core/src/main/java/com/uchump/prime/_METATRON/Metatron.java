package com.uchump.prime._METATRON;

import static com.uchump.prime._PRIME.uAppUtils.Height;
import static com.uchump.prime._PRIME.uAppUtils.Log;
import static com.uchump.prime._PRIME.uAppUtils.Width;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.uchump.prime.uChumpEngine;
import com.uchump.prime._PRIME.C_O.Maths;
import com.uchump.prime._PRIME.C_O.NIX.TimeKey;
import com.uchump.prime._PRIME.C_O.NIX.Transform;
import com.uchump.prime._PRIME.N_M._N.Type;
import com.uchump.prime._PRIME.SYS.uApp;
import com.uchump.prime._PRIME.SYS.A_I.iMonad;
import com.uchump.prime._PRIME.SYS.NIX._Shell;
import com.uchump.prime._PRIME.SYS.NIX._VTS;
import com.uchump.prime._PRIME.UI.aViewContext;
import com.uchump.prime._PRIME.UI._Camera.OrthoController;
import com.uchump.prime._PRIME.UI._Events.aEvent;
import com.uchump.prime._PRIME.UI._Events.aEventManager;
import com.uchump.prime._PRIME.UI._Events.iEventProcessor;
import com.uchump.prime._PRIME.UI._Events.iEventReciever;
import com.uchump.prime._PRIME.UI._Events.iEventSender;
import com.uchump.prime._PRIME.UI._Events.Prototype.Message;
import com.uchump.prime._RAUM.RAUM.WORLD._World;

public class Metatron extends InputMultiplexer implements iMonad, iEventProcessor {
	public final static Metatron TheMetatron;

	public TimeKey Module = new TimeKey(); // PULSER

	public TimeKey Metranome = new TimeKey(); // SCANNER
	public TimeKey DeltaTime = new TimeKey(); // FREQUENCY
	public TimeKey RealSecond = new TimeKey(); // COUNTER

	public TimeKey iTime = new TimeKey(); // COUNTER

	public static _Shell MetatronShell;
	public static MetatronConsole Console;

	protected static Boot INIT;
	public static uApp CURRENT;
	public static OrthoController CAMERA;
	public static aViewContext GlobalPrimeModal;

	static {
		TheMetatron = new Metatron();
		MetatronShell = _Shell.DomainShell("Metatron");
		MetatronShell.EventManager = new aEventManager();
		Console = new MetatronConsole();
		Gdx.input.setInputProcessor(TheMetatron);
	}

	protected Metatron() {

	}

	public void update(Number deltaTime) {

		DeltaTime.I = this.DeltaTime.I.byteValue() + deltaTime.floatValue();
		DeltaTime.i = 1;
		DeltaTime.t = deltaTime.floatValue();
		RealSecond.I = (this.RealSecond.I.floatValue() + deltaTime.floatValue()) % 360;
		//////
		Module.n = Byte.MIN_VALUE;
		Module.m = Byte.MAX_VALUE;
		Module.i = 1;

		Module.a = Module.a.byteValue() + this.Module.i.byteValue();
		Module.t = this.Module.I;
		Module.I = Maths.map(this.Module.a.floatValue(), this.Module.n.floatValue(), this.Module.m.floatValue(), 0f,
				1f);
		//////

		//////
		Metranome.n = Byte.MIN_VALUE;
		Metranome.m = Byte.MAX_VALUE;
		Metranome.i = 1;

		Metranome.a = Metranome.a.byteValue() + Metranome.i.byteValue();
		Metranome.t = Maths.map(Metranome.a.floatValue(), Metranome.n.floatValue(), Metranome.m.floatValue(), -1, 1);
		Metranome.I = Math.abs(Metranome.t.floatValue());
		//////

		iTime.I = ((RealSecond.I.floatValue() / (RealSecond.I.intValue() * DeltaTime.I.floatValue()))
				* DeltaTime.I.floatValue());
		if (iTime.I.floatValue() == Float.POSITIVE_INFINITY)
			iTime.I = 1f;
		// Log(this.toLog());

		MetatronShell.update(deltaTime.floatValue());
		// this.Shell.update(deltaTime.floatValue());

		if (INIT == null) {
			INIT = new Boot();
		}
		if (CURRENT == null) {
			this.launch(INIT);
		}
		CURRENT.update();
	}

	public static void launch(uApp app) {

		if (CURRENT == null || !CURRENT.equals(app)) {
			if (CURRENT != null && !CURRENT.equals(app)) {
				Log("METATRON_SHELL.CLOSING: [" + CURRENT.getClass().getSimpleName() + "]...");
				CURRENT.dispose();
			}
			Log("METATRON_SHELL.LAUNCHING: [" + app.getClass().getSimpleName() + "]...");
			uApp bootApp = app;
			CURRENT = bootApp;
			CURRENT.create();

			CURRENT.enter();
			CURRENT.resize(Width, Height);
			uChumpEngine.METATRON.setView(CURRENT.CurrentView);
			CURRENT.resume();
		}
	}

	public void setView(aViewContext view) {

		Log("Metatron.Shell.setView(" + view.getName() + ")");
		if (GlobalPrimeModal != null) {
			TheMetatron.removeProcessor(GlobalPrimeModal);
			GlobalPrimeModal.exit();
		}
		GlobalPrimeModal = view;
		TheMetatron.addProcessor(GlobalPrimeModal);
		Console.localPrimeModal = GlobalPrimeModal;
		GlobalPrimeModal.enter();

	}

	@Override
	public void addProcessor(InputProcessor processor) {
		if (processor == null)
			throw new NullPointerException("processor cannot be null");

		Log(processor.getClass().getSimpleName() + "                     <- MTRN Multiplexer ADDED");
		if (!this.getProcessors().contains(processor, true) && !this.getProcessors().contains(processor, false))
			getProcessors().add(processor);
		// Console.post("!METATRON:_:InputProcessor Added:_[" + processor + "]");
	}

	public void dispose() {
		_VTS.Global.clear();
		Type.Every.clear();
		_World.CoreMeta.clear();
		
		MetatronShell.terminate();
		Console.terminate();
		CURRENT.terminate();
	}

	public String toLog() {
		String log = "";

		// K V T
		log += 0 / 1;
		log += "\n";

		log += "\n";
		log += "[METATRON]\n";
		log += "{\n";
		log += "|>{DeltaTime [" + DeltaTime + "] " + DeltaTime.toLog() + "}\n"; // Constant N
		log += "|>{RealSecond [" + RealSecond + "] " + RealSecond.toLog() + "}\n"; // Accumulator 0->Inf, counter
		log += "|>{Module [" + Module + "] " + Module.toLog() + "}\n"; // Interpolator 0->N-^0 : 0->1-^0, returns to 0
																		// after reaching N
		log += "|>{Metranome [" + Metranome + "] " + Metranome.toLog() + "}\n"; // Cycle -N-N:-1<->1
		log += "|>{iTime} [" + iTime + "]" + iTime.toLog();
		log += "}\n";

		// log += ((Module.i.floatValue() - Module.Raw().byteValue() %
		// (RealSecond.I.byteValue() + 1)));// RNG?
		// log += "\n";

		// Log("ProjectedSecond: " + ((RealSecond.I.floatValue() /
		// (RealSecond.I.intValue() * DeltaTime.I.floatValue()))
		// * DeltaTime.I.floatValue()));

		return log;
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
	public Transform getTransform() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void transformUpdated() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean handle(Message m) {
		Log("Metatron Recieved: " + m);
		if (m.equals("BOOT_RESTART")) {
			Log("OK");
			this.launch(INIT);
		}
		return false;
	}

	public boolean handle(aEvent event) {
		if(event.content instanceof Message)
		{
			return this.handle((Message)event.content);
		}
		return false;
	}

}
