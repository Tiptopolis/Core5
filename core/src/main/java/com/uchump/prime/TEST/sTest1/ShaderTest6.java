package com.uchump.prime.TEST.sTest1;

import static com.uchump.prime._METATRON.Metatron.CAMERA;
import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime._PRIME.GdxFileUtils;
import com.uchump.prime._PRIME.C_O.VectorUtils;
import com.uchump.prime._PRIME.SYS.uApp;

public class ShaderTest6 extends uApp {

	OrthographicCamera cam;
	ShaderProgram shader;
	SpriteBatch batch;

	String VERT;
	String FRAG;

	public ShaderTest6() {
		super();
	}

	@Override
	public void create() {
		super.create();
		ShaderProgram.pedantic = false;

		String path = "/assets/TST/SHD/BoundShape/V6";
		ArrayList<FileHandle> fGet = GdxFileUtils.getFilesFrom("." + path);
		HashMap<String, FileHandle> fMap = GdxFileUtils.mapFiles(fGet);

		VERT = fMap.get("BndShpVert").readString();
		FRAG = fMap.get("BndShpFrag").readString();

		shader = new ShaderProgram(VERT, FRAG);
		if (!shader.isCompiled()) {
			System.err.println(shader.getLog());
			System.exit(0);
		}

		if (shader.getLog().length() != 0)
			System.out.println(shader.getLog());

		shader.bind();
	}

	float t = 0.1f;
	float d=1;
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		if(t>=1)
			d*=-1;
		
		if(t<0.1)
			d*=-1;
		Log(t);
		Log(shader.getUniforms());
		t+= deltaTime*d;
		
		Vector3 m = CAMERA.Camera.camera.unproject(new Vector3(MouseX,MouseY,0));
		
		shader.bind();
		shader.setUniformMatrix("u_projTrans", CAMERA.getProjection());
		shader.setUniformf("u_mouse", m);		
		shader.setUniformf("u_time", t);
		

	}

	@Override
	public void render() {
		super.render();

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Sketcher.setProjectionMatrix(CAMERA.getProjection());
		Sketcher.getBatch().setShader(shader);
		Sketcher.begin();
		Sketcher.Drawer.rectangle(-32, -32, 32, 32);
		Sketcher.Drawer.filledRectangle(0, 0, Width, Height);
		Sketcher.end();

	}

	@Override
	public void dispose() {
		super.dispose();
		shader.dispose();
	}
}
