package com.uchump.prime._PRIME.SYS.NMP;

import com.uchump.prime._PRIME.N_M._N.Node;
import com.uchump.prime._PRIME.N_M._N.Type;
import com.uchump.prime._PRIME.SYS.A_I.aProperty;
import com.uchump.prime._PRIME.SYS.Prototype.LookupTable;

public class _Component extends aProperty<aProperty> {

	public static Type Component = new Type("Component", _Component.class);
	public static Type ComponentType = new Type("Component", _Component.class, Type.class);

	public _Entity owner;

	public LookupTable<Node> Properties = new LookupTable<Node>();

	public _Component() {
		super();
	}

	public _Component(String label, Type data) {
		super(label, data);

	}

	public _Component(String label, aProperty p) {
		super(label, p);
	}

	
	
	public void assign(_Entity e)
	{
		this.owner = e;
	}
	
	@Override
	public aProperty get(String key) {
		// MUST LEAD AND END w/ <|>
		return (aProperty) this.Properties.get(key);
	}
	
	public aProperty get(String key, String val)
	{
		return (aProperty) this.Properties.get(key,val);
	}

	public void addProperty(aProperty p) {
		if (!this.Properties.contains(p.Label)) {
			this.Properties.add(p);
			p.header = this;
		}
	}

	public void removeProperty(aProperty p) {
		if (!this.Properties.contains(p.Label)) {
			this.Properties.remove(p);
			p.header = null;
		}
	}

	public boolean hasProperty(aProperty p) {
		return this.Properties.contains(p);
	}

	public boolean hasProperty(String p) {
		for (Node P : this.Properties) {
			if (P.Name().equals(p))
				return true;
		}

		return false;
	}

	public boolean hasPropertyOf(Type t) {
		for (Node P : this.Properties) {
			if (P.Type().equals(t))
				return true;
		}

		return false;
	}

	public boolean hasPropertyOf(String p) {
		for (Node P : this.Properties) {
			if (P.Type().Name().equals(p))
				return true;

			if (P.Type().Node.Name().equals(p))
				return true;
		}

		return false;
	}

	public boolean has(String s) {

		return (this.hasProperty(s) || this.hasPropertyOf(s));
	}

	public boolean has(Type t) {

		return this.hasPropertyOf(t);
	}

	public _Component cpy() {
		_Component C = new _Component(this.Label, this.Type());

		if (this.owner != null)
			C.owner = this.owner;

		if(this.Data!= null)
			C.Data=this.Data.cpy();
		
		return C;
	}

	@Override
	public String toString() {
		return this.Name() + ":" + this.get() + "   " + this.Class().getSimpleName() + this.Node + ":" + this.Type();
	}

	@Override
	public String toLog() {
		String log = this.toString() + "\n";
		String ind = "";
		for (int i = 0; i < this.Name().length(); i++)
			ind += " ";

		if (!this.Properties.isEmpty()) {

			log += ind + ":{Properties}:\n";
			for (Node p : this.Properties) {
				log += ind + "  " + p.toLog() + "\n";
			}
		}

		return log;
	}
}
