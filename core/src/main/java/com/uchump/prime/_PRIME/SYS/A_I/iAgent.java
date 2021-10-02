package com.uchump.prime._PRIME.SYS.A_I;

import com.uchump.prime._PRIME.UI._Events.Prototype.Signal;

public interface iAgent extends iMonad{

	public default iAgent getSource() {
		return this;
	}
	
	public default Signal<?> signal()
	{
		return null;
	}
	
	public default Signal<?> signal(Signal s)
	{
		return s;
	}
	
}
