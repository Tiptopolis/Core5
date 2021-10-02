package com.uchump.prime._PRIME.UI._Events;

import com.uchump.prime._PRIME.SYS.A_I.iAgent;

public interface iEventSender extends iAgent{

	
	
	public void broadcast();
	public void broadcast(Object at);
}
