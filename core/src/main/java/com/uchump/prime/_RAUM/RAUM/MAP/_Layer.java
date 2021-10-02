package com.uchump.prime._RAUM.RAUM.MAP;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.uchump.prime._PRIME.C_O.NIX.Rect;
import com.uchump.prime._PRIME.N_M._N.Node;
import com.uchump.prime._PRIME.N_M._N.Type;
import com.uchump.prime._PRIME.SYS.A_I.aProperty;
import com.uchump.prime._PRIME.SYS.NMP._Entity;
import com.uchump.prime._PRIME.SYS.NMP._Object;
import com.uchump.prime._PRIME.SYS.NMP._Struct;
import com.uchump.prime._PRIME.SYS.Prototype.iGraph;
import com.uchump.prime._RAUM.RAUM.META.NIX._Thing;
import com.uchump.prime._RAUM.RAUM.META.Prototype.Dex;
import com.uchump.prime._RAUM.RAUM.META.NIX._Archetype;

public class _Layer extends _Object implements iGraph {

	public static Type Layer = new Type("Layer", _Struct.class, Object.class);


	public boolean active = true;
	public _Layer Of;
	public Polygon Area;

	public Vector3 Size = new Vector3(1, 1, 1);
	public Vector3 CellSize = new Vector3(1, 1, 1);
	public Rect Bounds = new Rect(0, 0, 1, 1);

	public _Archetype CellTemplate;

	public ArrayList<_Layer> Layers = new ArrayList<_Layer>();
	public HashMap<Vector3, _Thing> Cells = new HashMap<Vector3, _Thing>(0);
	public ArrayList<_Entity> Present = new ArrayList<_Entity>();

	public _Layer(String label) {
		super(label);
		this.Node = Layer;
	}

	public _Layer(String label, int width, int height) {
		super(label);
		this.Size = new Vector3(width, height, 1);
		this.Node = Layer;
	}

	public _Layer(String label, float width, float height) {
		super(label);
		this.Size = new Vector3(width, height, 1);
		this.Node = Layer;
	}

	public _Layer(_Layer of) {
		super(of.Label);
		this.Node = Layer;
		this.Of = of;
	}

	public void pop(_Layer toTop) {
		if (this.Layers.contains(toTop)) {
			this.Layers.remove(toTop);
			this.Layers.add(toTop);
		} else
			this.Layers.add(toTop);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object put(Object key, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putAll(Map m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public Set keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node Vertex() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap Key(String k, Node v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Array Vertices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean newVertex(Node at) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Array Edges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(Node N) {
		// TODO Auto-generated method stub

	}

	public Array<_Layer> layers() {
		Array<_Layer> pN = new Array<_Layer>(false, 1, Node.class);
		if (this.Properties == null)
			for (_Layer P : this.Layers) {
				pN.add(P);
			}
		return pN;
	}

	public _Layer cpy() {
		_Layer copy = new _Layer(this.Name());

		for (Node N : this.Properties) {
			if (N instanceof aProperty)
				copy.addProperty((aProperty) N.cpy());
		}
		if (!this.Cells.isEmpty()) {
			for (Entry<Vector3, _Thing> E : this.Cells.entrySet()) {
				_Thing C = E.getValue().cpy();
				if (C.has("LayerID")) {

					C.that.set(copy);
				}
				copy.Cells.put(E.getKey().cpy(), C);
			}
		}

		return copy;
	}

	public _Layer clone(String newName, boolean strict) {
		_Layer copy = new _Layer(newName);
		copy.CellSize = this.CellSize.cpy();
		copy.CellTemplate = this.CellTemplate;

		for (Node N : this.Properties) {
			if (N instanceof aProperty)
				if (strict) {
					copy.addProperty((aProperty) N);
				} else {
					copy.addProperty((aProperty) N.cpy());
				}
		}
		if (!this.Cells.isEmpty()) {
			for (Entry<Vector3, _Thing> E : this.Cells.entrySet()) {
				_Thing C = null;
				if (strict) {
					C = E.getValue();
				} else {
					C = E.getValue().cpy();
				}
				if (C.has("LayerID")) {

					C.that.set(copy);
				}
				copy.Cells.put(E.getKey().cpy(), C);
			}
		}

		return copy;
	}

	public void dispose() {
		for (Entry<Vector3, _Thing> E : this.Cells.entrySet()) {
			_Thing T = E.getValue();
			T.destroy();
		}

		this.CellTemplate = null;
		this.Cells.clear();

	}

	@Override
	public String toString() {
		return "[" + this.Name() + "_" + this.Node.Name() + "]@" + this.Class().getSimpleName() + "[" + this.Type()
				+ "]";
		// REFERENCE NODE INDEX SYMBOL
	}

	@Override
	public String toLog() {
		String log = "";
		log += this.toString() + "\n";
		log += this.Of + "\n";
		log += "\n";
		log += "Layers: [" + this.Layers.size() + "]";
		log += "\n";
		String a = "";
		for (_Layer L : this.Layers) {
			if (L.active)
				a = "*";
			log += ("_" + a + L + this.Size + "\n");
		}
		log += "\n";
		log += "Properties: [" + this.Properties.size() + "]";
		log += "\n";
		for (Node N : this.Properties) {
			log += ("*" + N + "\n");
		}

		return log;
	}

}
