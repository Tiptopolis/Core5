package com.uchump.prime._PRIME.SYS.A_I;

import com.uchump.prime._PRIME.C_O.NIX.Transform;

public interface iMonad {
	
	public default boolean exists()
	{
		return true;
	}
	
	public default void exists(boolean f)
	{
		this.exists();		
	}
	
	public default String getName()
	{
		return this.getClass().getSimpleName();
	}
	
	public Transform getTransform();
	void transformUpdated(); //informed by transform that it has updated
	
	public String toLog();
}
