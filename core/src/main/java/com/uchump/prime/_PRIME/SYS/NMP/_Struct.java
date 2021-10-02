package com.uchump.prime._PRIME.SYS.NMP;

import static com.uchump.prime._PRIME.uAppUtils.*;

import com.badlogic.gdx.utils.Array;
import com.uchump.prime._PRIME.N_M._N;
import com.uchump.prime._PRIME.N_M._N.Node;
import com.uchump.prime._PRIME.SYS.A_I.aProperty;
import com.uchump.prime._PRIME.SYS.Prototype.LookupTable;

public class _Struct extends Node<Object> {

	public static Type Struct = new Type("Type", _Struct.class, Object.class);
	public _N Node;
	public LookupTable<Node> Properties = new LookupTable<Node>();

	public _Struct() {
		super("Struct", Struct);
		this.Node = Struct;

	}

	public _Struct(String name) {
		super(name, Struct);
		this.Node = Struct;
	}

	public _Struct(String name, Type t) {
		super(name, t);
		this.Node = Struct;
	}

	public _Struct(_N n) {
		super(n);
		this.Node = Struct;
	}

	@Override
	public aProperty get(Object key) {

		/*
		 * if (key != null && key instanceof aProperty) { return (aProperty)
		 * this.Properties.containsGet((Node) key, false); }
		 */
		if (key != null && key instanceof aProperty) {
			Log(" -=-=-=-=" + key.toString());
			return (aProperty) this.Properties.get(key);
		}

		return null;
	}

	@Override
	public aProperty get(String key) {
		// MUST LEAD AND END w/ <|>
		return (aProperty) this.Properties.get(key.toString());
	}

	public void addProperty(aProperty p) {
		this.Properties.add(p);
	}

	public Node that;
	public boolean hasProperty(aProperty p) {
		
		if(this.Properties.contains(p))
		{
			that = this.get(p);
			return true;
		}
		
		return false;
	}

	public boolean hasProperty(String p) {
		for (Node P : this.Properties) {
			if (P.Name().equals(p)) {
				that = P;
				return true;
			}
		}
		return false;
	}

	public aProperty put(aProperty value) {

		this.Properties.add(value.Label, value);

		return value;
	}

	@Override
	public Array<Node> properties() {

		Array<Node> pN = new Array<Node>(false, 1, Node.class);

		if (this.Properties == null)
			for (Node P : this.Properties) {

				pN.add(P);

			}
		return pN;
	}

	@Override
	public String toString() {
		return "[" + this.Name() + "_" + this.Node.Name() + "]@" + this.Class().getSimpleName() + "[" + this.Type()
				+ "]";
		// REFERENCE NODE INDEX SYMBOL
	}

	@Override
	public String toLog() {
		String log = "";
		log += this.toString() + "\n";
		log += "[" + this.Name() + "]_" + this.Node + "|" + "   " + this.Class().getSimpleName() + "_" + this.Type();

		return log;
	}

}
