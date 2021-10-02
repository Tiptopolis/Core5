package com.uchump.prime._RAUM.RAUM.META.Prototype;

import java.util.HashMap;
import java.util.Map.Entry;

import com.uchump.prime._PRIME.N_M._N.Node;
import com.uchump.prime._PRIME.N_M._N.Type;
import com.uchump.prime._PRIME.SYS.A_I.aProperty;
import com.uchump.prime._PRIME.SYS.NMP._Object;
import com.uchump.prime._RAUM.RAUM.META.CMP._Flags;

public class Flags extends _Flags {

	public Flags(String header) {
		super(header + "_FLG");
	}

	public boolean def(String t) {
		if(this.Get == null)
			this.Get = new HashMap<String, Boolean>();
		Type nT = new Type(false, t, _Object.Object);

		if (this.Get.containsValue(nT) || this.Get.containsKey(t))
			return false;

		else {
			Get.put(t, false);
			//aProperty p = new aProperty("Flag$" + this.Get.values().size(), nT);
			aProperty p = new aProperty(t, nT);
			this.addProperty(p);
			return true;
		}

	}

	public boolean is(String s) {

		for(Entry<String, Boolean> E : this.Get.entrySet())
		{
			if(E.getKey().equals(s))
				return E.getValue();
		}
		
		return this.has(s);
	}

	public boolean is(Type t) {

		
		for(Entry<String, Boolean> E : this.Get.entrySet())
		{
			if(E.getKey().contains(t.Name()))
				return E.getValue();
		}
		
		return this.has(t);
	}
	
	@Override
	public Flags cpy() {
		Flags C = new Flags("");
		C.Label = this.Label;

		if (this.owner != null)
			C.owner = this.owner;
		C.Get =  (HashMap<String, Boolean>) this.Get.clone();
		for (Node N : this.properties()) {
			if (N instanceof aProperty)
				C.addProperty(((aProperty) N).cpy());
		}

		return C;
	}
}
