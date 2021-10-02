package com.uchump.prime._PRIME.UI._GFX.Drawable;

import com.badlogic.gdx.graphics.Color;
import com.uchump.prime._PRIME.SYS.A_I.iMonad;

public interface iDrawable {

	public default Color innerColor() {
		return Color.WHITE;
	}

	public default void innerColor(Color c) {

	}

	public default Color outterColor() {
		return Color.BLACK;
	}

	public default void outterColor(Color c) {

	}

	public void draw();
	
}
