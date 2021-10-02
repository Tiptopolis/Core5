package com.uchump.prime._RAUM.RAUM.META.CMP;

import java.util.ArrayList;
import java.util.HashMap;

import com.uchump.prime._PRIME.N_M._N.Name;
import com.uchump.prime._PRIME.N_M._N.Node;
import com.uchump.prime._PRIME.N_M._N.Type;
import com.uchump.prime._PRIME.SYS.NIX._VTS;
import com.uchump.prime._PRIME.SYS.NMP._Component;
import com.uchump.prime._PRIME.SYS.NMP._Struct;
import com.uchump.prime._RAUM.RAUM.WORLD._World;

public class _Dex extends _Component {

	public HashMap<String, Type> LocalTypeRepository = new HashMap<String, Type>(8);

	public _Dex(String header) {
		super(header, _VTS.getA("LookUp"));
		if(_World.CoreMeta == null)
			_World.CoreMeta = new ArrayList<_Dex>();
		_World.CoreMeta.add(this);
	}

	@Override
	public boolean hasProperty(String t) {

		return this.LocalTypeRepository.containsKey(t);

	}

	@Override
	public boolean hasPropertyOf(String t) {
		for (Type T : LocalTypeRepository.values()) {
			if (T.Name().equals(t) || T.Node.Name().equals(t))
				return true;
		}
		return false;
	}

	@Override
	public boolean hasPropertyOf(Type t) {
		return this.LocalTypeRepository.containsValue(t);
	}

	public void append(Type t) {
		if (!this.LocalTypeRepository.containsValue(t))
			this.LocalTypeRepository.put(t.Reference, t);
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

			log += ind + ":{Defined}:\n";
			for (Node p : this.Properties) {
				log += ind + "  " + p.Name() + " " + p.get() + "\n";
			}
		}

		return log;
	}

}
