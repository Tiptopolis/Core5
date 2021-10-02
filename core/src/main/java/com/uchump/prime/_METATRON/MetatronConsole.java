package com.uchump.prime._METATRON;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;
import java.io.IOException;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.uchump.prime.uChumpEngine;
import com.uchump.prime._METATRON.SYS.ConsoleInputAdapter;
import com.uchump.prime._METATRON.SYS.mConsoleLogger;
import com.uchump.prime._PRIME.SYS.NIX._Shell;
import com.uchump.prime._PRIME.UI.aViewContext;
import com.uchump.prime._PRIME.UI._Events.Prototype.Message;

public class MetatronConsole implements InputProcessor {

	// Metatron UI
	protected static Metatron TheMetatron;
	public static MetatronConsole mConsole;
	protected static mConsoleLogger mLogger;
	public static _Shell mShell;
	protected Thread InteruptAvoidanceMechanism;
	protected static ConsoleInputAdapter IO;

	protected boolean running = false;
	public boolean echo = true;

	protected aViewContext localPrimeModal; // focus, target context

	public MetatronConsole() {
		TheMetatron = Metatron.TheMetatron;
		mShell = TheMetatron.MetatronShell;
		mConsole = this;

		mLogger = new mConsoleLogger(this);
		this.IO = new ConsoleInputAdapter(this);
		InteruptAvoidanceMechanism = new Thread("mConsole") {
			@Override
			public void run() {
				if (mShell != null)
					mShell = TheMetatron.MetatronShell;
				if (running) {
					try {
						MetatronConsole.this.mainLoop();
					} catch (Throwable t) {
						if (t instanceof RuntimeException)
							throw (RuntimeException) t;
						else
							throw new GdxRuntimeException(t);
					}
				}
			}
		};
		this.running = true;
		InteruptAvoidanceMechanism.start();
	}

	void mainLoop() throws IOException {
		System.out.println("_CONSOLE LOOP START");
		String tmp = ":";
		System.out.flush();
		while (running) {// STEP INSTRUCTIONS

			synchronized (IO) {
				tmp = IO.readLine();
				if (tmp.equals("SHELL:TERMINATE")) {
					Log(this.toLog());
					post("SHELL:TERMINATE");
					this.terminate();
				}

				if (tmp.equals(":LOG") || tmp.equals("")) {
					post(":LOG");
					Log(this.toLog());

				}

				if (tmp.equals("SHELL:LOG")) {
					Log(mShell.toLog());

				}
				post(tmp);

				if (echo)
					System.out.println("$&: [" + tmp + "]");

			}

			System.in.mark(0);
			System.in.reset();

			System.out.println("Console Loop Executed Successfully");

		}
		System.out.println("Shell Teminated");
	}

	public void terminate() {
		System.out.println("SHELL:TERMINATE...");
		this.running = false;
		Gdx.app.exit();
		System.exit(0);
	}

	public static boolean com(String input) {
		if (mLogger == null) {
			Log(">>no logger<<");
			return false;
		}

		mLogger.toLog(input);
		return true;
	}

	public static void post(String input) {
		if (mLogger != null && mLogger.active) {
			mConsoleLogger.toLog(input);
			mConsoleLogger.logOut();
		}
		Message m = new Message(input);
		if (Metatron.CURRENT != null)
			Metatron.CURRENT.handle(m);

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

	// Top-Level Input Filter
	public String toLog() {
		// java.lang.Thread.activeCount()

		String log = "";
		log += "\n";
		log += "#ThreadsActive- " + java.lang.Thread.activeCount();
		log += "\n";
		// log += ""+java.lang.Thread.getAllStackTraces();
		Map<Thread, StackTraceElement[]> threads = java.lang.Thread.getAllStackTraces();
		for (Map.Entry<Thread, StackTraceElement[]> t : threads.entrySet()) {

			if (!t.getKey().isDaemon()) {
				log += t.toString();
				log += "\n";
			}

		}
		log += "\n";

		return log;
	}
}
