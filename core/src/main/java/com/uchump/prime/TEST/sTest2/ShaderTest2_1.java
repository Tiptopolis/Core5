package com.uchump.prime.TEST.sTest2;

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
import com.uchump.prime.TEST.sTest1.tstEnv.Env2;
import com.uchump.prime._PRIME.GdxFileUtils;
import com.uchump.prime._PRIME.C_O.VectorUtils;
import com.uchump.prime._PRIME.C_O.NIX.Rect;
import com.uchump.prime._PRIME.SYS.uApp;

public class ShaderTest2_1 extends uApp {

	OrthographicCamera cam;
	ShaderProgram shader;
	SpriteBatch batch;

	String VERT;
	String FRAG;
	
	Env2 environment;

	public ShaderTest2_1() {
		super();
		
	}

	@Override
	public void create() {
		super.create();
		this.environment = new Env2(new Vector3(Width*3, Height*3,1));
		ShaderProgram.pedantic = false;

		String path = "/assets/TST/SHD/BoundShape2/1_0_0";
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
		Rect r = CAMERA.Camera.view;
		
		shader.bind();
		shader.setUniformMatrix("u_projTrans", CAMERA.Camera.camera.combined);	
		//shader.setUniformf("u_resolution", new Vector2(r.width, r.height));
		shader.setUniformf("u_resolution", new Vector2(Width, Height));
		shader.setUniformf("u_camP", (CAMERA.getCameraPosition().cpy()));
		shader.setUniformf("u_mouse", m);		
		shader.setUniformf("u_time", t);
		
		this.environment.update(deltaTime);
		this.environment.bind(this.shader);

	}

	@Override
	public void render() {
		super.render();

		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		Sketcher.getBatch().setShader(shader);
		Sketcher.begin();
		Rect r = CAMERA.Camera.view;
		Sketcher.Drawer.filledRectangle(0, 0, r.width, r.height);
		Log(Width + " : " + Height);
		Sketcher.end();

	}
	
	@Override
	public void resize(int width, int height)
	{
		super.resize(width, height);
		if(this.shader!= null && this.shader.isCompiled()) {
			Rect r = CAMERA.Camera.view;
			shader.setUniformf("u_resolution", new Vector2(r.width, r.height));
			
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		shader.dispose();
	}
}
