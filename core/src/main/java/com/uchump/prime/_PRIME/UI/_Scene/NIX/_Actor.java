package com.uchump.prime._PRIME.UI._Scene.NIX;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.uchump.prime._PRIME.C_O.NIX.Transform;
import com.uchump.prime._PRIME.N_M._N;
import com.uchump.prime._PRIME.N_M._N.Node;
import com.uchump.prime._PRIME.SYS.A_I.iMonad;
import com.uchump.prime._PRIME.UI._GFX.Drawable.iDrawable;
import com.uchump.prime._PRIME.UI._Scene.uInputListener;

public class _Actor extends Actor implements iDrawable, _N, iMonad{

	public String label;
	public Transform transform;
	public Node<Actor> node;
	public uInputListener hit;
	
	public _Actor()
	{
		this.label = "Actor";
		this.transform = new Transform();
		this.node = new Node("Actor", this);
		this.hit = new uInputListener();
		this.addListener(this.hit);
	}
	
	@Override
	public void draw() {

		
	}
	
	@Override
	public Object get() {
		
		return this;
	}
	
	@Override
	public Transform getTransform() {
		return this.transform;
	}

	@Override
	public void transformUpdated() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String Name() {
		return this.label;
	}

	@Override
	public Object Type() {
		
		return this.node.Type();
	}

	@Override
	public Object Class() {
		
		return Actor.class;
	}
	
	@Override
	public _N Symbol()
	{
		return this.node;
	}



	@Override
	public String toLog() {
		
		return this.label;
	}





}
