package com.uchump.prime._RAUM.RAUM.META.Prototype;

import java.util.HashMap;
import java.util.Map.Entry;

import com.uchump.prime._PRIME.N_M._N.Type;
import com.uchump.prime._PRIME.SYS.A_I.aProperty;
import com.uchump.prime._PRIME.SYS.NMP._Component;
import com.uchump.prime._PRIME.SYS.NMP._Object;
import com.uchump.prime._RAUM.RAUM.META.CMP._Tags;

public class Tags extends _Tags {

	public Tags(String header) {
		super(header + "_TAG");
		// TODO Auto-generated constructor stub
	}

	public boolean def(String key, String value) {
		if (this.Get == null)
			Get = new HashMap<String, Type>();
		Type nT = new Type(false, value, new Type(false,"TAG"));

		if (this.Get.containsValue(nT))
			return false;

		else {
			Get.put(key, nT);
			aProperty p = new aProperty(key, nT);
			this.addProperty(p);
			return true;
		}

	}

	public boolean def(Type nT)
	{
		if (this.Get.containsValue(nT))
			return false;

		else {
			Get.put(nT.Reference, nT);
			aProperty p = new aProperty(nT.Reference, nT);
			this.addProperty(p);
			return true;
		}
		
	}
	
	public boolean is(String s) {

		for (Entry<String, Type> E : this.Get.entrySet()) {
			if (E.getKey().contains(s))
				return E.getValue().equals(s) || E.getValue().isOf(s);
		}

		return this.has(s);
	}

	public boolean is(Type t) {

		for (Entry<String, Type> E : this.Get.entrySet()) {
			if (E.getKey().contains(t.Name()))
				return E.getValue().equals(t) || E.getValue().isOf(t);
		}

		return false;
	}

	@Override
	public Tags cpy() {
		Tags C = new Tags("");
		C.Label = this.Label;
		if (this.owner != null)
			C.owner = this.owner;
		C.Get = (HashMap<String, com.uchump.prime._PRIME.N_M._N.Type>) this.Get.clone();
		for (Node N : this.properties()) {
			if (N instanceof aProperty)
				C.addProperty(((aProperty) N).cpy());
		}

		return C;
	}

}
