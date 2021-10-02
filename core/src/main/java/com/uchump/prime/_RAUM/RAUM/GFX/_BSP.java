package com.uchump.prime._RAUM.RAUM.GFX;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime._PRIME.SYS.Prototype.Tree;
import com.uchump.prime._RAUM.RAUM.META.Prototype.Dex;

public class _BSP extends Tree {

	// BinarySpacePartition
	// list of edges, basically, on top of _BS.shape
	public static Dex PartitionTypes = new Dex("PartitionTypes");
	
	
	
	
	public _BSP() {
		super();
		this.Root=this;
	}
	


}
