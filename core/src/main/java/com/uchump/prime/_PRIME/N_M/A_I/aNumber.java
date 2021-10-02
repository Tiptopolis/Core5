package com.uchump.prime._PRIME.N_M.A_I;

import com.uchump.prime._PRIME.N_M.N_;
import com.uchump.prime._PRIME.N_M.UTIL.iStatique;
import com.uchump.prime._PRIME.N_M._N.Type;

public  class aNumber extends N_ {
	public static Type Number = new Type("Number", Number.class);
	public static Type NumberType = new Type("Number", Number.class, Type.Type);
	public aNumber() {
		super();
	}

	public aNumber(Number n) {
		super(n);
	}

	@Override
	public Object Class() {
		return Number;
	}

	

}
