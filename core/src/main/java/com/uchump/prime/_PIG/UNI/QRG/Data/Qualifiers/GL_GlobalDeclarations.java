package com.uchump.prime._PIG.UNI.QRG.Data.Qualifiers;

public enum GL_GlobalDeclarations {

	//data qualifiers
	ATTRIBUTE("attribute"),VARYING("varying"),UNIFORM("uniform"), CONST("const");
	
	public String handle;
	
	private GL_GlobalDeclarations(String handle)
	{
		this.handle = handle;
	}
	
	public String get()
	{
		return this.handle;
	}

}
