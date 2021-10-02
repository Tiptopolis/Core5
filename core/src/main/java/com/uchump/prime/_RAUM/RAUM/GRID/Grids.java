package com.uchump.prime._RAUM.RAUM.GRID;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.HashMap;
import java.util.Map.Entry;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime._PRIME.C_O.Geom;
import com.uchump.prime._PRIME.C_O.VectorUtils;
import com.uchump.prime._RAUM.RAUM.GFX._BoundShape;
import com.uchump.prime._RAUM.RAUM.MAP._Layer;
import com.uchump.prime._RAUM.RAUM.META.NIX._Thing;

public class Grids {

	public static RectLayerGen RectLayerGenerator;

	public static _Layer RectLayer(String label, int width, int height) {
		RectLayerGenerator = new RectLayerGen();
		_Layer L = RectLayerGenerator.genLayer(label, width, height);
		RectLayerGenerator = null;
		return L;
	}

	/////////
	//// RECT LAYER

	//// RECT LAYER
	/////////
	//// HEX LAYER

	//// HEX LAYER
	/////////

	public static _Layer BoxBlur(_Layer L, String byProp) {
		_Layer protemp = L.cpy();
		Kernel K = Kernel.def(new Vector2(3, 3));
		Log(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		Log(K.toLog());
		Log();

		for (Entry<Vector3, _Thing> E : protemp.Cells.entrySet()) {
			_Thing T = E.getValue();
			if (T.has(byProp) && Number.class.isAssignableFrom(T.get(byProp).get().getClass())) {
				Number N = (Number) T.get(byProp).get();
				
			} else
				return L;
		}
		
		
		
		protemp.dispose();
		return L;

	}

	public static class Kernel extends _BoundShape {
		// _________________________
		// |(-1, 1), ( 0, 1), ( 1, 1)|
		// |(-1, 0), ( 0, 0), ( 1, 0)|
		// |(-1,-1), ( 0,-1), ( 1,-1)|
		// -------------------------
		//
		// _________________________
		// |( 0, 1), ( 1, 1), ( 2, 1)|
		// |( 0, 0), ( 1, 0), ( 2, 0)|
		// |( 0,-1), ( 1,-1), ( 2,-1)|
		// -------------------------
		//
		// _________________________
		// |( 0, 0), ( 1, 0), ( 2, 0)|
		// |( 0,-1), ( 1,-1), ( 2,-1)|
		// |( 0,-2), ( 1,-2), ( 2,2)|
		// -------------------------
		// size, layout, & direction
		//
		// establish rect of Size

		HashMap<Vector3, _Thing> layout;
		HashMap<Vector3, String> labels;

		protected Kernel() {
			super();
		}

		public static Kernel def(Vector2 size, Vector2 dirOffset, int n, boolean index) {

			Kernel K = new Kernel();
			// Vector3 at = new Vector3(size.x/2, size.y/2, 0);
			Vector3 at = new Vector3();
			Vector3 radius = new Vector3(size.x / 2, size.y / 2, 1);
			Vector3 rotation = new Vector3(0, 1, 0);

			K.Transform.SetPosition(at);
			K.Transform.SetScale(radius);
			K.Transform.SetRotation(VectorUtils.upcast(rotation));
			K.fillColor = Color.CLEAR;
			K.lineColor = Color.BLACK;
			K.vertexNum = n;

			K.shape = Geom.generatePolygon(at, radius, rotation, n);
			if (index)
				for (Vector3 V : K.shape) {
					V.x = MathUtils.round(V.x);
					V.y = MathUtils.round(V.y);
					V.z = MathUtils.round(V.z);
				}
			return K;

		}

		public static Kernel def(Vector2 size, Vector2 dirOffset, int n) {
			return def(size, dirOffset, n, true);
		}

		public static Kernel def(Vector2 size, Vector2 dirOffset) {
			return def(size, dirOffset, 4);
		}

		public static Kernel def(Vector2 size) {
			return def(size, new Vector2(0, 0), 4);
		}

		public static Kernel apply(Vector2 atOffset) {
			return null;
		}

		@Override
		public String toLog() {
			String log = "";
			int c = 0;
			// for (int i = this.shape.size()-1; i >= 0 ; i--) {
			for (int i = 0; i < this.shape.size(); i++) {
				c++;
				Vector3 V = this.shape.get(i);
				log += i;
				log += V;
				log += " ";
				if ((MathUtils.isEqual(c % (this.Transform.GetLocalScale().y - 1), 0f)))
					log += this.Transform.GetLocalScale().y * 2 + "\n";

			}
			return log;
		}
	}

}
