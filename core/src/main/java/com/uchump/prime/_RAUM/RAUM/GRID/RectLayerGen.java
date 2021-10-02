package com.uchump.prime._RAUM.RAUM.GRID;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime._PRIME.C_O.RNG;
import com.uchump.prime._PRIME.N_M._N.Node;
import com.uchump.prime._PRIME.SYS.A_I.aProperty;
import com.uchump.prime._RAUM.RAUM.GFX._BoundShape;
import com.uchump.prime._RAUM.RAUM.MAP._Layer;
import com.uchump.prime._RAUM.RAUM.META.NIX._Archetype;
import com.uchump.prime._RAUM.RAUM.META.NIX._Thing;
import com.uchump.prime._RAUM.RAUM.META.Prototype.Dex;

public class RectLayerGen {

	public static _Layer genLayer(String label, int width, int height) {
		_Layer RECT = new _Layer(label, width, height);
		_Archetype RectCell = new _Archetype(label);
		RectCell.require(new aProperty("IndexID", 0));
		RectCell.require(new aProperty("LayerID", RECT));
		RectCell.require(new aProperty("Offset", new Vector3()));// location
		RectCell.require(new aProperty("Shape", _BoundShape.defVertex(new Vector3(), 1, Color.CLEAR)));
		RectCell.require(new aProperty("RndInt", 0));
		RECT.CellTemplate = RectCell;
		RECT.CellSize = new Vector3(32, 32, 1);

		return Grids.BoxBlur(genLinks(genTags(genCells(RECT))), "RndInt");
	}

	protected static _Layer genCells(_Layer L) {
		int c = 0;
		Vector2 ij = new Vector2();
		for (int i = 0; i < L.Size.x; i++) {
			for (int j = 0; j < L.Size.y; j++) {
				ij.set(i, j);
				_Thing newCell = new _Thing("Cell" + c, L.CellTemplate);
				newCell.get("IndexID").set(c);
				newCell.ID = new aProperty("CellID", L.Name() + "@" + c);
				Vector3 pos = new Vector3(i, j, 1);
				newCell.get("Offset").set(pos);
				_BoundShape b = _BoundShape.defShape(pos.cpy().scl(L.CellSize.cpy()).add(L.CellSize.cpy().scl(0.5f)),
						L.CellSize.cpy().scl(0.7f), new Vector3(0, 0, 0), Color.CLEAR, 4, true);

				newCell.get("BoundShape").set(b);
				b.fill = true;
				b.lines = true;
				b.fillColor = Color.CLEAR;
				b.lineColor = Color.BLACK;
				L.Cells.put(pos, newCell);

				newCell.get("RndInt").set(RNG.rndIntSeed(ij, new Vector2(0, 255)));

				c++;
				// OK, 1000 cells, each with an index & Layer ID component
			}
		}
		return L;
	}

	protected static _Layer genTags(_Layer L) {
		// combine into a RectData_Thing maybe?

		Vector3 S = L.Size.cpy();
		Vector2 min = new Vector2();
		Vector2 max = new Vector2(S.x, S.y);
		Vector2 BL = new Vector2();
		Vector2 BR = new Vector2(S.x, 0);
		Vector2 TL = new Vector2(0, S.y);
		Vector2 TR = new Vector2(S.x, S.y);
		for (int q = 0; q < max.x; q++) {
			for (int r = 0; r < max.y; r++) {
				Vector3 at = new Vector3(q, r, 1);
				_Thing C = L.Cells.get(at.cpy());
				if (C != null) {
					String markA = "";
					String markB = "";
					if (q == 0 && r == 0)
						markA = ".{Left,Bottom}";

					if (q == 0 && r == max.y)
						markA = ".{Left,Top}";

					if (q == max.x && r == 0)
						markA = ".{Right,Bottom}";

					if (q == max.x && r == max.y)
						markA = ".{Right,Top}";

					if (q == min.x)
						markB = ".<Boundary:Left>";
					if (r == min.y)
						markB = ".<Boundary:Bottom>";

					if (q == max.x)
						markB = ".<Boundary:Right>";
					if (r == max.y)
						markB = ".<Boundary:Top>";

					if (!markA.equals("")) {
						C.Tags.def("Corner", markA);
					}

					if (!markB.equals("")) {
						C.Tags.def("Bondary", markB);
					}
				}

			}
		}

		return L;
	}

	public static _Layer genLinks(_Layer L) {
		Vector3 S = L.Size.cpy();
		for (_Thing C : L.Cells.values()) {
			int xBound = 0; // -1 to 1;
			int yBound = 0; // -1 to 1;
			Vector3 p = (Vector3) C.get("Offset").get();
			if (MathUtils.isEqual(p.x, 0))
				xBound = -1;
			if (MathUtils.isEqual(p.x, S.x - 1))
				xBound = 1;
			if (MathUtils.isEqual(p.y, 0))
				yBound = -1;
			if (MathUtils.isEqual(p.y, S.y - 1))
				yBound = 1;

			if (xBound != -1) {
				Node<_Thing> N = new Node("Left:Neighbor",
						L.Cells.get(new Vector3(((int) p.x) - 1, ((int) p.y), 1)).get());
				C.connect("LEFT", N);
			}
			if (xBound != 1) {

				Node<_Thing> N = new Node("Right:Neighbor",
						L.Cells.get(new Vector3(((int) p.x) + 1, ((int) p.y), 1)).get());
				C.connect("RIGHT", N);
			}

			if (yBound != -1) {
				Node<_Thing> N = new Node("Down:Neighbor",
						L.Cells.get(new Vector3(((int) p.x), ((int) p.y - 1), 1)).get());
				C.connect("DOWN", N);
			}
			if (yBound != 1) {
				Node<_Thing> N = new Node("Up:Neighbor",
						L.Cells.get(new Vector3(((int) p.x), ((int) p.y) + 1, 1)).get());
				C.connect("UP", N);
			}

		}
		return L;
	}
}
