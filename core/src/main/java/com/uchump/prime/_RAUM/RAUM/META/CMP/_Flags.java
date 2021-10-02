package com.uchump.prime._RAUM.RAUM.META.CMP;

import java.util.HashMap;
import java.util.Map.Entry;

import com.uchump.prime._PRIME.N_M._N.Node;
import com.uchump.prime._PRIME.N_M._N.Type;
import com.uchump.prime._PRIME.SYS.A_I.aProperty;
import com.uchump.prime._PRIME.SYS.NIX._VTS;
import com.uchump.prime._PRIME.SYS.NMP._Component;
import com.uchump.prime._PRIME.SYS.NMP._Object;

public class _Flags extends _Component {
	// like a _Dex but for boolean properties
	public HashMap<String, Boolean> Get = new HashMap<String, Boolean>(8);

	public _Flags(String header) {
		// super(header, _VTS.getA("Is$"));
		super(header, new Type(false, "Is$", _Object._C));
	}

	@Override
	public boolean hasProperty(String t) {
		return this.Get.containsKey(t);

	}

	@Override
	public boolean hasProperty(aProperty p) {
		return Get.containsKey(p.Name());
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

		log += ind + ":{Flags}:\n";
		if (!this.Get.isEmpty()) {

			for (Entry<String, Boolean> E : this.Get.entrySet()) {

				log += ind + "  #" + E + "\n";
			}
		}

		return log;
	}

}