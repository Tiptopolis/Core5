package com.uchump.prime._RAUM.RAUM.WORLD;

import static com.uchump.prime._PRIME.uAppUtils.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.uchump.prime._PRIME.N_M._N;
import com.uchump.prime._PRIME.SYS.A_I.aProperty;
import com.uchump.prime._PRIME.SYS.NIX._Shell;
import com.uchump.prime._PRIME.SYS.NIX._VFS;
import com.uchump.prime._PRIME.SYS.NIX._VTS;
import com.uchump.prime._RAUM.RAUM.MAP._Map;
import com.uchump.prime._RAUM.RAUM.META._Environment;
import com.uchump.prime._RAUM.RAUM.META.CMP._Dex;

public class _World extends _Environment {

	
	public static _Map CurrentMap;
	public static ArrayList<_Dex> CoreMeta = new ArrayList<_Dex>();// <=plug your shit in here<=
	
	public HashMap<String, _Map> MapArchive = new HashMap<String, _Map>();

	public _World() {
		super("World");
	}

	public static aProperty getFrom(String of, String at) {

		for (_Dex D : CoreMeta) {
			if (D.Label.equals(of + "_")) {
				return D.get(at);
			}
		}

		return new aProperty("null", new Type(false));
	}

	public void dispose()
	{
		CurrentMap.dispose();
		CoreMeta.clear();
		MapArchive.clear();
	}
	
}
