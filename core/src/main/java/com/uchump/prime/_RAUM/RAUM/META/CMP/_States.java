package com.uchump.prime._RAUM.RAUM.META.CMP;

import com.uchump.prime._PRIME.N_M._N;
import com.uchump.prime._PRIME.SYS.A_I.aProperty;
import com.uchump.prime._PRIME.SYS.NMP._Component;
import com.uchump.prime._PRIME.SYS.Prototype.Tree;

public class _States extends _Component {

	public static Type TRANSITION = new Type("Transition", _N.Node.NodeType);
	public static Type STATE = new Type("State");

	public StateMachine States;

	public _States() {
		super("States", STATE);
		this.States = new StateMachine(this, "States");
		this.addProperty(new State(States, "Root"));
		this.addProperty(new State(States, "Any"));
		this.States.update(0);

	}

	public class State extends aProperty<State> {

		protected StateMachine Root;

		public State(StateMachine M) {
			super();
			this.Root = M;

		}

		public State(StateMachine M, String label) {
			super(label, STATE);
			this.Root = M;
		}

		public State(StateMachine M, Node N) {
			super(N.Label, N.Type());
			this.Root = M;
			this.header = N;
		}

		public void enter() {

		}

		public void exit() {

		}

	}

	public static class StateMachine extends Tree<State> {

		public _States States;
		public State Current;

		public StateMachine(_States root, String label) {
			super();
			this.States = root;
			this.Name = this.Name(label);

		}

		public void update(float deltaTime) {
			if (this.Current == null)
				this.Current = (State) States.get("Root");

		}

	}

	@Override
	public String toString() {
		return this.Name() + ":" + this.get() + "   " + this.Class().getSimpleName() + this.Node + ":" + this.Type();
	}

	@Override
	public String toLog() {
		String log = this.toString() + "\n";
		if (!this.Properties.isEmpty()) {
			String ind = "";
			for (int i = 0; i < this.Name().length(); i++)
				ind += " ";

			log += ind + ":{States}:\n";
			log += ind + ":[CURRENT]:";
			log += this.States.Current;
			log += "\n";
			for (Node p : this.Properties) {
				log += ind + "  " + p.toLog() + "\n";
			}
		}

		return log;
	}

}
