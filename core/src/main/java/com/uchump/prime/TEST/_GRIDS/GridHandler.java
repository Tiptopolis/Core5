package com.uchump.prime.TEST._GRIDS;

import static com.uchump.prime._METATRON.Metatron.CAMERA;
import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.uchump.prime.uChumpEngine;
import com.uchump.prime._PRIME.C_O.NIX.Rect;
import com.uchump.prime._RAUM.RAUM.GFX._BoundShape;
import com.uchump.prime._RAUM.RAUM.MAP._Layer;
import com.uchump.prime._RAUM.RAUM.META.NIX._Thing;

public class GridHandler implements InputProcessor {

	public _Thing lastOverCell;
	// public _Thing selectedCell;
	public ArrayList<_Thing> selectedCell;

	public GridHandler() {
		uChumpEngine.METATRON.addProcessor(this);
	}

	public void update(_Layer L) {

		for (_Thing C : L.Cells.values()) {
			if (C.has("Drawable")) {

				Vector3 p = (Vector3) C.get("Offset").get();
				Vector3 vis = p.cpy().scl(L.CellSize.cpy());
				Rect R = CAMERA.getViewRect().cpy().extendBy(true, L.CellSize.cpy().scl(2));

				if (R.contains(vis.x, vis.y)) {
					_BoundShape b = (_BoundShape) C.get("Shape").get();
					Vector3 mAt = CAMERA.Camera.camera.unproject(new Vector3(MouseX, MouseY, 0));
					if (b.contains(mAt.x, mAt.y) || (lastOverCell != null && lastOverCell.equals(C))) {
						lastOverCell = C;
						Color f = Color.LIGHT_GRAY;
						f.a = 0.25f;
						b.fillColor = f;
						b.lineColor = Color.RED;
					} else {

						int i = (int) C.get("RndInt").get();
						if (i == 0)
							i = 1;
						Color f = new Color(1f / i, 1f / i, 1f / i, 0.5f);
						b.fillColor = f;
						b.lineColor = Color.BLACK;

					}
					if (this.selectedCell != null && this.selectedCell.contains(C)) {
						b.lineColor = Color.BLUE;
						b.fillColor = Color.CLEAR;
					}

				}
			}
		}
	}

	public void dispose() {
		if (this.selectedCell != null)
			this.selectedCell.clear();
		uChumpEngine.METATRON.removeProcessor(this);
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		if (this.selectedCell == null)
			this.selectedCell = new ArrayList<_Thing>();

		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			this.selectedCell.add(this.lastOverCell);
			_BoundShape B = (_BoundShape) this.lastOverCell.get("Shape").get();
			B.linesInner = false;
			return true;
		} else {
			for (_Thing T : this.selectedCell) {
				_BoundShape B = (_BoundShape) T.get("Shape").get();
				B.linesInner = true;
			}
			this.selectedCell.clear();
			this.selectedCell.add(this.lastOverCell);
			_BoundShape B = (_BoundShape) this.lastOverCell.get("Shape").get();
			B.linesInner = false;
			return true;
		}

	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}

}
