package com.uchump.prime._RAUM.RAUM.META.Prototype;

import java.util.HashMap;

import com.uchump.prime._PRIME.N_M._N.Type;
import com.uchump.prime._PRIME.SYS.A_I.aProperty;
import com.uchump.prime._PRIME.SYS.NIX._VTS;
import com.uchump.prime._PRIME.SYS.NMP._Component;
import com.uchump.prime._PRIME.SYS.NMP._Object;
import com.uchump.prime._RAUM.RAUM.META.CMP._Dex;

public class Dex extends _Dex {

	public Dex(String header) {
		super(header + "_");
		
	}

	public boolean def(String t) {
		if(this.LocalTypeRepository.isEmpty())
			this.addProperty(new aProperty("LocalType$" + this.LocalTypeRepository.values().size(), new Type(false,"?",_Object.Object)));
		Type nT = new Type(false,t, _Object.Object);

		if (this.LocalTypeRepository.containsValue(nT) || this.LocalTypeRepository.containsKey(t))
			return false;

		else {
			LocalTypeRepository.put(t, nT);
			this.addProperty(new aProperty("LocalType$" + this.LocalTypeRepository.values().size(), nT));
			return true;
		}
	}
	
	public boolean def(String t, String v) {
		if(this.LocalTypeRepository.isEmpty())
			this.addProperty(new aProperty("LocalType$" + this.LocalTypeRepository.values().size(), new Type(false,"?",new Type(false,t))));
		Type nT = new Type(false,v,new Type(false,t));

		if (this.LocalTypeRepository.containsValue(nT) && this.LocalTypeRepository.containsKey(t))
			return false;

		else {
			LocalTypeRepository.put(t, nT);
			this.addProperty(new aProperty("LocalType$" + this.LocalTypeRepository.values().size(), nT));
			return true;
		}
	}

}
