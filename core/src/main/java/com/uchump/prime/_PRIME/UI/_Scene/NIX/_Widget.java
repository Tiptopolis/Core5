package com.uchump.prime._PRIME.UI._Scene.NIX;

import com.uchump.prime._PRIME.SYS.A_I.aProperty;
import com.uchump.prime._PRIME.SYS.NMP._Object;

public class _Widget extends _Object {

	
	public _Widget()
	{
		super();
		this.addProperty(new aProperty("Actor", new _Actor()));
	}
	
}
