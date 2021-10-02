#ifdef GL_ES
    precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;



uniform mat4 u_projTrans;
uniform vec2 u_resolution;
uniform vec2 u_mouse;
uniform float u_time;

void main() {

  
	float t = u_time;
	
	
	gl_FragColor = vec4(1,t,0,1);

	

}
