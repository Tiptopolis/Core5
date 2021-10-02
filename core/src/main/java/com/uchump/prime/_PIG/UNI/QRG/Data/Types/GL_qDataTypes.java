package com.uchump.prime._PIG.UNI.QRG.Data.Types;

public enum GL_qDataTypes {

	V("void"),
	B("bool"),
	I("int"),
	F("float"),
	V2("vec2"),
	V3("vec3"),
	V4("vec4"),
	;
	
	
	
	public String handle;
	
	private GL_qDataTypes(String handle)
	{
		this.handle = handle;
	}
	
	public String get()
	{
		return this.handle;
	}
	
}
