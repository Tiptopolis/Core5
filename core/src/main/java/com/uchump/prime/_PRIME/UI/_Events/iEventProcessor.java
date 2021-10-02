package com.uchump.prime._PRIME.UI._Events;

import com.badlogic.gdx.InputProcessor;
import com.uchump.prime._PRIME.UI._Events.Prototype.Message;

public interface iEventProcessor extends iEventReciever,iEventSender, InputProcessor{

	public boolean handle(Message m);
	
}
