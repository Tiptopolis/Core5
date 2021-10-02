package com.uchump.prime._PIG.UNI.QRG.Data.Types;

public enum GL_DataTypes {

	VOID("void"), //
	//
	BOOL("bool"), //
	BVECT2("bvect2"), //
	BVECT3("bvect3"), //
	BVECT4("bvect4"), //
	//
	INT("int"), //
	IVECT2("ivect2"), //
	IVECT3("ivect3"), //
	IVECT4("ivect4"), //
	//
	FLOAT("float"), //
	FVECT2("vect2"), //
	FVECT3("vect3"), //
	FVECT4("vect4"),//
	//
	SAMPLER1D("sampler1D"),//
	SAMPLER2D("sampler2D"),//
	SAMPLER3D("sampler3D"),//
	SAMPLERCUBE("samplerCube"),
	SAMPLER1DSHADOW("sampler1DShadow"),//
	SAMPLER2DSHADOW("sampler2DShadow"),//
	//
	
	;

	public String handle;

	private GL_DataTypes(String handle) {
		this.handle = handle;
	}

	public String get() {
		return this.handle;
	}

}
