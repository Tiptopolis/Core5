package com.uchump.prime._RAUM.RAUM.META.Prototype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.uchump.prime._PRIME.N_M._N;
import com.uchump.prime._PRIME.N_M._N.Node;
import com.uchump.prime._PRIME.N_M._N.Type;
import com.uchump.prime._PRIME.SYS.A_I.aProperty;
import com.uchump.prime._PRIME.SYS.NMP._Object;
import com.uchump.prime._RAUM.RAUM.META.NIX._Archetype;

public class dEnum extends Dex {

	protected _Archetype BaseType;

	public HashMap<String, _N> Values = new HashMap<String, _N>(8);
	// public ArrayList<String> Values = new ArrayList<String>();

	public dEnum(String label) {
		super(label);
		this.BaseType = new _Archetype(label);
		this.req(this.BaseType.required().toArray());
		def();
	}

	public dEnum(String name, String label) {
		super(name);
		this.BaseType = new _Archetype(label);
		this.req(this.BaseType.required().toArray());
		def();
	}

	public dEnum(_Archetype type) {
		super(type.Name());
		this.base(type);
		this.req(type.required().toArray());
		def();

	}

	public void base(_Archetype type) {
		this.BaseType = type;
		this.req(type.required().toArray());
	}

	public _Archetype base(String label, aProperty... properties) {
		_Archetype baseType = new _Archetype(label);
		this.BaseType = baseType;
		this.req(properties);

		return baseType;
	}

	public dEnum req(aProperty... properties) {

		for (aProperty P : properties) {
			this.BaseType.Required.put(P.Name(), P);
		}

		return this;
	}

	@Override
	public boolean def(String t) {
		if (this.LocalTypeRepository.isEmpty()) {
			this.addProperty(new aProperty("LocalType$" + this.LocalTypeRepository.values().size(),
					new Type(false, "?", this.BaseType.Kind)));
			this.Values.put("<?>:<" + this.BaseType.Reference + ">", new Type(false, "?", this.BaseType.Kind));
			this.BaseType.variant( new Type(false, "?", this.BaseType.Kind));
		}
		
		Type nT = new Type(false, t, this.BaseType.Kind);

		if (this.LocalTypeRepository.containsValue(nT) || this.LocalTypeRepository.containsKey(t))
			return false;

		else {
			LocalTypeRepository.put(t, nT);
			this.addProperty(new aProperty("LocalType$" + this.LocalTypeRepository.values().size(), nT));
			this.Values.put("<" + t + ">:<" + this.BaseType.Reference + ">", nT);
			this.BaseType.Variants.add(nT);
			return true;
		}

	}

	public dEnum def() {
		return this;
	}
	
	public _Archetype getTemplate()
	{
		return this.BaseType;
	}

	@Override
	public String toString() {
		return this.Name() + ":" + this.get() + "   " + this.Class().getSimpleName() + this.Node + ":" + this.Type();
	}

	@Override
	public String toLog() {
		String log = this.toString() + "\n";
		log += this.BaseType + "\n";

		String ind = "";
		for (int i = 0; i < this.Name().length(); i++)
			ind += " ";

		if (!this.Properties.isEmpty()) {

			log += ind + ":{Defined}:\n";
			for (Node p : this.Properties) {
				log += ind + "  " + p.Name() + " " + p.get() + "\n";
			}
		}

		if (!this.Values.isEmpty()) {
			log += ind + ":{Enum}:\n";
			for (Entry<String, _N> e : this.Values.entrySet()) {
				log += ind + " -" + e + "\n";
			}
		}

		if (!this.BaseType.Required.isEmpty()) {

			log += ind + ":{Required}:\n";
			for (aProperty p : this.BaseType.Required.values()) {
				log += ind + " *" + p + "\n";
			}
		}

		return log;
	}

}
