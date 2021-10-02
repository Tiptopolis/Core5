#ifdef GL_ES
    precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;



uniform mat4 u_projTrans;
uniform vec2 u_resolution;
uniform vec3 u_mouse;
uniform float u_time;

void main() {

  vec4 m = vec4(u_mouse.x,u_mouse.y,u_mouse.z,0)*u_projTrans; //properly projects
  
	float t = u_time;
	gl_FragColor = vec4(m.x,m.y,0.5,1);

	

}

float plot(vec2 st) {    
    return smoothstep(0.02, 0.0, abs(st.y - st.x));
}
