package com.uchump.prime._PRIME.SYS.A_I;

import static com.uchump.prime._PRIME.uAppUtils.*;

import com.uchump.prime._PRIME.N_M._N.Node;
import com.uchump.prime._PRIME.N_M._N.Type;
import com.uchump.prime._PRIME.SYS.NIX._VTS;

public class aProperty<T> extends Node<T> {

	public static Type Property = new Type("Property", aProperty.class);
	public static Type PropertyType = new Type("Property", aProperty.class, Type.class);

	public Node header;
	public Type Node;

	public aProperty() {
		this.Label = "aProperty";
		this.Of = Property;
		this.Data = (T) PropertyType;
		this.Node = Property;
	}

	public aProperty(String name, T data) {
		this.Label = name;
		this.Of = new Type(data.getClass().getSimpleName(), data.getClass());
		this.Data = data;
		this.Node = Property;
	}

	public aProperty(String label, Type data) {
		this.Label = label;
		this.Of = _VTS.getA(data.Type.Reference);
		this.Data = (T) data;
		// this.Node=data;
		this.Node = Property;
	}
	
	public aProperty(aProperty<T> p)
	{
		this.Label = p.Label;
		this.Of=p.Of;
		this.Data = p.Data;
		this.Node = Property;
	}

	@Override
	public <O extends Object, aProperty> aProperty set(O in) {
		this.Data = (T) in;
		return (aProperty) this;
	}
	
	
	public aProperty cpy()
	{
		return new aProperty(this);
	}

	public String headString() {
		String aCap = "";
		String bCap = "";
		if (this.Data instanceof Type) {
			aCap = "(";
			bCap = ")";
		}
		return this.Name() + ":" +aCap+ this.get()+bCap;
	}

	public String tailString() {
		return this.Class().getSimpleName() + this.Node + ":" + this.Type();
	}

	@Override
	public String toString() {
		

		return "[" + this.headString() + "]" + "[" + this.tailString() + "]";
	}

	@Override
	public String toLog() {
		return this.toString();
	}

}
