package com.uchump.prime._PIG.UNI.QRG.Data.Qualifiers;

public enum _GL_FnParams {

	IN("in"),
	OUT("out"),
	INOUT("inout"),
	CONST("const")
	;
	
	public String handle;
	
	private _GL_FnParams(String handle)
	{
		this.handle = handle;
	}
	
	public String get()
	{
		return this.handle;
	}
	
}
