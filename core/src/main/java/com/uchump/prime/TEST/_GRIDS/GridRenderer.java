package com.uchump.prime.TEST._GRIDS;

import static com.uchump.prime._METATRON.Metatron.CAMERA;
import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime._PRIME.C_O.NIX.Rect;
import com.uchump.prime._RAUM.RAUM.GFX._BoundShape;
import com.uchump.prime._RAUM.RAUM.MAP._Layer;
import com.uchump.prime._RAUM.RAUM.META.NIX._Thing;

public class GridRenderer {

	// OK, precalculate the visible cells

	public void drawLayer(_Layer L) {
		Sketcher.setProjectionMatrix(CAMERA.getProjection());
		Sketcher.begin();
		for (_Thing C : L.Cells.values()) {
			if (C.has("Drawable")) {
				// Log(C.ID); //OK

				Vector3 p = (Vector3) C.get("Offset").get();
				Vector3 vis = p.cpy().scl(L.CellSize.cpy());
				Rect R = CAMERA.getViewRect().cpy().extendBy(true, L.CellSize.cpy().scl(2));

				if (R.contains(vis.x, vis.y)) {
					_BoundShape b = (_BoundShape) C.get("Shape").get();					
					Sketcher.Drawer.setDefaultLineWidth(1+CAMERA.Camera.zoom);				
					b.draw();
					
				}

			}
		}
		Sketcher.end();
	}

}
