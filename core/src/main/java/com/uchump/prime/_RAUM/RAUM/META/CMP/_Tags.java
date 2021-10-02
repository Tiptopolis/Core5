package com.uchump.prime._RAUM.RAUM.META.CMP;

import java.util.ArrayList;
import java.util.HashMap;

import com.uchump.prime._PRIME.N_M._N.Type;
import com.uchump.prime._PRIME.SYS.A_I.aProperty;
import com.uchump.prime._PRIME.SYS.NIX._VTS;
import com.uchump.prime._PRIME.SYS.NMP._Component;
import com.uchump.prime._PRIME.SYS.NMP._Object;

public class _Tags extends _Component{
	// like a _Dex but for boolean properties
	public HashMap<String, Type> Get = new HashMap<String, Type>(0);

	public _Tags(String header) {
		super(header, new Type(false,"Of$", _Object._C));
	}
	
	
	

	@Override
	public boolean hasProperty(String t) {
		return this.Get.containsKey(t);

	}
	
	@Override
	public boolean hasProperty(aProperty p)
	{
		return Get.containsKey(p.Name()) || this.Get.containsValue(p);
	}
	
	
	public boolean hasProperty(Type t)
	{
		return Get.containsValue(t);
	}
}
